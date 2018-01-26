<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
</head>
<style type="text/css">
	*{
		margin: 0px;
		padding: 0px;
	}
	.detailTable{
		width: 100%;
		border-top: 1px solid #c1c1c1;
		border-left: 1px solid #c1c1c1;
	}
	.detailTable tr{
		text-align: center;

	}
	.detailTable tr td,.detailTable tr th{
		border-right: 1px solid #c1c1c1;
		border-bottom: 1px solid #c1c1c1;
		line-height:26px;
	}
	.detailTable tr th{
		background: #2d8dcd;
		color: #fff;
	}
</style>
<body>
	<div id="print-box" style="width:190mm;margin: auto;">
	</div>
</body>
<script type="text/javascript">
	(function(){
		var utils = {
			elemArr:[],
			paperHeight:1047,
			template:${template},
			allData:${allData}
		}
		var printBox = document.getElementById("print-box");
		//替换自定义标签
		utils.template = tagRender(utils.template,utils.allData);
		//主表字段替换
		utils.template = codeParse(utils.template,utils.allData);
		printBox.innerHTML = utils.template;
		//明细表渲染
		detailTableRender(utils.allData);
		//按顺序顺序渲染所有节点
		allElemsRender();
		//章位调整
		chaptersMove();
		//删除最后无用内容
		delEndFutility();
		if (utils.allData.cachetUrl){
			checkMark(printBox,utils.allData);
		}
		//循环已有表格
		function tableDetailRender(elems){
			for (var i = elems.length - 1; i >= 0; i--) {
				everyTableDetail(elems[i]);
			}
		}
		//已有表格的每一个表格
		/**
		elem  每一个明细表
		*/
		function everyTableDetail(elem){
			//获取当前表明
			var tableName = elem.getAttribute('table-name');
			//获取所有的tr 为数组
			var allTrDetail = getAllTrContent(elem);
			var tableBox = document.querySelectorAll('div[data-id="tableBox-'+tableName+'"]')[0];
			//获取表头
			var hander = allTrDetail[0];
			elem.parentNode.removeChild(elem);
			allTrDetail=allTrDetail.slice(1, allTrDetail.length)
			tableSplit(tableBox,allTrDetail,hander,0,tableName);
		}
		//============主表字段替换===================
		//字段替换
		function codeParse(template, data,prefixes){
		    if (!template){
		        return '';
		    }
		    var t = removeSpace(template), key, reg;
		　　　 //遍历该数据项下所有的属性，将该属性作为key值来查找标签，然后替换
		    for (key in data) {
		       t = keyParse(t ,key ,data[key],prefixes);
		    }
		    for(key in data){
		        t = noKeyParse(t, key, data[key],prefixes)
		    }
		    return t;
		}
		//替换掉属性的写法
		function keyParse(template,paramName,data,prefixes) {
		    var t = template, reg;
				if(!prefixes){
					prefixes = "";
				}
		    for(var key in data){
		        reg = new RegExp('{{' + prefixes+paramName+'\\$'+ key + '}}', 'g');
		        t = t.replace(reg,data[key]||'');
		    }
		    return t;
		}
		//默认替换
		function noKeyParse(template, paramName, data,prefixes) {
		    var t = template, reg;
				if(!prefixes){
					prefixes = "";
				}
		    for (var key in data) {
		        reg = new RegExp('{{' + prefixes + paramName + '}}', 'g');
		        t = t.replace(reg, data.showValue||'');
		    }
		    return t;
		}
		//去掉所有空格
		function removeSpace(template){
		    return template;
		}
		//=============明细表变量替换===================
		function detailTableRender(allData){
			var allDetailTable = getNode('table[data-id^="table-"]');
			for(var i=0;i<allDetailTable.length;i++){
				var elem = allDetailTable[i];
				var tableName = elem.getAttribute('table-name');
				everyDetail({tableName:tableName,elem:elem},allData);
			}
		}
		//每一个明细表的渲染
		function everyDetail(detail,allData){
			// var newDetail
			var detailData = allData[detail.tableName];
			var tableTitle = document.getElementById("tableTitle-"+detail.tableName);
			if(!detailData){
				detail.elem.innerHTML = '';
				if(tableTitle){
					tableTitle.innerHTML = '';
				}
				return;
			}
			var thElem = document.getElementById('th-'+detail.tableName);
			var tdElem = document.getElementById('td-'+detail.tableName);
			var tBottomElem = document.getElementById('tBottom-'+detail.tableName);
			var tdStr = '';
			detail.elem.innerHTML = '';
			detail.elem.insertBefore(thElem,detail.elem.childNodes[0]);
			detailData.map(function(item,index){
				tdStr += '<tr>'+codeParse(tdElem.innerHTML,item,detail.tableName+'-')+'</tr>';
			});
			detail.elem.innerHTML = detail.elem.innerHTML + tdStr;
			detail.elem.appendChild(tBottomElem);
			var tableBox = document.createElement('div');
			tableBox.setAttribute('data-id','tableBox-'+detail.tableName);
			detail.elem.parentNode.insertBefore( tableBox, detail.elem.nextSibling );
		}
		//=============标签替换========================
		function tagRender(template,data){
		    var allData = data
		    var imgReg = new RegExp('#{img}', 'ig');
		    //分页标签
		    var pageReg = new RegExp('#{pagination}','ig');
		    //二维码
		    var qrImgReg = new RegExp('#{qrCode}','ig');
		    //区域划分组件开始
		    var includeStart = new RegExp('#{includeStart}','ig')
		    //区域划分组件结束
		    var includeEnd = new RegExp('#{includeEnd}', 'ig')
		    //文章结束
		    var allEnd = new RegExp('#{allEnd}','ig');
		    //附件内容开始
		    var attachmentStart = new RegExp('#{attachmentStart}', 'ig');
		    //附件内容结束
		    var attachmentEnd = new RegExp('#{attachmentEnd}','ig');
			var produceFdfTime = new RegExp('#{produceFdfTime}','ig');
		    var imgLabelling =allData.cachetUrl ? '<img style="position:absolute;display:inline-block;width:160px;height:160px;left:-80px;top:-80px" src = "'+allData.cachetUrl+'">' : '';
		    var qrImg = allData.qrImgUrl ? '<img style="position:fixed;width:100px;height:100px;right:30px;top:10px" src = "'+allData.qrImgUrl+'">' : '';
		    template = template.replace(imgReg, '<span class="print-other-chapter" style="position: relative;">'+imgLabelling+'</span>');
		    template= template.replace(pageReg,'<div class = "print-pagination"></div>');
		    template = template.replace(qrImgReg, qrImg);
		    template = template.replace(includeStart,'<div class="print-include-start"></div>');
		    template = template.replace(includeEnd, '<div class="print-include-end"></div>');
		    template = template.replace(allEnd,'<div class="print-all-end"></div>');
		    template = template.replace(attachmentStart, '<div class="print-attachment-start"></div>');
		    template = template.replace(attachmentEnd, '<div class="print-attachment-end"></div>');
			template = template.replace(produceFdfTime,dateFormat("yyyy年MM月dd日"))
		    return template;
		}
		//=============顺序渲染已有标签====================
		function tableSplit(tableBox,allTrDetail,hander,num,tableName){
			if(!tableBox){
				return;
			}
			var tableId = "table"+tableName+num;
			var divId = "div"+num;
			var trHtml = '<tr>'+hander+'</tr>';
			tableBox.innerHTML+= '<div id="'+divId+'" style="box-sizing: border-box;padding:15px;"><table cellspacing="0" class="detailTable" id="'+tableId+'" align="center" >'+trHtml+'</table></div>';
			for (var i = 0; i < allTrDetail.length ; i++) {
				var oldtrHtml = trHtml;
				trHtml+='<tr>'+allTrDetail[i]+'</tr>';
				var newTable = document.getElementById(tableId);
				newTable.innerHTML = trHtml
				var divElem = document.getElementById(divId);
				var divDetail = getElemDetail(divElem);
				if(Math.floor(divDetail.top/utils.paperHeight) != Math.floor(divDetail.bottom/utils.paperHeight)){
					newTable.innerHTML = oldtrHtml;
					allTrDetail=allTrDetail.slice(i, allTrDetail.length)
					divElem = document.getElementById(divId);
					var divDetail = getElemDetail(divElem);
					var divHeight = (Math.ceil(divDetail.bottom/utils.paperHeight)*utils.paperHeight)- divDetail.top;
					divElem.style.height = divHeight+'px';
					break;
				}
			}
			//数组不存在或者数组长度为零或者数组循环到结尾则不再继续
			if(!allTrDetail || !allTrDetail.length || i==allTrDetail.length){
				return false;
			}else{
				tableSplit(tableBox,allTrDetail,hander,num+=1)
			}
		}
		//获取所有的tr数据
		function getAllTrContent(elem){
			var contents = elem.querySelectorAll("tr");
			var arr = [];
			for (var i = 0; i <contents.length; i++) {
				arr.push(contents[i].innerHTML);
			}
			return arr;
		}
		//每一个分页标记的渲染
		function everyPagination(elem) {
			var detail = getElemDetail(elem),
				top = detail.top,
				pageNum = Math.ceil(top / utils.paperHeight),
				diffValue = pageNum * utils.paperHeight - top,
				height = diffValue > 0 ? diffValue : 0;
			elem.style.height = height + "px";
			elem.style.marginTop = 0 + "px";
		}
		//每一个包围标记的渲染
		function everyInclude(startElem, endElem) {
			var startDetail = getElemDetail(startElem),
				endDetail = getElemDetail(endElem),
				startTop = startDetail.top ,
				endTop = endDetail.top ,
				startPageNum = Math.ceil(startTop / utils.paperHeight),
				endPageNum = Math.ceil(endTop / utils.paperHeight);
			if (endPageNum > startPageNum) {
				var diffValue = startPageNum * utils.paperHeight - startTop,
					height = diffValue > 0 ? diffValue : 0;
				startElem.style.height = height + "px";
				startElem.style.marginTop = 0 + "px";
			}
		}
		//获取节点
		function getNode(elem) {
			return document.querySelectorAll(elem);
		}
		//顺序渲染所有节点
		function allElemsRender() {
			sortAll();
			for (var i = 0; i < utils.elemArr.length; i++) {
				var elem = utils.elemArr[i];
				if (elem.type === "include") {
					everyInclude(elem.start, elem.end);
				} else if(elem.type == "page"){
					everyPagination(elem.elem);
				} else {
					everyTableDetail(elem.elem);
				}
			}
		}
		//所有替换元素进行排序
		function sortAll() {
			produceElemArr(".print-pagination", 'page')
			produceElemArr('.print-include', 'include')
			produceElemArr('table[data-id^="table-"]', 'tableDetail')
			utils.elemArr.sort(function (a, b) {
				return b.top - a.top < 0
			});
		}
		//获取每一个渲染的元素
		function produceElemArr(className, type) {
			var elems = getNode(className);
			var detail = {};
			if (type === "include") {
				var start = getNode(className + '-start');
				var end = getNode(className + '-end');
				for (var i = 0; i < start.length; i++) {
					detail =getElemDetail(start[i]);
					utils.elemArr.push({ type: type, start: start[i], end: end[i], top: detail.top });
				}
			} else {
				for (var i = 0; i < elems.length; i++) {
					detail = getElemDetail(elems[i]);
					utils.elemArr.push({ type: type, elem: elems[i], top: detail.top });
				}
			}
		}
		//印章位置调整
		function chaptersMove(params) {
			var elems = getNode(".print-other-chapter-img");
			for (var i = 0; i < elems.length; i++) {
				everyChapter(elems[i]);
			}
		}
		//每一个章的位置调整
		function everyChapter(elem) {
			var detail = getElemDetail(elem),
				topTop = detail.top ,
				bottomTop = topTop + detail.height,
				numTop = Math.ceil(topTop / utils.paperHeight),
				numBottom = Math.ceil(bottomTop / utils.paperHeight),
				distanceTop = Math.abs(numTop * utils.paperHeight - topTop),
				distanceBottom = Math.abs(numBottom * utils.paperHeight - bottomTop);
			if (numTop != numBottom) {
				var elemTop = parseInt(elem.style.top)
				if (distanceTop < distanceBottom) {
					elem.style.top = elemTop + distanceTop + 50 + 'px';
				} else {
					elem.style.top = elemTop - distanceBottom - 50 + "px"
				}
			} else {
				var elemTop = parseInt(elem.style.top)
				var newTop = Math.abs((numTop - 1) * utils.paperHeight - topTop);
				var newBottom = Math.abs((numTop) * utils.paperHeight - bottomTop);
				if (newTop < 20) {
					elem.style.top = elemTop + 20 + 'px';
				}
				if (newBottom < 20) {
					elem.style.top = elemTop - 20 + 'px';
				}
			}
		}
		//去掉尾部无用节点
		function delEndFutility(elem) {
			elem = elem || getNode(".print-all-end")[0];
			if (!elem) {
				return;
			}
			var nextElem = elem.nextSibling;
			if (nextElem) {
				delEndFutility(nextElem);
				delNowElem(nextElem);
			}
		}
		//删除该元素
		function delNowElem(elem) {
			elem.parentNode.removeChild(elem)
		}
		//控制页面的高度
		function controlHeight(elem) {
			var detail = getElemDetail(elem);
			var endHeight = '';
			elem.style.height = detail.height - 30 + 'px';
			elem.style.overflow = "hidden";
			detail = getElemDetail(elem);
			endHeight = Math.ceil(detail.height / utils.paperHeight) * utils.paperHeight;
			elem.style.height = endHeight - 30 + "px";
		}
		function getElemDetail(elem){
			var detail = elem.getBoundingClientRect(),
			    left= elem.offsetLeft,
		　　		top= elem.offsetTop,
				right = left + detail.width,
				bottom = top + detail.height;
			return {
				top:top,
				right:right,
				bottom:bottom,
				left:left,
				height:detail.height,
				width:detail.width
			}
		}
		//========dateformat函数================
		function dateFormat(fmt) {
			var that = new Date();
			var o = {
		    "M+": that.getMonth() + 1, // 月份
		    "d+": that.getDate(), // 日
		    "h+": that.getHours(), // 小时
		    "m+": that.getMinutes(), // 分
		    "s+": that.getSeconds(), // 秒
		    "q+": Math.floor((that.getMonth() + 3) / 3), // 季度
		    "S": that.getMilliseconds() // 毫秒
		  };
		  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (that.getFullYear() + "").substr(4 - RegExp.$1.length));
		  for (var k in o)
		  if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		  return fmt;
		}
		//齐缝章
		function checkMark(mainElem,allData){
		    var startElem = getNode('.print-attachment-start')[0];
		    var endElem = getNode('.print-attachment-end')[0];
		    var startDetail = '',
		        endDetail = '',
		        startTop = 0,
		        endTop = 0,
		        startNum = 0,
		        endNum = 0; 
		    var mainDetil = mainElem.getBoundingClientRect(),
		        mainHeight = mainDetil.height,
		        pageNum = Math.ceil(mainHeight/utils.paperHeight),
		        markElem = '';
		    var isHave = false;
		    if(startElem && endElem){
		        isHave = true;
		        startDetail = startElem.getBoundingClientRect();
		        endDetail = endElem.getBoundingClientRect();
		        startTop = startDetail.top;
		        endTop = endDetail.top;
		        startNum = Math.floor(startTop / utils.paperHeight);
		        endNum = Math.floor(endTop / utils.paperHeight);
		    }
		    if (isHave && pageNum - 1 <= 1 ){
		        return;
		    }else if(pageNum > 1){
		        for(var i = 0; i < pageNum; i++){
		            if (isHave) {
		                if (i < startNum || i > endNum) {
		                   var diffValue = endNum - startNum + 1;
		                   markElem += everyCheckMark(i, pageNum - diffValue, i > endNum ? endNum : 0,allData)
		                }
		            }else {
		                markElem += everyCheckMark(i, pageNum, 0,allData)
		            }
		        }
		    }
		    mainElem.innerHTML = mainElem.innerHTML + markElem;
		}
		//每一页骑缝章的渲染
		function everyCheckMark(num, pageNum,endNum,allData){
		   var boxWidth = Math.ceil(160/pageNum*1000)/1000;
		   var top = num*utils.paperHeight+utils.paperHeight/2-160/2;
		   var elemImg = '<div style="height:'+160+'px;width:'+boxWidth+'px;overflow:hidden;position:absolute;right:0px;top:'+top+'px;">'+
		       '<img style="width:' + 160 + 'px;height:' + 160 + 'px;display:inline-block;left:' + ((-num + endNum) * boxWidth)+'px;position:absolute;" src = "'+allData.cachetUrl+'"/>'+
		                 '</div>';
		   return elemImg;
		}
	})()
</script>
</html>
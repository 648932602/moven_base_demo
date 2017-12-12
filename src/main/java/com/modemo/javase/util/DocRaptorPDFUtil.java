package com.modemo.javase.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import com.docraptor.ApiClient;
import com.docraptor.ApiException;
import com.docraptor.Doc;
import com.docraptor.DocApi;
import com.docraptor.PrinceOptions;

public class DocRaptorPDFUtil {
	public static final String DOG = "/Users/moshengwei/Downloads/iText2PDF/dog.bmp";
	public static final String FOX = "/Users/moshengwei/Downloads/iText2PDF/fox.bmp";

	public static final String DEST = "/Users/moshengwei/Downloads/iText2PDF/hello_world.pdf";

	public static void main(String args[]) throws ApiException, IOException {
		long startTime = System.currentTimeMillis();
		File file = new File(DEST);
		file.getParentFile().mkdirs();
		new DocRaptorPDFUtil().test01(DEST);
		long entTime = System.currentTimeMillis();
		System.out.println("use = " + (entTime - startTime));
	}

	public void test01(String dest) throws ApiException, IOException {
		DocApi docraptor = new DocApi();
		ApiClient client = docraptor.getApiClient();
		client.setUsername("YOUR_API_KEY_HERE");
		// client.setDebugging(true);

		Doc doc = new Doc();
		doc.setTest(true); // test documents are free but watermarked
//		doc.setDocumentContent(getJSHtml()); // supply content directly
//		doc.setDocumentUrl("http://local.moven.cn/moven_sso/static/html/login.html"); // or use a
		doc.setDocumentUrl("http://local.moven.cn/moven_sso/static/html/login.html"); // or use a
		// url
		doc.setDocumentType(Doc.DocumentTypeEnum.PDF); // PDF or XLS or XLSX
		doc.setName(dest); // help you find a document later
		doc.setJavascript(true); // enable JavaScript processing
//		PrinceOptions prince_options = new PrinceOptions();
//		 doc.setPrinceOptions(prince_options);
		// prince_options.setMedia("screen"); // use screen styles instead of print
		// styles
//		prince_options.setBaseurl("http://local.moven.cn"); // pretend URL when using
		// document_content
		byte[] pdfBytes = docraptor.createDoc(doc);
		File newFile = new File(dest);
		OutputStream os = new FileOutputStream(newFile);
		os.write(pdfBytes); // 把流一次性写入一个文件里面
		os.flush();
		os.close();  
	}
	
	private static String getXHtml() {
		String xHtml = "<p style=\"text-align: center; line-height: 29px\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 24px;\">氪空间场地使用服务合同</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: 29px; text-align: right;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" + 
				"		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;合同编号：【】</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">甲方（提供方）：【】</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">通讯地址：【】</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">联系人：【】&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" + 
				"		联系电话：【】</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">电子邮箱：【】</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">&nbsp;</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">乙方（使用方）：【】</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">通讯地址：【】</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">联系人：【】&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" + 
				"		联系电话：【】</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">电子邮箱：【】</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">&nbsp;</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">根据《中华人民共和国合同法》及有关法律规定，甲、乙双方在平等自愿的基础上，就本协议第一条所指的场地活动(“有关活动”)之场地服务相关事宜，甲乙双方经友好协商达成以下内容：</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 35px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">一、 有关活动基本信息：</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">1.1 活动名称：【】；</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">1.2\n" + 
				"		活动地点：【】社区，具体地点为：【】。 </span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">1.3 乙方使用场地的时间为：<span\n" + 
				"		style=\"font-family: 宋体; font-size: 12px; text-decoration: underline;\">【】年【】月【】日起至【】年【】月【】日止。甲方交付场地的时间为【】年【】月【】日。每日具体使用时间（依据24小时制格式填写）：【】\n" + 
				"			时至【】时。</span></span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 35px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">二、 费用及支付方式:</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">2.1 场地费用总计为<span\n" + 
				"		style=\"font-family: 宋体; font-size: 12px; text-decoration: underline;\">：¥【】元(大写：【】元整)，作为甲方提供服务的费用。</span></span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">2.2\n" + 
				"		乙方应在本合同签署后五（5）日内，以转账的方式向甲方支付全部服务费用。甲方应在收到款项后十（10）日内提供相应的收据或发票。</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">2.3 甲方指定如下收款账户：</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">公司名称：【】</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">开户银行：【】</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">银行账号：【】</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 35px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">三、 双方的权利义务:</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">3.1\n" + 
				"		甲方按场地现状向乙方提供活动场地，配合乙方完成活动的举办。</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">3.2\n" + 
				"		乙方应按政府有关部门的要求配合办理活动的报批报审手续。</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">3.3\n" + 
				"		乙方应按照有关国家机关批准的内容和方式举办活动。如因为超越国家机关批准的相关活动范围引起的活动被取消、中止、叫停等，乙方负全部责任。</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">3.4\n" + 
				"		乙方需自行布场并保证使用过程中场地内甲方所有家具及设备完好，如有损坏按原价赔偿。</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">3.5\n" + 
				"		乙方应对活动过程中的安全负责，如活动过程中发生安全事故给甲方或第三方造成人身及财产损害的，由乙方承担全部责任（甲方单方原因引起的除外）。</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">3.6\n" + 
				"		乙方场地使用完毕后需将场地内所有物品恢复原状后再交还甲方。</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">3.7 其他约定：【】。</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 35px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">四、 违约责任：</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">4.1\n" + 
				"		任何一方无正当理由，不得提前终止本合同。本合同提前终止不影响此前双方因履行本合同而已经享有的权利和/或承担的义务，如甲方已经按照合同规定向乙方提供服务，乙方应按本合同规定的价格就甲方已经提供的服务向甲方支付相应费用。</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">4.2\n" + 
				"		如合同任何一方违反合同约定，应赔偿对方所受损失，具体损失以实际损失为准。</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 35px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">五、 其他：</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">5.1\n" + 
				"		因本合同引起的一切纠纷，双方应友好协商解决，协商不成的可提交合同签订地人民法院提起诉讼解决。</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">5.2\n" + 
				"		本合同一式叁份，甲方执贰份，乙方持壹份，具有同等法律效力。合同自双方签字、盖章之日起生效。</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">（以下无正文，为签署页）</span>\n" + 
				"</p>\n" + 
				"<p style=\"line-height: normal; text-indent: 0em;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\">&nbsp;#{includeStart}</span>\n" + 
				"</p>\n" + 
				"<p style=\"text-indent: 32px; line-height: normal;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px;\"></span>\n" + 
				"</p>\n" + 
				"<table>\n" + 
				"	<tbody>\n" + 
				"		<tr class=\"firstRow\">\n" + 
				"			<td width=\"368\" valign=\"top\" style=\"word-break: break-all;\"><p\n" + 
				"					style=\"text-indent: 2em;\">\n" + 
				"					<span style=\"font-family: 宋体; font-size: 12px; text-indent: 32px;\">合同签订地：北京市朝阳区。</span>\n" + 
				"				</p></td>\n" + 
				"			<td width=\"368\" valign=\"top\"><br /></td>\n" + 
				"		</tr>\n" + 
				"		<tr>\n" + 
				"			<td width=\"368\" valign=\"top\" style=\"word-break: break-all;\"><p\n" + 
				"					style=\"text-indent: 2em;\">\n" + 
				"					<span style=\"font-family: 宋体; font-size: 12px; text-indent: 32px;\">甲方（盖章）：&nbsp;\n" + 
				"						&nbsp;</span>\n" + 
				"				</p></td>\n" + 
				"			<td width=\"368\" valign=\"top\" style=\"word-break: break-all;\"><p\n" + 
				"					style=\"text-indent: 2em;\">\n" + 
				"					<span style=\"font-family: 宋体; font-size: 12px; text-indent: 32px;\">乙方（盖章）：</span>\n" + 
				"				</p></td>\n" + 
				"		</tr>\n" + 
				"		<tr>\n" + 
				"			<td width=\"368\" valign=\"top\" style=\"word-break: break-all;\"><p\n" + 
				"					style=\"text-indent: 2em;\">\n" + 
				"					<span style=\"font-family: 宋体; font-size: 12px; text-indent: 32px;\">联系人(签字)：#{img}</span>\n" + 
				"				</p></td>\n" + 
				"			<td width=\"368\" valign=\"top\" style=\"word-break: break-all;\"><p\n" + 
				"					style=\"text-indent: 2em;\">\n" + 
				"					<span style=\"font-family: 宋体; font-size: 12px; text-indent: 32px;\">联系人(签字)：</span>\n" + 
				"				</p></td>\n" + 
				"		</tr>\n" + 
				"		<tr>\n" + 
				"			<td width=\"368\" valign=\"top\" style=\"word-break: break-all;\"><p\n" + 
				"					style=\"text-indent: 2em;\">\n" + 
				"					<span style=\"font-family: 宋体; font-size: 12px; text-indent: 32px;\">签署日期：&nbsp;&nbsp;&nbsp;\n" + 
				"						年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日&nbsp;</span>\n" + 
				"				</p></td>\n" + 
				"			<td width=\"368\" valign=\"top\" style=\"word-break: break-all;\"><p\n" + 
				"					style=\"text-indent: 2em;\">\n" + 
				"					<span style=\"font-family: 宋体; font-size: 12px; text-indent: 32px;\">签署日期：&nbsp;&nbsp;&nbsp;\n" + 
				"						年&nbsp;&nbsp;&nbsp; 月&nbsp;&nbsp;&nbsp; 日</span>\n" + 
				"				</p></td>\n" + 
				"		</tr>\n" + 
				"	</tbody>\n" + 
				"</table>\n" + 
				"<p style=\"line-height: normal; text-indent: 0em;\">\n" + 
				"	<span style=\"font-family: 宋体; font-size: 12px; text-indent: 0em;\">#{includeEnd}&nbsp;</span><br />\n" + 
				"</p>\n" + 
				"<p>\n" + 
				"	<br />\n" + 
				"</p>";
		return xHtml;
	}
	
	private static String getJSHtml() {
		String jsHtml = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" + 
				"<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + 
				"<head>\n" + 
				"<style>\n" + 
				"    .print-other p,.print-other span{\n" + 
				"        color: #999999;\n" + 
				"    }\n" + 
				"    .print-other table tbody tr{\n" + 
				"        line-height: 24px;\n" + 
				"    }\n" + 
				"    .print-other table tbody tr p{\n" + 
				"        line-height: 8px;\n" + 
				"    }\n" + 
				"    .print-other{\n" + 
				"        width:200mm;\n" + 
				"        position: relative;\n" + 
				"        margin:auto;\n" + 
				"        box-sizing: border-box;\n" + 
				"        padding: 20px;\n" + 
				"        padding-bottom: 0px;\n" + 
				"    }\n" + 
				"</style>\n" + 
				"</head>\n" + 
				"<body style=\"font-family: 'Arial Unicode MS'\">\n" + 
				"	<div \n" + 
				"    className=\"print-other\"\n" + 
				"    id = 'print-other'\n" + 
				">\n" + 
				"</div>\n" + 
				"</body>\n" + 
				"<script type=\"text/javascript\">\n" + 
				"(function(){\n" + 
				"    var print = document.getElementById(\"print-other\");\n" + 
				"    http(\"Get\",\"http://local.krspace.cn/api/krspace-erp-web/sys/print/template/type/wf-request?requestId=7\",function(response){\n" + 
				"        print.innerHTML = response.content;\n" + 
				"    })\n" + 
				"    // function allRender(){\n" + 
				"        \n" + 
				"    //     var templateData = templateParse(this.configData.template,allData);\n" + 
				"    //     print.innerHTML = codeParse(templateData, allData);\n" + 
				"    //     //明细表\n" + 
				"    //     // var detailTr = document.querySelectorAll(\".money-detail tr\");\n" + 
				"    //     //明细表渲染\n" + 
				"    //     // this.detailCodeParse(detailTr,this.configData.allData.moneyDetail)\n" + 
				"\n" + 
				"    //     //按顺序顺序渲染所有节点\n" + 
				"    //     allElemsRender();\n" + 
				"    //     //章位调整\n" + 
				"    //     chaptersMove();\n" + 
				"    //     //删除最后无用内容      \n" + 
				"    //     delEndFutility();\n" + 
				"        \n" + 
				"    //     //齐缝章\n" + 
				"    //     if (allData.cachetUrl){\n" + 
				"\n" + 
				"    //         checkMark(print);\n" + 
				"    //     }\n" + 
				"    //     controlHeight(print)\n" + 
				"    // }\n" + 
				"    // //控制页面的高度\n" + 
				"    // function js_getDPI() {\n" + 
				"    //   var arrDPI = new Array();\n" + 
				"    //   if (window.screen.deviceXDPI != undefined) {\n" + 
				"    //     arrDPI[0] = window.screen.deviceXDPI;\n" + 
				"    //     arrDPI[1] = window.screen.deviceYDPI;\n" + 
				"    //   } else {\n" + 
				"    //     var tmpNode = document.createElement(\"DIV\");\n" + 
				"    //     tmpNode.style.cssText = \"width:1in;height:1in;position:absolute;left:0px;top:0px;z-index:99;visibility:hidden\";\n" + 
				"    //     document.body.appendChild(tmpNode);\n" + 
				"    //     arrDPI[0] = parseInt(tmpNode.offsetWidth);\n" + 
				"    //     arrDPI[1] = parseInt(tmpNode.offsetHeight);\n" + 
				"    //     tmpNode.parentNode.removeChild(tmpNode);\n" + 
				"    //   }\n" + 
				"    //   return arrDPI;\n" + 
				"    // }\n" + 
				"\n" + 
				"\n" + 
				"    function http(type,url,callback) {\n" + 
				"        var that = this;\n" + 
				"        var xhr = new XMLHttpRequest();  // XMLHttpRequest对象用于在后台与服务器交换数据\n" + 
				"        xhr.open(type, url, true);\n" + 
				"        xhr.responseType = 'json';\n" + 
				"        xhr.onreadystatechange = function () {\n" + 
				"            if (xhr.readyState == 4 && xhr.status == 200 || xhr.status == 304) { // readyState == 4说明请求已完成\n" + 
				"                if (xhr.response.code<0) {\n" + 
				"                    window.location = '/new/login.html';\n" + 
				"                    return;\n" + 
				"                }\n" + 
				"                callback(xhr.response)\n" + 
				"            }\n" + 
				"        };\n" + 
				"        xhr.send();\n" + 
				"    }\n" + 
				"\n" + 
				"    // function systemJudge() {\n" + 
				"    //     var mac = /macintosh|mac os x/i.test(navigator.userAgent);\n" + 
				"    //     var win = /windows|win32/i.test(navigator.userAgent);\n" + 
				"    //     if(mac){\n" + 
				"    //       return \"mac\";\n" + 
				"    //     }\n" + 
				"    //     if(win){\n" + 
				"    //       return \"window\";\n" + 
				"    //     }\n" + 
				"    // }\n" + 
				"\n" + 
				"    // var dpi = js_getDPI(),\n" + 
				"    //     paperHeight = Math.ceil((dpi[1]/25.4*297)*100)/100,//整张纸的高\n" + 
				"    //     uselessHeight = 60 + 25 ,//要减去的高\n" + 
				"    //     markHeight = 160,//章的高\n" + 
				"    //     newDate = parseInt(Math.random()*1000+1000),\n" + 
				"    //     markWidth = 160,\n" + 
				"    //     elemArr = [],//章的宽\n" + 
				"    //     allData = {};\n" + 
				"    // //字段替换\n" + 
				"    // function codeParse(template, data){\n" + 
				"    //     if (!template){\n" + 
				"    //         return '';\n" + 
				"    //     }\n" + 
				"    //     var t = removeSpace(template), key, reg;\n" + 
				"    // 　　　 //遍历该数据项下所有的属性，将该属性作为key值来查找标签，然后替换\n" + 
				"    //     for (key in data) {\n" + 
				"    //        t = keyParse(t ,key ,data[key]);\n" + 
				"    //     }\n" + 
				"    //     for(key in data){\n" + 
				"    //         t = noKeyParse(t, key, data[key])\n" + 
				"    //     }\n" + 
				"    //     return t;\n" + 
				"    // }\n" + 
				"    // //去掉所有空格\n" + 
				"    // function removeSpace(template){\n" + 
				"    //     return template;\n" + 
				"    //     return template.replace(/\\s/g,''); \n" + 
				"    // }\n" + 
				"    // //替换掉属性的写法\n" + 
				"    // function keyParse(template,paramName,data) {\n" + 
				"    //     var t = template, reg;\n" + 
				"    //     for(var key in data){   \n" + 
				"    //         reg = new RegExp('{{' + paramName+'\\\\$'+ key + '}}', 'g'); \n" + 
				"    //         t = t.replace(reg,data[key]||'')\n" + 
				"    //     }  \n" + 
				"    //     return t; \n" + 
				"    // }\n" + 
				"    // //\n" + 
				"    // function noKeyParse(template, paramName, data) {\n" + 
				"    //     var t = template, reg;\n" + 
				"    //     for (var key in data) {\n" + 
				"           \n" + 
				"    //         reg = new RegExp('{{' + paramName + '}}', 'g');\n" + 
				"    //         t = t.replace(reg, data.showValue||'')\n" + 
				"    //     }\n" + 
				"    //     return t; \n" + 
				"    // }\n" + 
				"\n" + 
				"    // //标记替换\n" + 
				"    // function templateParse(template,data){\n" + 
				"    //     allData = Object.assign({}, data)\n" + 
				"    //     var imgReg = new RegExp('#{img}', 'ig');\n" + 
				"    //     //分页标签\n" + 
				"    //     var pageReg = new RegExp('#{pagination}','ig');\n" + 
				"    //     //二维码\n" + 
				"    //     var qrImgReg = new RegExp('#{qrCode}','ig'); \n" + 
				"    //     //区域划分组件开始\n" + 
				"    //     var includeStart = new RegExp('#{includeStart}','ig')\n" + 
				"    //     //区域划分组件结束\n" + 
				"    //     var includeEnd = new RegExp('#{includeEnd}', 'ig')\n" + 
				"    //     //文章结束\n" + 
				"    //     var allEnd = new RegExp('#{allEnd}','ig');\n" + 
				"    //     //附件内容开始\n" + 
				"    //     var attachmentStart = new RegExp('#{attachmentStart}', 'ig');\n" + 
				"    //     //附件内容结束\n" + 
				"    //     var attachmentEnd = new RegExp('#{attachmentEnd}','ig');\n" + 
				"        \n" + 
				"    //     var imgLabelling =allData.cachetUrl ? '<img style=\"position:absolute;display:inline-block;width:160px;height:160px;left:-80px;top:-80px\" src = \"'+allData.cachetUrl+'\">' : '';\n" + 
				"    //     template = template.replace(imgReg, '<span class=\"print-other-chapter' + newDate + '\" style=\"position: relative;\">'+imgLabelling+'</span>');\n" + 
				"    //     template= template.replace(pageReg,'<div class = \"print-pagination'+newDate+'\"></div>');\n" + 
				"    //     template = template.replace(qrImgReg, '<span class=\"print-qr-code' + newDate + '\"><img src = \"' + allData.cachetUrl+'\"></span>');\n" + 
				"    //     template = template.replace(includeStart,'<div class=\"print-include-start'+newDate+'\"></div>');\n" + 
				"    //     template = template.replace(includeEnd, '<div class=\"print-include-end' + newDate + '\"></div>');\n" + 
				"    //     template = template.replace(allEnd,'<div class=\"print-all-end'+newDate+'\"></div>');\n" + 
				"    //     template = template.replace(attachmentStart, '<div class=\"print-attachment-start' + newDate + '\"></div>');\n" + 
				"    //     template = template.replace(attachmentEnd, '<div class=\"print-attachment-end' + newDate + '\"></div>');\n" + 
				"    //     return template;\n" + 
				"    // }\n" + 
				"    // //每一个分页标记的渲染\n" + 
				"    // function everyPagination(elem){\n" + 
				"    //     var detail = elem.getBoundingClientRect(),\n" + 
				"    //         top = detail.top-uselessHeight,\n" + 
				"    //         pageNum = Math.ceil(top/paperHeight),\n" + 
				"    //         diffValue = pageNum * paperHeight - top,\n" + 
				"    //         height = diffValue > 0 ? diffValue : 0;\n" + 
				"    //         elem.style.height = height + \"px\";\n" + 
				"    //         elem.style.marginTop = 50 + \"px\";\n" + 
				"    // }\n" + 
				"    // //每一个包围标记的渲染\n" + 
				"    // function everyInclude(startElem,endElem){\n" + 
				"    //     var startDetail = startElem.getBoundingClientRect(),\n" + 
				"    //         endDetail = endElem.getBoundingClientRect(),\n" + 
				"    //         startTop = startDetail.top - uselessHeight,\n" + 
				"    //         endTop = endDetail.top - uselessHeight,\n" + 
				"           \n" + 
				"    //         startPageNum =  Math.ceil(startTop/paperHeight),\n" + 
				"    //         endPageNum = Math.ceil(endTop/paperHeight);\n" + 
				"    //         if(endPageNum>startPageNum){\n" + 
				"    //             var diffValue = startPageNum * paperHeight - startTop,\n" + 
				"    //                 height = diffValue > 0 ? diffValue : 0;\n" + 
				"    //                 startElem.style.height = height + \"px\";\n" + 
				"    //                 startElem.style.marginTop = 50 + \"px\";\n" + 
				"    //         }      \n" + 
				"    // }\n" + 
				"\n" + 
				"    // //获取节点\n" + 
				"    // function getNode(elem){\n" + 
				"    //     return document.querySelectorAll(elem);\n" + 
				"    // }\n" + 
				"\n" + 
				"    // //齐缝章\n" + 
				"    // function checkMark(mainElem){\n" + 
				"    //     /**\n" + 
				"    //      * startElem 指的是附件部分的开始标识\n" + 
				"    //      * endElem 指的是附件部分的结束标识\n" + 
				"    //      */\n" + 
				"    //     var startElem = getNode('.print-attachment-start' + newDate)[0];\n" + 
				"    //     var endElem = getNode('.print-attachment-end' + newDate)[0];\n" + 
				"    //     var startDetail = \"\",\n" + 
				"    //         endDetail = '',\n" + 
				"    //         startTop =0,\n" + 
				"    //         endTop = 0,\n" + 
				"    //         startNum =0,\n" + 
				"    //         endNum = 0; \n" + 
				"\n" + 
				"    //     var mainDetil = mainElem.getBoundingClientRect(),\n" + 
				"    //         mainHeight = mainDetil.height,\n" + 
				"    //         pageNum = Math.ceil(mainHeight/paperHeight),\n" + 
				"    //         markElem = '';\n" + 
				"    //     var isHave = false;\n" + 
				"        \n" + 
				"    //     if(startElem && endElem){\n" + 
				"            \n" + 
				"    //         isHave = true;\n" + 
				"    //         startDetail = startElem.getBoundingClientRect();\n" + 
				"    //         endDetail = endElem.getBoundingClientRect();\n" + 
				"    //         startTop = startDetail.top;\n" + 
				"    //         endTop = endDetail.top;\n" + 
				"    //         startNum = Math.floor(startTop / paperHeight);\n" + 
				"    //         endNum = Math.floor(endTop / paperHeight);\n" + 
				"    //     }\n" + 
				"    //     console.log(pageNum, \"++++++++\", startNum, endNum)\n" + 
				"    //     if (isHave && pageNum-1 <=1 ){\n" + 
				"    //         return;\n" + 
				"    //     }else if(pageNum>1){\n" + 
				"    //         for(let i = 0; i<pageNum;i++){\n" + 
				"    //             if (isHave) {\n" + 
				"    //                 if (i < startNum || i > endNum) {\n" + 
				"                    \n" + 
				"    //                         var diffValue = endNum - startNum + 1;\n" + 
				"    //                         markElem += everyCheckMark(i, pageNum - diffValue, i > endNum ? endNum : 0)\n" + 
				"                    \n" + 
				"    //                 }\n" + 
				"    //             }else {\n" + 
				"    //                 markElem += everyCheckMark(i, pageNum, 0)\n" + 
				"    //             }\n" + 
				"    //         }\n" + 
				"            \n" + 
				"    //     }\n" + 
				"    //     mainElem.innerHTML = mainElem.innerHTML + markElem;\n" + 
				"    // }\n" + 
				"    // //每一页骑缝章的渲染\n" + 
				"    // function everyCheckMark(num, pageNum,endNum){\n" + 
				"    //    var boxWidth = Math.ceil(markWidth/pageNum*1000)/1000;\n" + 
				"    //    var top = num*paperHeight+paperHeight/2-markHeight/2;\n" + 
				"    //    var elemImg = '<div style=\"height:'+markHeight+'px;width:'+boxWidth+'px;overflow:hidden;position:absolute;right:0px;top:'+top+'px;\">'+\n" + 
				"    //        '<img style=\"width:' + markWidth + 'px;height:' + markHeight + 'px;display:inline-block;left:' + ((-num + endNum) * boxWidth)+'px;position:absolute;\" src = \"'+allData.cachetUrl+'\"/>'+\n" + 
				"    //                  '</div>';\n" + 
				"       \n" + 
				"    //    return elemImg;\n" + 
				"    // }\n" + 
				"    // //顺序渲染所有节点\n" + 
				"    // function allElemsRender(){\n" + 
				"    //     sortAll();\n" + 
				"    //     for (var i = 0; i < elemArr.length; i++) {\n" + 
				"    //         var elem = elemArr[i];\n" + 
				"    //         if(elem.type === \"include\"){\n" + 
				"    //             everyInclude(elem.start,elem.end);\n" + 
				"    //         }else{\n" + 
				"    //             everyPagination(elem.elem);   \n" + 
				"    //         }\n" + 
				"    //     }\n" + 
				"    // }\n" + 
				"    // //所有替换元素进行排序\n" + 
				"    // function sortAll(){\n" + 
				"    //     produceElemArr(\".print-pagination\",'page')\n" + 
				"    //     produceElemArr('.print-include','include')\n" + 
				"    //     elemArr.sort(function(a,b){\n" + 
				"    //         return b.top-a.top<0\n" + 
				"    //     });\n" + 
				"    // }\n" + 
				"    // //获取每一个渲染的元素\n" + 
				"    // function produceElemArr(className,type){\n" + 
				"    //     var elems = getNode(className+newDate);\n" + 
				"    //     var detail = {};\n" + 
				"    //     if(type === \"include\"){\n" + 
				"    //         var start = getNode(className+'-start'+newDate);\n" + 
				"    //         var end = getNode(className+'-end'+newDate);\n" + 
				"    //         for(let i=0;i<start.length;i++){\n" + 
				"    //             detail = start[i].getBoundingClientRect();\n" + 
				"    //             elemArr.push({type:type,start:start[i],end:end[i],top:detail.top});\n" + 
				"    //         }\n" + 
				"    //     }else{\n" + 
				"    //         for(let i=0;i<elems.length;i++){\n" + 
				"    //             detail = elems[i].getBoundingClientRect();\n" + 
				"    //             elemArr.push({type:type,elem:elems[i],top:detail.top});\n" + 
				"    //         }\n" + 
				"    //     }\n" + 
				"    // }\n" + 
				"    // //印章位置调整\n" + 
				"    // function chaptersMove(params) {\n" + 
				"    //     var elems = getNode(\".print-other-chapter\" + newDate +\" img\");\n" + 
				"    //     for(let i = 0; i<elems.length;i++ ){\n" + 
				"    //         everyChapter(elems[i]);\n" + 
				"    //     }\n" + 
				"    // }\n" + 
				"    // //每一个章的位置调整\n" + 
				"    // function everyChapter(elem) {\n" + 
				"    //     var detail = elem.getBoundingClientRect(),\n" + 
				"    //         topTop = detail.top - uselessHeight,\n" + 
				"    //         bottomTop = topTop + detail.height, \n" + 
				"    //         numTop = Math.ceil(topTop / paperHeight),\n" + 
				"    //         numBottom = Math.ceil(bottomTop / paperHeight),\n" + 
				"    //         distanceTop = Math.abs(numTop * paperHeight - topTop),\n" + 
				"    //         distanceBottom = Math.abs(numBottom * paperHeight- bottomTop);\n" + 
				"    //     if (numTop!= numBottom){\n" + 
				"    //         let elemTop = parseInt(elem.style.top)\n" + 
				"    //         if(distanceTop < distanceBottom){\n" + 
				"    //             elem.style.top = elemTop + distanceTop +50+ 'px';\n" + 
				"    //         }else{\n" + 
				"    //             elem.style.top = elemTop - distanceBottom -50+ \"px\"\n" + 
				"    //         }\n" + 
				"    //     }else{\n" + 
				"    //         let elemTop = parseInt(elem.style.top)\n" + 
				"    //         let newTop = Math.abs((numTop - 1) * paperHeight - topTop);\n" + 
				"    //         let newBottom = Math.abs((numTop) * paperHeight - bottomTop);\n" + 
				"    //         if (newTop<20){\n" + 
				"    //             elem.style.top = elemTop + 20 + 'px';\n" + 
				"    //         }\n" + 
				"    //         if (newBottom<20){\n" + 
				"    //             elem.style.top = elemTop  - 20 + 'px';\n" + 
				"    //         }\n" + 
				"            \n" + 
				"    //     }\n" + 
				"        \n" + 
				"    // }\n" + 
				"    // //去掉尾部无用节点\n" + 
				"    // function delEndFutility(elem) {\n" + 
				"    //     elem= elem || getNode(\".print-all-end\"+newDate)[0];\n" + 
				"    //     if(!elem){\n" + 
				"    //         return;\n" + 
				"    //     }\n" + 
				"    //     let nextElem = elem.nextSibling;\n" + 
				"    //     if(nextElem){\n" + 
				"    //         delEndFutility(nextElem);\n" + 
				"    //         delNowElem(nextElem);\n" + 
				"    //     }\n" + 
				"    // }\n" + 
				"    // //删除该元素\n" + 
				"    // function delNowElem(elem) {\n" + 
				"    //     elem.parentNode.removeChild(elem)\n" + 
				"    // }\n" + 
				"    // //控制页面的高度\n" + 
				"    // function controlHeight(elem){\n" + 
				"    //     var detail = elem.getBoundingClientRect();\n" + 
				"    //     var endHeight = '';\n" + 
				"    //     elem.style.height = detail.height - 30 + 'px';\n" + 
				"    //     elem.style.overflow = \"hidden\";\n" + 
				"    //     detail = elem.getBoundingClientRect();\n" + 
				"    //     endHeight = Math.ceil(detail.height/paperHeight)*paperHeight;\n" + 
				"    //     elem.style.height = endHeight - 30 + \"px\";\n" + 
				"    // }\n" + 
				"\n" + 
				"})()\n" + 
				"</script>\n" + 
				"</html>";
		return jsHtml;
	}
}

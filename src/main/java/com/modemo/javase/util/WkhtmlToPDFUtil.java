package com.modemo.javase.util;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.moven.common.utils.FreemarkerUtil;
/**
 * <p>Title:HtmlToPDFUtil</p>
 * <p>Description:HTML转PDF工具类</p>
 * @author moshengwei
 * @date 2017年12月15日 下午1:59:58
 */
public class WkhtmlToPDFUtil {
	private static final Logger logger = LoggerFactory.getLogger(WkhtmlToPDFUtil.class);
	//公章宽度
	private static final int IMG_WIDTH = 160;
	//公章高度
	private static final int IMG_HEIGHT = 160;
	//PDF工具地址
	public static final String TOOL_PATH = "/usr/local/bin/wkhtmltopdf";
	//公章图片地址
	public static final String IMG = "http://krspace-upload.oss-cn-qingdao.aliyuncs.com/activity_unzip/201707/Y/131233886_443.png";
	//生成HTML文件地址
	public static final String DEST_HTML = "/Users/moshengwei/Downloads/iText2PDF/hello_world.html";
	//生成无公章PDF地址
	public static final String DEST = "/Users/moshengwei/Downloads/iText2PDF/hello_world.pdf";
	//生成有公章PDF地址
	public static final String DEST_IMG = "/Users/moshengwei/Downloads/iText2PDF/hello_world_img.pdf";

	/**
	 * Description:将html转换成pdf
	 * 
	 * @author moshengwei
	 * @date 2017年12月12日 下午5:34:16
	 * @param htmlPath
	 *            html路径
	 * @param pdfPath
	 *            pdf路径
	 * @return
	 * @throws Exception
	 */
	public static boolean convert(String htmlPath, String pdfPath) {
		File file = new File(pdfPath);
		File parent = file.getParentFile();
		// 如果pdf保存路径不存在，则创建路径
		if (!parent.exists()) {
			parent.mkdirs();
		}
		StringBuilder cmd = new StringBuilder();
		cmd.append(TOOL_PATH);
		cmd.append(" ");
//		cmd.append(" -B 10mm "); //下边距(默认10mm)
//		cmd.append(" -L 10mm "); //左边距(默认10mm)
//		cmd.append(" -R 10mm "); //右边距(默认10mm)
//		cmd.append(" -T 10mm "); //上边距(默认10mm)
//		cmd.append(" --page-width 210mm "); //页面宽度
//		cmd.append(" --page-height 297mm "); //页面高度
//		cmd.append(" --page-size A4 ");// 设置纸张大小: A4(默认A4)
//		cmd.append(" --disable-smart-shrinking ");// 禁用智能缩放
//		cmd.append(" --enable-smart-shrinking ");// 启用智能缩放
//		cmd.append(" --zoom 1 ");// 使用这个缩放因子 (default 1)
//		cmd.append(" --debug-javascript "); //启用JS的debug模式
		cmd.append(" --enable-javascript "); //允许运行JS
		cmd.append(" --encoding utf-8 ");// 设置编码：UTF-8
		cmd.append(" --dpi 532 ");// 使用DPI可改变像素和DPI比例
		cmd.append(htmlPath); // 源文件(HTML)路径
		cmd.append(" ");
		cmd.append(pdfPath);// 生成文件(PDF)路径
		boolean result = true;
		try {
			Process proc = Runtime.getRuntime().exec(cmd.toString());
			HtmlToPdfHandler errorput = new HtmlToPdfHandler(proc.getErrorStream());
			HtmlToPdfHandler output = new HtmlToPdfHandler(proc.getInputStream());
			errorput.start();
			output.start();
			proc.waitFor();
		} catch (Exception e) {
			result = false;
			logger.error("HTML转换PDF异常", e);
		}
		return result;
	}
	
	/**
	 * Description:解析HTML页面
	 * @author moshengwei
	 * @date 2017年12月13日 下午6:57:25
	 * @param tempContent	页面模板内容
	 * @param data			页面数据
	 * @return
	 */
	public static String getHTML(String tempContent, JSONObject data) {
		String htmlStr = "";
		try {
			htmlStr = FreemarkerUtil.processTemplateStr(tempContent, "htmlTemp", data);
		} catch (Exception e) {
			logger.error("解析HTML模板文件异常", e);
		}
		return htmlStr;
	}

	/**
	 * Description:切割骑缝章
	 * @author moshengwei
	 * @date 2017年12月12日 下午7:06:51
	 * @param imgPath	图片路径
	 * @param n		切割份数
	 * @return
	 */
	private static Image[] subImages(String imgPath, int n) {
		Image[] nImage = new Image[n];
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		BufferedImage img = null;
		try {
			if (StringUtils.isNotBlank(imgPath) && imgPath.startsWith("http")) {
				img = ImageIO.read(new URL(imgPath));
			} else {
				img = ImageIO.read(new File(imgPath));
			}
		} catch (IOException e) {
			logger.error("读取齐缝章文件异常", e);
		}
		//修改图片尺寸
		BufferedImage image = new BufferedImage(IMG_WIDTH,IMG_HEIGHT,img.getType());
		image.getGraphics().drawImage(img, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
		int h = image.getHeight();
		int w = image.getWidth();

		int sw = w / n;
		for (int i = 0; i < n; i++) {
			BufferedImage subImg;
			if (i == n - 1) {// 最后剩余部分
				subImg = image.getSubimage(i * sw, 0, w - i * sw, h);
			} else {// 前n-1块均匀切
				subImg = image.getSubimage(i * sw, 0, sw, h);
			}
			try {
				ImageIO.write(subImg, imgPath.substring(imgPath.lastIndexOf('.') + 1), out);
				nImage[i] = Image.getInstance(out.toByteArray());
				out.flush();
				out.reset();
			} catch (IOException | BadElementException e) {
				logger.error("切割齐缝章异常", e);
			} finally {
				try {
					out.close();
				} catch (IOException e) {
					logger.error("关闭齐缝章输出流异常", e);
				}
			}
		}
		return nImage;
	}

	/**
	 * Description:盖骑缝章
	 * @author moshengwei
	 * @date 2017年12月12日 下午7:07:29
	 * @param sourcePath	源文件(无章PDF)路径
	 * @param targetPath	生成文件(有章PDF)路径
	 * @param picPath	公章路径
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static void stamperCheckMarkPDF(String sourcePath, String targetPath, String picPath) {
		PdfReader reader = null;
		PdfStamper stamp = null;
		try {
			reader = new PdfReader(sourcePath);
			stamp = new PdfStamper(reader, new FileOutputStream(targetPath));
		} catch (DocumentException | IOException e) {
			logger.error("读取无章PDF文件异常", e);
		}
		
		// 加完印章后的pdf
		Rectangle pageSize = reader.getPageSize(1);// 获得第一页
		float height = pageSize.getHeight();
		float width = pageSize.getWidth();

		int nums = reader.getNumberOfPages();
		Image[] nImage = subImages(picPath, nums);// 生成骑缝章切割图片

		for (int n = 1; n <= nums; n++) {
			PdfContentByte over = stamp.getOverContent(n);// 设置在第几页打印印章
			Image img = nImage[n - 1];// 选择图片
			img.setAbsolutePosition(width - img.getWidth(), height / 2 - img.getHeight() / 2);// 控制图片位置
			try {
				over.addImage(img);
			} catch (DocumentException e) {
				logger.error("为PDF加盖齐缝章异常", e);
			}
		}
		try {
			stamp.close();
		} catch (DocumentException | IOException e) {
			logger.error("关闭齐缝章加盖输出流异常", e);
		}
	}

	/**
	 * Description:生成HTML文件
	 * @author moshengwei
	 * @date 2017年12月12日 下午7:08:21
	 * @param htmlPath	HTML文件路径
	 * @param htmlContent	HTML文件内容
	 */
	public static void createHTML(String htmlPath, String htmlContent) {
		File file = new File(htmlPath);
		File parent = file.getParentFile();
		// 如果pdf保存路径不存在，则创建路径
		if (!parent.exists()) {
			parent.mkdirs();
		}
		PrintStream ps = null;
		try {
			ps = new PrintStream(file, "utf-8");
			file.createNewFile();
		} catch (IOException e) {
			logger.error("关闭齐缝章加盖输出流异常", e);
		} finally {
			ps.print(htmlContent);
			ps.close();
		}
	}

	/**
	 * Description:删除临时文件
	 * @author moshengwei
	 * @date 2017年12月12日 下午7:08:43
	 * @param tempFilePath 临时文件路径
	 */
	public static void removeTempFile(String tempFilePath) {
		File file = new File(tempFilePath);
		file.delete();
	}

}

/**
 * <p>Title:HtmlToPdfHandler</p>
 * <p>Description:HTML转PDF处理类</p>
 * @author moshengwei
 * @date 2017年12月15日 下午1:59:36
 */
class HtmlToPdfHandler extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(HtmlToPdfHandler.class);
	private InputStream is;

	public HtmlToPdfHandler(InputStream is) {
		this.is = is;
	}

	public void run() {
		try {
			InputStreamReader isr = new InputStreamReader(is, "utf-8");
			BufferedReader br = new BufferedReader(isr);
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line.toString()); // 输出内容
			}
		} catch (IOException e) {
			logger.error("生成PDF异常", e);
		}
	}
}

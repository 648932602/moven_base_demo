package com.modemo.javase.util;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.itextpdf.text.DocumentException;

public class ITextPDFUtil {
	public static final String DOG = "/Users/moshengwei/Downloads/iText2PDF/dog.bmp";
	public static final String FOX = "/Users/moshengwei/Downloads/iText2PDF/fox.bmp";

	public static final String DEST = "/Users/moshengwei/Downloads/iText2PDF/hello_world.pdf";

	public static void main(String args[])
			throws IOException, DocumentException, java.io.IOException, ParserConfigurationException, SAXException {
		long startTime = System.currentTimeMillis();
		File file = new File(DEST);
		file.getParentFile().mkdirs();
//		new ITextPDFUtil().test05(DEST);
		long entTime = System.currentTimeMillis();
		System.out.println("use = " + (entTime - startTime));
	}

//	public void test01(String dest) throws IOException, FileNotFoundException {
//		// Initialize PDF writer
//		PdfWriter writer = new PdfWriter(dest);
//
//		// Initialize PDF document
//		PdfDocument pdf = new PdfDocument(writer);
//
//		// Initialize document
//		Document document = new Document(pdf);
//
//		// Add paragraph to the document
//		document.add(new Paragraph("Hello World!-test"));
//
//		// Close document
//		document.close();
//	}
//
//	public void test02(String dest) throws IOException, java.io.IOException {
//		// Initialize PDF writer
//		PdfWriter writer = new PdfWriter(dest);
//
//		// Initialize PDF document
//		PdfDocument pdf = new PdfDocument(writer);
//
//		// Initialize document
//		Document document = new Document(pdf);
//		// Create a PdfFont
//		PdfFont font = PdfFontFactory.createFont(FontConstants.TIMES_ROMAN);
//		// Add a Paragraph
//		document.add(new Paragraph("iText is:").setFont(font));
//		// Create a List
//		List list = new List().setSymbolIndent(12).setListSymbol("\u2022").setFont(font);
//		// Add ListItem objects
//		list.add(new ListItem("Never gonna give you up")).add(new ListItem("Never gonna let you down"))
//				.add(new ListItem("Never gonna run around and desert you"))
//				.add(new ListItem("Never gonna make you cry")).add(new ListItem("Never gonna say goodbye"))
//				.add(new ListItem("Never gonna tell a lie and hurt you"));
//		// Add the list
//		document.add(list);
//
//		// Close document
//		document.close();
//	}
//
//	public void test03(String dest) throws IOException, FileNotFoundException, MalformedURLException {
//		// Initialize PDF writer
//		PdfWriter writer = new PdfWriter(dest);
//
//		// Initialize PDF document
//		PdfDocument pdf = new PdfDocument(writer);
//
//		// Initialize document
//		Document document = new Document(pdf);
//
//		// Compose Paragraph
//		Image fox = new Image(ImageDataFactory.create(FOX));
//		Image dog = new Image(ImageDataFactory.create(DOG));
//		Paragraph p = new Paragraph("The quick brown ").add(fox).add(" jumps over the lazy ").add(dog);
//		// Add Paragraph to document
//		document.add(p);
//
//		// Close document
//		document.close();
//	}
//
//	public void test04(String dest) throws DocumentException, java.io.IOException {
//		String inputFile = "/Users/moshengwei/Downloads/iText2PDF/firstdoc.html";
//		String url = new File(inputFile).toURI().toURL().toString();
//		OutputStream os = new FileOutputStream(dest);
//		ITextRenderer render = new ITextRenderer();
//		render.setDocument(url);
//		render.layout();
//		render.createPDF(os);
//		render.finishPDF();
//		os.close();
//	}
//
//	public void test05(String dest) throws ParserConfigurationException, SAXException, java.io.IOException,
//			DocumentException, com.itextpdf.text.DocumentException {
//		StringBuffer buf = new StringBuffer();
//		buf.append("<html>");
//		// put in some style
//		buf.append("<head><style language='text/css'>");
//		buf.append("h3 { border: 1px solid #aaaaff; background: #ccccff; ");
//		buf.append("padding: 1em; text-transform: capitalize; font-family: sansserif; font-weight: normal;}");
//		buf.append("p { margin: 1em 1em 4em 3em; } p:first-letter { color: red; font-size: 150%; }");
//		buf.append(
//				"h2 { background: #5555ff; color: white; border: 10px solid black; padding: 3em; font-size: 200%; }");
//		buf.append("</style></head>");
//		// generate the body
//		// buf.append("<body style=\"font-family:'Arial Unicode MS'\">");
//		buf.append("<body style=\"font-family:'宋体'\">");
//		for (int i = 99; i > 0; i--) {
//			buf.append("<h3>" + i + " bottles of beer on the wall, " + i + " bottles of beer!</h3>");
//			buf.append("<p>Take one down and pass it around, " + (i - 1) + " bottles of beer on the wall</p>");
//		}
//		buf.append(
//				"<h2 style=\"font-size: 12px; text-indent: 32px;\">Go to the store and buy some more,a就是看看中文行不行。aa 99 bottles of beer on the wall.</h2>");
//		buf.append("</body>");
//		buf.append("</html>");
//		ITextRenderer renderer = new ITextRenderer();
//		// 解决中文不显示
//		ITextFontResolver fontResolver = renderer.getFontResolver();
//		// fontResolver.addFont("/Users/moshengwei/Downloads/iText2PDF/Arial Unicode
//		// MS.ttf", BaseFont.IDENTITY_H,
//		// BaseFont.NOT_EMBEDDED);
//		// fontResolver.addFont("/Users/moshengwei/Downloads/iText2PDF/SimSun-ExtB.TTF",
//		// BaseFont.IDENTITY_H,
//		// BaseFont.NOT_EMBEDDED);
//		BaseFont font = BaseFont.createFont("/font/SimSun-ExtB.TTF", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//		String inputFile = "/Users/moshengwei/Downloads/iText2PDF/firstdoc.html";
//		renderer.setDocument(inputFile);
//		// renderer.setDocumentFromString(buf.toString());
//		OutputStream os = new FileOutputStream(dest);
//		renderer.layout();
//		renderer.createPDF(os);
//		os.close();
//	}
//
//	private static void test06(String dest) {
//		ByteArrayOutputStream baos = new ByteArrayOutputStream(OUTPUT_BYTE_ARRAY_INITIAL_SIZE);
//		Document document = new Document(PageSize.A4);
//		PdfWriter writer = PdfWriter.getInstance(document, baos);
//		writer.setViewerPreferences(PdfWriter.AllowPrinting | PdfWriter.PageLayoutSinglePage);
//		BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//		Font font = new Font(bf, 12, Font.NORMAL);
//		document.open();
//		Paragraph p = new Paragraph("你好", font);
//		document.add(p);
//		document.add(new Paragraph("Test2"));
//		Table table = new Table(2, 3);
//		table.addCell(new Phrase("我好", font));
//		table.addCell("C2R1");
//		table.addCell("C1R2");
//		table.addCell("C2R2");
//		Cell c = (Cell) table.getElement(0, 0);
//		c.setVerticalAlignment("Middle");
//		c.setBackgroundColor(new Color(255, 0, 0));
//		c.setHorizontalAlignment("Center");
//		document.add(table);
//		document.close();
//		baos.writeTo(new FileOutputStream("F:\\test.pdf"));
//	}

}

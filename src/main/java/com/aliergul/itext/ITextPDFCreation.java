package com.aliergul.itext;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import com.aliergul.pdfbox.App;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class ITextPDFCreation {
  private static final String PDF_FILE_PATH = "D:\\Eclipse2021\\ornek2.pdf";
  private static final String PDF_IMAGE_PATH =
      App.class.getResource("../../../email.png").toString().substring(6);// "file:\" kesmek için 6
                                                                          // yazdım.

  public static void main(String[] args) {

    try {
      createPDF();
    } catch (IOException | DocumentException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  private static void createPDF() throws FileNotFoundException, IOException, DocumentException {
    Document pdf = new Document(PageSize.A4, 20, 20, 20, 20);
    System.out.println("PDF created");

    try (FileOutputStream fos = new FileOutputStream(PDF_FILE_PATH)) { // içerik doldurmaca burada
                                                                       // başlıyor
      PdfWriter writer = PdfWriter.getInstance(pdf, fos);
      // writer.setEncryption("gizli".getBytes(), "mizli".getBytes(), PdfWriter.ALLOW_PRINTING,
      // PdfWriter.ENCRYPTION_AES_128);

      pdf.open();
      pdf.add(new Paragraph("bir gün okula giderken..."));
      pdf.add(new Paragraph("her şeye dikkat ederken..."));
      pdf.add(new Paragraph("bir kız çıktı karşıma..."));
      Image img = Image.getInstance(PDF_IMAGE_PATH);
      img.setAbsolutePosition(450f, 10f);
      img.scaleToFit(50f, 50f);
      pdf.add(img);

      pdf.addAuthor("Ali ERGÜL");
      pdf.addCreationDate();
      pdf.addCreator("Automated creation");
      pdf.addTitle("iText ile PDF dosyası oluşturma");
      pdf.addKeywords("pdf; java; bilgeadam;");
      pdf.close();
      writer.close();
    }
    System.out.println("PDF content written");
  }
}

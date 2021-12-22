package com.aliergul.pdfbox;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;

/**
 * PDFBox
 *
 */
public class App {
  private static final String PDF_FILE_PATH = "D:\\Eclipse2021\\ornek.pdf";
  private static final String PDF_IMAGE_PATH =
      App.class.getResource("../../../email.png").toString().substring(6);// "file:\" kesmek için 6
                                                                          // yazdım.

  public static void main(String[] args) {

    try {
      createPDF();
      createPDFWithImage();
      readPDF();
    } catch (IOException e) {
      System.err.println("PDF iþlenirken hata oluþtu: " + e.getMessage());
    }

  }

  private static void createPDFWithImage() throws IOException {
    PDPageContentStream content = null;
    try (PDDocument pdf = new PDDocument()) {
      PDPage page = new PDPage();
      pdf.addPage(page);
      content = new PDPageContentStream(pdf, page);

      System.out.println(PDF_IMAGE_PATH);
      PDImageXObject pdImage = PDImageXObject.createFromFile(PDF_IMAGE_PATH, pdf);

      content.drawImage(pdImage, 20f, 20f, pdImage.getWidth(), pdImage.getHeight());
      content.beginText();
      content.setFont(PDType1Font.TIMES_BOLD, 14); // kullanýlacak font'un belirlenmesi
      content.setLeading(14.5f); // öndek boþluk
      content.newLineAtOffset(20, 750); // origin of the page is left-bottom corner
      String line = "Bir gün okula giderken...";
      content.showText(line);

      content.setFont(PDType1Font.TIMES_BOLD, 11); // kullanýlacak font'un belirlenmesi
      content.newLine();
      content.showText("her seye dikkat ederken...");
      content.newLine();
      content.showText("bir kiz cikti karsima...");

      content.endText();
      content.close();
      pdf.save(PDF_FILE_PATH);
      System.out.println("PDF yaratýldý");
    } finally {
      if (content != null) {
        try {
          content.close();
        } catch (Exception e) {
          // do nothing
        }
      }
    }
  }

  private static void readPDF() throws IOException {

    File pdfFile = new File(PDF_FILE_PATH);
    try (PDDocument pdf = PDDocument.load(pdfFile)) {
      PDFTextStripper stripper = new PDFTextStripper(); // DFF'ten text okumak için kullanýlan sýnýf
      String text = stripper.getText(pdf);
      System.out.println(text);
    }
  }

  private static void createPDF() throws IOException {

    PDPageContentStream content = null;
    try (PDDocument pdf = new PDDocument()) {
      PDPage page = new PDPage();
      pdf.addPage(page);
      content = new PDPageContentStream(pdf, page);

      content.beginText();
      content.setFont(PDType1Font.TIMES_BOLD, 14); // kullanýlacak font'un belirlenmesi
      content.setLeading(14.5f); // öndek boþluk
      content.newLineAtOffset(20, 750); // origin of the page is left-bottom corner
      String line = "Bir gün okula giderken...";
      content.showText(line);
      content.setFont(PDType1Font.TIMES_BOLD, 11); // kullanýlacak font'un belirlenmesi
      content.newLine();
      content.showText("her seye dikkat ederken...");
      content.newLine();
      content.showText("bir kiz cikti karsima...");

      content.endText();
      content.close();
      pdf.save(PDF_FILE_PATH);
      System.out.println("PDF yaratýldý");
    } finally {
      if (content != null) {
        try {
          content.close();
        } catch (Exception e) {
          // do nothing
        }
      }
    }
  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author kyle_
 */
@ManagedBean(name = "studiekostenformulier")
@SessionScoped
public class StudiekostenformulierBean {
    
    private String newFileName;
    private PDPageContentStream content;
    private PDType1Font font;
    private final int fontSize;
    
    public StudiekostenformulierBean() {
        newFileName = "Studiekostenformulier2";
        font = PDType1Font.HELVETICA;
        fontSize = 10;
    }
    
    
    /**
     * Creates a PDF with dummie information. This method can be used to get the
     * X and Y of some specific textboxes.
     * @throws IOException 
     */
    public void DummieCreate() throws IOException {

        // Gegevens van de werknemer
        addSmallText(0, "Registration Form", 380, 685); // Naam werknemer        
        addSmallText(0, "23-43-2018", 380, 665); // Datum in dienst      
        addSmallText(0, "Professor", 380, 645); // Functie
        
        // Gegevens van de Studie
        addSmallText(0, "Professor", 380, 575); // Te volgen studie
        addSmallText(0, "Het instututie", 380, 555); // Startdatum
        addSmallText(0, "23-34-2016", 380, 535); // Einddatum
        addSmallText(0, "5 dagen", 380, 515); // Cursusdagen
        addSmallText(0, "Hallo dit is een test tekst.", 77, 457); // Cursusdagen
        
        addSmallText(0, "100 euro", 380, 325); // Te vergoeden studiekosten
        addSmallText(0, "200 euro", 380, 305); // Examengelden
        addSmallText(0, "300 euro", 380, 285); // Vervoerskosten
        addSmallText(0, "400 euro", 380, 265); // Verblijfskosten
        addSmallText(0, "200 euro", 380, 245); // Overige kosten
        addSmallText(0, "1200 euro", 380, 215); // Totaal excl. BTW
        
        addSmallText(1, "Hallo dit is een test tekst.", 77, 723); // Categorie
        addSmallText(1, "1200 euro", 380, 631); // Afschrijvingsperiode
        addSmallText(1, "Shamposi", 380, 567); // Manager
    }

    /**
     * Adds text to the PDF file.
     * @param pageIndex Pagenumber where the text will be displayed.
     * @param text Text that will be displayed on the PDF file
     * @param xPos X position on the PDF file
     * @param yPos Y position on the PDF file
     * @throws IOException 
     */
    public void addSmallText(int pageIndex, String text, int xPos, int yPos) throws IOException {
        
        try (PDDocument document = PDDocument.load(new File("studiekostenformulier.pdf"))) {
            PDPage page = document.getPage(pageIndex);
            content = new PDPageContentStream(document, page, true, true);
            
            content.beginText();
            content.setFont(font, fontSize);
            content.moveTextPositionByAmount(xPos, yPos);
            content.drawString(text);
            content.endText();
            
            content.close();
            document.save(newFileName);
            document.close();
        }
    }
    
    public void addBigText(int pageIndex, String text, int xPos, int yPos) throws IOException {
        //TODO: big text blocks.
    }
}

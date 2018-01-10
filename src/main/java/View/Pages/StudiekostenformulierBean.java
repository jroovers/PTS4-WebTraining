/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View.Pages;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.List;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
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
    
   
    
    public String nameEmployer; // Naam werknemer  
    public Date dateService; // Datum in dienst 
    public String function; // Functie
    public String studyToFollow; // Te volgen studie
    public Date startDate; // Startdatum
    public Date endDate; // Einddatum
    public String courseDays; // Cursusdagen
    public String description; // Beschrijving
    public String studyCostsToBeReimbursed; // Te vergoeden studiekosten
    public String examinationFees; // Examengelden
    public String transportationCosts; // Vervoerskosten
    public String accommodationCosts; // Verblijfskosten
    public String otherCosts; // Overige kosten
    public String totalCosts; // Totaal excl. BTW
    public String category; // Categorie
    public String deprecaitionPeriod; // Afschrijvingsperiode
    public String manager; // Manager

    
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
     * Creates a PDF file with all the values. If a value is not filled in it will
     * not be added to the PDF file.
     * @throws java.io.IOException
     */
    public void Create() throws IOException {
        
        // Gegevens van de werknemer
        addSmallText(0, nameEmployer, 380, 685); // Naam werknemer        
        addSmallText(0, dateService.toString(), 380, 665); // Datum in dienst      
        addSmallText(0, function, 380, 645); // Functie
        
        // Gegevens van de Studie
        addSmallText(0, studyToFollow, 380, 575); // Te volgen studie
        addSmallText(0, startDate.toString(), 380, 555); // Startdatum
        addSmallText(0, endDate.toString(), 380, 535); // Einddatum
        addSmallText(0, courseDays, 380, 515); // Cursusdagen
        addSmallText(0, description, 77, 457); // Beschrijving
        
        addSmallText(0, studyCostsToBeReimbursed, 380, 325); // Te vergoeden studiekosten
        addSmallText(0, examinationFees, 380, 305); // Examengelden
        addSmallText(0, transportationCosts, 380, 285); // Vervoerskosten
        addSmallText(0, accommodationCosts, 380, 265); // Verblijfskosten
        addSmallText(0, otherCosts, 380, 245); // Overige kosten
        addSmallText(0, totalCosts, 380, 215); // Totaal excl. BTW
        
        addSmallText(1, category, 77, 723); // Categorie
        addSmallText(1, deprecaitionPeriod, 380, 631); // Afschrijvingsperiode
        addSmallText(1, manager, 380, 567); // Manager
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
    
    /**
     * Download a pdf file of the form to the browser. 
     * @throws IOException 
     */
    public void downloadPdf() throws IOException {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        HttpServletResponse response = (HttpServletResponse) facesContext.getELContext();
        
        response.reset();
        response.setHeader("Content-Type", "application/pdf");
        
        OutputStream responseOutputStream = response.getOutputStream();
        
         // Read PDF contents
        URL url = new URL("Studiekostenformulier2");
        InputStream pdfInputStream = url.openStream();
         
        // Read PDF contents and write them to the output
        byte[] bytesBuffer = new byte[2048];
        int bytesRead;
        while ((bytesRead = pdfInputStream.read(bytesBuffer)) > 0) {
            responseOutputStream.write(bytesBuffer, 0, bytesRead);
        }
         
        // Make sure that everything is out
        responseOutputStream.flush();
          
        // Close both streams
        pdfInputStream.close();
        responseOutputStream.close();
         
        // JSF doc: 
        // Signal the JavaServer Faces implementation that the HTTP response for this request has already been generated 
        // (such as an HTTP redirect), and that the request processing lifecycle should be terminated
        // as soon as the current phase is c
        
    }

    public String getNameEmployer() {
        return nameEmployer;
    }

    public void setNameEmployer(String nameEmployer) {
        this.nameEmployer = nameEmployer;
    }

    public Date getDateService() {
        return dateService;
    }

    public void setDateService(Date dateService) {
        this.dateService = dateService;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    public String getStudyToFollow() {
        return studyToFollow;
    }

    public void setStudyToFollow(String studyToFollow) {
        this.studyToFollow = studyToFollow;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getCourseDays() {
        return courseDays;
    }

    public void setCourseDays(String courseDays) {
        this.courseDays = courseDays;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudyCostsToBeReimbursed() {
        return studyCostsToBeReimbursed;
    }

    public void setStudyCostsToBeReimbursed(String studyCostsToBeReimbursed) {
        this.studyCostsToBeReimbursed = studyCostsToBeReimbursed;
    }

    public String getExaminationFees() {
        return examinationFees;
    }

    public void setExaminationFees(String examinationFees) {
        this.examinationFees = examinationFees;
    }

    public String getTransportationCosts() {
        return transportationCosts;
    }

    public void setTransportationCosts(String transportationCosts) {
        this.transportationCosts = transportationCosts;
    }

    public String getAccommodationCosts() {
        return accommodationCosts;
    }

    public void setAccommodationCosts(String accommodationCosts) {
        this.accommodationCosts = accommodationCosts;
    }

    public String getOtherCosts() {
        return otherCosts;
    }

    public void setOtherCosts(String otherCosts) {
        this.otherCosts = otherCosts;
    }

    public String getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(String totalCosts) {
        this.totalCosts = totalCosts;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDeprecaitionPeriod() {
        return deprecaitionPeriod;
    }

    public void setDeprecaitionPeriod(String deprecaitionPeriod) {
        this.deprecaitionPeriod = deprecaitionPeriod;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
    
    
}

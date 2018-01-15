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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author kyle_
 */
@Named(value = "studiekostenformulier")
@RequestScoped
public class StudiekostenformulierBean {

    private String nameEmployee; // Naam werknemer  
    private Date dateService; // Datum in dienst 
    private String function; // Functie
    private String studyToFollow; // Te volgen studie
    private String institute; // Instelling / instituut
    private Date startDate; // Startdatum
    private Date endDate; // Einddatum
    private String courseDays; // Cursusdagen
    private String description; // Beschrijving
    private Double studyCostsToBeReimbursed1; // Te vergoeden studiekosten1
    private Double studyCostsToBeReimbursed2; // Te vergoeden studiekosten2
    private Double examinationFees; // Examengelden
    private Double transportationCosts; // Vervoerskosten
    private Double accommodationCosts; // Verblijfskosten
    private Double otherCosts; // Overige kosten
    private Double totalCosts; // Totaal excl. BTW
    private String category; // Categorie
    private String deprecationPeriod; // Afschrijvingsperiode
    private String manager; // Manager
    private StreamedContent file;

    private List<String> functions;
    private List<String> managers;

    private String newFileName;
    private PDPageContentStream content;
    private PDType1Font font;
    private final int fontSize;

    public StudiekostenformulierBean() {
        functions = new ArrayList<>();
        managers = new ArrayList<>();
        fillFunctionList();
        fillManagersList();
        newFileName = "Studiekostenformulier2";
        font = PDType1Font.HELVETICA;
        fontSize = 10;
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/Studiekostenformulier.pdf");
        file = new DefaultStreamedContent(stream, "image/pdf", "Studiekostenformulier.pdf");
    }

    /**
     * Creates a PDF file with all the values. If a value is not filled in it
     * will not be added to the PDF file.
     *
     * @return
     * @throws java.io.IOException
     */
    public void Create() throws IOException {

        // Gegevens van de werknemer
        addSmallText(0, nameEmployee, 380, 685); // Naam werknemer        
        addSmallText(0, dateService.toString(), 380, 665); // Datum in dienst      
        addSmallText(0, function, 380, 645); // Functie

        // Gegevens van de Studie
        addSmallText(0, studyToFollow, 380, 575); // Te volgen studie
        addSmallText(0, startDate.toString(), 380, 555); // Startdatum
        addSmallText(0, endDate.toString(), 380, 535); // Einddatum
        addSmallText(0, courseDays, 380, 515); // Cursusdagen
        addSmallText(0, description, 77, 457); // Beschrijving

        addSmallText(0, studyCostsToBeReimbursed2.toString(), 380, 325); // Te vergoeden studiekosten
        addSmallText(0, examinationFees.toString(), 380, 305); // Examengelden
        addSmallText(0, transportationCosts.toString(), 380, 285); // Vervoerskosten
        addSmallText(0, accommodationCosts.toString(), 380, 265); // Verblijfskosten
        addSmallText(0, otherCosts.toString(), 380, 245); // Overige kosten
        addSmallText(0, totalCosts.toString(), 380, 215); // Totaal excl. BTW

        addSmallText(1, category, 77, 723); // Categorie
        addSmallText(1, deprecationPeriod, 380, 631); // Afschrijvingsperiode
        addSmallText(1, manager, 380, 567); // Manager

    }

    /**
     * Adds text to the PDF file.
     *
     * @param pageIndex Pagenumber where the text will be displayed.
     * @param text Text that will be displayed on the PDF file
     * @param xPos X position on the PDF file
     * @param yPos Y position on the PDF file
     * @throws IOException
     */
    public void addSmallText(int pageIndex, String text, int xPos, int yPos) throws IOException {

        File newFile = new File("C:\\studiekostenformulier.pdf");
        if (!newFile.exists()) {
            newFile.createNewFile();
        }

        try (PDDocument document = PDDocument.load(new File(newFile.getPath()))) {
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
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void addBigText(int pageIndex, String text, int xPos, int yPos) throws IOException {
        //TODO: big text blocks.
    }

    /**
     * Download a pdf file of the form to the browser.
     *
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

    public StreamedContent getFile() {
        return file;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
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

    public List<String> getFunctions() {
        return this.functions;
    }

    public void setFunctions(List<String> functions) {
        this.functions = functions;
    }

    public String getStudyToFollow() {
        return studyToFollow;
    }

    public void setStudyToFollow(String studyToFollow) {
        this.studyToFollow = studyToFollow;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
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

    public Double getStudyCostsToBeReimbursed1() {
        return studyCostsToBeReimbursed1;
    }

    public void setStudyCostsToBeReimbursed1(Double studyCostsToBeReimbursed1) {
        this.studyCostsToBeReimbursed1 = studyCostsToBeReimbursed1;
    }

    public Double getStudyCostsToBeReimbursed2() {
        return studyCostsToBeReimbursed2;
    }

    public void setStudyCostsToBeReimbursed2(Double studyCostsToBeReimbursed2) {
        this.studyCostsToBeReimbursed2 = studyCostsToBeReimbursed2;
    }

    public Double getExaminationFees() {
        return examinationFees;
    }

    public void setExaminationFees(Double examinationFees) {
        this.examinationFees = examinationFees;
    }

    public Double getTransportationCosts() {
        return transportationCosts;
    }

    public void setTransportationCosts(Double transportationCosts) {
        this.transportationCosts = transportationCosts;
    }

    public Double getAccommodationCosts() {
        return accommodationCosts;
    }

    public void setAccommodationCosts(Double accommodationCosts) {
        this.accommodationCosts = accommodationCosts;
    }

    public Double getOtherCosts() {
        return otherCosts;
    }

    public void setOtherCosts(Double otherCosts) {
        this.otherCosts = otherCosts;
    }

    public Double getTotalCosts() {
        return totalCosts;
    }

    public void setTotalCosts(Double totalCosts) {
        this.totalCosts = totalCosts;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDeprecationPeriod() {
        return deprecationPeriod;
    }

    public void setDeprecationPeriod(String deprecaitionPeriod) {
        this.deprecationPeriod = deprecaitionPeriod;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public List<String> getManagers() {
        return managers;
    }

    public void setManagers(List<String> managers) {
        this.managers = managers;
    }

    private void fillFunctionList() {
        String functionList = "Adviseur Reward,"
                + "Ambtelijk Secretaris,"
                + "Assistent Controller,"
                + "BI Analyst C,"
                + "BI Engineer A,"
                + "BI Engineer B,"
                + "BI Engineer B spec. design,"
                + "BI Engineer C,"
                + "BI Engineer instroom,"
                + "Business Development Adviseur,"
                + "Business Unit Manager,"
                + "Cateringmedewerker,"
                + "Commercieel medewerker,"
                + "Commercieel Unit Manager,"
                + "Contractmanager,"
                + "Controller,"
                + "Coördinator Opleidingen & Marketing,"
                + "Coördinator Recruitering,"
                + "Coördinator Secretariaat,"
                + "Database Administrator A,"
                + "Database Administrator B,"
                + "Database Administrator C,"
                + "Database Administrator instroom,"
                + "Deployment medewerker,"
                + "Facilitair medewerker,"
                + "Functioneel Architect C,"
                + "Hoofd ICT Services,"
                + "Hoofd Kenniscentrum,"
                + "Human Development Adviseur,"
                + "Implementatie consultant / Productowner,"
                + "Implementatie consultant A,"
                + "Implementatie consultant B,"
                + "Implementatie consultant C,"
                + "Implementatie consultant instroom,"
                + "Information Analyst A,"
                + "Information Analyst B,"
                + "Information Analyst C,"
                + "Information Analyst instroom,"
                + "Infrastructure Engineer A,"
                + "Infrastructure Engineer B,"
                + "Infrastructure Engineer C,"
                + "Infrastructure Engineer instroom,"
                + "IT Architect B,"
                + "IT Architect B spec. BI,"
                + "IT Architect B spec. Data,"
                + "IT Architect B spec. Infrastructure,"
                + "IT Architect C,"
                + "IT Architect C spec. BI,"
                + "IT Program Manager,"
                + "Jurist/Directiesecretaris,"
                + "Learning Consultant,"
                + "Manager HRD,"
                + "Manager MITS,"
                + "Manager PDC,"
                + "Manager Professional Services,"
                + "Manager Sales Support & Marketing,"
                + "Manager Support,"
                + "Managing Consultant,"
                + "Marketeer,"
                + "Marketing assistent,"
                + "Marketing Manager A,"
                + "Marketing Manager B,"
                + "Medewerker Arbeidsmarktcommunicatie,"
                + "Medewerker Support A,"
                + "Medewerker Support B,"
                + "Medewerker Support C,"
                + "Medewerker Support instroom,"
                + "Office Manager,"
                + "Opleidingsadviseur,"
                + "Principal Architect,"
                + "Principal Architect spec. Technology Manager,"
                + "Project Manager / Information Analyst C,"
                + "Project Manager / IT Architect,"
                + "Project Manager A,"
                + "Project Manager B,"
                + "Project Manager C,"
                + "Project Manager instroom,"
                + "QA Manager,"
                + "Receptioniste,"
                + "Recruiter,"
                + "Requirements Engineer A,"
                + "Requirements Engineer B,"
                + "Requirements Engineer C,"
                + "Sales Consultant,"
                + "Sales Manager,"
                + "Secretaresse,"
                + "Senior HR Adviseur,"
                + "Senior medewerkster Human Recruitment Reward en Development,"
                + "Senior Recruiter,"
                + "Service Delivery Manager,"
                + "Software Engineer A,"
                + "Software Engineer A spec. Application Management,"
                + "Software Engineer B,"
                + "Software Engineer B spec. Application Management,"
                + "Software Engineer C,"
                + "Software Engineer C spec. Application Management,"
                + "Software Engineer instroom,"
                + "Technical Consultant,"
                + "Test Engineer A,"
                + "Test Engineer B,"
                + "Test Engineer C,"
                + "Test Engineer instroom,"
                + "Testmanager,"
                + "Trainer I,"
                + "Trainer II,"
                + "Trainer III";

        functions = Arrays.asList(functionList.split("\\s*,\\s*"));

    }

    private void fillManagersList() {
        String managerList = "Alex Roelvink,"
                + "Alex van Assem,"
                + "Arjan Hiemstra,"
                + "Arnold de Boer,"
                + "Bart Aarsen,"
                + "Bas Meerman,"
                + "Dennis Joosten,"
                + "Dennis Jungerius,"
                + "Gert Jan Timmerman,"
                + "Henk Brands,"
                + "Herman Gerdsen,"
                + "Ivo Diepstraten,"
                + "Maaike Lausberg,"
                + "Maarten Giezen,"
                + "Marco Braakman,"
                + "Mark Klabbers,"
                + "Mark van Mullem,"
                + "Martijn de Vries,"
                + "Melvin Anbeek,"
                + "Mieke Jakobs,"
                + "Mirjam Lemaire,"
                + "Olaf Winkel,"
                + "Oscar Lubbers,"
                + "Paul Borgeld,"
                + "Paul van Kuppevelt,"
                + "Pieter van Harten,"
                + "Pim Govers,"
                + "Rinie Bos,"
                + "Sjors Meekels,"
                + "Stijn Versteegen,"
                + "Tim Vermeulen,"
                + "Toon Jansen,"
                + "Vincent Lukassen,"
                + "Wiljag Denekamp,"
                + "Willem Jan Vastenholt,"
                + "William Maas,"
                + "Xander Buffart";

        managers = Arrays.asList(managerList.split("\\s*,\\s*"));
    }

}

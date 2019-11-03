
package org.coursecollector.esi.service;

import org.springframework.stereotype.Service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import java.awt.Image; 
import java.awt.image.*; 
import java.io.*; 
import org.springframework.web.multipart.MultipartFile;

/**
 * OCR Service that will permit to convert courses images into text and pdf
 * 
 * @author Solofo R.
 * 
 */
@Service
public class OcrService {
    
    /**
     * The OCR lib tha we will use in this service
     */
    private Tesseract ocr;
    
    public OcrService() throws IOException {
        this.ocr = new Tesseract();
        this.ocr.setDatapath(this.buildTessdataPath());
    }
    
    /**
     * Build the path to tessdata parent Directory
     * @return String
     */
    private String buildTessdataPath() throws IOException {
        String currentDirPath = new File( "." ).getCanonicalPath();
        return currentDirPath + File.separator
                + "src" + File.separator
                + "main" + File.separator
                + "java" + File.separator
                + "org" + File.separator
                + "coursecollector" + File.separator
                + "esi" + File.separator
                + "service" + File.separator
                + "tessdata";
    }
    
    /**
     * Convert MultipartFile to String
     * @param MultipartFile
     * @return String
     */
    public String multipartFileToString(MultipartFile file) throws IOException, TesseractException  {
        File convFile = this.multipartFileToFile(file);
        System.out.println("#### SUCCEED TO CONVERT MULTIPART FILE #####");
        return this.ocr.doOCR(convFile);
    }
    
    /**
     * Convert MultipartFile to File cause tesseract work only with File
     * @author David Landup
     * @param MultipartFile
     * @return File
     * @see <a href="https://stackabuse.com/tesseract-simple-java-optical-character-recognition/">Tesseract: Simple Java Optical Character Recognition</a>
     */
    private File multipartFileToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        convFile.createNewFile();
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

}

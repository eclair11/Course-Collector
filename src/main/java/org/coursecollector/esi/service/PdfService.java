
package org.coursecollector.esi.service;

import org.springframework.stereotype.Service;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import java.io.*;

/**
 * Service that will permit to convert text into pdf files
 * 
 * @author Solofo R.
 */
@Service
public class PdfService {

    /**
     * Convert text into pdf file we adapted code in @see <a href=
     * "https://www.roseindia.net/java/java-conversion/TextToPDF.shtml">convert text
     * to pdf</a>
     * 
     * @param String text to convert into pdf
     * @param String path to file parent directory
     * @param String name of the pdf file
     * @return void
     */
    public void textToPdf(String text, String filePath, String filename)
            throws FileNotFoundException, DocumentException {
        Document document = new Document(PageSize.A4, 36, 72, 108, 180);
        PdfWriter.getInstance(document, new FileOutputStream(filePath + filename + ".pdf"));
        document.open();
        document.add(new Paragraph(text));
        document.close();
    }

}

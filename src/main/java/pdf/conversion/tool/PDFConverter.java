package pdf.conversion.tool;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;


public class PDFConverter {
    private static Logger LOGGER = Logger.getLogger("InfoLogging");

    public static void main(String[] args) {
        try {
            PDFManager pdfManager = new PDFManager();
            String filePath = pdfManager.getFilePath();
            File folder = new File(filePath);

            File[] listOfFiles = folder.listFiles();
            File[] listOfPdf = pdfManager.isPDF(listOfFiles);

            for (File i : listOfPdf) {
                String fileName = i.getName();
                LOGGER.info(String.format("processing file %s", fileName));

                String pdfToText = pdfManager.pdfToText(filePath + fileName);
                pdfToText = pdfToText.replaceAll("\\s", " ");

                if (pdfToText.isEmpty()) {
                    LOGGER.info(String.format("PDF to Text Conversion of file %s failed.", fileName));
                } else {
                    LOGGER.info(String.format("The text parsed from the %s", fileName));
                    PDFManager.writeStringToFile(fileName, pdfToText);
                }
            }
            LOGGER.info(String.format("%s file(s) parsed to text", listOfPdf.length));
            System.exit(0);
        }
        catch (IOException e){
            System.out.println("Caught");
        }
    }
}

package pdf.conversion.tool;

import java.io.File;
import java.io.IOException;

public class PDFConverter {

    public static void main(String[] args) throws IOException {

        String path = System.getProperty("user.dir");
        path = path.substring(0, path.indexOf("pdfconverter"));
        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (int i = 0; i < listOfFiles.length; i++) {

            if (listOfFiles[i].isFile()) {
                files = listOfFiles[i].getName();
                if (files.endsWith(".pdf") || files.endsWith(".PDF")) {
                    System.out.println(String.format("processing file %s", files));
                    String nfiles = path;
                    PDFManager pdfManager = new PDFManager();
                    String pdfToText = pdfManager.pdftoText(nfiles + files);

                    if (pdfToText == null) {
                        System.err.println(String.format("PDF to Text Conversion of file %s failed.", files));
                    } else {
                        System.out.println(String.format("The text parsed from the %s", files));
                        PDFManager.writeStringToFile(files, pdfToText);
                    }
                }
            }
        }
        System.exit(0);
    }
}

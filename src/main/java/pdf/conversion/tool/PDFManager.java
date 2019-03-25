package pdf.conversion.tool;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

class PDFManager {

    public PDFManager(){}

    String toText(String filePath) throws IOException {

        PDDocument pdDoc = PDDocument.load(new File(filePath));

        PDFTextStripper pdfStripper = new PDFTextStripper();
        pdfStripper.setStartPage(1);
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());

        String text = pdfStripper.getText(pdDoc);
        pdDoc.close();
        return text;
    }

    String getFilePath(){
        String str = System.getProperty("user.dir");
        return str.substring(0, str.indexOf("pdf-converter"));
    }

    File[] isPDF(File[] listOfFiles) {
        return Arrays.stream(listOfFiles)
                .filter(file -> (file.getName().endsWith("pdf") || file.getName().endsWith("PDF")))
                .toArray(File[]::new);
    }

    String pdfToText(String path) throws IOException {
        return toText(path);
    }

    static void writeStringToFile(String files, String pdfToText) throws IOException {
        String output = System.getProperty("user.dir");
        output = output.substring(0, output.indexOf("pdf-converter"));
        new File(output + files + ".txt");

        String fileName = (files.contains(".pdf")) ? files.substring(0, files.indexOf(".pdf")) : files.substring(0, files.indexOf(".PDF"));
        // try with resource
        try (Writer fileWriter = new FileWriter(output + fileName + ".txt")) {
            fileWriter.write(pdfToText);
        }
    }
}

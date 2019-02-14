package pdf.conversion.tool;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class PDFManager {
    private PDFParser parser;
    private PDFTextStripper pdfStripper;
    private PDDocument pdDoc;
    private COSDocument cosDoc;
    private String Text;
    private String filePath;
    private File file;

    public PDFManager() {
    }

    public String ToText() throws IOException {
        this.pdfStripper = null;
        this.pdDoc = null;
        this.cosDoc = null;

        file = new File(filePath);
        parser = new PDFParser(new RandomAccessFile(file, "r")); // update for PDFBox V 2.0

        parser.parse();
        cosDoc = parser.getDocument();
        pdfStripper = new PDFTextStripper();
        pdDoc = new PDDocument(cosDoc);
        pdDoc.getNumberOfPages();
        pdfStripper.setStartPage(1);
        pdfStripper.setEndPage(pdDoc.getNumberOfPages());

        Text = pdfStripper.getText(pdDoc);
        return Text;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String pdftoText(String filePath) throws IOException {
        this.setFilePath(filePath);
        return ToText();
    }

    public static void writeStringToFile(String files, String pdfToText ) throws IOException {
        String output = System.getProperty("user.dir") + "/";
        File newFile = new File(output + files + ".txt");

        String fileName = (files.indexOf(".pdf") != -1) ?
                files.substring(0, files.indexOf(".pdf")) :
                files.substring(0, files.indexOf(".PDF"));

        Writer fileWriter = new FileWriter(output + fileName + ".txt");
        fileWriter.write(pdfToText);
        fileWriter.close();
    }
}

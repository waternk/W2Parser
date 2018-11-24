package w2parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Container for the contents of a CE-QUAL-W2 control file
 */
public class W2ControlFile {
    private String w2ControlFilename;
    private List<String> w2ControlList = new ArrayList<>();
    private File directoryPath;
    private File graphFile;

    public W2ControlFile(String infile) throws FileNotFoundException {
        w2ControlFilename = infile;
        directoryPath = new File(w2ControlFilename).getParentFile();
        graphFile = new File(directoryPath.toString(), "graph.npt");
        load(infile);
        handleCardNamesThatVary();
    }

    public File getDirectoryPath() { return directoryPath; }

    public String getW2ControlFilename() {
        return w2ControlFilename;
    }

    public String getGraphFilename() { return graphFile.toString(); }

    public String getLine(int i) {
        return w2ControlList.get(i);
    }

    public void setLine(int i, String line) {
        w2ControlList.set(i, line);
    }

    public int size() {
        return w2ControlList.size();
    }

    public void load(String infile) throws FileNotFoundException {
        File file = new File(infile);
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            w2ControlList.add(sc.nextLine());
        }
    }

    /**
     * Some card names may vary from one control file to another
     * Replace these with a specified card name
     */
    private void handleCardNamesThatVary() {
        for (int i = 0; i < w2ControlList.size(); i++) {
            String line = w2ControlList.get(i).toUpperCase();
            // Handle TSR Layer / TSR Depth card
            if (line.startsWith("TSR") && line.contains("ETSR")) {
                String newLine = "TSR LAYE" + line.substring(8,line.length());
                w2ControlList.set(i, newLine);
            }
        }

    }

    public void save(String outfile) {
        Path file = Paths.get(outfile);
        try {
            Files.write(file, w2ControlList, Charset.forName("UTF-8"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}

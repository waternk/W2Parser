package w2parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Graph File, to handle graph.npt
 */
public class GraphFile {
    List<Constituent> Constituents = new ArrayList<>();
    List<Constituent> DerivedConstituents = new ArrayList<>();

    public GraphFile(String infile) {
        File file = new File(infile);
        ArrayList<String> Lines = new ArrayList<>();
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNextLine()) {
            Lines.add(sc.nextLine());
        }

        boolean readCname = false;
        boolean readCDname = false;
        int columnNumber = 1;

        for (String line : Lines) {
            if (line.trim().equals("")) {
                readCname = false;
                readCDname = false;
                columnNumber = 1;
            }

            if (readCname || readCDname) {
                String nameAndUnits = line.substring(0,43).trim();
                String[] records = nameAndUnits.trim().split(",");
                String longName = records[0].trim();
                String shortName = longName;
                String units = "";
                if (records.length > 1) units = records[1].trim();
                Constituent c = new Constituent(shortName, longName, units, columnNumber);
                if (readCname) {
                    Constituents.add(c);
                }
                else if (readCDname) {
                    DerivedConstituents.add(c);
                }
                columnNumber++;
            }

            if (line.toUpperCase().contains("CNAME")) readCname = true ;
            if (line.toUpperCase().contains("CDNAME")) readCDname = true ;

        }

    }

    public List<Constituent> getConstituents() {
        return Constituents;
    }

    public List<Constituent> getDerivedConstituents() {
        return DerivedConstituents;
    }

}

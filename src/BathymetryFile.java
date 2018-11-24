package w2parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * w2parser.BathymetryFile supports reading data from -- or writing data to -- a CE-QUAL-W2 file
 */
public class BathymetryFile {

    private String bathymetryFilename;
    private List<String> bathymetryData = new ArrayList<>();
    private BathymetryRecord<Integer> Segments =
            new BathymetryRecord<>("Segments");
    private BathymetryRecord<Double> DLX = new BathymetryRecord<>("DLX");
    private BathymetryRecord<Double> ELWS = new BathymetryRecord<>("ELWS");
    private BathymetryRecord<Double> PHIO = new BathymetryRecord<>("PHIO");
    private BathymetryRecord<Double> FRICT = new BathymetryRecord<>("FRICT");
    private Globals.FILE_TYPE fileType;
    private int numSegments = 0;
    private int numLayers;
    private static final int fieldWidth = 8;
    private W2ControlFile w2con;

    public BathymetryFile(W2ControlFile w2con, String infile, int numLayers) throws FileNotFoundException {
        this.w2con = w2con;
        bathymetryFilename = infile;
        this.numLayers = numLayers;
        load(infile);
    }

    private <T> void updateRecordCsv(BathymetryRecord<T> record) {
        StringBuilder sb = new StringBuilder(record.getIdentifier() + ",");
        int lineNum;
        for (lineNum = 0; lineNum < bathymetryData.size(); lineNum++) {
            if (bathymetryData.get(lineNum).startsWith(record.getIdentifier()))
                break;
        }

        for (T value : record) {
            sb.append(value + ",");
        }
        String line = sb.toString();
        line = line.substring(0, line.length() - 1);
        bathymetryData.set(lineNum, line);
    }

    private <T> void updateRecordTxt(BathymetryRecord<T> record, int numRecords,
                                     String formatString) {
        int lineNum;
        for (lineNum = 0; lineNum < bathymetryData.size(); lineNum++) {
            if (bathymetryData.get(lineNum).startsWith(record.getIdentifier()))
                break;
        }

        bathymetryData.set(lineNum, record.getIdentifier());
        lineNum += 1;
        int recordNum = 1;
        StringBuilder sb = new StringBuilder();
        for (T value : record) {
            sb.append(String.format(formatString, value));
            // Write ten records per line
            if (recordNum % 10 == 0 || recordNum == numRecords) {
                bathymetryData.set(lineNum, sb.toString());
                sb = new StringBuilder();
                lineNum += 1;
            }
            recordNum++;
        }
    }

    /**
     * Get bathymetry filename
     * @return bathymetryFilename: bathymetry filename
     */
    public String getBathymetryFilename() {
        return bathymetryFilename;
    }

    /**
     * Set bathymetry filename
     * @param bathymetryFilename: bathymetry filename
     */
    public void setBathymetryFilename(String bathymetryFilename) {
        this.bathymetryFilename = bathymetryFilename;
    }

    /**
     * Create format string for double-type records using
     * the magnitude (characteristic) of the number to the left
     * of the decimal place.
     * @param record
     * @return
     */
    private String createFormatString(BathymetryRecord<Double> record) {
        int characteristic = record.getCharacteristic();
        int padding = 1;
        if (characteristic > 5) {
           padding = 0;
        }
        int precision = fieldWidth - record.getCharacteristic() - padding - 1;
        return "%" + String.format("%d.%df", fieldWidth, precision);
    }

    /**
     * Get segment numbers
     * @return Segments: list of segment numbers
     */
    public BathymetryRecord<Integer> getSegments() {
        return Segments;
    }

    /**
     * Set segment lengths
     * @param Segments: list of segment lengths
     */
    public void setSegments(BathymetryRecord<Integer> Segments) {
        if (fileType == Globals.FILE_TYPE.CSV) {
            updateRecordCsv(Segments);
        }
        else {
            String formatString = "%" + fieldWidth + "d";
            updateRecordTxt(Segments, numSegments, formatString);
        }
        this.Segments = Segments;
    }

    /**
     * Get segment lengths
     * @return DLX: list of segment lengths
     */
    public BathymetryRecord<Double> getDLX() {
        return DLX;
    }

    /**
     * Set segment lengths
     * @param DLX: list of segment lengths
     */
    public void setDLX(BathymetryRecord<Double> DLX) {
        if (fileType == Globals.FILE_TYPE.CSV) {
            updateRecordCsv(DLX);
        }
        else {
            String formatString = createFormatString(DLX);
            updateRecordTxt(DLX, numSegments, formatString);
        }
        this.DLX = DLX;
    }

    /**
     * Get water surface elevations
     * @return ELWS: list of water surface elevations
     */
    public BathymetryRecord<Double> getELWS() {
        return ELWS;
    }

    /**
     * Set water surface elevations
     * @param ELWS : w2parser.BathymetryRecord of water surface elevations
     */
    public void setELWS(BathymetryRecord<Double> ELWS) {
        if (fileType == Globals.FILE_TYPE.CSV) {
            updateRecordCsv(ELWS);
        }
        else {
            String formatString = createFormatString(ELWS);
            updateRecordTxt(ELWS, numSegments, formatString);
        }
        this.ELWS = ELWS;
    }

    /**
     * Get orientation angles
     * @return PHIO: orientation angles
     */
    public BathymetryRecord<Double> getPHIO() {
        return PHIO;
    }

    /**
     * Set orientation angles
     * @param PHIO: orientation angles
     */
    public void setPHIO(BathymetryRecord<Double> PHIO) {
        if (fileType == Globals.FILE_TYPE.CSV) {
            PHIO.setIdentifier("PHIO");
            updateRecordCsv(PHIO);
        }
        else {
            PHIO.setIdentifier("Angle");
            String formatString = createFormatString(PHIO);
            updateRecordTxt(PHIO, numSegments, formatString);
        }
        this.PHIO = PHIO;
    }

    /**
     * Get friction coefficients
     * @return FRICT: list of friction coefficients
     */
    public BathymetryRecord<Double> getFRICT() {
        return FRICT;
    }

    /**
     * Set friction coefficients
     * @param FRICT: list of friction coefficients
     */
    public void setFRICT(BathymetryRecord<Double> FRICT) {
        if (fileType == Globals.FILE_TYPE.CSV) {
            FRICT.setIdentifier("FRICT");
            updateRecordCsv(FRICT);
        }
        else {
            FRICT.setIdentifier("Friction");
            String formatString = createFormatString(FRICT);
            updateRecordTxt(FRICT, numSegments, formatString);
        }
        this.FRICT = FRICT;
    }

    /**
     * Load bathymetry from file into memory
     * @param infile
     * @throws FileNotFoundException
     */
    private void load(String infile) throws FileNotFoundException {
        File file = new File(w2con.getDirectoryPath().toString(), infile);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            bathymetryData.add(sc.nextLine());
        }
        if (bathymetryData.get(0).startsWith("$") ) {
            fileType = Globals.FILE_TYPE.CSV;
            // Number of segments will be computed later
            numSegments = 0;
        } else {
            fileType = Globals.FILE_TYPE.TXT;

            // Determine number of segments
            for (String line : bathymetryData) {
                if (line.toUpperCase().startsWith("WIDTH OF SEGMENT")) {
                    numSegments++;
                }
            }
        }

        int i = 0;
        while (i < bathymetryData.size()) {
            String line = bathymetryData.get(i);
            if (line.toUpperCase().startsWith("SEG")) {
                // Parse segment lengths (this applies to the new CSV format only)
                Segments = parseRecordCsvInteger(i);
            }
            else if (line.toUpperCase().startsWith("DLX")) {
                // Parse segment lengths
                if (fileType == Globals.FILE_TYPE.CSV) {
                    DLX = parseRecordCsvDouble(i);
                } else {
                    DLX = parseRecordTxtDouble(i, numSegments);
                }
            }
            else if (line.toUpperCase().startsWith("ELWS")) {
                // Parse water surface elevations
                if (fileType == Globals.FILE_TYPE.CSV) {
                    ELWS = parseRecordCsvDouble(i);
                } else {
                    ELWS = parseRecordTxtDouble(i, numSegments);
                }
            }
            else if (line.toUpperCase().startsWith("ANGLE") ||
            line.toUpperCase().startsWith("PHIO")) {
                // Parse segment angles
                if (fileType == Globals.FILE_TYPE.CSV) {
                    PHIO = parseRecordCsvDouble(i);
                } else {
                    PHIO = parseRecordTxtDouble(i, numSegments);
                }
            }
            else if (line.toUpperCase().startsWith("FRICT")) {
                // Parse friction coefficients
                if (fileType == Globals.FILE_TYPE.CSV) {
                    FRICT = parseRecordCsvDouble(i);
                } else {
                    FRICT = parseRecordTxtDouble(i, numSegments);
                }
            }
            else if (line.toUpperCase().startsWith("DZ")) {
                // Parse layer thicknesses????
                // This only applies to the old fixed-width format bathymetry file
            }
            else if (line.toUpperCase().startsWith("WIDTH OF SEGMENT") ||
            line.toUpperCase().startsWith("LAYER")) {
                // Parse segment widths
                // This is significantly different for each format
            }
            i++;
        }
    }

    /**
     * Save bathymetry to file
     * @param outfile
     */
    public void save(String outfile) {
        Path file = Paths.get(outfile);
        try {
            Files.write(file, bathymetryData, Charset.forName("UTF-8"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parse CSV record, double type
     */
    private BathymetryRecord<Double> parseRecordCsvDouble(int index) {
        String[] values = bathymetryData.get(index).trim().split(",");
        BathymetryRecord<Double> record = new BathymetryRecord<>("");
        record.setIdentifier(values[0]);
        for (int i = 1; i < values.length; i++) {
            record.add(Double.parseDouble(values[i]));
        }
        return record;
    }

    /**
     * Parse CSV record, integer type
     */
    private BathymetryRecord<Integer> parseRecordCsvInteger(int index) {
        String[] values = bathymetryData.get(index).trim().split(",");
        BathymetryRecord<Integer> record = new BathymetryRecord<>("");
        record.setIdentifier(values[0]);
        for (int i = 1; i < values.length; i++) {
            int value = Integer.parseInt(values[i].trim());
            record.add(value);
        }
        return record;
    }

    /**
     * Parse CSV record, double type
     */
    private BathymetryRecord<Double> parseRecordTxtDouble(int index, int numRecords) {
        BathymetryRecord<Double> record = new BathymetryRecord<>("");
        record.setIdentifier(bathymetryData.get(index).trim());

        // Compute number of lines to read
        int numLines = numRecords/10;
        if (numLines * 10 < numRecords) {
            numLines += 1;
        }

        index += 1;
        StringBuilder sb = new StringBuilder();
        for (int i = index; i < index + numLines; i++) {
            sb.append(bathymetryData.get(i));
        }

        for (int idx = 0; idx <= sb.length() - fieldWidth; idx += fieldWidth) {
            String str = sb.substring(idx, idx + fieldWidth);
            record.add(Double.parseDouble(str));
        }

        return record;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String line : bathymetryData) {
            sb.append(line).append("\n");
        }
        return sb.toString().trim();
    }

}

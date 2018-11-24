package w2parser;

import java.util.ArrayList;
import java.util.List;

public abstract class Card {
    W2ControlFile w2ControlFile;
    String cardName;
    String titleLine;
    // Number of record lines. For most cards, this equals one.
    // For file cards, this is the number of branches or water bodies.
    int numRecordLines;
    List<String> recordLines = new ArrayList<>();

    public Card(W2ControlFile w2ControlFile, String cardName, int numRecordLines) {
        this.w2ControlFile = w2ControlFile;
        this.cardName = cardName;
        this.numRecordLines = numRecordLines;
        getRecord();
    }

    public Card(W2ControlFile w2ControlFile, int numRecordLines) {
        this.w2ControlFile = w2ControlFile;
        this.numRecordLines = numRecordLines;
        getRecord();
        parseText();
    }

    public void setTitleLine(String titleLine) {
        this.titleLine = titleLine;
    }

    public String getTitleLine() {
        return this.titleLine;
    }

    /**
     * Retrieve card text from the w2parser.W2ControlFile list
     */
    public void getRecord() {
        String line;
        for (int i = 0; i < w2ControlFile.size(); i++) {
            // Only check 1st 8 characters
            String cardNameShort;
            if (cardName.length() > 8) {
                cardNameShort = cardName.substring(0,8);
            } else {
                cardNameShort = cardName;
            }

            line = w2ControlFile.getLine(i).toUpperCase();
            if (line.startsWith(cardNameShort)) {
                this.titleLine = w2ControlFile.getLine(i);
                for (int j = 0; j < numRecordLines; j++) {
                    line = w2ControlFile.getLine(i + j + 1);
                    recordLines.add(line);
                }
                break;
            }
        }
    }

    /**
     * Update card text in the w2parser.W2ControlFile list
     */
    public void updateRecord() {
        updateText();
        String line;
        for (int i = 0; i < w2ControlFile.size(); i++) {
            line = w2ControlFile.getLine(i).toUpperCase();
            if (line.startsWith(cardName)) {
                w2ControlFile.setLine(i, this.titleLine);
                for (int j = 0; j < numRecordLines; j++) {
                    w2ControlFile.setLine(i + j + 1, recordLines.get(j));
                }
            }
        }
    }

    @Override
    public String toString() {
        String str = titleLine + "\n";
        for (String line : recordLines) {
            str += line + "\n";
        }
       return str;
    }

    /**
     * Parse the W2 control file text to individual variables
     */
    public abstract void parseText();

    /**
     * Update the W2 control file text from the current variables
     */
    public abstract void updateText();

    /**
     * @param line
     * @return
     */

    /**
     *
     * Parse a line containing fields in fixed-width format
     * As of CE-QUAL-W2 version 4.1, the field width has always been eight characters
     * and have 10 fields. Most cards leave the first field blank.
     *
     * The most common usage is:
     * Fields = parseLine(line, 8, 2, 10)
     *
     * @param line
     * @param fieldWidth Field width in characters
     * @param startField First field to read (one-based)
     * @param endField Last field to read (one-based)
     * @return
     */
    public List<String> parseLine(String line, int fieldWidth, int startField, int endField) {
        List<String> Fields = new ArrayList<>();
        for (int j = (startField - 1); j < endField; j++) {
            int start = Math.min(j * fieldWidth, line.length());
            int end = Math.min(j * fieldWidth + fieldWidth, line.length());
            String field = line.substring(start, end);
            if (!field.equals(""))
                Fields.add(field.trim());
        }
        return Fields;
    }
}

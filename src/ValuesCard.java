package w2parser;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles cards containing a column of identifiers and a column of values
 * This is a more generalized version of the FileCard class and should replace it.
 */
public class ValuesCard extends Card {
    private List<String> identifiers; // Water body or branch names
    private List<String> values;

    public ValuesCard(W2ControlFile w2ControlFile, String cardName, int numRecordLines) {
        super(w2ControlFile, cardName, numRecordLines);
        parseText();
    }

    public List<String> getValues() {
        return values;
    }

    public void clearIdentifiersAndValues() {
        identifiers.clear();
        values.clear();
        updateText();
    }

    public void addIdentifierAndValue(String identifier, String value) {
        identifiers.add(identifier);
        values.add(value);
        updateText();
    }

    public String getIdentifier(int i) {
        return identifiers.get(i);
    }

    public String getValue(int i) {
        return values.get(i);
    }

    public void setIdentifierAndValue(int i, String identifier, String value) {
        identifiers.set(i, identifier);
        values.set(i, value);
        updateText();
    }

    @Override
    public void parseText() {
        String[] records = new String[2];
        String line;
        identifiers = new ArrayList<>();
        values = new ArrayList<>();
        for (int i = 0; i < numRecordLines; i++) {
            line = recordLines.get(i).trim();
            records[0] = line.substring(0, 7);
            records[1] = line.substring(8, line.length());
            identifiers.add(records[0].trim());
            values.add(records[1].trim());
        }
    }

    @Override
    public void updateText() {
        String str;
        for (int i = 0; i < numRecordLines; i++) {
            str = String.format("%-8s%s", identifiers.get(i), values.get(i));
            recordLines.set(i, str);
        }
    }

}

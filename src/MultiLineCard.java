package w2parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles multiple line cards
 */
public class MultiLineCard extends Card {
    private List<List<String>> Data;

    public MultiLineCard(W2ControlFile w2ControlFile, String cardName, int numRecordLines) {
        super(w2ControlFile, cardName, numRecordLines);
        parseText();
    }

    public List<List<String>> getData() {
        return Data;
    }

    public void setData(List<List<String>> data) {
        Data = data;
        updateText();
    }

    public void addData(List<String> data) {
        Data.add(data);
        updateText();
    }

    public String getValue(int row, int col) {
       return Data.get(row).get(col);
    }

    public void setValue(int row, int col, String value) {
        List<String> line = Data.get(row);
        line.set(col, value);
        Data.set(row, line);
        updateText();
    }

    @Override
    public void parseText() {
        Data = new ArrayList<>();
        List<String> lineData;
        for (int i = 0; i < numRecordLines; i++) {
            lineData = parseLine(recordLines.get(i), 8, 1, 10);
            Data.add(lineData);
        }
    }

    @Override
    public void updateText() {
        String line = "";
        for (int i = 0; i < numRecordLines; i++) {
            for (int j = 0; i < Data.size(); j++) {
                if (j == 0) {
                    line = String.format("%-8s", Data.get(j));
                } else {
                    line += String.format("%8s", Data.get(j));
                }
            }
            recordLines.set(i, line);
        }
    }

}

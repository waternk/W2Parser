package w2parser;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.ArrayList;
import java.util.List;

/**
 * Multiple Record Repeating Integer Card
 *
 * This card is used to read cards containing one or more records that span multiple lines each.
 */
public class MultiRecordRepeatingIntegerCard extends Card {
    List<String> Names; // Constituent names
    List<List<Integer>> Values; // Specifies which constituents are on or off (Values: ON or OFF)
    int numFields; // Total number of fields -- spread over multiple lines, e.g., number of branches
    int numRecords; // e.g. number of constituents
    int numLinesPerRecord;
    int numLinesPerCard;

    public MultiRecordRepeatingIntegerCard(W2ControlFile w2ControlFile, String cardName, int numRecords, int numFields) {
        super(w2ControlFile, cardName, ((int) (numRecords * Math.ceil(numFields/9.0))));
        this.numRecords = numRecords;
        this.numFields = numFields;
        this.numLinesPerRecord = (int) Math.ceil(numFields/9.0);
        this.numLinesPerCard = numLinesPerRecord * numRecords;
        parseText();
    }

    public List<String> getNames() {
        return Names;
    }

    public void setNames(List<String> Names) {
        this.Names = Names;
        updateText();
    }

    public List<List<Integer>> getValues() {
        return Values;
    }

    public void setValues(List<List<Integer>> values) {
        Values = values;
        updateText();
    }

    @Override
    public void parseText() {
        Names = new ArrayList<>();
        Values = new ArrayList<>();

        int lineNum = 0;
        for (int jr = 0; jr < numRecords; jr++) {
            List<Integer> recordData = new ArrayList<>();
            for (int jf = 0; jf < numLinesPerRecord; jf++) {
                List<String> Fields = parseLine(recordLines.get(lineNum), 8, 1, 10);
                for (int j = 1; j < Fields.size(); j++) {
                    recordData.add(Integer.parseInt(Fields.get(j)));
                }
                if (jf == 0) Names.add(Fields.get(0));
                lineNum++;
            }
            Values.add(recordData);
        }
    }

    @Override
    public void updateText() {
        // TODO Not Implemented
    }

}

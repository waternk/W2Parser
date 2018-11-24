package w2parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Number of Structures Card
 *
 * This card has one line per water body
 */
public class NumberStructuresCard extends Card {
    List<Integer> NSTR;     // Number of branch outlet structures
    List<String> DYNELEV;   // Use the dynamic centerline elevation for the structure? (ON, OFF, blank (equals OFF))
    List<String> Identifiers;
    String DYNELEV_default = Globals.OFF;
    int numBranches;

    public NumberStructuresCard(W2ControlFile w2ControlFile, int numBranches) {
        super(w2ControlFile, CardNames.NumberStructures, numBranches);
        this.numBranches = numBranches;
        parseText();
    }

    public List<Integer> getNSTR() {
        return NSTR;
    }

    public void setNSTR(List<Integer> NSTR) {
        this.NSTR = NSTR;
        updateText();
    }

    public List<String> getDYNELEV() {
        return DYNELEV;
    }

    public void setDYNELEV(List<String> DYNELEV) {
        this.DYNELEV = DYNELEV;
        updateText();
    }

    @Override
    public void parseText() {
        NSTR = new ArrayList<>();
        DYNELEV = new ArrayList<>();
        Identifiers = new ArrayList<>();

        for (int i = 0; i < numBranches; i++) {
            List<String> Fields = parseLine(recordLines.get(i), 8, 1, 10);
            Identifiers.add(Fields.get(0));
            NSTR.add(Integer.parseInt(Fields.get(1)));
            if (Fields.size() > 2) {
                DYNELEV.add(Fields.get(2));
            } else {
                DYNELEV.add(DYNELEV_default);
            }
        }
    }

    @Override
    public void updateText() {
        for (int i = 0; i < numBranches; i++) {
            String str = String.format("%-8s%8d%8s",
                    Identifiers.get(i), NSTR.get(i), DYNELEV.get(i));
            recordLines.set(i, str);
        }
    }
}

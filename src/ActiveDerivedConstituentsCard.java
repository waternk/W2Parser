package w2parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Active Derived Constituents Card
 *
 * This card contains the status of each constituent as active (ON) or inactive (OFF).
 * There is one line per constituent, with a value for each waterbody.
 */
public class ActiveDerivedConstituentsCard extends Card {
    List<String> constituentNames; // Constituent names
    List<List<String>> Data;     // State of each constituent (ON or OFF)
    int numConstituents;
    int numWaterbodies;

    public ActiveDerivedConstituentsCard(W2ControlFile w2ControlFile, int numConstituents, int numWaterbodies) {
        super(w2ControlFile, "CST DERI", numConstituents);
        this.numConstituents = numConstituents;
        this.numWaterbodies = numWaterbodies;
        parseText();
    }

    public List<String> getConstituentNames() {
        return constituentNames;
    }

    public void setConstituentNames(List<String> constituentNames) {
        this.constituentNames = constituentNames;
        updateText();
    }

    public List<List<String>> getData() {
        return Data;
    }

    public void setCAC(List<List<String>> Data) {
        this.Data = Data;
        updateText();
    }

    @Override
    public void parseText() {
        constituentNames = new ArrayList<>();
        Data = new ArrayList<>();
        for (int jwb = 0; jwb < numWaterbodies; jwb++) {
            Data.add(new ArrayList<>());
        }

        for (int jc = 0; jc < numConstituents; jc++) {
            List<String> Fields = parseLine(recordLines.get(jc), 8, 1, 10);
            constituentNames.add(Fields.get(0));
            for (int jwb = 0; jwb < numWaterbodies; jwb++) {
                int col = jwb + 1;
//                String str = Fields.get(col);
//                List<String> wb = Data.get(jwb);
//                wb.add(str);
                Data.get(jwb).add(Fields.get(col));
            }
        }
    }

    @Override
    public void updateText() {
        for (int i = 0; i < numConstituents; i++) {
            String str = String.format("%-8s", constituentNames.get(i));
            for (int j = 0; j < numWaterbodies; j++) {
                str += String.format("%%8s", Data.get(j).get(i));
                recordLines.set(i, str);
            }
        }
    }
}

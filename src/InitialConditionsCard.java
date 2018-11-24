package w2parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Initial Conditions Card
 *
 * This card has one line per water body
 */
public class InitialConditionsCard extends Card {
    List<Double> T2I;
    List<Double> ICEI;
    List<String> WTYPEC;
    List<String> GRIDC;
    List<String> Identifiers;
    int numWaterbodies;

    public InitialConditionsCard(W2ControlFile w2ControlFile, int numWaterbodies) {
        super(w2ControlFile, "INIT CND", numWaterbodies);
        this.numWaterbodies = numWaterbodies;
        parseText();
    }

    public List<Double> getT2I() {
        return T2I;
    }

    public void setT2I(List<Double> t2I) {
        T2I = t2I;
        updateText();
    }

    public List<Double> getICEI() {
        return ICEI;
    }

    public void setICEI(List<Double> ICEI) {
        this.ICEI = ICEI;
        updateText();
    }

    public List<String> getWTYPEC() {
        return WTYPEC;
    }

    public void setWTYPEC(List<String> WTYPEC) {
        this.WTYPEC = WTYPEC;
        updateText();
    }

    public List<String> getGRIDC() {
        return GRIDC;
    }

    public void setGRIDC(List<String> GRIDC) {
        this.GRIDC = GRIDC;
        updateText();
    }

    public int getNumWaterbodies() {
        return numWaterbodies;
    }

    public void setNumWaterbodies(int numWaterbodies) {
        this.numWaterbodies = numWaterbodies;
        updateText();
    }

    @Override
    public void parseText() {
        T2I = new ArrayList<>();
        ICEI = new ArrayList<>();
        WTYPEC = new ArrayList<>();
        GRIDC = new ArrayList<>();
        Identifiers = new ArrayList<>();

        for (int i = 0; i < numWaterbodies; i++) {
            List<String> Fields = parseLine(recordLines.get(i), 8, 1, 10);
            Identifiers.add(Fields.get(0));
            T2I.add(Double.parseDouble(Fields.get(1)));
            ICEI.add(Double.parseDouble(Fields.get(2)));
            WTYPEC.add(Fields.get(3));
            GRIDC.add(Fields.get(4));
        }
    }

    @Override
    public void updateText() {
        for (int i = 0; i < numWaterbodies; i++) {
            String str = String.format("%-8s%8.5f%8.5f%8s%8s",
                    Identifiers.get(i), T2I.get(i), ICEI.get(i), WTYPEC.get(i), GRIDC.get(i));
            recordLines.set(i, str);
        }
    }
}

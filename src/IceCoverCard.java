package w2parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Ice Cover Card
 *
 * This card has one line per water body
 */
public class IceCoverCard extends Card {
    List<String> ICEC;
    List<String> SLICEC;
    List<Double> ALBEDO;
    List<Double> HWICE;
    List<Double> BICE;
    List<Double> GICE;
    List<Double> ICEMIN;
    List<Double> ICET2;
    List<String> Identifiers;
    int numWaterbodies;

    public IceCoverCard(W2ControlFile w2ControlFile, int numWaterbodies) {
        super(w2ControlFile, "ICE COVE", numWaterbodies);
        this.numWaterbodies = numWaterbodies;
        parseText();
    }

    public List<String> getICEC() {
        return ICEC;
    }

    public void setICEC(List<String> ICEC) {
        this.ICEC = ICEC;
        updateText();
    }

    public List<String> getSLICEC() {
        return SLICEC;
    }

    public void setSLICEC(List<String> SLICEC) {
        this.SLICEC = SLICEC;
        updateText();
    }

    public List<Double> getALBEDO() {
        return ALBEDO;
    }

    public void setALBEDO(List<Double> ALBEDO) {
        this.ALBEDO = ALBEDO;
        updateText();
    }

    public List<Double> getHWICE() {
        return HWICE;
    }

    public void setHWICE(List<Double> HWICE) {
        this.HWICE = HWICE;
        updateText();
    }

    public List<Double> getBICE() {
        return BICE;
    }

    public void setBICE(List<Double> BICE) {
        this.BICE = BICE;
        updateText();
    }

    public List<Double> getGICE() {
        return GICE;
    }

    public void setGICE(List<Double> GICE) {
        this.GICE = GICE;
        updateText();
    }

    public List<Double> getICEMIN() {
        return ICEMIN;
    }

    public void setICEMIN(List<Double> ICEMIN) {
        this.ICEMIN = ICEMIN;
        updateText();
    }

    public List<Double> getICET2() {
        return ICET2;
    }

    public void setICET2(List<Double> ICET2) {
        this.ICET2 = ICET2;
        updateText();
    }

    @Override
    public void parseText() {
        ICEC = new ArrayList<>();
        SLICEC = new ArrayList<>();
        ALBEDO = new ArrayList<>();
        HWICE = new ArrayList<>();
        BICE = new ArrayList<>();
        GICE = new ArrayList<>();
        ICEMIN = new ArrayList<>();
        ICET2 = new ArrayList<>();
        Identifiers = new ArrayList<>();

        for (int i = 0; i < numWaterbodies; i++) {
            List<String> Fields = parseLine(recordLines.get(i), 8, 1, 10);
            Identifiers.add(Fields.get(0));
            ICEC.add(Fields.get(1));
            SLICEC.add(Fields.get(2));
            ALBEDO.add(Double.parseDouble(Fields.get(3)));
            HWICE.add(Double.parseDouble(Fields.get(4)));
            BICE.add(Double.parseDouble(Fields.get(5)));
            GICE.add(Double.parseDouble(Fields.get(6)));
            ICEMIN.add(Double.parseDouble(Fields.get(7)));
            ICET2.add(Double.parseDouble(Fields.get(8)));
        }
    }

    @Override
    public void updateText() {
        for (int i = 0; i < numWaterbodies; i++) {
            String str = String.format("%-8s%8s%8s%8.5f%8.5f%8.5f%8.5f%8.5f%8.5f",
                    Identifiers.get(i), ICEC.get(i), SLICEC.get(i), ALBEDO.get(i),
                    HWICE.get(i), BICE.get(i), GICE.get(i), ICEMIN.get(i), ICET2.get(i));
            recordLines.set(i, str);
        }
    }
}

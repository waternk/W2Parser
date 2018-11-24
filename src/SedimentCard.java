package w2parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Sediment Card
 *
 * This card has one line per water body
 */
public class SedimentCard extends Card {
    List<String> SEDC;
    List<String> SEDPRC;
    List<Double> SEDCI;
    List<Double> SEDS;
    List<Double> SEDK;
    List<Double> FSOD;
    List<Double> FSED;
    List<Double> SEDBR;
    List<String> DYNSEDK;
    List<String> Identifiers;
    int numWaterbodies;

    public SedimentCard(W2ControlFile w2ControlFile, int numWaterbodies) {
        super(w2ControlFile, "SEDIMENT", numWaterbodies);
        this.numWaterbodies = numWaterbodies;
        parseText();
    }

    public List<String> getSEDC() {
        return SEDC;
    }

    public void setSEDC(List<String> SEDC) {
        this.SEDC = SEDC;
        updateText();
    }

    public List<String> getSEDPRC() {
        return SEDPRC;
    }

    public void setSEDPRC(List<String> SEDPRC) {
        this.SEDPRC = SEDPRC;
        updateText();
    }

    public List<Double> getSEDCI() {
        return SEDCI;
    }

    public void setSEDCI(List<Double> SEDCI) {
        this.SEDCI = SEDCI;
        updateText();
    }

    public List<Double> getSEDS() {
        return SEDS;
    }

    public void setSEDS(List<Double> SEDS) {
        this.SEDS = SEDS;
        updateText();
    }

    public List<Double> getSEDK() {
        return SEDK;
    }

    public void setSEDK(List<Double> SEDK) {
        this.SEDK = SEDK;
        updateText();
    }

    public List<Double> getFSOD() {
        return FSOD;
    }

    public void setFSOD(List<Double> FSOD) {
        this.FSOD = FSOD;
        updateText();
    }

    public List<Double> getFSED() {
        return FSED;
    }

    public void setFSED(List<Double> FSED) {
        this.FSED = FSED;
        updateText();
    }

    public List<Double> getSEDBR() {
        return SEDBR;
    }

    public void setSEDBR(List<Double> SEDBR) {
        this.SEDBR = SEDBR;
        updateText();
    }

    public List<String> getDYNSEDK() {
        return DYNSEDK;
    }

    public void setDYNSEDK(List<String> DYNSEDK) {
        this.DYNSEDK = DYNSEDK;
        updateText();
    }

    @Override
    public void parseText() {
        SEDC = new ArrayList<>();
        SEDPRC = new ArrayList<>();
        SEDCI = new ArrayList<>();
        SEDS = new ArrayList<>();
        SEDK = new ArrayList<>();
        FSOD = new ArrayList<>();
        FSED = new ArrayList<>();
        SEDBR = new ArrayList<>();
        DYNSEDK = new ArrayList<>();
        Identifiers = new ArrayList<>();

        for (int i = 0; i < numWaterbodies; i++) {
            List<String> Fields = parseLine(recordLines.get(i), 8, 1, 10);
            Identifiers.add(Fields.get(0));
            SEDC.add(Fields.get(1));
            SEDPRC.add(Fields.get(2));
            SEDCI.add(Double.parseDouble(Fields.get(3)));
            SEDS.add(Double.parseDouble(Fields.get(4)));
            SEDK.add(Double.parseDouble(Fields.get(5)));
            FSOD.add(Double.parseDouble(Fields.get(6)));
            FSED.add(Double.parseDouble(Fields.get(7)));
            SEDBR.add(Double.parseDouble(Fields.get(8)));
            DYNSEDK.add(Fields.get(9));
        }
    }

    @Override
    public void updateText() {
        for (int i = 0; i < numWaterbodies; i++) {
            String str = String.format("%-8s%8s%8s%8.5f%8.5f%8.5f%8.5f%8.5f%8.5f",
                    Identifiers.get(i), SEDC.get(i), SEDPRC.get(i), SEDCI.get(i),
                    SEDS.get(i), SEDK.get(i), FSOD.get(i), FSED.get(i), SEDBR.get(i),
                    DYNSEDK.get(i));
            recordLines.set(i, str);
        }
    }
}

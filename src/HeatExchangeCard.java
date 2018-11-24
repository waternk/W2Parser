package w2parser;

import java.util.ArrayList;
import java.util.List;

public class HeatExchangeCard extends Card {
    int numWaterbodies;
    List<String> SLHTC;
    List<String> SROC;
    List<String> RHEVAP;
    List<String> METIC;
    List<String> FETCHC;
    List<Double> AFW;
    List<Double> BFW;
    List<Double> CFW;
    List<Double> WINDH;
    List<String> Identifiers;

    public HeatExchangeCard(W2ControlFile w2ControlFile, int numWaterbodies) {
        super(w2ControlFile, "HEAT EXCH", numWaterbodies);
        this.numWaterbodies = numWaterbodies;
        parseText();
    }

    public List<String> getSLHTC() {
        return SLHTC;
    }

    public void setSLHTC(List<String> SLHTC) {
        this.SLHTC = SLHTC;
        updateText();
    }

    public List<String> getSROC() {
        return SROC;
    }

    public void setSROC(List<String> SROC) {
        this.SROC = SROC;
        updateText();
    }

    public List<String> getRHEVAP() {
        return RHEVAP;
    }

    public void setRHEVAP(List<String> RHEVAP) {
        this.RHEVAP = RHEVAP;
        updateText();
    }

    public List<String> getMETIC() {
        return METIC;
    }

    public void setMETIC(List<String> METIC) {
        this.METIC = METIC;
        updateText();
    }

    public List<String> getFETCHC() {
        return FETCHC;
    }

    public void setFETCHC(List<String> FETCHC) {
        this.FETCHC = FETCHC;
        updateText();
    }

    public List<Double> getAFW() {
        return AFW;
    }

    public void setAFW(List<Double> AFW) {
        this.AFW = AFW;
        updateText();
    }

    public List<Double> getBFW() {
        return BFW;
    }

    public void setBFW(List<Double> BFW) {
        this.BFW = BFW;
        updateText();
    }

    public List<Double> getCFW() {
        return CFW;
    }

    public void setCFW(List<Double> CFW) {
        this.CFW = CFW;
        updateText();
    }

    public List<Double> getWINDH() {
        return WINDH;
    }

    public void setWINDH(List<Double> WINDH) {
        this.WINDH = WINDH;
        updateText();
    }

    @Override
    public void parseText() {
        SLHTC = new ArrayList<>();
        SROC = new ArrayList<>();
        RHEVAP = new ArrayList<>();
        METIC = new ArrayList<>();
        FETCHC = new ArrayList<>();
        AFW = new ArrayList<>();
        BFW = new ArrayList<>();
        CFW = new ArrayList<>();
        WINDH = new ArrayList<>();
        Identifiers = new ArrayList<>();

        for (int i = 0; i < numRecordLines; i++) {
            List<String> Fields = parseLine(recordLines.get(i), 8, 1, 10);
            Identifiers.add(Fields.get(0));
            SLHTC.add(Fields.get(1));
            SROC.add(Fields.get(2));
            RHEVAP.add(Fields.get(3));
            METIC.add(Fields.get(4));
            FETCHC.add(Fields.get(5));
            AFW.add(Double.parseDouble(Fields.get(6)));
            BFW.add(Double.parseDouble(Fields.get(7)));
            CFW.add(Double.parseDouble(Fields.get(8)));
            WINDH.add(Double.parseDouble(Fields.get(9)));
        }
    }

    @Override
    public void updateText() {
        for (int i = 0; i < numRecordLines; i++) {
            String str = String.format("%-8s%8s%8s%8s%8s%8s%8.5f%8.5f%8.5f%8.5f",
                    Identifiers.get(i), SLHTC.get(i), SROC.get(i), RHEVAP.get(i), METIC.get(i),
                    FETCHC.get(i), AFW.get(i), BFW.get(i), CFW.get(i), WINDH.get(i));
            recordLines.set(i, str);
        }
    }
}


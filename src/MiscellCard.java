package w2parser;

import java.util.List;

/**
 * Miscellaneous Card
 */
public class MiscellCard extends Card {
    private int nday;
    private String selectc;
    private String habtatc;
    private String envirpc;
    private String aeratec;
    private String inituwl;

    public MiscellCard(W2ControlFile w2ControlFile) {
        super(w2ControlFile, "MISCELL", 1);
        parseText();
    }

    public int getNday() {
        return nday;
    }

    public void setNday(int nday) {
        this.nday = nday;
        updateText();
    }

    public String getSelectc() {
        return selectc;
    }

    public void setSelectc(String selectc) {
        this.selectc = selectc.toUpperCase();
        updateText();
    }

    public String getEnvirpc() {
        return envirpc;
    }

    public void setEnvirpc(String envirpc) {
        this.envirpc = envirpc.toUpperCase();
        updateText();
    }

    public String getAeratec() {
        return aeratec;
    }

    public void setAeratec(String aeratec) {
        this.aeratec = aeratec.toUpperCase();
        updateText();
    }

    public String getInituwl() {
        return inituwl;
    }

    public void setInituwl(String inituwl) {
        this.inituwl = inituwl.toUpperCase();
        updateText();
    }

    public String getHabtatc() {
        return habtatc;
    }

    public void setHabtatc(String habtatc) {
        this.habtatc = habtatc.toUpperCase();
        updateText();
    }

    @Override
    public void parseText() {
        List<String> Fields = parseLine(recordLines.get(0), 8, 2, 10);
        nday = Integer.parseInt(Fields.get(0));
        selectc = Fields.get(1);
        habtatc = Fields.get(2);
        envirpc = Fields.get(3);
        aeratec = Fields.get(4);
        inituwl = Fields.get(5);
    }

    @Override
    public void updateText() {
        String str = String.format("%8s%8d%8s%8s%8s%8s%8s", "",
                nday, selectc, habtatc, envirpc, aeratec, inituwl);
        recordLines.set(0, str);
    }
}


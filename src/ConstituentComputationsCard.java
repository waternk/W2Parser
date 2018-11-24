package w2parser;

import java.util.List;

/**
 * Constituent Computations Card
 */
public class ConstituentComputationsCard extends Card {
    private String ccc;
    private String limc;
    private int cuf;

    public ConstituentComputationsCard(W2ControlFile w2ControlFile) {
        super(w2ControlFile, CardNames.ConstituentComputations, 1);
        parseText();
    }

    public String getCCC() {
        return ccc;
    }

    public void setCCC(String ccc) {
        this.ccc = ccc;
        updateText();
    }

    public String getLIMC() {
        return limc;
    }

    public void setLIMC(String limc) {
        this.limc = limc;
        updateText();
    }

    public int getCUF() {
        return cuf;
    }

    public void setCUF(int cuf) {
        this.cuf = cuf;
        updateText();
    }

    @Override
    public void parseText() {
        List<String> Fields = parseLine(recordLines.get(0), 8, 2, 10);
        ccc = Fields.get(0);
        limc = Fields.get(1);
        cuf = Integer.parseInt(Fields.get(2));
    }

    @Override
    public void updateText() {
        String str = String.format("%8s%8s%8s%8d",
                "", ccc, limc, cuf);
        recordLines.set(0, str);
    }
}



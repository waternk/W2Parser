package w2parser;

import java.util.List;

/**
 * Time Series Record (TSR) Plot Card
 */
public class TsrPlotCard extends Card {
    private String tsrc;
    private int ntsr;
    private int nitsr;

    public TsrPlotCard(W2ControlFile w2ControlFile) {
        super(w2ControlFile, "TSR PLOT", 1);
        parseText();
    }

    public String getTSRC() {
        return tsrc;
    }

    public void setTSRC(String tsrc) {
        this.tsrc = tsrc;
        updateText();
    }

    public int getNTSR() {
        return ntsr;
    }

    public void setNTSR(int ntsr) {
        this.ntsr = ntsr;
        updateText();
    }

    public int getNITSR() {
        return nitsr;
    }

    public void setNITSR(int nitsr) {
        this.nitsr = nitsr;
        updateText();
    }

    @Override
    public void parseText() {
        List<String> Fields = parseLine(recordLines.get(0), 8, 2, 10);
        tsrc = Fields.get(0);
        ntsr = Integer.parseInt(Fields.get(1));
        nitsr = Integer.parseInt(Fields.get(2));
    }

    @Override
    public void updateText() {
        String str = String.format("%8s%8s%8d%8d",
                "", tsrc, ntsr, nitsr);
        recordLines.set(0, str);
    }
}

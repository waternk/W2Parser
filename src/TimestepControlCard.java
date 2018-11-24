package w2parser;

import java.util.List;

/**
 * Timestep Control Card
 */
public class TimestepControlCard extends Card {
    private int ndt;
    private double dltMin;
    private String dltIntr;

    public TimestepControlCard(W2ControlFile w2ControlFile) {
        super(w2ControlFile, CardNames.TimestepControl, 1);
        parseText();
    }

    public int getNdt() {
        return ndt;
    }

    public void setNdt(int ndt) {
        this.ndt = ndt;
        updateText();
    }

    public double getDltMin() {
        return dltMin;
    }

    public void setDltMin(double dltMin) {
        this.dltMin = dltMin;
        updateText();
    }

    public String getDltIntr() {
        return dltIntr;
    }

    public void setDltIntr(String dltIntr) {
        this.dltIntr = dltIntr.toUpperCase();
        updateText();
    }

    @Override
    public void parseText() {
//        String[] records = recordLines.get(0).trim().split("\\s+");
        List<String> Fields = parseLine(recordLines.get(0), 8, 2, 10);
        ndt = Integer.parseInt(Fields.get(0));
        dltMin = Double.parseDouble(Fields.get(1));
        dltIntr = Fields.get(2);
    }

    @Override
    public void updateText() {
        String str = String.format("%8s%8d%8.5f%8s", "",
                ndt, dltMin, dltIntr);
        recordLines.set(0, str);
    }
}


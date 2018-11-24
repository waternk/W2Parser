package w2parser;

import java.util.List;

/**
 * Time Control Card
 */
public class TimeControlCard extends Card {
    private double jdayMin;
    private double jdayMax;
    private int startYear;

    public TimeControlCard(W2ControlFile w2ControlFile) {
        super(w2ControlFile, "TIME CON", 1);
        parseText();
    }

    public double getJdayMin() {
        return jdayMin;
    }

    public void setJdayMin(double jdayMin) {
        this.jdayMin = jdayMin;
        updateText();
    }

    public double getJdayMax() {
        return jdayMax;
    }

    public void setJdayMax(double jdayMax) {
        this.jdayMax = jdayMax;
        updateText();
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
        updateText();
    }

    @Override
    public void parseText() {
        List<String> Fields = parseLine(recordLines.get(0), 8, 2, 10);
        jdayMin = Double.parseDouble(Fields.get(0));
        jdayMax = Double.parseDouble(Fields.get(1));
        startYear = Integer.parseInt(Fields.get(2));
    }

    @Override
    public void updateText() {
        String str = String.format("%8s%8.3f%8.3f%8d", "",
                jdayMin, jdayMax, startYear);
        recordLines.set(0, str);
    }
}


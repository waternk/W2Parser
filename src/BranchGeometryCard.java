package w2parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Branch Geometry Card
 */
public class BranchGeometryCard extends Card {
    List<Integer> US;         // Branch upstream segment
    List<Integer> DS;         // Branch downstream segment
    List<Integer> UHS;        // Upstream boundary condition
    List<Integer> DHS;        // Downstream boundary condition
    List<Integer> UQB;        // Upstream internal flow boundary condition - IGNORE
    List<Integer> DQB;        // Downstream internal flow boundary condition - IGNORE
    List<Integer> NLMIN;      // Minimum number of layers for a segment to be active
    List<Double> Slope;       // Branch bottom slope (actual)
    List<Double> SlopeC;      // Hydraulic equivalent branch slope
    List<String> Identifiers; // Identifiers

    public BranchGeometryCard(W2ControlFile w2ControlFile, int numBranches) {
        super(w2ControlFile, "BRANCH G", numBranches);
        parseText();
    }

    public List<Integer> getUS() {
        return US;
    }

    public void setUS(List<Integer> US) {
        this.US = US;
        updateText();
    }

    public List<Integer> getDS() {
        return DS;
    }

    public void setDS(List<Integer> DS) {
        this.DS = DS;
        updateText();
    }

    public List<Integer> getUHS() {
        return UHS;
    }

    public void setUHS(List<Integer> UHS) {
        this.UHS = UHS;
        updateText();
    }

    public List<Integer> getDHS() {
        return DHS;
    }

    public void setDHS(List<Integer> DHS) {
        this.DHS = DHS;
        updateText();
    }

    public List<Integer> getUQB() {
        return UQB;
    }

    public void setUQB(List<Integer> UQB) {
        this.UQB = UQB;
        updateText();
    }

    public List<Integer> getDQB() {
        return DQB;
    }

    public void setDQB(List<Integer> DQB) {
        this.DQB = DQB;
        updateText();
    }

    public List<Integer> getNLMIN() {
        return NLMIN;
    }

    public void setNLMIN(List<Integer> NLMIN) {
        this.NLMIN = NLMIN;
        updateText();
    }

    public List<Double> getSlope() {
        return Slope;
    }

    public void setSlope(List<Double> Slope) {
        this.Slope = Slope;
        updateText();
    }

    public List<Double> getSlopeC() {
        return SlopeC;
    }

    public void setSlopeC(List<Double> SlopeC) {
        this.SlopeC = SlopeC;
        updateText();
    }

    @Override
    public void parseText() {
        US = new ArrayList<>();
        DS = new ArrayList<>();
        UHS = new ArrayList<>();
        DHS = new ArrayList<>();
        UQB = new ArrayList<>();
        DQB = new ArrayList<>();
        NLMIN = new ArrayList<>();
        Slope = new ArrayList<>();
        SlopeC = new ArrayList<>();
        Identifiers = new ArrayList<>();

        for (int i = 0; i < numRecordLines; i++) {
            List<String> records = parseLine(recordLines.get(i), 8, 1, 10);
            Identifiers.add(records.get(0));
            US.add(Integer.parseInt(records.get(1)));
            DS.add(Integer.parseInt(records.get(2)));
            UHS.add(Integer.parseInt(records.get(3)));
            DHS.add(Integer.parseInt(records.get(4)));
            UQB.add(Integer.parseInt(records.get(5)));
            DQB.add(Integer.parseInt(records.get(6)));
            NLMIN.add(Integer.parseInt(records.get(7)));
            Slope.add(Double.parseDouble(records.get(8)));
            SlopeC.add(Double.parseDouble(records.get(9)));
        }
    }

    @Override
    public void updateText() {
        for (int i = 0; i < numRecordLines; i++) {
            String str = String.format("%-8s%8d%8d%8d%8d%8d%8d%8d%8.5f%8.5f",
                    Identifiers.get(i), US.get(i), DS.get(i), UHS.get(i), DHS.get(i), UQB.get(i),
                    DQB.get(i), NLMIN.get(i), Slope.get(i), SlopeC.get(i));
            recordLines.set(i, str);
        }
    }
}


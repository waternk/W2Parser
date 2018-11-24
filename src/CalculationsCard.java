package w2parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Calculations Card
 *
 * This card has one line per water body
 */
public class CalculationsCard extends Card {
    List<String> VBC; // Volume balance calculation, ON or OFF
    List<String> EBC; // Thermal energy balance calculation, ON or OFF
    List<String> MBC; // Mass balance calculation, ON or OFF
    List<String> PQC; // Density placed inflows, ON or OFF
    List<String> EVC; // Evaporation included in water budget, ON or OFF
    List<String> PRC; // Precipitation included, ON or OFF
    List<String> Identifiers;

    public CalculationsCard(W2ControlFile w2ControlFile, int numRecordLines) {
        super(w2ControlFile, CardNames.Calculations, numRecordLines);
        parseText();
    }

    public List<String> getVBC() { return VBC; }

    public void setVBC(List<String> VBC) {
        this.VBC = VBC;
        updateText();
    }

    public List<String> getEBC() { return EBC; }

    public void setEBC(List<String> EBC) {
        this.EBC = EBC;
        updateText();
    }

    public List<String> getMBC() { return MBC; }

    public void setMBC(List<String> MBC) {
        this.MBC = MBC;
        updateText();
    }

    public List<String> getPQC() { return PQC; }

    public void setPQC(List<String> PQC) {
        this.PQC = PQC;
        updateText();
    }

    public List<String> getEVC() { return EVC; }

    public void setEVC(List<String> EVC) {
        this.EVC = EVC;
        updateText();
    }

    public List<String> getPRC() { return PRC; }

    public void setPRC(List<String> PRC) {
        this.PRC = PRC;
        updateText();
    }

    @Override
    public void parseText() {
        VBC = new ArrayList<>();
        EBC = new ArrayList<>();
        MBC = new ArrayList<>();
        PQC = new ArrayList<>();
        EVC = new ArrayList<>();
        PRC = new ArrayList<>();
        Identifiers = new ArrayList<>();

        for (int i = 0; i < numRecordLines; i++) {
            List<String> Fields = parseLine(recordLines.get(i), 8, 1, 10);
            Identifiers.add(Fields.get(0));
            VBC.add(Fields.get(1));
            EBC.add(Fields.get(2));
            MBC.add(Fields.get(3));
            PQC.add(Fields.get(4));
            EVC.add(Fields.get(5));
            PRC.add(Fields.get(6));
        }
    }

    @Override
    public void updateText() {
        for (int i = 0; i < numRecordLines; i++) {
            String str = String.format("%-8s%8s%8s%8s%8s%8s%8s",
                    Identifiers.get(i), VBC.get(i), EBC.get(i), MBC.get(i), PQC.get(i), EVC.get(i), PRC.get(i));
            recordLines.set(i, str);
        }
    }
}



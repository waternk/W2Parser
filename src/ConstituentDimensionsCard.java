package w2parser;

import java.util.List;

/**
 * Constituent Dimensions Card
 */
public class ConstituentDimensionsCard extends Card {
    private int ngc;  // Number of generic constituents
    private int nss;  // Number of inorganic suspended solids
    private int nal;  // Number of algal groups
    private int nep;  // Number of epiphyton/periphyton groups
    private int nbod; // Number of CBOD groups
    private int nmc;  // Number of macrophyte groups
    private int nzp;  // Number of zooplankton groups

    public ConstituentDimensionsCard(W2ControlFile w2ControlFile) {
        super(w2ControlFile, "CONSTITU", 1);
        parseText();
    }

    public int getNGC() {
        return ngc;
    }

    public void setNGC(int ngc) {
        this.ngc = ngc;
        updateText();
    }

    public int getNSS() {
        return nss;
    }

    public void setNSS(int nss) {
        this.nss = nss;
        updateText();
    }

    public int getNAL() {
        return nal;
    }

    public void setNAL(int nal) {
        this.nal = nal;
        updateText();
    }

    public int getNEP() {
        return nep;
    }

    public void setNEP(int nep) {
        this.nep = nep;
        updateText();
    }

    public int getNBOD() {
        return nbod;
    }

    public void setNBOD(int nbod) {
        this.nbod = nbod;
        updateText();
    }

    public int getNMC() {
        return nmc;
    }

    public void setNMC(int nmc) {
        this.nmc = nmc;
        updateText();
    }

    public int getNZP() {
        return nzp;
    }

    public void setNZP(int nzp) {
        this.nzp = nzp;
        updateText();
    }

    @Override
    public void parseText() {
        List<String> Fields = parseLine(recordLines.get(0), 8, 2, 10);
        ngc = Integer.parseInt(Fields.get(0));
        nss = Integer.parseInt(Fields.get(1));
        nal = Integer.parseInt(Fields.get(2));
        nep = Integer.parseInt(Fields.get(3));
        nbod = Integer.parseInt(Fields.get(4));
        nmc = Integer.parseInt(Fields.get(5));
        nzp = Integer.parseInt(Fields.get(6));
    }

    @Override
    public void updateText() {
        String str = String.format("%8s%8d%8d%8d%8d%8d%8d%8d",
                "", ngc, nss, nal, nep, nbod, nmc, nzp);
        recordLines.set(0, str);
    }
}



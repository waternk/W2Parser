package w2parser;

import java.util.List;

/**
 * Inflow/Outflow Card
 */
public class InOutflowCard extends Card {
    private int ntr; // Number of tributaries
    private int nst; // Number of structures
    private int niw; // Number of internal weirs
    private int nwd; // Number of withdrawals
    private int ngt; // Number of gates
    private int nsp; // Number of spillways
    private int npi; // Number of pipes
    private int npu; // Number of pumps

    public InOutflowCard(W2ControlFile w2ControlFile) {
        super(w2ControlFile, "IN/OUTFL", 1);
        parseText();
    }

    public int getNumTributaries() {
        return ntr;
    }

    public void setNumTributaries(int ntr) {
        this.ntr = ntr;
        updateText();
    }

    public int getNumStructures() {
        return nst;
    }

    public void setNumStructures(int nst) {
        this.nst = nst;
        updateText();
    }

    public int getNumInternalWeirs() {
        return niw;
    }

    public void setNumInternalWeirs(int niw) {
        this.niw = niw;
        updateText();
    }

    public int getNumWithdrawals() {
        return nwd;
    }

    public void setNumWithdrawals(int nwd) {
        this.nwd = nwd;
        updateText();
    }

    public int getNumGates() {
        return ngt;
    }

    public void setNumGates(int ngt) {
        this.ngt = ngt;
        updateText();
    }

    public int getNumSpillways() {
        return nsp;
    }

    public void setNumSpillways(int nsp) {
        this.nsp = nsp;
        updateText();
    }

    public int getNumPipes() {
        return npi;
    }

    public void setNumPipes(int npi) {
        this.npi = npi;
        updateText();
    }

    public int getNumPumps() {
        return npu;
    }

    public void setNumPumps(int npu) {
        this.npu = npu;
        updateText();
    }

    @Override
    public void parseText() {
//        String[] records = recordLines.get(0).trim().split("\\s+");
        List<String> Fields = parseLine(recordLines.get(0), 8, 2, 10);
        ntr = Integer.parseInt(Fields.get(0));
        nst = Integer.parseInt(Fields.get(1));
        niw = Integer.parseInt(Fields.get(2));
        nwd = Integer.parseInt(Fields.get(3));
        ngt = Integer.parseInt(Fields.get(4));
        nsp = Integer.parseInt(Fields.get(5));
        npi = Integer.parseInt(Fields.get(6));
        npu = Integer.parseInt(Fields.get(7));
    }

    @Override
    public void updateText() {
        String str = String.format("%8s%8d%8d%8d%8d%8d%8d%8d%8d",
                "", ntr, nst, niw, nwd, ngt, nsp, npi, npu);
        recordLines.set(0, str);
    }
}

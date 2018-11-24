package w2parser;

import java.util.List;

public class RestartCard extends Card {
    private String rsoc;
    private int nrso;
    private String rsic;

    public RestartCard(W2ControlFile w2ControlFile) {
        super(w2ControlFile, "RESTART", 1);
        parseText();
    }

    public String getRsoc() {
        return rsoc;
    }

    public void setRsoc(String rsoc) {
        this.rsoc = rsoc;
        updateText();
    }

    public int getNrso() {
        return nrso;
    }

    public void setNrso(int nrso) {
        this.nrso = nrso;
        updateText();
    }

    public String getRsic() {
        return rsic;
    }

    public void setRsic(String rsic) {
        this.rsic = rsic;
        updateText();
    }

    @Override
    public void parseText() {
        List<String> Fields = parseLine(recordLines.get(0), 8, 2, 10);
        rsoc = Fields.get(0);
        nrso = Integer.parseInt(Fields.get(1));
        rsic = Fields.get(2);
    }

    @Override
    public void updateText() {
        String str = String.format("%8s%8s%8d%8s", "",
                rsoc, nrso, rsic);
        recordLines.set(0, str);
    }
}


package w2parser;

import java.util.List;

/**
 * Withdrawal Output Card
 */
public class WithdrawalOutputCard extends Card {
    private String WDOC;
    private int NWDO;
    private int NIWDO;
    private String identifier;

    public WithdrawalOutputCard(W2ControlFile w2ControlFile) {
        super(w2ControlFile, "WITH OUT", 1);
        parseText();
    }

    public String getWDOC() {
        return WDOC;
    }

    public void setWDOC(String WDOC) {
        this.WDOC = WDOC;
        updateText();
    }

    public int getNWDO() {
        return NWDO;
    }

    public void setNWDO(int NWDO) {
        this.NWDO = NWDO;
        updateText();
    }

    public int getNIWDO() {
        return NIWDO;
    }

    public void setNIWDO(int NIWDO) {
        this.NIWDO = NIWDO;
        updateText();
    }

    @Override
    public void parseText() {
        List<String> Fields = parseLine(recordLines.get(0), 8, 1, 10);
        identifier = Fields.get(0);
        WDOC = Fields.get(1);
        NWDO = Integer.parseInt(Fields.get(2));
        NIWDO = Integer.parseInt(Fields.get(3));
    }

    @Override
    public void updateText() {
        String str = String.format("%-8s%8s%8d%8d",
                identifier, WDOC, NWDO, NIWDO);
        recordLines.set(0, str);
    }
}



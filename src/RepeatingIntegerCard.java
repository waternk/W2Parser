package w2parser;

import java.util.ArrayList;
import java.util.List;

public class RepeatingIntegerCard extends Card {
    private List<Integer> Data;
    private int numFields;
    private String format;

    public RepeatingIntegerCard(W2ControlFile w2ControlFile, String cardName, int numFields, String format) {
        super(w2ControlFile, cardName, (int) Math.ceil(numFields/9.0));
        this.numFields = numFields;
        this.format = format;
        this.Data = new ArrayList<>();
        parseText();
    }

    public List<Integer> getData() {
        return Data;
    }

    public void setData(List<Integer> Data) {
        this.Data = Data;
        updateText();
    }

    public void clearData() {
        Data.clear();
    }

    public void addData(Integer data) {
        this.Data.add(data);
        updateText();
    }

    @Override
    public void parseText() {
        int numLines = (int) Math.ceil(numFields/9.0); // This needs to be recomputed each time, as Data changes size
        for (int i = 0; i < numLines; i++) {
            String line = recordLines.get(i);
            List<String> Fields = parseLine(line, 8, 2, 10);
            Fields.forEach(field -> {
                if (!field.equals(""))
                    Data.add(Integer.parseInt(field));}
            );
        }
    }

    @Override
    public void updateText() {
        String str = String.format("%8s", "");
        int line = 0;
        for (int i = 0; i < Data.size(); i++) {
            int data = Data.get(i);
            if ((i > 0 && i % 9 == 0)) {
                recordLines.set(line, str);
                str = String.format("%8s", "");
                line++;
            }

            str += String.format(format, data);

            if (i == (Data.size() - 1)) {
                recordLines.set(line, str);
            }
        }
    }
}


package w2parser;

import java.util.ArrayList;
import java.util.List;

public class W2File {
    private String fileName;
    private List<String> Units;
    private List<String> ColumnNames;

    public W2File(String infile) {
        fileName = infile;
        Units = new ArrayList<>();
        ColumnNames = new ArrayList<>();
    }

}

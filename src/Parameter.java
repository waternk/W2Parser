package w2parser;

public class Parameter {
    String location;            // Location (waterbody, branch, segment, depth, flow type, etc.)
    String shortName;           // Short name of parameter - to display in model linking editor
    String longName;            // Long name of parameter - to display in plots and tool tip of model linking editor
    String units;               // Parameter units
    int columnNumber;           // Column number in output file (zero indexed, with Julian day at column 0)
    int numColumns;             // Total number of data columns (parameters)
    String fileName;            // Input or output filename
    String inflowOutflow;       // Flow direction (inflow or outflow)
    String inputOutput;         // I/O type (input or output)
    int waterBody;              // Waterbody number (optional)
    int branch;                 // Branch number (optional)
    int segment;                // Segment number (optional)
    String verticalLocation;    // String containing vertical location (either depth or layer number, optional)

    public Parameter(String location, String shortName, String longName, String units,
                     int columnNumber, int numColumns, String fileName,
                     String inflowOutflow, String inputOutput) {
        this.location = location;
        this.shortName = shortName;
        this.longName = longName;
        this.units = units;
        this.columnNumber = columnNumber;
        this.numColumns = numColumns;
        this.fileName = fileName;
        this.inflowOutflow = inflowOutflow;
        this.inputOutput = inputOutput;
        this.waterBody = 0;
        this.branch = 0;
        this.segment = 0;
        this.verticalLocation = "";
    }

    public String getLocation() { return location; }

    public String getShortName() { return shortName; }

    public String getLongName() { return longName; }

    public String getUnits() { return units; }

    public int getColumnNumber() { return columnNumber; }

    public int getNumColumns() { return numColumns; }

    public String getFileName() { return fileName; }

    public String getInflowOutflow() { return inflowOutflow; }

    public String getInputOutput() { return inputOutput; }

    public void setNumColumns(int numColumns) {
        this.numColumns = numColumns;
    }

    public int getWaterBody() {
        return waterBody;
    }

    public void setWaterBody(int waterBody) {
        this.waterBody = waterBody;
    }

    public int getBranch() {
        return branch;
    }

    public void setBranch(int branch) {
        this.branch = branch;
    }

    public int getSegment() {
        return segment;
    }

    public void setSegment(int segment) {
        this.segment = segment;
    }

    public String getVerticalLocation() {
        return verticalLocation;
    }

    public void setVerticalLocation(String verticalLocation) {
        this.verticalLocation = verticalLocation;
    }
}

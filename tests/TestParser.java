import org.junit.Test;
import w2parser.*;
import java.io.FileNotFoundException;
import java.util.List;

public class TestParser {

    @Test
    public void testParserParticleTracking() throws FileNotFoundException {
        String infile = "data/ParticleTracking/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        Parser parser = new Parser(w2con);
        parser.readControlFile();

        // Get input parameters for flow, temperature, and constituents
        List<Parameter> InputParameters = parser.getInputParameters();

        // Get input parameters for meteorology
        List<Parameter> MetParameters = parser.getMeteorologyParameters();

        // Get TSR output parameters for flow, temperature, and constituents
        List<Parameter> TsrOutputParameters = parser.getTsrOutputParameters();

        // Display tables
        System.out.println("\nTable of Flow, Temperature, and Constituent Inputs:\n");
        parser.printTable(InputParameters);
        System.out.println("\nTable of Meteorology Inputs:\n");
        parser.printTable(MetParameters);
        System.out.println("\nTable of TSR Outputs:\n");
        parser.printTable(TsrOutputParameters);

        // Write tables to output files
        parser.writeTable(InputParameters, "results/ParticleTracking/W2_input_parameters.txt");
        parser.writeTable(MetParameters, "results/ParticleTracking/W2_met_parameters.txt");
        parser.writeTable(TsrOutputParameters, "results/ParticleTracking/W2_TSR_output_parameters.txt");
        parser.writeInitialWaterSurfaceElevations("results/ParticleTracking/W2_WSEL.txt");

        // Update time window
        parser.setJdayMin(1.5);
        parser.setJdayMax(52.0);
        parser.setStartYear(2019);

        // Get and revise initial water surface elevations
        List<List<Double>> WSEL = parser.getInitialWaterSurfaceElevations();

        // Set model output timestep and granularity
        parser.setOutputTimestep(2.0, Globals.Granularity.HOURS);
        parser.saveControlFile("results/ParticleTracking/w2_con.npt");
    }

    @Test
    public void testParserGrandCoulee() throws FileNotFoundException {
        String infile = "data/GCL/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        Parser parser = new Parser(w2con);
        parser.readControlFile();

        // Get flow, temperature, and constituent input parameters
        List<Parameter> InputParameters = parser.getInputParameters();

        // Get meteorology input parameters
        List<Parameter> MetParameters = parser.getMeteorologyParameters();

        // Get output parameters for flow, temperature, and constituents
        List<Parameter> TsrOutputParameters = parser.getTsrOutputParameters();

        // Display tables
        System.out.println("\nTable of Flow, Temperature, and Constituent Inputs:\n");
        parser.printTable(InputParameters);
        System.out.println("\nTable of Meteorology Inputs:\n");
        parser.printTable(MetParameters);
        System.out.println("\nTable of TSR Outputs:\n");
        parser.printTable(TsrOutputParameters);

        // Write tables to output files
        parser.writeTable(InputParameters, "results/GCL/W2_input_parameters.txt");
        parser.writeTable(MetParameters, "results/GCL/W2_met_parameters.txt");
        parser.writeTable(TsrOutputParameters, "results/ParticleTracking/W2_TSR_output_parameters.txt");
        parser.writeInitialWaterSurfaceElevations("results/GCL/W2_WSEL.txt");

        parser.saveControlFile("results/GCL/w2_con.npt");
    }
}

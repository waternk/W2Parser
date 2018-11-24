import org.junit.Test;
import w2parser.*;
import java.io.FileNotFoundException;

public class TestRestartCard {

    @Test
    public void testRestart() throws FileNotFoundException {
        String infile = "data/ColumbiaSlough/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        RestartCard restartCard = new RestartCard(w2con);
        int nrso = restartCard.getNrso();
        RepeatingDoubleCard rsoDateCard = new RepeatingDoubleCard(w2con,
                CardNames.RestartDate, nrso, "%8.2f");
        RepeatingDoubleCard rsoFreqCard = new RepeatingDoubleCard(w2con,
                CardNames.RestartFrequency, nrso, "%8.3f");

        // Turn Restart output on
        restartCard.setRsoc(Globals.ON);
        restartCard.setRsic(Globals.OFF);
        restartCard.setNrso(2);

        // Clear existing RSO dates
        rsoDateCard.clearData();

        // Add a single RSOD at Julian day 1
        rsoDateCard.addData(40.0);
        rsoDateCard.addData(50.0);

        // Clear existing RSO frequencies
        rsoFreqCard.clearData();

        // Add a single frequency (to be specified by UI)
        rsoFreqCard.addData(100.0);
        rsoFreqCard.addData(200.0);

        // Update Restart filename
        FileCard rsiFileCard = new FileCard(w2con,
                "RSI FILE", 1);
        rsiFileCard.clearFileNames();
        rsiFileCard.addFilename("rso.opt", "");

        // Turn on restart file....

        // Update records (commit to w2 control file array in memory)
        restartCard.updateRecord();
        rsoDateCard.updateRecord();
        rsoFreqCard.updateRecord();
        rsiFileCard.updateRecord();

        // Write updated W2 control file
        String outfile = "results/ColumbiaSlough/w2_con.npt.testRestart";
        w2con.save(outfile);
    }
}

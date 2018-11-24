import org.junit.Test;
import w2parser.Globals;
import w2parser.TimestepControlCard;
import w2parser.W2ControlFile;
import java.io.File;
import java.io.FileNotFoundException;

public class TestDltControlCard {

    @Test
    public void testDltControlCard() throws FileNotFoundException {
        String infile = "data/ColumbiaSlough/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        TimestepControlCard dltControlCard = new TimestepControlCard(w2con);
        dltControlCard.setNdt(1);
        dltControlCard.setDltMin(0.0001);
        dltControlCard.setDltIntr(Globals.ON);
        dltControlCard.updateRecord();

        // Write updated W2 control file
        String outpath = "results/ColumbiaSlough";
        String outfile = new File(outpath,
                "w2_con.npt.testDltControlCard").toString();
        w2con.save(outfile);
    }
}

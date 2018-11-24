import org.junit.Test;
import w2parser.Globals;
import w2parser.MiscellCard;
import w2parser.W2ControlFile;
import java.io.File;
import java.io.FileNotFoundException;

public class TestMiscellCard {

    @Test
    public void testMiscellCard() throws FileNotFoundException {
        String infile = "data/ColumbiaSlough/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        MiscellCard miscellCard = new MiscellCard(w2con);
        System.out.println(miscellCard);

        // Turn habitat volume on
        String state = miscellCard.getHabtatc();
        System.out.println("Habitat volume state: " + state);
        miscellCard.setHabtatc(Globals.ON);
        miscellCard.updateRecord();

        // Write updated W2 control file
        String outpath = "results/ColumbiaSlough";
        String outfile = new File(outpath,
                "w2_con.npt.testMiscell").toString();
        w2con.save(outfile);
    }
}

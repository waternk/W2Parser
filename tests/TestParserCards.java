import org.junit.Test;
import w2parser.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class TestParserCards {

    @Test
    public void testCreateOutputFlows() throws FileNotFoundException {
        String infile = "data/ColumbiaSlough/w2_con.npt";
        String outfile = "results/ColumbiaSlough/" +
                "w2_con.npt.testReadInfiles";
        W2ControlFile w2con = new W2ControlFile(infile);
        GridCard gridCard = new GridCard(w2con);
        int numBranches = gridCard.getNumBranches();
        int numWaterbodies = gridCard.getNumWaterBodies();

        ArrayList<String> outFiles = new ArrayList<>();

        for (int branch = 1; branch <= numBranches; branch++) {
            outFiles.add("qwo_br" + branch + ".opt");
        }
    }

}

import org.junit.Test;
import w2parser.GridCard;
import w2parser.W2ControlFile;
import java.io.FileNotFoundException;

public class TestGridCard {

    @Test
    public void testGridCard() throws FileNotFoundException {
        String infile = "data/ColumbiaSlough/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        GridCard gridCard = new GridCard(w2con);
        System.out.println(gridCard);
        gridCard.setNumBranches(500);
        gridCard.setNumWaterBodies(1000);
        gridCard.updateRecord();
    }


}

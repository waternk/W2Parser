import org.junit.Test;
import w2parser.CardNames;
import w2parser.MultiLineCard;
import w2parser.W2ControlFile;
import java.io.FileNotFoundException;

public class TestMultiLineCard {

    @Test
    public void testMultiLineCard() throws FileNotFoundException {
        String infile = "data/ParticleTracking/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        MultiLineCard mCard = new MultiLineCard(w2con,
                CardNames.InflowActiveConstituentControl, 39);
    }
}

import org.junit.Test;
import w2parser.TimeControlCard;
import w2parser.W2ControlFile;
import java.io.FileNotFoundException;

public class TestTimeControlCard {

    @Test
    public void testTimeControlCard() throws FileNotFoundException {
        String infile = "data/ColumbiaSlough/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        TimeControlCard timeControlCard = new TimeControlCard(w2con);
        double jdayMin = timeControlCard.getJdayMin();
        double jdayMax = timeControlCard.getJdayMax();
        int startYear = timeControlCard.getStartYear();
        System.out.println(timeControlCard);
    }
}

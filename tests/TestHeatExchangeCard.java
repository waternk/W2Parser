import org.junit.Test;
import w2parser.GridCard;
import w2parser.HeatExchangeCard;
import w2parser.W2ControlFile;
import java.io.FileNotFoundException;
import java.util.List;

public class TestHeatExchangeCard {

    @Test
    public void Test1() throws FileNotFoundException {
        String infile = "data/ParticleTracking/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        GridCard gridCard = new GridCard(w2con);
        HeatExchangeCard hCard = new HeatExchangeCard(w2con, gridCard.getNumWaterBodies());
        List<String> SROC = hCard.getSROC();
        SROC.set(0, "Hello");
        hCard.setSROC(SROC);
        hCard.updateRecord();
    }
}

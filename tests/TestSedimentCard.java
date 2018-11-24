import org.junit.Test;
import w2parser.GridCard;
import w2parser.SedimentCard;
import w2parser.W2ControlFile;

import java.io.FileNotFoundException;
import java.util.List;

public class TestSedimentCard {

    @Test
    public void Test1() throws FileNotFoundException {
        W2ControlFile w2con = new W2ControlFile("data/ParticleTracking/w2_con.npt");
        GridCard gridCard = new GridCard(w2con);
        SedimentCard sCard = new SedimentCard(w2con, gridCard.getNumWaterBodies());
        List<String> SEDC = sCard.getSEDC();
        SEDC.set(0, "Hello");
        sCard.setSEDC(SEDC);;
        sCard.updateRecord();
    }
}

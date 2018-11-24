import org.junit.Test;
import w2parser.GridCard;
import w2parser.IceCoverCard;
import w2parser.W2ControlFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class TestIceCoverCard {

    @Test
    public void Test1() throws FileNotFoundException {
        W2ControlFile w2con = new W2ControlFile("data/ParticleTracking/w2_con.npt");
        GridCard gridCard = new GridCard(w2con);
        IceCoverCard iCard = new IceCoverCard(w2con, gridCard.getNumWaterBodies());
        List<Double> ALBEDO = iCard.getALBEDO();
        ALBEDO.set(0, -99.0);
        iCard.setALBEDO(ALBEDO);
        iCard.updateRecord();
    }
}

import org.junit.Test;
import w2parser.GridCard;
import w2parser.LocationCard;
import w2parser.W2ControlFile;

import java.io.FileNotFoundException;
import java.util.List;

public class TestLocationCard {

    @Test
    public void testLocationCard() throws FileNotFoundException {
        String infile = "data/ParticleTracking/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        GridCard gridCard = new GridCard(w2con);
        LocationCard lCard = new LocationCard(w2con, gridCard.getNumWaterBodies());
        List<Double> LAT = lCard.getLAT();
        List<Double> LONG = lCard.getLONG();
        List<Double> EBOT = lCard.getEBOT();
        List<Integer> BS = lCard.getBS();
        List<Integer> BE = lCard.getBE();
        List<Integer> JBDN = lCard.getJBDN();
        LAT.set(0, 42.42);
        lCard.setLAT(LAT);
        lCard.updateRecord();
    }
}

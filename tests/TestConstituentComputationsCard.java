import org.junit.Test;
import w2parser.ConstituentComputationsCard;
import w2parser.W2ControlFile;
import java.io.FileNotFoundException;

public class TestConstituentComputationsCard {

    @Test
    public void testReadLIMC() throws FileNotFoundException {
        String infile = "data/ParticleTracking/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        ConstituentComputationsCard cstCompCard = new ConstituentComputationsCard(w2con);
        String limc = cstCompCard.getLIMC();
        System.out.println("LIMC = " + limc);
    }
}

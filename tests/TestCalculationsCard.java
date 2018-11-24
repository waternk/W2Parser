import org.junit.Test;
import w2parser.CalculationsCard;
import w2parser.GridCard;
import w2parser.W2ControlFile;
import java.io.FileNotFoundException;
import java.util.List;

public class TestCalculationsCard {

    @Test
    public void testCalculationsCard() throws FileNotFoundException {
        String infile = "data/ParticleTracking/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        GridCard gridCard = new GridCard(w2con);
        CalculationsCard cCard = new CalculationsCard(w2con, gridCard.getNumWaterBodies());
        List<String> EBC = cCard.getEBC();
        List<String> EVC = cCard.getEVC();
        List<String> MBC = cCard.getMBC();
        List<String> PQC = cCard.getPQC();
        List<String> PRC = cCard.getPRC();
        List<String> VBC = cCard.getVBC();
        EBC.set(0, "Hello");
        cCard.updateRecord();
    }
}

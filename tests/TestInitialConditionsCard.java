import org.junit.Test;
import w2parser.GridCard;
import w2parser.InitialConditionsCard;
import w2parser.W2ControlFile;
import java.io.FileNotFoundException;
import java.util.List;

public class TestInitialConditionsCard {

    @Test
    public void Test1() throws FileNotFoundException {
        String infile = "data/ParticleTracking/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        GridCard gridCard = new GridCard(w2con);
        InitialConditionsCard iCard = new InitialConditionsCard(w2con, gridCard.getNumWaterBodies());
        List<Double> T2I = iCard.getT2I();
        T2I.set(0, -99.0);
        iCard.setT2I(T2I);
        iCard.updateRecord();
    }
}

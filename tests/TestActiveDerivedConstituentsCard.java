import org.junit.Test;
import w2parser.*;

import java.io.FileNotFoundException;
import java.util.List;

public class TestActiveDerivedConstituentsCard {


    @Test
    public void Test1() throws FileNotFoundException {
        String infile = "data/ParticleTracking/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        String graphFilename = w2con.getGraphFilename();
        GraphFile graphFile = new GraphFile(graphFilename);
        List<Constituent> GraphFileConstituents = graphFile.getDerivedConstituents();
        int NDT = GraphFileConstituents.size();
        GridCard gridCard = new GridCard(w2con);
        int NWB = gridCard.getNumWaterBodies();
        ActiveDerivedConstituentsCard card = new ActiveDerivedConstituentsCard(w2con, NDT, NWB);
    }
}

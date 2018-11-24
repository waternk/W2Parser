import org.junit.Test;
import w2parser.*;
import java.io.FileNotFoundException;
import java.util.List;

public class TestRepeatingIntegerCard {

    @Test
    public void testRepeatingIntegerCard() throws FileNotFoundException {
        String infile = "data/ParticleTracking/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        RepeatingIntegerCard rdc = new RepeatingIntegerCard(w2con,
                CardNames.WithdrawalSegment, 5, "%8d");
        List<Integer> IWDO = rdc.getData();
        IWDO.forEach(System.out::println);
        for (int i = 0; i < IWDO.size(); i++) {
            IWDO.set(i, IWDO.get(i) * 2);
        }

        rdc.setData(IWDO);
        rdc.updateRecord();
        w2con.save("results/ParticleTracking/w2_con.npt_revised");
    }

    @Test
    public void testReadNITSR() throws FileNotFoundException {
        String infile = "data/ParticleTracking/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
        TsrPlotCard tsrPlotCard = new TsrPlotCard(w2con);
        int nitsr = tsrPlotCard.getNITSR();
        System.out.println("NITSR = " + nitsr);
    }

    @Test
    public void testReadITSR() throws FileNotFoundException {
        String infile = "data/ParticleTracking/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);

        TsrPlotCard tsrPlotCard = new TsrPlotCard(w2con);
        int nitsr = tsrPlotCard.getNITSR();

        RepeatingIntegerCard rdc = new RepeatingIntegerCard(w2con, CardNames.TimeSeriesSegment, nitsr, "%8d");
        List<Integer> tsrSegments = rdc.getData();
        tsrSegments.forEach(System.out::println);

        for (int i = 0; i < tsrSegments.size(); i++) {
            tsrSegments.set(i, tsrSegments.get(i)*2);
        }

        tsrSegments.add(93);
        tsrSegments.add(95);

        rdc.setData(tsrSegments);
        rdc.updateRecord();
        w2con.save("results/ParticleTracking/w2_con.npt_revised");
    }
}

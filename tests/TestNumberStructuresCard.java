import org.junit.Test;
import w2parser.Globals;
import w2parser.GridCard;
import w2parser.NumberStructuresCard;
import w2parser.W2ControlFile;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class TestNumberStructuresCard {
    NumberStructuresCard nCard;

    public List<Integer> getNSTR(String infile) throws FileNotFoundException {
        W2ControlFile w2con = new W2ControlFile(infile);
        GridCard gridCard = new GridCard(w2con);
        int numBranches = gridCard.getNumBranches();
        nCard = new NumberStructuresCard(w2con, numBranches);
        List<Integer> NSTR = nCard.getNSTR();
        return NSTR;
    }

    public List<String> getDYNELEV(String infile) throws FileNotFoundException {
        W2ControlFile w2con = new W2ControlFile(infile);
        GridCard gridCard = new GridCard(w2con);
        int numBranches = gridCard.getNumBranches();
        NumberStructuresCard nCard = new NumberStructuresCard(w2con, numBranches);
        List<String> DYNELEV = nCard.getDYNELEV();
        return DYNELEV;
    }

    @Test
    public void test_Get_NSTR_ColumbiaSlough() throws FileNotFoundException {
        List<Integer> NSTR_expected = Arrays.asList(0, 0);
        List<Integer> NSTR_actual = getNSTR("data/ColumbiaSlough/w2_con.npt");
        assert NSTR_expected.equals(NSTR_actual);
    }

    @Test
    public void test_Get_NSTR_GrandCoulee() throws FileNotFoundException {
        List<Integer> NSTR_expected = Arrays.asList(
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0);
        List<Integer> NSTR_actual = getNSTR("data/GCL/w2_con.npt");
        assert NSTR_expected.equals(NSTR_actual);
    }

    @Test
    public void test_Get_NSTR_ParticleTracking() throws FileNotFoundException {
        List<Integer> NSTR_actual = getNSTR("data/ParticleTracking/w2_con.npt");
        List<Integer> NSTR_expected = Arrays.asList(5, 0, 0, 0, 0, 0);
        assert NSTR_expected.equals(NSTR_actual);
    }

    @Test
    public void test_Get_NSTR_TheDalles() throws FileNotFoundException {
        List<Integer> NSTR_expected = Arrays.asList(
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0, 0);
        List<Integer> NSTR_actual = getNSTR("data/GCL/w2_con.npt");
        assert NSTR_expected.equals(NSTR_actual);
    }

    @Test
    public void test_Get_DYNELEV_ColumbiaSlough() throws FileNotFoundException {
        List<String> DYNELEV_expected = Arrays.asList(Globals.OFF, Globals.OFF);
        List<String> DYNELEV_actual = getDYNELEV("data/ColumbiaSlough/w2_con.npt");
        assert DYNELEV_expected.equals(DYNELEV_actual);
    }

    @Test
    public void test_Get_DYNELEV_GrandCoulee() throws FileNotFoundException {
        List<String> DYNELEV_expected = Arrays.asList(
                Globals.OFF, Globals.OFF, Globals.OFF, Globals.OFF, Globals.OFF,
                Globals.OFF, Globals.OFF, Globals.OFF, Globals.OFF, Globals.OFF,
                Globals.OFF, Globals.OFF, Globals.OFF, Globals.OFF, Globals.OFF,
                Globals.OFF, Globals.OFF, Globals.OFF, Globals.OFF, Globals.OFF,
                Globals.OFF, Globals.OFF, Globals.OFF, Globals.OFF, Globals.OFF);
        List<String> DYNELEV_actual = getDYNELEV("data/GCL/w2_con.npt");
        assert DYNELEV_expected.equals(DYNELEV_actual);
    }

    @Test
    public void test_Get_DYNELEV_ParticleTracking() throws FileNotFoundException {
        List<String> DYNELEV_expected = Arrays.asList(
                Globals.OFF, Globals.OFF, Globals.OFF,
                Globals.OFF, Globals.OFF, Globals.OFF);
        List<String> DYNELEV_actual = getDYNELEV("data/ParticleTracking/w2_con.npt");
        assert DYNELEV_expected.equals(DYNELEV_actual);
    }

    @Test
    public void test_Get_DYNELEV_TheDalles() throws FileNotFoundException {
        List<String> DYNELEV_expected = Arrays.asList(Globals.OFF);
        List<String> DYNELEV_actual = getDYNELEV("data/TDA/w2_con.npt");
        assert DYNELEV_expected.equals(DYNELEV_actual);
    }

    @Test
    public void test_Set_NSTR_ParticleTracking() throws FileNotFoundException {
        List<Integer> NSTR = getNSTR("data/ParticleTracking/w2_con.npt");
        NSTR.set(0, 20);
        nCard.setNSTR(NSTR);
        nCard.updateRecord();
    }
}

import org.junit.Test;
import w2parser.W2ControlFile;
import java.io.FileNotFoundException;

public class TestW2ControlFile {

    @Test
    public void testReadW2ControlFile() throws FileNotFoundException {
        String infile = "data/ColumbiaSlough/w2_con.npt";
        W2ControlFile w2con = new W2ControlFile(infile);
    }

    @Test
    public void testWriteW2ControlFile() throws FileNotFoundException {
        String infile = "data/ColumbiaSlough/w2_con.npt";
        String outfile = "results/ColumbiaSlough/w2_con.npt.NEW";
        W2ControlFile w2con = new W2ControlFile(infile);
        w2con.save(outfile);
    }
}

import org.junit.Test;
import w2parser.CardNames;
import w2parser.FileCard;
import w2parser.GridCard;
import w2parser.W2ControlFile;

import java.io.FileNotFoundException;

public class TestFileCard {

    @Test
    public void testReadWriteInfiles() throws FileNotFoundException {
        String infile = "data/ColumbiaSlough/w2_con.npt";
        String outfile = "results/ColumbiaSlough/" +
                "w2_con.npt.testReadInfiles";
        W2ControlFile w2con = new W2ControlFile(infile);
        GridCard gridCard = new GridCard(w2con);
        int numBranches = gridCard.getNumBranches();
        int numWaterbodies = gridCard.getNumWaterBodies();
        FileCard qinCard = new FileCard(w2con, CardNames.BranchInflowFilenames, numBranches);
        FileCard tinCard = new FileCard(w2con, CardNames.BranchInflowTemperatureFilenames, numBranches);
        FileCard cinCard = new FileCard(w2con, CardNames.BranchInflowConcentrationFilenames, numBranches);
        FileCard bthCard = new FileCard(w2con, CardNames.BathymetryFilenames, numWaterbodies);
        System.out.println(qinCard);
        System.out.println(tinCard);
        System.out.println(cinCard);
        System.out.println(bthCard);
        System.out.println(qinCard.getFileNames());
        System.out.println(tinCard.getFileNames());
        System.out.println(cinCard.getFileNames());
        System.out.println(bthCard.getFileNames());
        qinCard.setFileName(0, "QinFile1");
        qinCard.setFileName(1, "QinFile2");
        qinCard.updateRecord();
        tinCard.setFileName(0, "TinFile1");
        tinCard.setFileName(1, "TinFile2");
        tinCard.updateRecord();
        cinCard.setFileName(0, "CinFile1");
        cinCard.setFileName(1, "CinFile2");
        cinCard.updateRecord();
        bthCard.setFileName(0, "BthFile1");
        bthCard.updateRecord();
        w2con.save(outfile);
    }
}

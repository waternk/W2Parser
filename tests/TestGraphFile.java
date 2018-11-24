import org.junit.Test;
import w2parser.Constituent;
import w2parser.GraphFile;
import java.io.FileNotFoundException;
import java.util.List;

public class TestGraphFile {

    @Test
    public void testGraphFile() throws FileNotFoundException {
        String infile = "data/ParticleTracking/graph.npt";
        GraphFile graph = new GraphFile(infile);

        List<Constituent> Constituents = graph.getConstituents();
        int numColumns = Constituents.size();

        System.out.println("Constituents:");
        for (Constituent constituent : Constituents) {
            System.out.println("Name: " + constituent.getLongName() +
                    ", Units: " + constituent.getUnits() +
                    ", Column: " + constituent.getColumnNumber() + "/" + numColumns);
        }

        List<Constituent> DerivedConstituents = graph.getDerivedConstituents();
        numColumns = DerivedConstituents.size();

        System.out.println("\nDerived Constituents:");
        for (Constituent constituent : DerivedConstituents) {
            System.out.println("Name: " + constituent.getLongName() +
                    ", Units: " + constituent.getUnits() +
                    ", Column: " + constituent.getColumnNumber() + "/" + numColumns);
        }
    }

}

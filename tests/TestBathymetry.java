import org.junit.Test;
import w2parser.*;

import java.io.FileNotFoundException;
import java.util.List;

public class TestBathymetry {
    @Test
    public void testBathymetryNewSingleWaterBody()
            throws FileNotFoundException {
        W2ControlFile w2con = new W2ControlFile("data/TDA/w2_con.npt");
        GridCard gridCard = new GridCard(w2con);
        int numLayers = gridCard.setNumLayers();
        BathymetryFile bathymetryFile =
                new BathymetryFile(w2con, "data/TDA/TDA_NAVD88_BTH_2014_121117.csv",
                        numLayers);
        BathymetryRecord<Integer> Segments = bathymetryFile.getSegments();
        BathymetryRecord<Double> ELWS = new BathymetryRecord<>("ELWS");
        for (int i = 0; i < Segments.size(); i++) {
            ELWS.add((double) i);
        }
        bathymetryFile.setELWS(ELWS);
        String outfile = "results/TDA/bathymetry.csv.testBathymetry";
        bathymetryFile.save(outfile);
        System.out.println(bathymetryFile);
    }

    public BathymetryRecord<Double> scaleData(BathymetryRecord<Double> record, double scale,
                                              double offset) {
        for (int i = 0; i < record.size(); i++) {
            double value = record.get(i) * scale + offset;
            record.set(i, value);
        }
        return record;
    }

    @Test
    public void testBathymetryLegacyMultipleWaterBodies()
            throws FileNotFoundException {
        // Prepare water surface elevation data to write to all bathymetry files
//        List<Double> DLX = new ArrayList<>();
//        List<Double> ELWS = new ArrayList<>();
//        List<Double> PHIO = new ArrayList<>();
//        List<Double> FRICT = new ArrayList<>();
//        for (int i = 0; i < 20; i++) {
//            DLX.add(50.0 + i);
//            ELWS.add(200.0 + i);
//            PHIO.add(i/2.0);
//            FRICT.add(i/100.0);
//        }

        W2ControlFile w2con = new W2ControlFile("data/GCL/w2_con.npt");
        GridCard gridCard = new GridCard(w2con);
        int numWaterBodies = gridCard.getNumWaterBodies();
        int numLayers = gridCard.setNumLayers();
        FileCard bathymetryFileCard = new FileCard(w2con,
                "BTH FILE", numWaterBodies);
        List<String> fileNames = bathymetryFileCard.getFileNames();
        fileNames.forEach(f ->
                {
                    try {
                        BathymetryFile bathymetryFile =
                                new BathymetryFile(w2con, "data/GCL/" + f, numLayers);

                        // Scale the original values as a tracer
                        BathymetryRecord<Double> DLX = bathymetryFile.getDLX();
                        BathymetryRecord<Double> ELWS = bathymetryFile.getELWS();
                        BathymetryRecord<Double> PHIO = bathymetryFile.getPHIO();
                        BathymetryRecord<Double> FRICT = bathymetryFile.getFRICT();
                        DLX = scaleData(DLX, 1.0, 100.0);
                        ELWS = scaleData(ELWS, 1.0, 100.0);
                        PHIO = scaleData(PHIO, 1.0, 1.0);
                        FRICT = scaleData(FRICT, 1.0, 1.0);
                        bathymetryFile.setDLX(DLX);
                        bathymetryFile.setELWS(ELWS);
                        bathymetryFile.setPHIO(PHIO);
                        bathymetryFile.setFRICT(FRICT);
                        String outfile = "results/GCL/" + f + "_NEW_ELWS";
                        bathymetryFile.save(outfile);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
        );
    }
}

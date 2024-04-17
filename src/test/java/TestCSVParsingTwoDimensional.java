import org.junit.After;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * These tests only check if the parsing handles errors correctly, they do <b>NOT</b> check for the correctness of the DataFrame.
 */
public class TestCSVParsingTwoDimensional {

    private DataFrame<String, String, Object> dataFrameCSV;

    private static final String WRONG_PATH = "NoSuchPathExists";
    private static final String PATH_CORRECT = "src/test/resources/CSVTwoDimCorrect.csv";
    private static final String PATH_CORRECT_DELIMITER = "src/test/resources/CSVTwoDimCorrectDelimiter.csv";
    private static final String PATH_INCORRECT_COL = "src/test/resources/CSVTwoDimIncorrectCol.csv";
    private static final String PATH_INCORRECT_VALUE = "src/test/resources/CSVTwoDimIncorrectValue.csv";
    private static final String PATH_INCORRECT_LABEL = "src/test/resources/CSVTwoDimIncorrectLabel.csv";
    private static final String PATH_INCORRECT_INCONSISTENT_VALUE = "src/test/resources/CSVTwoDimIncorrectInconsistentValue.csv";


    @After
    public void endTest() {
        this.dataFrameCSV = null;
    }

    /**
     * Check if no errors are thrown while parsing a correct CSV.
     */
    @Test
    public void testParsingCorrectCSV() throws Exception {
        dataFrameCSV = new DataFrame<>(PATH_CORRECT, ';');
    }


    /**
     * Check if no errors are thrown while parsing a correct CSV with a different delimiter : ','.
     */
    @Test
    public void testParsingCorrectCSVDelimiter() throws Exception {
        dataFrameCSV = new DataFrame<>(PATH_CORRECT_DELIMITER, ',');
    }


    /**
     * Check if a FileNotFoundException is thrown while parsing a CSV that doesn't exist.
     */
    @Test(expected = FileNotFoundException.class)
    public void testMissingCSV() throws Exception{
        dataFrameCSV = new DataFrame<>(WRONG_PATH, ';');
    }


    /**
     * Check if a IllegalArgumentException is thrown while parsing a CSV with missing columns.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParsingIncorrectCSVColumns() throws Exception{
        dataFrameCSV = new DataFrame<>(PATH_INCORRECT_COL, ';');
    }


    /**
     * Check if a IllegalArgumentException is thrown while parsing a CSV with missing values.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParsingIncorrectCSVValues() throws Exception{
        dataFrameCSV = new DataFrame<>(PATH_INCORRECT_VALUE, ';');
    }


    /**
     * Check if a IllegalArgumentException is thrown while parsing a CSV with missing labels.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParsingIncorrectCSVLabels() throws Exception{
        dataFrameCSV = new DataFrame<>(PATH_INCORRECT_LABEL, ';');
    }


    /**
     * Check if a IllegalArgumentException is thrown while parsing a CSV with inconsistent values.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testParsingIncorrectCSVInconsistentValues() throws Exception{
        dataFrameCSV = new DataFrame<>(PATH_INCORRECT_INCONSISTENT_VALUE, ';');
    }

}

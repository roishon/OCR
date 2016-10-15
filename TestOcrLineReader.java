package de.interhyp.cleancode.ocrLine;

import de.interhyp.cleancode.ocr.OcrLineReader;
import de.interhyp.cleancode.ocr.OcrLine;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by rshachor on 15.10.2016.
 */
public class TestOcrLineReader {

    @Test
    public void testGetTextFromOcrLines ()
    {
        List<String> lines = Arrays.asList
                (
                        " _   _   _   _   _   _ ",
                        "  | |_| |_|  _| | | |_ ",
                        "  |  _| |_| |_  |_|  _|",
                        "    ",
                        "     _       _ ",
                        " |   _| |_| |_ ",
                        " |   _|   | |_|"
                );

        String expected = "798205\n1346";
        String result = new OcrLineReader().getTextFromOcrLines(lines);
        assertEquals(result, expected);
    }




    @Test
    public void testGroupOcrLines ()
    {
        List<String> lines1 = Arrays.asList
                (
                        " _   _   _   _   _   _ ",
                        "  | |_| |_|  _| | | |_ ",
                        "  |  _| |_| |_  |_|  _|"
                );

        List<String> lines2 = Arrays.asList
                (
                        "     _       _ ",
                        " |   _| |_| |_ ",
                        " |   _|   | |_|"
                );

        OcrLine ocrLine0 = new OcrLine(lines1);
        OcrLine ocrLine1 = new OcrLine(lines2);

        List<String> allLines = new ArrayList<>();
        allLines.addAll(lines1);
        allLines.add (" ");
        allLines.addAll(lines2);

        List<OcrLine> ocrLines = new OcrLineReader().groupOcrLines(allLines);

        assertEquals(ocrLines.get(0), ocrLine0);
        assertEquals(ocrLines.get(1), ocrLine1);
    }

}

package de.interhyp.cleancode.ocrLine;

import de.interhyp.cleancode.ocr.OcrLine;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by rshachor on 13.10.2016.
 */
public class TestOcrLine {


    @Test
    public void testTransferOcrLineToString ()
    {
        List<String> lines = Arrays.asList
                (
                        " _   _ ",
                        "  | |_|",
                        "  |  _|"
                );

        OcrLine ocr = new OcrLine(lines);
        String expected = "79";

        String result  = ocr.transferOcrLineToString();
        assertEquals(result, expected);
    }


    @Test
    public void testTransferOcrLineToNumbers ()
    {
        List<String> lines = Arrays.asList
                (
                        " _   _ ",
                        "  | |_|",
                        "  |  _|"
                );

        OcrLine ocr = new OcrLine(lines);
        List<Integer> expected = Arrays.asList(7, 9);

        List<Integer> result  = ocr.transferOcrLineToNumbers();
        assertEquals(result, expected);

    }

    @Test
    public void testMapFlatStringToDigit ()
    {
        String flatDigit = ".|.._..||";
        int expected = 4;
        int result = OcrLine.mapFlatStringToDigit(flatDigit);

        assertEquals(result, expected);
    }



    @Test
    public void testBuildFlatStringDigit ()
    {
        List<String> lines = Arrays.asList
                (
                        " _   _ ",
                        "  | |_|",
                        "  |  _|"
                );

        OcrLine ocr = new OcrLine(lines);
        int startColumn = 4;
        String expected = ".|.___.||";

        String result = ocr.buildFlatStringDigit(startColumn);

        assertEquals (result, expected);
    }


    @Test
    public void testReadColumn ()
    {
        List<String> lines = Arrays.asList
                (
                        "aaaDeee",
                        "114 567",
                        "99/$--z"
                );

        OcrLine ocr = new OcrLine(lines);
        int columnIndex = 3;
        String expected = "D.$";

        String result = ocr.readColumn(columnIndex);
        assertEquals (result, expected);
    }



    @Test
    public void testReadCharCorrectForm ()
    {
        String line = "AAA BBB";
        List<String> lines = Arrays.asList(line, line, line);
        OcrLine ocr = new OcrLine(lines);

        int index = 2;
        int indexSpace = 3;
        char expected = 'A';
        char expectedSpace = '.';

        char result = ocr.readCharCorrectForm(0, index);
        char resultSpace = ocr.readCharCorrectForm(0, indexSpace);

        assertEquals(expected, result);
        assertEquals(expectedSpace, resultSpace);
    }




}

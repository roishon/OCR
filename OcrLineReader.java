package de.interhyp.cleancode.ocr;

import de.interhyp.cleancode.ocr.OcrLine;

import java.util.*;

/**
 * Created by rshachor on 13.10.2016.
 */
public class OcrLineReader {


    public String getTextFromOcrLines(List<String> lines)
    {
        List<OcrLine> ocrLines = groupOcrLines(lines);

        StringBuilder builder = new StringBuilder();

        for (OcrLine ocrLine : ocrLines)
        {
            String line = ocrLine.transferOcrLineToString();
            builder.append(line +  "\n");
        }

        String result = builder.toString();
        return result.substring(0 , result.length() - 1);
    }

    public List<OcrLine> groupOcrLines (List<String> lines)
    {
        //todo: trimm lines
        List<OcrLine> ocrLines = new ArrayList<>();

        for (int line = 0; line < lines.size() - 1; line += 4)
        {
            List<String> digitLines =  new ArrayList<>();
            digitLines.add (lines.get (line));
            digitLines.add (lines.get (line + 1));
            digitLines.add (lines.get (line + 2));

            OcrLine ocrLine = new OcrLine(digitLines);
            ocrLines.add(ocrLine);
        }

        return ocrLines;
    }















}

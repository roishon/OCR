package de.interhyp.cleancode.ocr;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rshachor on 15.10.2016.
 */
public class OcrLine {

    static Map<String, Integer> digitsLexicon = new HashMap<>();
    static
    {
        digitsLexicon.put (".||_._.||", 0);
        digitsLexicon.put ("....||...", 1);
        digitsLexicon.put ("..|___.|.", 2);
        digitsLexicon.put ("...___.||", 3);
        digitsLexicon.put (".|.._..||", 4);
        digitsLexicon.put (".|.___..|", 5);
        digitsLexicon.put (".||___..|", 6);
        digitsLexicon.put ("..._...||", 7);
        digitsLexicon.put (".||___.||", 8);
        digitsLexicon.put (".|.___.||", 9);
    }

    private List<String> lines;

    public OcrLine(List<String> lines)
    {
        if (lines.size() != 3)
            throw new IllegalArgumentException ("An Ocr line must contain exactly 3 text lines");

        if (!(lines.get (0).length() == lines.get (1).length() && lines.get (1).length() == lines.get (2).length()))
            throw new IllegalArgumentException("All 3 Ocr lines must be of the same length");

        this.lines = lines;
    }


    public String transferOcrLineToString ()
    {
        List<Integer> numbers = transferOcrLineToNumbers();

        String line = numbersToString(numbers);

        return line;
    }

    private String numbersToString (List<Integer> numbers)
    {
        StringBuilder sb = new StringBuilder ();
        for (Integer number : numbers)
            sb.append(String.valueOf(number));


        return sb.toString();
    }


    public List<Integer> transferOcrLineToNumbers ()
    {
        List<Integer> result = new ArrayList<>();

        int length = getLinesLength();

        for (int column = 0; column < length; column += 4)
        {
            String flatStringDigit = buildFlatStringDigit (column);
            int digit =  mapFlatStringToDigit(flatStringDigit);
            result.add(digit);
        }

        return result;
    }

    private int getLinesLength ()
    {
        return lines.get (0).length();
    }




    public static int mapFlatStringToDigit(String flat)
    {
        return digitsLexicon.get (flat);
    }



    public String buildFlatStringDigit (int firstColumn)
    {
        String column1 = readColumn(firstColumn);
        String column2 = readColumn(firstColumn + 1);
        String column3 = readColumn(firstColumn + 2);

        return column1 + column2 + column3;
    }


    public String readColumn(int column)
    {
        Character c1 = readCharCorrectForm(0, column);
        Character c2 = readCharCorrectForm(1, column);
        Character c3 = readCharCorrectForm(2, column);

        return c1.toString() + c2.toString() + c3.toString();
    }

    public char readCharCorrectForm (int line, int column)
    {
        char c = lines.get (line).charAt(column);
        if (c == ' ')
            return '.';

        return c;
    }

    @Override
    public boolean equals (Object object)
    {
        if (object == null)
            return false;
        if (object.getClass() != OcrLine.class)
            return false;

        OcrLine other = (OcrLine) object;

        return  other.lines.get (0).equals(lines.get(0)) &&
                other.lines.get (1).equals(lines.get(1)) &&
                other.lines.get (2).equals(lines.get(2));
    }


}

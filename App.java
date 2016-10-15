package de.interhyp.cleancode.ocr;

import de.interhyp.cleancode.Fileloader;

import java.util.List;

/**
 * Created by rshachor on 15.10.2016.
 */
public class App {

    public static void main (String[] args)
    {
        new App ().run (args[0]);

    }

    public void run (String fileName)
    {
        final String path = "C:/develop/ber_start/src/";
        List<String> lines = Fileloader.loadFile(path + fileName);
        String text = new OcrLineReader().getTextFromOcrLines(lines);

        IO.print(text);
    }
}

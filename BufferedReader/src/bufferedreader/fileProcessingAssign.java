/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bufferedreader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author og
 */
public class fileProcessingAssign {

    /**
     * @param args the command line arguments
     */
    static Map<String, String> map = new HashMap<String, String>();

    public static void main(String[] args) throws FileNotFoundException, IOException {
        // TODO code application logic here
        File file = new File("exhibitA-input.csv");
        StringBuffer content = new StringBuffer();
        BufferedReader reader = null;

        reader = new BufferedReader(new FileReader(file));
        String text = null;
        String cvsSplitBy = "\t";


        try {
            while ((text = reader.readLine()) != null) {
                String[] linesfromcsv = text.split(cvsSplitBy);
                if (linesfromcsv[3].contains("10/08/2016")) {
                    map.put(linesfromcsv[1], linesfromcsv[2]);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        try {
            System.setOut(new PrintStream(new File("output-file-with-final-result.txt")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        //System.out.println(Arrays.asList(map)); //print HashMap value        

        //count key value mappings using a new 
    Map<String, Integer> result = new TreeMap<String, Integer>();
    map.entrySet().stream().map((entry) -> entry.getValue()).forEachOrdered((value) -> {
        Integer count = result.get(value);
        if (count == null)
            result.put(value, new Integer(1));
        else
            result.put(value, new Integer(count+1));
        });
          System.out.println(Arrays.asList(result)); //print HashMap value
    }

}

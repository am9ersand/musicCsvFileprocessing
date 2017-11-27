/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package singlekeymultiplevalueusingguava2;

/**
 *
 * @author og
 */
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import com.google.common.collect.SetMultimap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Set;

/**
 *
 * HashMap - Single Key and Multiple Values using Google Guava Collections
 *
 *
 *
 * @author 
 *
 * @version 
 *
 */
public class SingleKeyMultipleValueUsingGuava2 {

    public String distinctPlayCount(Set<String> x) {

        return "";

    }

    public static void main(String[] args) throws FileNotFoundException {

        // create multimap to store key and values
        File file = new File("exhibitA-input.csv");
        StringBuffer content = new StringBuffer();
        BufferedReader reader = null;

        reader = new BufferedReader(new FileReader(file));
        String text = null;
        String cvsSplitBy = "\t";

        SetMultimap<String, String> myMultimap = HashMultimap.create();
        try {
            while ((text = reader.readLine()) != null) {
                String[] linesfromcsv = text.split(cvsSplitBy);
                if (linesfromcsv[3].contains("10/08/2016")) {

                    myMultimap.put(linesfromcsv[2], linesfromcsv[1]);

                    
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
            //System.setOut(new PrintStream(new File("output-file-with-final-result.txt")));// Print to file
            System.setOut(new PrintStream(new File("rtest-output-file-with-final-result.txt")));// Print to file
        } catch (Exception e) {
            e.printStackTrace();
        }
        //Print multimap which will have unique values per key, distinct values per key because a hashMultimap is used  
        // System.out.println(Arrays.asList(myMultimap)); 
        //System.out.println(Arrays.asList("\n"+myMultimap.entries()));

        // retrieve and display values
        // get all the set of keys
        SetMultimap<String, Integer> myMultimapforUniqueValues = HashMultimap.create(); //for collecting client id and unique play counts

        //count number of clients played a specified number of unique songs
        int counterForUsersWhoPlayedExactly346 = 0;
        int maxnumberofDistinctPlayed = 0;
        String clientIdOfMaxPlayedDistinct = "";

        //System.out.println("\n");
        for (String key : myMultimap.keySet()) {

            int numberOfKeyValuesCounter = myMultimap.get(key).size();

            //Populate Map with client id ie key and number of unique song ids
            myMultimapforUniqueValues.put(key, numberOfKeyValuesCounter);

            //test for maximum number of distinct songs played
            if (numberOfKeyValuesCounter > maxnumberofDistinctPlayed) {
                maxnumberofDistinctPlayed = numberOfKeyValuesCounter;
                clientIdOfMaxPlayedDistinct = key;
            }

            //test for number of clients with 346 distinct songs played
            if (numberOfKeyValuesCounter == 346) {

                counterForUsersWhoPlayedExactly346++;

            }

            //System.out.println("Client Id "+key+" played "+ numberOfKeyValuesCounter+" unique songs");
        }

        Multiset<Integer> multiset = HashMultiset.create();
        for (Integer x : myMultimapforUniqueValues.values()) {
            multiset.add(x);

        }
        //Print values of DISTINCT PLAY COUNT and CLIENT COUNT
        System.out.println("DISTINCT PLAY COUNT" + " X " + "CLIENT COUNT");
        System.out.println(Arrays.asList(multiset) + "\n");
        
        /**
        //get all key values from myMultimapforUniqueValues in a set 
        Set<String> keys = myMultimapforUniqueValues.keySet();
        // iterate through the key set and display key and values
        for (String key : keys) {

            System.out.println("Client Id = " + key);

            System.out.println("Number of unique songs = " + myMultimapforUniqueValues.get(key) + "\n");

        }*/

//print number of users with specified number of unique played songs
        //System.out.println("\n" + counterForUsersWhoPlayedExactly346 + " clients played 346 unique songs");
        //System.out.println("\n" + maxnumberofDistinctPlayed + " is the maximum number of distinct songs played by clientId " + clientIdOfMaxPlayedDistinct);
//System.out.println("\n"+Arrays.asList(myMultimapforUniqueValues));

    }

}


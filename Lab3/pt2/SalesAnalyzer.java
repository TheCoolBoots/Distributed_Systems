package Lab3.pt2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.io.FileWriter;
import java.util.Enumeration;
import java.util.Collections;

/**
 * for each line item: lookup salesID get date add lineItem quantity to
 * keyTable[date]
 * 29, 2019-11-03, 15:27:07, 13, 6
 */

class SalesAnalyzer {

    public static void analyzeSales(String salesFilepath) throws IOException {

        Hashtable<String, LinkedList<String>> numSalesByDate = new Hashtable<String, LinkedList<String>>();

        BufferedReader br = new BufferedReader(new FileReader(salesFilepath));
        String currentLine;

        while ((currentLine = br.readLine()) != null) {
            String[] tokens = currentLine.split(", ");

            if(tokens.length != 5)
                System.out.println("INFO: bad input skipped");
            else {
                String salesDate = tokens[1];
                String timeAndId = String.format("(%s, %s)", tokens[2], tokens[0]);

                if (!numSalesByDate.containsKey(salesDate)){
                    numSalesByDate.put(salesDate, new LinkedList<String>());
                }

                numSalesByDate.get(salesDate).add(timeAndId);
                Collections.sort(numSalesByDate.get(salesDate));
            }
        }

        br.close();

        writeToFile(numSalesByDate);
    }

    private static void writeToFile(Hashtable<String, LinkedList<String>> table) throws IOException{

        Enumeration<String> keys = table.keys();
        LinkedList<String> outputLines = new LinkedList<String>();

        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            outputLines.add(String.format("%s %s\n", key, String.join(", ", table.get(key))));
        }

        Collections.sort(outputLines);

        FileWriter fw = new FileWriter("analysis.out");

        for(String line : outputLines){
            fw.write(line);
        }

        fw.close();
    }

    public static void main(String[] args) {
        try {
            analyzeSales("sales.out");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
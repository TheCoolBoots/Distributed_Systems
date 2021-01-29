package Lab1_2;
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
 */

class SalesAnalyzer {

    public static void analyzeSales(String salesFilepath) throws IOException {

        // LinkedList<String> salesDates = importSalesDates(salesFilepath);

        Hashtable<String, Integer> numSalesByDate = new Hashtable<String, Integer>();

        BufferedReader br = new BufferedReader(new FileReader(salesFilepath)); // creates a buffering character input
                                                                                  // stream

        String currentLine;

        while ((currentLine = br.readLine()) != null) {
            String[] tokens = currentLine.split(", ");

            if(tokens.length != 5)
                System.out.println("INFO: bad input skipped");
            else {
                String salesDate = tokens[1];

                if (numSalesByDate.containsKey(salesDate)) {
                    numSalesByDate.put(salesDate, numSalesByDate.get(salesDate) + 1);
                } else {
                    numSalesByDate.put(salesDate, 1);
                }
            }
        }

        br.close();

        writeToFile("analysis.out", numSalesByDate);
    }

    private static void writeToFile(String filepath, Hashtable<String, Integer> table) throws IOException{

        Enumeration<String> keys = table.keys();
        LinkedList<String> outputLines = new LinkedList<String>();

        while(keys.hasMoreElements()){
            String key = keys.nextElement();
            outputLines.add(String.format("%s %d\n", key, table.get(key)));
        }

        Collections.sort(outputLines);
        
        FileWriter fw = new FileWriter(filepath);

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
package Lab1_2;
import java.util.Random;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Time;
import java.time.LocalDate;

/**
 * store = [store id], [store name], [street address], [city], [zip], [state], [phone number]
 * customer = [customer id], [first last name], [birthdate], [street address], [city], [zip], [state], [phone number]
 * sales = [sale id], [date], [time], [storeID], [customerID]
 * product = [prod id], [description], [price]
 * line item = [line id], [sale id], [product id], [quantity] 
 * 
 * 
 * need databases of:
 *      first names
 *      last names
 *      store names
 *      addresses [street address] [city] [zip] [state]
 *      product descriptions
 */

public class CreateDatabases{

    public static void generateData(int numStores, int numCustomers, int numSales, int numProducts, int numLineItems){
        generateCustomerData("customers.out", numCustomers);
        generateStoreData("store.out", numStores);
        generateSalesData("sales.out", numSales, numStores, numCustomers);
        generateProductData("products.out", numProducts);
        generateLineItemData("lineItems.out", numSales, numProducts);
    }

    public static void generateStoreData(String outputFilepath, int numStores){
        try{
            String[] storeNames = getStringsFromFile("StoreNames.txt", numStores);
            Address[] storeAddresses =  getAddressesFromFile("Addresses.txt", numStores);
            String[] storePhoneNumbers = generatePhoneNumbers(numStores);

            FileWriter fw = new FileWriter(outputFilepath);
 
            //store = [store id], [store name], [street address], [city], [zip], [state], [phone number]
            for (int i = 0; i < numStores; i++) {
                String line = String.format("%d, %s, %s, %s, %s, %s, %s\n", i, storeNames[i], storeAddresses[i].getStreetAddr(), 
                   storeAddresses[i].getCity(), storeAddresses[i].getZip(), storeAddresses[i].getState(), storePhoneNumbers[i]);
                fw.write(line);
            }
         
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateCustomerData(String outputFilepath, int numCustomers){
        try{
            String[] names = getNamesFromFiles("FirstNames.txt", "LastNames.txt", numCustomers);
            String[] birthdates = generateDates(80, 16, numCustomers); 
            Address[] custAddresses =  getAddressesFromFile("Addresses.txt", numCustomers);
            String[] custPhoneNumbers = generatePhoneNumbers(numCustomers);

            FileWriter fw = new FileWriter(outputFilepath);
            
            // customer = [customer id], [first last name], [birthdate], [street address], [city], [zip], [state], [phone number]
            for (int i = 0; i < numCustomers; i++) {
                String formatString = "%d, %s, %s, %s, %s, %s, %s, %s\n";
                String line = String.format(formatString, i, names[i], birthdates[i], custAddresses[i].getStreetAddr(), custAddresses[i].getCity(),
                    custAddresses[i].getZip(), custAddresses[i].getState(), custPhoneNumbers[i]);
                fw.write(line);
            }
         
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateSalesData(String outputFilepath, int numSales, int numStores, int numCustomers){
        try{
            String[] times = generateTimes(numSales);
            String[] dates = generateDates(2, 0, numSales); 

            FileWriter fw = new FileWriter(outputFilepath);
            Random rand = new Random();
            
            // sales = [sale id], [date], [time], [storeID], [customerID]
            for (int i = 0; i < numCustomers; i++) {
                String formatString = "%d, %s, %s, %d, %d\n";
                String line = String.format(formatString, i, dates[i], times[i], rand.nextInt(numStores), rand.nextInt(numCustomers));
                fw.write(line);
            }
         
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void generateProductData(String outputFilepath, int numProducts){
        try{
            String[] descriptions = getStringsFromFile("ProductDescriptions.txt", numProducts);

            FileWriter fw = new FileWriter(outputFilepath);
            Random rand = new Random();
            
            // product = [prod id], [description], [price]
            for (int i = 0; i < numProducts; i++) {
                String formatString = "%d, %s, %s\n";
                String line = String.format(formatString, i, descriptions[i], String.format("$%d.%d", rand.nextInt(100), rand.nextInt(100)));
                fw.write(line);
            }
         
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public static void generateLineItemData(String outputFilepath, int numSales, int numProducts){
        //line item = [line id], [sale id], [product id], [quantity]
        try{
            FileWriter fw = new FileWriter(outputFilepath);
            Random rand = new Random();
            
            for (int i = 0; i < numProducts; i++) {
                String formatString = "%d, %d, %d, %d\n";
                String line = String.format(formatString, i, rand.nextInt(numSales), rand.nextInt(numProducts), rand.nextInt(10));
                fw.write(line);
            }
         
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }

    public static String[] generatePhoneNumbers(int num){
        Random random = new Random();

        String[] phoneNumbers = new String[num];

        for(int i = 0; i<num; i++){
            int areaCode = random.nextInt(900) + 100;
            int firstBit = random.nextInt(900) + 100;
            int lastBit = random.nextInt(10000);

            phoneNumbers[i] = String.format("(%d) %d-%d", areaCode, firstBit, lastBit);
        }
        
        return phoneNumbers;
    }

    public static String[] generateDates(int maxYearsAgo, int minYearsAgo, int num){
        Random random = new Random();

        String[] dates = new String[num];

        for(int i = 0; i < num; i++){
            int minDay = (int) LocalDate.of(2020-maxYearsAgo, 1, 1).toEpochDay();
            int maxDay = (int) LocalDate.of(2020-minYearsAgo, 1, 1).toEpochDay();
            long randomDay = minDay + random.nextInt(maxDay - minDay);
    
            LocalDate randomDate = LocalDate.ofEpochDay(randomDay);

            dates[i] = randomDate.toString();
        }

        // Arrays.sort(dates);
        return dates;
    }

    public static String[] generateTimes(int num){
        Random random = new Random();

        String[] times = new String[num];

        for(int i = 0; i < num; i++){
            int millisInDay = 24*60*60*1000;
            Time time = new Time((long)random.nextInt(millisInDay));
            times[i] = time.toString();
        }
        return times;
    }

    public static Address[] getAddressesFromFile(String filepath, int num) throws IOException{
        String fileContents = null;

        fileContents = Files.readString(Paths.get(filepath));
        String[] lines = fileContents.trim().split("\n");

        Random rand = new Random();

        Address[] addresses = new Address[num];
        for(int i = 0; i < num; i++){
            addresses[i] = new Address(lines[rand.nextInt(lines.length)]);
        }

        return addresses;
    }

    public static String[] getNamesFromFiles(String firstNamesFilepath, String lastNamesFilepath, int num) throws IOException{
        String firstNameContents = Files.readString(Paths.get(firstNamesFilepath));
        String lastNameContents = Files.readString(Paths.get(lastNamesFilepath));

        String[] firstNames = firstNameContents.split("\n");
        String[] lastNames = lastNameContents.split("\n");

        Random rand = new Random();

        String[] names = new String[num];
        for(int i = 0; i < num; i++){
            names[i] = String.join(" ", firstNames[rand.nextInt(firstNames.length)].trim(), lastNames[rand.nextInt(lastNames.length)].trim());
        }
        // Arrays.sort(names);
        return names;
    }

    public static String[] getStringsFromFile(String filepath, int num) throws IOException{
        String fileContents = null;

        fileContents = Files.readString(Paths.get(filepath));
        String[] lines = fileContents.split("\n");

        Random rand = new Random();

        String[] strings = new String[num];
        for(int i = 0; i < num; i++){
            strings[i] = lines[rand.nextInt(lines.length)].trim();
        }

        return strings;
    }

    public static void main(String[] args){
        // System.out.println(CreateDatabases.generatePhoneNumber());
        // generateStoreData("stores.out", 10);
        // generateCustomerData("customers.out", 10);
        generateData(30, 30, 30, 30, 30);
    }
}
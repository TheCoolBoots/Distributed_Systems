package Lab1_2;

public class Address{

    private String streetAddr;
    private String city;
    private String zip;
    private String state;

    public Address(String fromDatabase){
        String[] streetCity = fromDatabase.split(",");
        this.streetAddr = streetCity[0].trim();

        String[] rest = streetCity[1].trim().split(" ");
        this.city = rest[0];
        this.zip = rest[1];
        this.state = rest[2];
    }

    public String getStreetAddr(){
        return streetAddr;
    }

    public String getCity(){
        return city;
    }

    public String getZip(){
        return zip;
    }

    public String getState(){
        return state;
    }

    // public static void main(String[] args){
    //     Address test = new Address("425 Route 31, Macedon NY 14502");
    //     System.out.println(test.getStreetAddr());
    //     System.out.println(test.getCity());
    //     System.out.println(test.getZip());
    //     System.out.println(test.getState());
    // }
}
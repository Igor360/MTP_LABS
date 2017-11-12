package LAB8;

import LAB6.WordHandler;

public class Interface {
    public static void main(String [] args){
        try {
            Client client = new Client();
            client.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

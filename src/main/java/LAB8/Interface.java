package LAB8;

import LAB6.WordHandler;

public class Interface {
    public static void main(String [] args){
        try {
            Client.getListLargestStars();
            Client.getListLargestCommited();
            Client.getInformationAboutRepository();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

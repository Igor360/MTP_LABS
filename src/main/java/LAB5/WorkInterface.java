package LAB5;

import java.io.IOException;
import java.util.Scanner;


/**
 *  console commands
 */
enum UICommands{
    exit,
    help,
    author,
    jason,
    orgjson,
    gson,
}


public class WorkInterface {

    private static String command = new String();
    private static Scanner scanner = new Scanner(System.in);

    public static void main (String [] args){
        setCommand(scanner);
    }




    /**
     *  start the program
     */
    public static void start(){

    }

    /**
     * get command from keyboard
     * @param inputData
     */
    public static void setCommand(Scanner inputData){ //
        do{
            try{
                System.out.println("Введите команду: ");
                command = inputData.next();
                break;
            }catch (Exception e){
                inputData = new Scanner(System.in);
                System.out.println("Даные введение не верно!!!!");
                continue;
            }
        }while (true);
        commandProcessing();
    }

    /**
     *  show on screen all commands
     */
    public static void showCommands(){
        System.out.println("Все команди: ");
        for (UICommands comand: UICommands.values()) {
            System.out.println(comand.toString());
        }

    }

    /**
     * method who processing commands typed in from keyboard
     */
    public static void commandProcessing(){
         // test data

        Employeer director = new Employeer("ffff","fff","ffff","099900000", "Kiev");
        Company company = new Company("ESR", "Kiev", "6-00-99");
        director.setCompany(company);
        director.addEmployee(new Employee("fdf","dfd","dff","099559555","kiev"));


        //

        long start = 0, end = 0;
        UICommands comand = null;
            try {
                comand = UICommands.valueOf(command);
            }catch (Exception e){
                System.out.println("Не верно введена команда");
                setCommand(scanner);
            }
            switch (comand){
                case jason:
                    start = System.nanoTime();
                    try {
                        JASONConvert.toJSON(director);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    end = System.nanoTime();
                    System.out.printf("JASON  serialize time: %s \n\r", WorkInterface.changeTimeFormat(end - start));
                    start = System.nanoTime();
                    try {
                        Employeer test = JASONConvert.toJavaObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    end = System.nanoTime();
                    System.out.printf("JASON  deserialize time: %s \n\r", WorkInterface.changeTimeFormat(end - start));
                    break;
                case orgjson:
                    start = System.nanoTime();
                    try {
                        OrgJsonConvert.toJSON(director);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    end = System.nanoTime();
                    System.out.printf("ORG.JSON  serialize time: %s \n\r", WorkInterface.changeTimeFormat(end - start));
                    start = System.nanoTime();
                    try {
                        Employeer test = OrgJsonConvert.toJavaObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    end = System.nanoTime();
                    System.out.printf("ORG.JSON  deserialize time: %s \n\r", WorkInterface.changeTimeFormat(end - start));
                    break;
                case gson:
                    start = System.nanoTime();
                    try {
                        JASONConvert.toJSON(director);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    end = System.nanoTime();
                    System.out.printf("GSON  serialize time: %s \n\r", WorkInterface.changeTimeFormat(end - start));
                    start = System.nanoTime();
                    try {
                        Employeer test = GoogleJsonConvert.toJavaObject();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    end = System.nanoTime();
                    System.out.printf("GSON  deserialize time: %s \n\r", WorkInterface.changeTimeFormat(end - start));
                    break;
                case author:
                    System.out.printf("Davidenko Igor \n\r");
                    return;
                case exit:
                    return;
                case help:
                    showCommands();
                    break;
                default:
                    System.out.printf("Чото не то ТЫ МНЕ НАПИСАВ !!!! \n\r");
                    break;
            }
            setCommand(scanner);
    }


    /**
     * method who represent numbers in next format : "1 000 000"
     * @param time
     * @return
     */
    public static String changeTimeFormat(long time){
        char [] textArray = Long.toString(time).toCharArray();
        int iter = 0;
        StringBuffer finalyTime = new StringBuffer();
        for (int i = textArray.length - 1; i >= 0; i--) {
            finalyTime.append(textArray[iter]);
            if (i%3 == 0)
                finalyTime.append(" ");
            iter++;
        }
        return finalyTime.toString();
    }
}

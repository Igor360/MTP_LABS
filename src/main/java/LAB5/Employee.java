package LAB5;

public class Employee extends Worker {
    private boolean isOnWork = false;

    public Employee(){
        super();
    }

    public Employee(String firstName, String secondName, String lastName, String number, String address){
        super(firstName, secondName, lastName, number, address);
    }
}

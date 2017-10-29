package LAB5;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Random;

public class Worker {

    @JsonProperty("id")
    public int id = 0;

    @JsonProperty("firstName")
    public String firstName = null;

    @JsonProperty("secondName")
    public String secondName = null;

    @JsonProperty("lastNumber")
    public String lastName = null;

    @JsonProperty("number")
    public String number = null;

    @JsonProperty("address")
    public String address = null;

    public Worker(){
        this.id = 0;
        this.firstName = null;
        this.secondName = null;
        this.lastName = null;
        this.number = null;
    }

    public Worker(String firstName, String secondName, String lastName, String number, String address){
        this.id = (new Random()).nextInt();
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.number = number;
        this.address = address;
    }

}

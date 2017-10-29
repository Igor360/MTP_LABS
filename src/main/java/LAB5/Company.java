package LAB5;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Random;

public class Company {

    @JsonProperty("id")
    public int id;

    @JsonProperty("nameCompany")
    public String name;

    @JsonProperty("address")
    public String address;

    @JsonProperty("phone")
    public String phone;

    public Company()
    {
        this.id = 0;
        this.name = null;
        this.address = null;
        this.phone = null;
    }

    public Company(String name, String adress, String phone){
        this.id = (new Random()).nextInt();
        this.name = name;
        this.address = adress;
        this.phone = phone;
    }
}

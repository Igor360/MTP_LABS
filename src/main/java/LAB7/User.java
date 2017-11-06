package LAB7;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.*;


public class User {


    @NotNull
    private int id;


    @NotNull
    @Size(min = 2, max = 64)
    private String username;

    @NotNull
    @Size(min = 6)
    private String password;

    @NotNull
    @Email
    private String email;


    @AssertTrue
    private boolean isInSystem = true;

    @Positive
    private double money = 0.0;

    @Positive
    private long numVisit = 0;

    @NotNull
    private Timer timer = new Timer();

    private List<@NotNull Object> shopping = new ArrayList<>();

    private Map<@NotNull String, @Min(0) Integer> thing = new HashMap<>();

    @Valid
    private UserDate birthday = new UserDate(0, 0, 0);

    @NotNull
    private byte[] image = new byte[256];

    public User(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isInSystem() {
        return isInSystem;
    }

    public void setInSystem(boolean inSystem) {
        isInSystem = inSystem;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public long getNumVisit() {
        return numVisit;
    }

    public void setNumVisit(long numVisit) {
        this.numVisit = numVisit;
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    public List<Object> getShopping() {
        return shopping;
    }

    public void setShopping(Object shop) {
        this.shopping.add(shop);
    }

    public Map<String, Integer> getThing() {
        return thing;
    }

    public void setThing(Map<String, Integer> thing) {
        this.thing = thing;
    }

    public UserDate getBirthday() {
        return birthday;
    }

    public void setBirthday(UserDate birthday) {
        this.birthday = birthday;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

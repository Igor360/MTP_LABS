package LAB7;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class UserDate {
    @Setter
    @Getter
    @Max(31) @Min(1)
    private int day;

    @Setter @Getter
    @Max(12) @Min(1)
    private int month;

    @Setter @Getter
    @Max(9999) @Min(0)
    private int year;

    public UserDate(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }

    @Override
    public String toString(){
        return String.format("%s-%s-%s", day,month,year);
    }
}

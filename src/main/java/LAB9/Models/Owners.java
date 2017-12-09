package LAB9.Models;

import lombok.Builder;
import lombok.Getter;

public class Owners {
    @Getter
    private String nameUser = null;
    @Getter
    private String repositiory = null;

    public Owners(String nameUser, String repositiory) {
        this.nameUser = nameUser;
        this.repositiory = repositiory;
    }

    @Override
    public String toString(){
        return String.format("Login: %s \t Repository: %s \n", nameUser, repositiory);
    }
}

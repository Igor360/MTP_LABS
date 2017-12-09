package LAB9.GitClasses;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Languages {



    @Getter @Setter
    private String name;

    public Languages(String name){
        this.name = name;
    }
}

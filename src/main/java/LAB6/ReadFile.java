package LAB6;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
    private FileReader fileReader = null;
    private BufferedReader bufReader = null;
    public String pathToFile = null;
    private List<String> words  = new ArrayList<String>();


    public List<String> getWords() {
        return words;
    }

    public ReadFile(String path) throws Exception{
        this.pathToFile = path;
        this.fileReader = new FileReader(path);
        this.bufReader = new BufferedReader(this.fileReader);
    }

    public void read() throws Exception{
        String readData = null;
        while ((readData = this.bufReader.readLine()) != null){
            this.words.add(readData);
        }
    }


}

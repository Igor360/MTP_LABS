package LAB6;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * it is class for reading our file
 */
public class ReadFile {
    private FileReader fileReader = null;
    private BufferedReader bufReader = null;
    public String pathToFile = null;
    private Map<Integer,List<String>> dictionaryWords = new HashMap<Integer, List<String>>();


    /**
     * class constructor
     * @param path - file name
     * @throws Exception
     */
    public ReadFile(String path) throws Exception{
        this.pathToFile = path;
        this.fileReader = new FileReader(path);
        this.bufReader = new BufferedReader(this.fileReader);
    }


    /**
     * getter for variable  dictionaryWords
     * @return
     */
    public Map<Integer, List<String>> getDictionaryWords() {
        return dictionaryWords;
    }


    /**
     * method whose read our file and add read data to our dictionary
     * @throws Exception
     */
    public void read() throws Exception{
        String readData = null;
        String word = null;
        int sizeWord = 0;
        List<String> listwords = new ArrayList<String>();
        while ((readData = this.bufReader.readLine()) != null){
            word = readData.trim();
            sizeWord = readData.length();
            if(dictionaryWords.containsKey(sizeWord)){
                dictionaryWords.get(sizeWord).add(word);
            }else {
                listwords.add(word);
                dictionaryWords.put(sizeWord, listwords);
            }
            sizeWord = 0;
            listwords = new ArrayList<String>();
            readData = null;
            word = null;
        }
    }


}

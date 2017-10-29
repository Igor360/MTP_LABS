package LAB6;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadFile {
    private FileReader fileReader = null;
    private BufferedReader bufReader = null;
    public String pathToFile = null;
    private String readData = null;


    private boolean isRead = false;


    public ReadFile(String path) throws Exception{
            this.pathToFile = path;
            this.fileReader = new FileReader(path);
            this.bufReader = new BufferedReader(this.fileReader);
    }

    public void read() throws Exception{
            this.readData = this.bufReader.readLine();
            if (this.readData != null) {
                this.isRead = true;
            }else {
                this.isRead = false;
            }
    }

    public String getReadData(){
            return this.readData;
    }

}

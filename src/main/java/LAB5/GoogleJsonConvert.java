package LAB5;

import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GoogleJsonConvert {

    private final static String baseFile = "Company_2.json";

    public static void toJSON(Employeer employeer) throws IOException {
        FileWriter writer = new FileWriter(baseFile);
        Gson serilize = new Gson();
        writer.write(serilize.toJson(employeer));
        System.out.println("json created!");
        writer.close();
    }

    public static Employeer toJavaObject() throws IOException {
        FileReader reader = new FileReader(baseFile);
        Gson gson = new Gson();
        return gson.fromJson(reader, Employeer.class);
    }
}

package LAB5;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JASONConvert {
    private final static String baseFile = "Company.json";

    public static void toJSON(Employeer employeer) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), employeer);
        System.out.println("json created!");
    }

    public static Employeer toJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(baseFile), Employeer.class);
    }
}

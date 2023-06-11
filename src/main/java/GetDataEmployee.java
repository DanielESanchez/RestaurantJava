import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GetDataEmployee {
    //private static final String fileName = "chef.json";


    public List<Employee> getData(String fileName) throws IOException {
        GetFileData app = new GetFileData();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        String data = app.getFileFromResourceAsStream(fileName);
        //String data = convertInputStreamToString(is);
        List<Employee> list = Arrays.asList(mapper.readValue(data, Employee[].class));
        //list.stream().forEach(x -> System.out.println(x.name));
        return list;
    }
}

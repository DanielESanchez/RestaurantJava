import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GetDataEmployee {

    public List<Employee> getData(String fileName) throws IOException {
        GetFileData app = new GetFileData();
        ObjectMapper mapper = new ObjectMapper();
        String data = app.getFileFromResourceAsStream(fileName);
        List<Employee> list = Arrays.asList(mapper.readValue(data, Employee[].class));
        return list;
    }
}

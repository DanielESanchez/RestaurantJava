import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GetDataTable {
    private static final String fileName = "table.json";
    public List<Table> getData() throws IOException {
        GetFileData app = new GetFileData();
        ObjectMapper mapper = new ObjectMapper();
        String data = app.getFileFromResourceAsStream(fileName);
        List<Table> list = Arrays.asList(mapper.readValue(data, Table[].class));
        return list;
    }
}

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GetDataMenu {
    private static final String fileName = "menu.json";

    public List<Menu> getData() throws IOException {
        GetFileData app = new GetFileData();
        ObjectMapper mapper = new ObjectMapper();
        String data = app.getFileFromResourceAsStream(this.fileName);
        List<Menu> list = Arrays.asList(mapper.readValue(data, Menu[].class));
        return list;
    }
}

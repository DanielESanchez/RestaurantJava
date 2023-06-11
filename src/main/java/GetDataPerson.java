import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.Arrays;
import java.util.List;

public class GetDataPerson {
    private static final String fileName = "person.json";


    public List<Person> getData() throws IOException {
        GetFileData app = new GetFileData();
        ObjectMapper mapper = new ObjectMapper();
        String data = app.getFileFromResourceAsStream(this.fileName);
        //String data = convertInputStreamToString(is);
        List<Person> list = Arrays.asList(mapper.readValue(data, Person[].class));
        //list.stream().forEach(x -> System.out.println(x.name));
        return list;
    }




}




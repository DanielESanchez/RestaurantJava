import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GetDataTable {
    GetFileData getFileData;
    private static final String fileName = StaticVariables.TABLE_FILE_NAME;
    public GetDataTable(GetFileData getFileData){
        this.getFileData = getFileData;
    }
    public List<Table> getData() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String data = getFileData.getFileFromResourceAsStream(fileName);
        return Arrays.asList(mapper.readValue(data, Table[].class));
    }

    public List<Table> getAvailableTables(List<Table> tables){
        List<Table> availableTables = new ArrayList<>();
        for(Table table : tables){
            if(table.getIsEmpty()) availableTables.add(table);
        }
        return availableTables;
    }
}

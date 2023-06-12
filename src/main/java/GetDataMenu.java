import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class GetDataMenu {
    private static final String fileName = "menu.json";

    public List<Menu> getData() throws IOException {
        GetFileData app = new GetFileData();
        ObjectMapper mapper = new ObjectMapper();
        String data = app.getFileFromResourceAsStream(this.fileName);
        List<Menu> list = Arrays.asList(mapper.readValue(data, Menu[].class));
        return list;
    }


    public List<String> getAvailableCategories(List<Menu> fullMenu){
        List<String> availableCategories = new ArrayList<>();
        for(Menu menu: fullMenu){
            String categoryFromFullMenu = menu.getCategoryName();
            availableCategories.add(categoryFromFullMenu);
        }
        Set<String> setOfCategories = new HashSet<>(availableCategories);
        availableCategories.clear();
        availableCategories.addAll(setOfCategories);
        return availableCategories;
    }

    public List<Menu> getMenuByCategoryName(List<Menu> fullMenu, String categoryToSearch){
        List<Menu> menByCategory = new ArrayList<>();
        for(Menu menu: fullMenu){
            String categoryFromMenu = menu.getCategoryName();
            if(categoryFromMenu.equals(categoryToSearch)){
                menByCategory.add(menu);
            }
        }
        return menByCategory;
    }
}

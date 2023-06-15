import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

public class GetDataMenu {
    private static final String fileName = StaticStrings.MENU_FILE_NAME;

    public List<MenuItem> getData() throws IOException {
        GetFileData app = new GetFileData();
        ObjectMapper mapper = new ObjectMapper();
        String data = app.getFileFromResourceAsStream(this.fileName);
        List<MenuItem> list = Arrays.asList(mapper.readValue(data, MenuItem[].class));
        return list;
    }


    public List<String> getAvailableCategories(List<MenuItem> fullMenuItems){
        List<String> availableCategories = new ArrayList<>();
        for(MenuItem menuItem : fullMenuItems){
            String categoryFromFullMenu = menuItem.getCategoryName();
            availableCategories.add(categoryFromFullMenu);
        }
        Set<String> setOfCategories = new HashSet<>(availableCategories);
        availableCategories.clear();
        availableCategories.addAll(setOfCategories);
        return availableCategories;
    }

    public List<MenuItem> getMenuByCategoryName(List<MenuItem> fullMenuItems, String categoryToSearch){
        List<MenuItem> menByCategory = new ArrayList<>();
        for(MenuItem menuItem : fullMenuItems){
            String categoryFromMenu = menuItem.getCategoryName();
            if(categoryFromMenu.equals(categoryToSearch)){
                menByCategory.add(menuItem);
            }
        }
        return menByCategory;
    }
}

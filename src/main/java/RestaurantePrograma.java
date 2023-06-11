import java.io.IOException;
import java.util.List;

public class RestaurantePrograma {
    public static final int DEFAULT_BUFFER_SIZE = 8192;

    public static void main(String[] args) throws IOException {

        GetDataPerson getDataPerson = new GetDataPerson();
        GetDataMenu getDataMenu = new GetDataMenu();
        System.out.println("Obteniendo Datos Del Menu");
        List<Menu> menu = getDataMenu.getData();
        System.out.println("Obteniendo Datos Del Menu");
        List<Person> people = getDataPerson.getData();
        menu.stream().forEach(x -> System.out.println(x.getName()));
        people.stream().forEach(x -> System.out.println(x.name));
    }


}




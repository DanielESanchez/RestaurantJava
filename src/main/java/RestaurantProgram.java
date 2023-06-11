import java.io.IOException;
import java.util.List;

public class RestaurantProgram {

    public static void main(String[] args) throws IOException {

        GetDataPerson getDataPerson = new GetDataPerson();
        GetDataMenu getDataMenu = new GetDataMenu();
        GetDataEmployee getDataChef = new GetDataEmployee();
        GetDataTable getDataTable = new GetDataTable();
        //List<Person> people = getDataPerson.getData();
        List<Employee> chefs =  getDataChef.getData("chef.json");
        List<Employee> cashiers =  getDataChef.getData("cashier.json");
        List<Employee> waiters =  getDataChef.getData("waiter.json");
        List<Menu> menu = getDataMenu.getData();
        List<Table> tables = getDataTable.getData();
        System.out.println("Obteniendo Datos De Tablas");
        tables.stream().forEach(x -> {
                System.out.println(x.getTableNumber());
                System.out.println(x.getIsEmpty());
        });
    }
}




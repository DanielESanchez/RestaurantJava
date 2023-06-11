import java.io.IOException;
import java.util.List;

public class RestaurantePrograma {

    public static void main(String[] args) throws IOException {

        GetDataPerson getDataPerson = new GetDataPerson();
        GetDataMenu getDataMenu = new GetDataMenu();
        GetDataEmployee getDataChef = new GetDataEmployee();
        List<Menu> menu = getDataMenu.getData();
        //List<Person> people = getDataPerson.getData();
        List<Employee> chefs =  getDataChef.getData("chef.json");
        List<Employee> cashiers =  getDataChef.getData("cashier.json");
        List<Employee> waiters =  getDataChef.getData("waiter.json");
        System.out.println("Obteniendo Datos Del Menu");
        menu.stream().forEach(x -> System.out.println(x.getName()));
        System.out.println("---------------------");
        /*System.out.println("Obteniendo Datos Del Personas");
        people.stream().forEach(x -> System.out.println(x.getName()));*/
        System.out.println("Datos de Chef");
        System.out.println("---------------------");
        chefs.stream().forEach(x -> {
                System.out.println(x.getName());
                System.out.println(x.getHireDate());
                System.out.println(x.getSalary());
                System.out.println(x.getIsWorking());
                System.out.println(x.getJob());
                System.out.println("---------------------");
        });
        System.out.println("Datos de Waiters");
        System.out.println("---------------------");
        waiters.stream().forEach(x -> {
            System.out.println(x.getName());
            System.out.println(x.getHireDate());
            System.out.println(x.getSalary());
            System.out.println(x.getIsWorking());
            System.out.println(x.getJob());
            System.out.println("---------------------");
        });
        System.out.println("Datos de Cashiers");
        System.out.println("---------------------");
        cashiers.stream().forEach(x -> {
            System.out.println(x.getName());
            System.out.println(x.getHireDate());
            System.out.println(x.getSalary());
            System.out.println(x.getIsWorking());
            System.out.println(x.getJob());
            System.out.println("---------------------");
        });
    }
}




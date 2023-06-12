import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RestaurantProgram {
    private static final String closeMessage = "You did not write correct answer. " +
            "If you want to close the program press ENTER, " +
            "else write anything to start again";
    private static final String completedMessage = "The process was completed. " +
            "If you want to close the program press ENTER, " +
            "else write anything to start again";

    public static void main(String[] args) throws IOException {

        GetDataPerson getDataPerson = new GetDataPerson();
        GetDataMenu getDataMenu = new GetDataMenu();
        GetDataEmployee getDataChef = new GetDataEmployee();
        GetDataTable dataTable = new GetDataTable();
        //List<Person> people = getDataPerson.getData();
        List<Employee> chefs =  getDataChef.getData("chef.json");
        List<Employee> cashiers =  getDataChef.getData("cashier.json");
        List<Employee> waiters =  getDataChef.getData("waiter.json");
        List<Menu> menu = getDataMenu.getData();
        List<Table> tables = dataTable.getData();
        Scanner in = new Scanner(System.in);
        //Para obtener lista de categorias
        List<String> availableCategories = getDataMenu.getAvailableCategories(menu);
        for(String category: availableCategories){
            System.out.println(category);
        }
        //para obtener lista de menu por categoria
        List<Menu> menuByCategory = getDataMenu.getMenuByCategoryName(menu,"Dessert");
        for(Menu menuItem: menuByCategory){
            System.out.println(menuItem.getName());
            System.out.println(menuItem.getCategoryName());
        }
        String keepRunningProgram = "continue";
        while(!keepRunningProgram.equals("")) {
            System.out.println("--------------------------------------------");
            System.out.println("--------------------------------------------");
            System.out.println("Restaurant");
            System.out.println("Tables Available: " + dataTable.getAvailableTables(tables));
            System.out.println("Choose an option from below and write the number to start the process");
            System.out.println("1 - Assign table");
            System.out.println("2 - Add To Order");
            System.out.println("3 - Start Billing Process");
            int answerOption = in.nextInt();
            if (answerOption != 1 && answerOption != 2) {
                System.out.println(closeMessage);
                keepRunningProgram = in.nextLine();
                continue;
            }
            if(answerOption == 1){
                System.out.println(completedMessage);
                keepRunningProgram = in.nextLine();
                continue;
            }
            if(answerOption == 2){
                System.out.println(completedMessage);
                keepRunningProgram = in.nextLine();
                continue;
            }
            System.out.println("Table Assigned");
        }

    }

}




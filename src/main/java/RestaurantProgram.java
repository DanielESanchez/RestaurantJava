import java.io.IOException;
import java.util.ArrayList;
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
        GetDataEmployee getDataEmployee = new GetDataEmployee();
        GetDataTable dataTable = new GetDataTable();
        List<Employee> chefs =  getDataEmployee.getData("chef.json");
        List<Employee> cashiers =  getDataEmployee.getData("cashier.json");
        List<Employee> waiters =  getDataEmployee.getData("waiter.json");
        List<MenuItem> menuItems = getDataMenu.getData();
        List<Table> tables = dataTable.getData();
        List<Order> orders = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        List<String> availableCategories = getDataMenu.getAvailableCategories(menuItems);
        List<MenuItem> menuItemByCategory = getDataMenu.getMenuByCategoryName(menuItems,"Dessert");
        String keepRunningProgram = "continue";
        while(!keepRunningProgram.equals("")) {
            System.out.println("--------------------------------------------\n" +
                    "--------------------------------------------\n" +
                    "Restaurant System\n" +
                    "Choose an option from below and write the number to start the process\n" +
                    "1 - Assign table\n" +
                    "2 - Add To Order\n" +
                    "3 - Start Billing Process");
            String answerOption = in.nextLine();
            if(answerOption.equals("1")){
                List<Table> availableTables = dataTable.getAvailableTables(tables);
                if(availableTables.size() < 1){
                    System.out.println("There is no table available. If you want to close the program press ENTER, else write anything to start again");
                    keepRunningProgram = in.nextLine();
                    continue;
                }
                System.out.println("Available Tables:");
                for (Table table: availableTables){
                    System.out.println(table.getTableNumber());
                }
                System.out.println("Please write a number from below to assign:");
                String numberTableSelected = in.nextLine();
                Table tableToAssign = null;
                for (Table table: availableTables){
                    if(numberTableSelected.equals( Integer.toString(table.getTableNumber())) ){
                        tableToAssign = table;
                        System.out.println("Table Assigned "+ table.getTableNumber());
                        Employee waiterAssigned = getDataEmployee.getEmployeeToAssign(waiters, "waiter");
                        System.out.println("Waiter Assigned "+ waiterAssigned.getName() + " " + waiterAssigned.getLastName());
                        Order newOrder = new Order(waiterAssigned, tableToAssign);
                        orders.add(newOrder);
                        int indexSelectedTable = tables.indexOf(table);
                        tables.get(indexSelectedTable).changeStatusTable();
                        System.out.println(completedMessage);
                    }
                }
                if(tableToAssign == null){
                    System.out.println(closeMessage);
                    keepRunningProgram = in.nextLine();
                    continue;
                }
            }
            else if(answerOption.equals("2")){
                System.out.println(completedMessage);
            }
            else{
                System.out.println(closeMessage);
            }
            System.out.println("Actual Orders: "+ orders.size());
            keepRunningProgram = in.nextLine();
        }

    }

}




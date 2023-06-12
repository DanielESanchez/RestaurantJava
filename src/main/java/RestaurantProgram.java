import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantProgram {

    private static final String WELCOME_MESSAGE = "--------------------------------------------\n" +
            "--------------------------------------------\n" +
            "Restaurant System\n" +
            "Choose an option from below and write the number to start the process\n" +
            "1 - Assign table\n" +
            "2 - Add To Order\n" +
            "3 - Start Billing Process";
    private static final String CLOSE_MESSAGE = "You did not write correct answer. " +
            "If you want to close the program press ENTER, " +
            "else write anything to start again";
    private static final String COMPLETED_MESSAGE = "The process was completed. " +
            "If you want to close the program press ENTER, " +
            "else write anything to start again";
    private static final String NO_TABLE_AVAILABLE = "There is no table available. If you want to close the program press ENTER, else write anything to start again";
    private static final String NO_ORDES_AVAILABLE = "There is no order available. If you want to close the program press ENTER, else write anything to start again";

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
        String keepRunningProgram = "continue";
        while(!keepRunningProgram.equals("")) {
            System.out.println(WELCOME_MESSAGE);
            String answerOption = in.nextLine();
            if(answerOption.equals("1")){
                List<Table> availableTables = dataTable.getAvailableTables(tables);
                if(availableTables.size() < 1){
                    System.out.println(NO_TABLE_AVAILABLE);
                    keepRunningProgram = in.nextLine();
                    continue;
                }
                System.out.println("Available Tables:");
                for (Table table: availableTables){
                    System.out.println(table.getTableNumber());
                }
                System.out.println("Please write a number from above to assign:");
                String numberTableSelected = in.nextLine();
                Table tableToAssign = null;
                for (Table table: availableTables){
                    if(numberTableSelected.equals( Integer.toString(table.getTableNumber())) ){
                        tableToAssign = table;
                        System.out.println("Table Assigned "+ table.getTableNumber());
                        Employee waiterAssigned = getDataEmployee.getEmployeeToAssign(waiters, "waiter", waiters.size());
                        System.out.println("Waiter Assigned "+ waiterAssigned.getName() + " " + waiterAssigned.getLastName());
                        Order newOrder = new Order(waiterAssigned, tableToAssign);
                        int indexEmployeeAssigned = waiters.indexOf(waiterAssigned);
                        waiters.get(indexEmployeeAssigned).setWorking(true);
                        orders.add(newOrder);
                        int indexSelectedTable = tables.indexOf(table);
                        tables.get(indexSelectedTable).changeStatusTable();
                        System.out.println(COMPLETED_MESSAGE);
                    }
                }
                if(tableToAssign == null){
                    System.out.println(CLOSE_MESSAGE);
                    keepRunningProgram = in.nextLine();
                    continue;
                }
            }
            else if(answerOption.equals("2")){
                if(orders.size()<1){
                    System.out.println(NO_ORDES_AVAILABLE);
                    keepRunningProgram = in.nextLine();
                    continue;
                }
                String regexDigit = "\\d+";
                Order selectedOrder;
                System.out.println("--------------------------------");
                System.out.println("Select an order to update from the list below: ");
                for (Order order: orders){
                    int orderNumber = orders.indexOf(order) + 1;
                    System.out.println("Write " + orderNumber + " to choose Table Number " + order.getTable().getTableNumber());
                }
                String selectedOrderIndex = in.nextLine();
                boolean selectedOrderIsNumber = selectedOrderIndex.matches(regexDigit);
                if(!selectedOrderIsNumber){
                    System.out.println(CLOSE_MESSAGE);
                    keepRunningProgram = in.nextLine();
                    continue;
                }
                int selectedOrderIndexInt = Integer.parseInt(selectedOrderIndex);
                if(selectedOrderIndexInt > orders.size() || selectedOrderIndexInt < 1){
                    System.out.println(CLOSE_MESSAGE);
                    keepRunningProgram = in.nextLine();
                    continue;
                }
                selectedOrder = orders.get( selectedOrderIndexInt-1 );
                System.out.println("You have chosen table: " + selectedOrder.getTable().getTableNumber());
                List<String> availableCategories = getDataMenu.getAvailableCategories(menuItems);
                System.out.println("--------------------------------");
                System.out.println("Available Categories");
                int categoryCount = 1;
                for(String category: availableCategories){
                    System.out.println(categoryCount++ + " " + category);
                }
                System.out.println("Write the number corresponding to the category you want to see: ");
                String selectedCategory = in.nextLine();
                String selectedCategoryName = "";
                boolean categoryIsNumber = selectedCategory.matches(regexDigit);
                if(!categoryIsNumber){
                    System.out.println(CLOSE_MESSAGE);
                    keepRunningProgram = in.nextLine();
                    continue;
                }
                int selectedCategoryInt = Integer.parseInt(selectedCategory);
                selectedCategoryName = availableCategories.get( selectedCategoryInt-1 );
                System.out.println("--------------------------------");
                System.out.println("Showing all menu items for: " + selectedCategoryName);
                List<MenuItem> itemsByCategory = getDataMenu.getMenuByCategoryName(menuItems, selectedCategoryName);
                for(MenuItem menuItem: itemsByCategory){
                    System.out.println("--------------------------------");
                    System.out.println("Id: " + menuItem.getId());
                    System.out.println("Name: " + menuItem.getName());
                    System.out.println("Description: " + menuItem.getDescription());
                    System.out.println(menuItem.getPrice() + " $");
                    System.out.println("--------------------------------");
                }
                System.out.println("Write the ID corresponding to the item you want to choose");
                String itemToChoose = in.nextLine();
                MenuItem selectedMenuItem = null;
                for(MenuItem menuItem: itemsByCategory){
                    if(itemToChoose.equals(menuItem.getId())){
                        selectedMenuItem = menuItem;
                        break;
                    }
                }
                if(selectedMenuItem == null){
                    System.out.println(CLOSE_MESSAGE);
                    keepRunningProgram = in.nextLine();
                    continue;
                }
                System.out.println("You added: " + selectedMenuItem.getName());
                Employee chefAssigned = getDataEmployee.getEmployeeToAssign(chefs, "chef", chefs.size());
                int indexChefAssigned = chefs.indexOf(chefAssigned);
                chefs.get(indexChefAssigned).setWorking(true);
                OrderItem orderItemToAdd = new OrderItem(chefAssigned, selectedMenuItem);
                selectedOrder.addItemToOrder(orderItemToAdd);
                System.out.println(COMPLETED_MESSAGE);
            }
            else if(answerOption.equals("3")){
                System.out.println(COMPLETED_MESSAGE);
            }
            else{
                System.out.println(CLOSE_MESSAGE);
            }
            keepRunningProgram = in.nextLine();
        }

    }

}




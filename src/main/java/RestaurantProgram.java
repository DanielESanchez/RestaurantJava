import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantProgram {
    List<Employee> chefs;
    List<Employee> cashiers;
    List<Employee> waiters;
    List<MenuItem> menuItems;
    List<Table> tables;
    List<Order> orders;
    GetDataMenu getDataMenu;
    GetDataEmployee getDataEmployee;
    GetDataTable dataTable;
    Scanner in;

    public static void main(String[] args) throws IOException {
        RestaurantProgram restaurantProgram = new RestaurantProgram();
        restaurantProgram.start();
    }

    protected void start() throws IOException {
        String keepRunningProgram = StaticStrings.KEEP_RUNNING_PROGRAM_INITIAL;
        getDataMenu = new GetDataMenu();
        getDataEmployee = new GetDataEmployee();
        dataTable = new GetDataTable();
        chefs =  getDataEmployee.getData(StaticStrings.CHEF_FILE_NAME);
        cashiers =  getDataEmployee.getData(StaticStrings.CASHIER_FILE_NAME);
        waiters =  getDataEmployee.getData(StaticStrings.WAITER_FILE_NAME);
        menuItems = getDataMenu.getData();
        tables = dataTable.getData();
        orders = new ArrayList<>();
        in = new Scanner(System.in);
        while(!keepRunningProgram.isBlank() || !keepRunningProgram.isEmpty()) {
            System.out.println(StaticStrings.WELCOME_MESSAGE);
            String answerOption = in.nextLine();
            switch (answerOption) {
                case StaticStrings.ASSIGN_TABLE_OPTION ->
                    assignTable();
                case StaticStrings.ADD_TO_ORDER_OPTION ->
                    addToOrder();
                case StaticStrings.GET_ORDER_INFO_OPTION ->
                    getOrderInfo();
                case StaticStrings.START_BILLING_PROCESS_OPTION ->
                    startBillingProcess();
                default ->
                    System.out.println(StaticStrings.CLOSE_MESSAGE_ERROR + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            }
            keepRunningProgram = in.nextLine();
        }
    }

    protected void assignTable(){
        List<Table> availableTables = dataTable.getAvailableTables(tables);
        if (availableTables.size() < 1) {
            System.out.println(StaticStrings.NO_TABLE_AVAILABLE + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        System.out.println(StaticStrings.AVAILABLE_TABLES_TITLE);
        for (Table table : availableTables) {
            System.out.println(table.getTableNumber());
        }
        System.out.println(StaticStrings.WRITE_NUMBER_TEXT);
        String numberTableSelected = in.nextLine();
        Table tableToAssign = null;
        for (Table table : availableTables) {
            if (numberTableSelected.equals(Integer.toString(table.getTableNumber()))) {
                tableToAssign = table;
                System.out.println(StaticStrings.TABLE_ASSIGNED + table.getTableNumber());
                Employee waiterAssigned = getDataEmployee.getEmployeeToAssign(waiters, StaticStrings.WAITER_JOB_NAME, waiters.size());
                System.out.println(StaticStrings.WAITER_ASSIGNED_TEXT + waiterAssigned.getFullName());
                Order newOrder = new Order(waiterAssigned, tableToAssign);
                int indexEmployeeAssigned = waiters.indexOf(waiterAssigned);
                waiters.get(indexEmployeeAssigned).setWorking(true);
                orders.add(newOrder);
                int indexSelectedTable = tables.indexOf(table);
                tables.get(indexSelectedTable).changeStatusTable();
                System.out.println(StaticStrings.COMPLETED_MESSAGE + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            }
        }
        if (tableToAssign == null) {
            System.out.println(StaticStrings.NO_SELECTED_TABLE + StaticStrings.CLOSE_PROGRAM_MESSAGE);
        }
    }

    protected void addToOrder(){
        if (orders.size() < 1) {
            System.out.println(StaticStrings.NO_ORDERS_AVAILABLE + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        Order selectedOrder;
        System.out.println(StaticStrings.SELECT_TABLE_TITLE);
        for (Order order : orders) {
            int orderNumber = orders.indexOf(order) + 1;
            System.out.printf(StaticStrings.CHOOSE_NUMBER_TABLE_TEXT, orderNumber, order.getTable().getTableNumber());
        }
        String selectedOrderIndex = in.nextLine();
        boolean selectedOrderIsNumber = selectedOrderIndex.matches(StaticStrings.REG_EXP_FOR_NUMBER);
        if (!selectedOrderIsNumber) {
            System.out.println(StaticStrings.NO_NUMBER_RECEIVED + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        int selectedOrderIndexInt = Integer.parseInt(selectedOrderIndex);
        if (selectedOrderIndexInt > orders.size() || selectedOrderIndexInt < 1) {
            System.out.println(StaticStrings.ORDER_DOES_NOT_EXISTS + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        selectedOrder = orders.get(selectedOrderIndexInt - 1);
        System.out.println(StaticStrings.CHOSEN_TABLE_TEXT + selectedOrder.getTable().getTableNumber());
        List<String> availableCategories = getDataMenu.getAvailableCategories(menuItems);
        System.out.println(StaticStrings.AVAILABLE_CATEGORIES_TITLE);
        int categoryCount = 1;
        for (String category : availableCategories) {
            System.out.println(categoryCount++ + StaticStrings.SPACE_SEPARATOR + category);
        }
        System.out.println(StaticStrings.WRITE_NUMBER_CATEGORY);
        String selectedCategory = in.nextLine();
        boolean categoryIsNumber = selectedCategory.matches(StaticStrings.REG_EXP_FOR_NUMBER);
        if (!categoryIsNumber) {
            System.out.println(StaticStrings.NO_NUMBER_RECEIVED + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        int selectedCategoryInt = Integer.parseInt(selectedCategory);
        if (selectedCategoryInt> availableCategories.size() || selectedCategoryInt < 1) {
            System.out.println(StaticStrings.CATEGORY_DOES_NOT_EXISTS + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        String selectedCategoryName = availableCategories.get(selectedCategoryInt - 1);
        System.out.printf(StaticStrings.SHOWING_ITEMS_MENU, selectedCategoryName);
        List<MenuItem> itemsByCategory = getDataMenu.getMenuByCategoryName(menuItems, selectedCategoryName);
        for (MenuItem menuItem : itemsByCategory) {
            System.out.printf(StaticStrings.ITEM_MENU_FORMAT, menuItem.getId(), menuItem.getName(),
                    menuItem.getDescription(), menuItem.getPrice()
            );
        }
        System.out.println(StaticStrings.WRITE_ID_TO_CHOOSE_ITEM);
        String itemToChoose = in.nextLine();
        MenuItem selectedMenuItem = null;
        for (MenuItem menuItem : itemsByCategory) {
            if (itemToChoose.equals(menuItem.getId())) {
                selectedMenuItem = menuItem;
                break;
            }
        }
        if (selectedMenuItem == null) {
            System.out.println(StaticStrings.ITEM_DOES_NOT_EXISTS + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        System.out.println(StaticStrings.WRITE_QUANTITY_ORDER);
        String quantityInput = in.nextLine();
        boolean quantityInputIsNumber = quantityInput.matches(StaticStrings.REG_EXP_FOR_NUMBER);
        if (!quantityInputIsNumber) {
            System.out.println(StaticStrings.NO_NUMBER_RECEIVED + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        int quantity = Integer.parseInt(quantityInput);
        System.out.printf(StaticStrings.ADDED_ITEM_TEXT, quantity, selectedMenuItem.getName());
        Employee chefAssigned = getDataEmployee.getEmployeeToAssign(chefs, StaticStrings.CHEF_JOB_NAME, chefs.size());
        int indexChefAssigned = chefs.indexOf(chefAssigned);
        chefs.get(indexChefAssigned).setWorking(true);
        OrderItem orderItemToAdd = new OrderItem(chefAssigned, selectedMenuItem);
        orderItemToAdd.setQuantity(quantity);
        selectedOrder.addItemToOrder(orderItemToAdd);
        System.out.println(StaticStrings.COMPLETED_MESSAGE + StaticStrings.CLOSE_PROGRAM_MESSAGE);
    }

    protected void getOrderInfo(){
        if (orders.size() < 1) {
            System.out.println(StaticStrings.NO_ORDERS_AVAILABLE + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        Order selectedOrder;
        System.out.println(StaticStrings.SELECT_ITEM_TO_UPDATE);
        for (Order order : orders) {
            int orderNumber = orders.indexOf(order) + 1;
            System.out.printf(StaticStrings.CHOOSE_NUMBER_TABLE_TEXT, orderNumber, order.getTable().getTableNumber());
        }
        String selectedOrderIndex = in.nextLine();
        boolean selectedOrderIsNumber = selectedOrderIndex.matches(StaticStrings.REG_EXP_FOR_NUMBER);
        if (!selectedOrderIsNumber) {
            System.out.println(StaticStrings.NO_NUMBER_RECEIVED + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        int selectedOrderIndexInt = Integer.parseInt(selectedOrderIndex);
        if (selectedOrderIndexInt > orders.size() || selectedOrderIndexInt < 1) {
            System.out.println(StaticStrings.ORDER_DOES_NOT_EXISTS + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        selectedOrder = orders.get(selectedOrderIndexInt - 1);
        if(selectedOrder.getOrderList().size()<1){
            System.out.println(StaticStrings.ORDER_EMPTY + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        String completedStatus = (selectedOrder.getIsCompleted()) ? StaticStrings.COMPLETED_TEXT : StaticStrings.NOT_COMPLETED_TEXT;
        System.out.printf(StaticStrings.INFO_ORDER_TOP, selectedOrder.getTable().getTableNumber(), selectedOrder.getWaiterAssigned().getFullName(), completedStatus);
        int countItems = 1;
        for(OrderItem orderItem : selectedOrder.getOrderList()){
            String orderItemStatus = (orderItem.getIsCompleted()) ? StaticStrings.COMPLETED_TEXT : StaticStrings.NOT_COMPLETED_TEXT;
            System.out.printf(StaticStrings.INFO_ORDER_BOTTOM, countItems++, orderItem.getMenuItem().getName(), orderItem.getQuantity(),
                    orderItemStatus, orderItem.getChefAssigned().getFullName());
        }
        System.out.println(StaticStrings.CLOSE_PROGRAM_MESSAGE);
    }

    protected void startBillingProcess(){
        if (orders.size() < 1) {
            System.out.println(StaticStrings.NO_ORDERS_AVAILABLE + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        Order selectedOrder;
        System.out.println(StaticStrings.SELECT_BILLING_ORDER);
        for (Order order : orders) {
            int orderNumber = orders.indexOf(order) + 1;
            System.out.printf(StaticStrings.CHOOSE_NUMBER_TABLE_TEXT, orderNumber, order.getTable().getTableNumber());
        }
        String selectedOrderIndex = in.nextLine();
        boolean selectedOrderIsNumber = selectedOrderIndex.matches(StaticStrings.REG_EXP_FOR_NUMBER);
        if (!selectedOrderIsNumber) {
            System.out.println(StaticStrings.NO_NUMBER_RECEIVED + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        int selectedOrderIndexInt = Integer.parseInt(selectedOrderIndex);
        if (selectedOrderIndexInt > orders.size() || selectedOrderIndexInt < 1) {
            System.out.println(StaticStrings.ORDER_DOES_NOT_EXISTS + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        selectedOrder = orders.get(selectedOrderIndexInt - 1);
        System.out.printf(StaticStrings.WRITE_CUSTOMER_INFO_TEXT, StaticStrings.FIRST_NAME_LABEL);
        String firstName = in.nextLine();
        System.out.printf(StaticStrings.WRITE_CUSTOMER_INFO_TEXT, StaticStrings.LAST_NAME_LABEL);
        String lastName = in.nextLine();
        System.out.printf(StaticStrings.WRITE_CUSTOMER_INFO_TEXT, StaticStrings.EMAIL_LABEL);
        String email = in.nextLine();
        System.out.printf(StaticStrings.WRITE_CUSTOMER_INFO_TEXT, StaticStrings.AGE_LABEL);
        String ageString = in.nextLine();
        while (!ageString.matches(StaticStrings.REG_EXP_FOR_NUMBER)) {
            System.out.println(StaticStrings.NO_NUMBER_FOR_AGE);
            ageString = in.nextLine();
        }
        int ageInt = Integer.parseInt(ageString);
        System.out.printf(StaticStrings.WRITE_CUSTOMER_INFO_TEXT, StaticStrings.PHONE_NUMBER_LABEL);
        String phoneNumber = in.nextLine();
        System.out.printf(StaticStrings.WRITE_CUSTOMER_INFO_TEXT, StaticStrings.TAX_NAME_LABEL);
        String taxId = in.nextLine();
        Customer customer = new Customer(ageInt);
        customer.setName(firstName);
        customer.setLastName(lastName);
        customer.setTaxId(taxId);
        customer.setAge(ageInt);
        customer.setEmail(email);
        customer.setPhone(phoneNumber);
        Employee cashierAssigned = getDataEmployee.getEmployeeToAssign(cashiers, StaticStrings.CASHIER_JOB_NAME, cashiers.size());
        Bill bill = new Bill(selectedOrder.getOrderList(), selectedOrder.getTable(), customer, 0, cashierAssigned);
        System.out.println(bill.generateBill() + StaticStrings.BILL_PAID_QUESTION);
        String wasPaid = in.nextLine().toLowerCase();
        if (!wasPaid.equals(StaticStrings.YES_ANSWER) && !wasPaid.equals(StaticStrings.NO_ANSWER)) {
            System.out.println(StaticStrings.CLOSE_MESSAGE_ERROR + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        if (wasPaid.equals(StaticStrings.NO_ANSWER)) {
            System.out.println(StaticStrings.BILL_NOT_PAID + StaticStrings.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        int indexTable = tables.indexOf(selectedOrder.getTable());
        orders.remove(selectedOrderIndexInt - 1);
        tables.get(indexTable).changeStatusTable();
        System.out.println(StaticStrings.PAYMENT_COMPLETED + StaticStrings.CLOSE_PROGRAM_MESSAGE);
    }

}




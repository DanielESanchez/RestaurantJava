import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantProgram {
    List<Chef> chefs;
    List<Cashier> cashiers;
    List<Waiter> waiters;
    List<MenuItem> menuItems;
    List<Table> tables;
    List<Order> orders;
    GetDataMenu dataMenu;
    GetDataEmployee dataEmployee;
    GetDataTable dataTable;
    Scanner in;

    public static void main(String[] args) throws IOException {
        RestaurantProgram restaurantProgram = new RestaurantProgram();
        restaurantProgram.start();
    }

    protected void start() throws IOException {
        String keepRunningProgram = StaticVariables.KEEP_RUNNING_PROGRAM_INITIAL;
        GetFileData getFileData = new GetFileData();
        dataMenu = new GetDataMenu(getFileData);
        dataEmployee = new GetDataEmployee(getFileData);
        dataTable = new GetDataTable(getFileData);
        chefs =  dataEmployee.getDataChef(StaticVariables.CHEF_FILE_NAME);
        cashiers =  dataEmployee.getDataCashier(StaticVariables.CASHIER_FILE_NAME);
        waiters =  dataEmployee.getDataWaiter(StaticVariables.WAITER_FILE_NAME);
        menuItems = dataMenu.getData();
        tables = dataTable.getData();
        orders = new ArrayList<>();
        in = new Scanner(System.in);
        while(!keepRunningProgram.isBlank() || !keepRunningProgram.isEmpty()) {
            System.out.println(StaticVariables.WELCOME_MESSAGE);
            String answerOption = in.nextLine();
            switch (answerOption) {
                case StaticVariables.ASSIGN_TABLE_OPTION ->
                    assignTable();
                case StaticVariables.ADD_TO_ORDER_OPTION ->
                    addToOrder();
                case StaticVariables.GET_ORDER_INFO_OPTION ->
                    getOrderInfo();
                case StaticVariables.START_BILLING_PROCESS_OPTION ->
                    startBillingProcess();
                default ->
                    System.out.println(StaticVariables.CLOSE_MESSAGE_ERROR + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            }
            keepRunningProgram = in.nextLine();
        }
    }

    protected void assignTable(){
        List<Table> availableTables = dataTable.getAvailableTables(tables);
        if (availableTables.size() < 1) {
            System.out.println(StaticVariables.NO_TABLE_AVAILABLE + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        System.out.println(StaticVariables.AVAILABLE_TABLES_TITLE);
        for (Table table : availableTables) {
            System.out.println(table.getTableNumber());
        }
        System.out.println(StaticVariables.WRITE_NUMBER_TEXT);
        String numberTableSelected = in.nextLine();
        Table tableToAssign = null;
        for (Table table : availableTables) {
            if (numberTableSelected.equals(Integer.toString(table.getTableNumber()))) {
                tableToAssign = table;
                System.out.println(StaticVariables.TABLE_ASSIGNED + table.getTableNumber());
                Waiter waiterAssigned = dataEmployee.getWaiterToAssign(waiters, StaticVariables.WAITER_JOB_NAME, waiters.size());
                System.out.println(StaticVariables.WAITER_ASSIGNED_TEXT + waiterAssigned.getFullName());
                Order newOrder = new Order(waiterAssigned, tableToAssign);
                int indexEmployeeAssigned = waiters.indexOf(waiterAssigned);
                waiters.get(indexEmployeeAssigned).setWorking(true);
                orders.add(newOrder);
                int indexSelectedTable = tables.indexOf(table);
                tables.get(indexSelectedTable).changeStatusTable();
                System.out.println(StaticVariables.COMPLETED_MESSAGE + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            }
        }
        if (tableToAssign == null) {
            System.out.println(StaticVariables.NO_SELECTED_TABLE + StaticVariables.CLOSE_PROGRAM_MESSAGE);
        }
    }

    protected void addToOrder(){
        if (orders.size() < 1) {
            System.out.println(StaticVariables.NO_ORDERS_AVAILABLE + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        Order selectedOrder;
        System.out.println(StaticVariables.SELECT_TABLE_TITLE);
        for (Order order : orders) {
            int orderNumber = orders.indexOf(order) + 1;
            System.out.printf(StaticVariables.CHOOSE_NUMBER_TABLE_TEXT, orderNumber, order.getTable().getTableNumber());
        }
        String selectedOrderIndex = in.nextLine();
        boolean selectedOrderIsNumber = selectedOrderIndex.matches(StaticVariables.REG_EXP_FOR_NUMBER);
        if (!selectedOrderIsNumber) {
            System.out.println(StaticVariables.NO_NUMBER_RECEIVED + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        int selectedOrderIndexInt = Integer.parseInt(selectedOrderIndex);
        if (selectedOrderIndexInt > orders.size() || selectedOrderIndexInt < 1) {
            System.out.println(StaticVariables.ORDER_DOES_NOT_EXISTS + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        selectedOrder = orders.get(selectedOrderIndexInt - 1);
        System.out.println(StaticVariables.CHOSEN_TABLE_TEXT + selectedOrder.getTable().getTableNumber());
        List<String> availableCategories = dataMenu.getAvailableCategories(menuItems);
        System.out.println(StaticVariables.AVAILABLE_CATEGORIES_TITLE);
        int categoryCount = 1;
        for (String category : availableCategories) {
            System.out.println(categoryCount++ + StaticVariables.SPACE_SEPARATOR + category);
        }
        System.out.println(StaticVariables.WRITE_NUMBER_CATEGORY);
        String selectedCategory = in.nextLine();
        boolean categoryIsNumber = selectedCategory.matches(StaticVariables.REG_EXP_FOR_NUMBER);
        if (!categoryIsNumber) {
            System.out.println(StaticVariables.NO_NUMBER_RECEIVED + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        int selectedCategoryInt = Integer.parseInt(selectedCategory);
        if (selectedCategoryInt> availableCategories.size() || selectedCategoryInt < 1) {
            System.out.println(StaticVariables.CATEGORY_DOES_NOT_EXISTS + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        String selectedCategoryName = availableCategories.get(selectedCategoryInt - 1);
        System.out.printf(StaticVariables.SHOWING_ITEMS_MENU, selectedCategoryName);
        List<MenuItem> itemsByCategory = dataMenu.getMenuByCategoryName(menuItems, selectedCategoryName);
        for (MenuItem menuItem : itemsByCategory) {
            System.out.printf(StaticVariables.ITEM_MENU_FORMAT, menuItem.getId(), menuItem.getName(),
                    menuItem.getDescription(), menuItem.getPrice()
            );
        }
        System.out.println(StaticVariables.WRITE_ID_TO_CHOOSE_ITEM);
        String itemToChoose = in.nextLine();
        MenuItem selectedMenuItem = null;
        for (MenuItem menuItem : itemsByCategory) {
            if (itemToChoose.equals(menuItem.getId())) {
                selectedMenuItem = menuItem;
                break;
            }
        }
        if (selectedMenuItem == null) {
            System.out.println(StaticVariables.ITEM_DOES_NOT_EXISTS + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        System.out.println(StaticVariables.WRITE_QUANTITY_ORDER);
        String quantityInput = in.nextLine();
        boolean quantityInputIsNumber = quantityInput.matches(StaticVariables.REG_EXP_FOR_NUMBER);
        if (!quantityInputIsNumber) {
            System.out.println(StaticVariables.NO_NUMBER_RECEIVED + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        int quantity = Integer.parseInt(quantityInput);
        System.out.printf(StaticVariables.ADDED_ITEM_TEXT, quantity, selectedMenuItem.getName());
        Chef chefAssigned = dataEmployee.getChefToAssign(chefs, StaticVariables.CHEF_JOB_NAME, chefs.size());
        int indexChefAssigned = chefs.indexOf(chefAssigned);
        chefs.get(indexChefAssigned).setWorking(true);
        OrderItem orderItemToAdd = new OrderItem(chefAssigned, selectedMenuItem);
        orderItemToAdd.setQuantity(quantity);
        selectedOrder.addItemToOrder(orderItemToAdd);
        System.out.println(StaticVariables.COMPLETED_MESSAGE + StaticVariables.CLOSE_PROGRAM_MESSAGE);
    }

    protected void getOrderInfo(){
        if (orders.size() < 1) {
            System.out.println(StaticVariables.NO_ORDERS_AVAILABLE + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        Order selectedOrder;
        System.out.println(StaticVariables.SELECT_ITEM_TO_UPDATE);
        for (Order order : orders) {
            int orderNumber = orders.indexOf(order) + 1;
            System.out.printf(StaticVariables.CHOOSE_NUMBER_TABLE_TEXT, orderNumber, order.getTable().getTableNumber());
        }
        String selectedOrderIndex = in.nextLine();
        boolean selectedOrderIsNumber = selectedOrderIndex.matches(StaticVariables.REG_EXP_FOR_NUMBER);
        if (!selectedOrderIsNumber) {
            System.out.println(StaticVariables.NO_NUMBER_RECEIVED + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        int selectedOrderIndexInt = Integer.parseInt(selectedOrderIndex);
        if (selectedOrderIndexInt > orders.size() || selectedOrderIndexInt < 1) {
            System.out.println(StaticVariables.ORDER_DOES_NOT_EXISTS + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        selectedOrder = orders.get(selectedOrderIndexInt - 1);
        if(selectedOrder.getOrderList().size()<1){
            System.out.println(StaticVariables.ORDER_EMPTY + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        String completedStatus = (selectedOrder.getIsCompleted()) ? StaticVariables.COMPLETED_TEXT : StaticVariables.NOT_COMPLETED_TEXT;
        System.out.printf(StaticVariables.INFO_ORDER_TOP, selectedOrder.getTable().getTableNumber(), selectedOrder.getWaiterAssigned().getFullName(), completedStatus);
        int countItems = 1;
        for(OrderItem orderItem : selectedOrder.getOrderList()){
            String orderItemStatus = (orderItem.getIsCompleted()) ? StaticVariables.COMPLETED_TEXT : StaticVariables.NOT_COMPLETED_TEXT;
            System.out.printf(StaticVariables.INFO_ORDER_BOTTOM, countItems++, orderItem.getMenuItem().getName(), orderItem.getQuantity(),
                    orderItemStatus, orderItem.getChefAssigned().getFullName());
        }
        System.out.println(StaticVariables.CLOSE_PROGRAM_MESSAGE);
    }

    protected void startBillingProcess(){
        if (orders.size() < 1) {
            System.out.println(StaticVariables.NO_ORDERS_AVAILABLE + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        Order selectedOrder;
        System.out.println(StaticVariables.SELECT_BILLING_ORDER);
        for (Order order : orders) {
            int orderNumber = orders.indexOf(order) + 1;
            System.out.printf(StaticVariables.CHOOSE_NUMBER_TABLE_TEXT, orderNumber, order.getTable().getTableNumber());
        }
        String selectedOrderIndex = in.nextLine();
        boolean selectedOrderIsNumber = selectedOrderIndex.matches(StaticVariables.REG_EXP_FOR_NUMBER);
        if (!selectedOrderIsNumber) {
            System.out.println(StaticVariables.NO_NUMBER_RECEIVED + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        int selectedOrderIndexInt = Integer.parseInt(selectedOrderIndex);
        if (selectedOrderIndexInt > orders.size() || selectedOrderIndexInt < 1) {
            System.out.println(StaticVariables.ORDER_DOES_NOT_EXISTS + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        selectedOrder = orders.get(selectedOrderIndexInt - 1);
        if(selectedOrder.getOrderList().size()<1){
            System.out.println(StaticVariables.ORDER_EMPTY + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        System.out.printf(StaticVariables.WRITE_CUSTOMER_INFO_TEXT, StaticVariables.FIRST_NAME_LABEL);
        String firstName = in.nextLine();
        System.out.printf(StaticVariables.WRITE_CUSTOMER_INFO_TEXT, StaticVariables.LAST_NAME_LABEL);
        String lastName = in.nextLine();
        System.out.printf(StaticVariables.WRITE_CUSTOMER_INFO_TEXT, StaticVariables.EMAIL_LABEL);
        String email = in.nextLine();
        System.out.printf(StaticVariables.WRITE_CUSTOMER_INFO_TEXT, StaticVariables.AGE_LABEL);
        String ageString = in.nextLine();
        while (!ageString.matches(StaticVariables.REG_EXP_FOR_NUMBER)) {
            System.out.println(StaticVariables.NO_NUMBER_FOR_AGE);
            ageString = in.nextLine();
        }
        int ageInt = Integer.parseInt(ageString);
        System.out.printf(StaticVariables.WRITE_CUSTOMER_INFO_TEXT, StaticVariables.PHONE_NUMBER_LABEL);
        String phoneNumber = in.nextLine();
        System.out.printf(StaticVariables.WRITE_CUSTOMER_INFO_TEXT, StaticVariables.TAX_NAME_LABEL);
        String taxId = in.nextLine();
        Customer customer = new Customer(ageInt);
        customer.setName(firstName);
        customer.setLastName(lastName);
        customer.setTaxId(taxId);
        customer.setAge(ageInt);
        customer.setEmail(email);
        customer.setPhone(phoneNumber);
        Cashier cashierAssigned = dataEmployee.getCashierToAssign(cashiers, StaticVariables.CASHIER_JOB_NAME, cashiers.size());
        Bill bill = new Bill(selectedOrder.getOrderList(), selectedOrder.getTable(), customer, 0, cashierAssigned);
        System.out.println(bill.generateBill() + StaticVariables.BILL_PAID_QUESTION);
        String wasPaid = in.nextLine().toLowerCase();
        if (!wasPaid.equals(StaticVariables.YES_ANSWER) && !wasPaid.equals(StaticVariables.NO_ANSWER)) {
            System.out.println(StaticVariables.CLOSE_MESSAGE_ERROR + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        if (wasPaid.equals(StaticVariables.NO_ANSWER)) {
            System.out.println(StaticVariables.BILL_NOT_PAID + StaticVariables.CLOSE_PROGRAM_MESSAGE);
            return;
        }
        int indexTable = tables.indexOf(selectedOrder.getTable());
        orders.remove(selectedOrderIndexInt - 1);
        tables.get(indexTable).changeStatusTable();
        System.out.println(StaticVariables.PAYMENT_COMPLETED + StaticVariables.CLOSE_PROGRAM_MESSAGE);
    }

}




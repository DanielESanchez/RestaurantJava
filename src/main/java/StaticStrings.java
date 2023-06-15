public class StaticStrings {
    public static final String WELCOME_MESSAGE = """
            --------------------------------------------
            --------------------------------------------
            Restaurant System
            Choose an option from below and write the number to start the process
            1 - Assign table
            2 - Add to an existing order
            3 - Get order info
            4 - Start billing process""";
    public static final String ITEM_MENU_FORMAT = """
            --------------------------------
            Id: %s
            Name: %s
            Description: %s
            Price: %s$
            --------------------------------
            %n""";
    public static final String INFO_ORDER_TOP = """
            -------------------------------
            Showing order for table: %s
            --------------------------------
            Waiter Assigned: %s
            Completed Status: %s
            
            Items:
            
            """;
    public static final String INFO_ORDER_BOTTOM = """
            *************************
            %s- %s
            Quantity: %s
            Completed: %s
            Chef: %s
            *************************
            """;
    public static final String SELECT_TABLE_TITLE = "--------------------------------\nSelect an order to update from the list below:\n";
    public static final String AVAILABLE_CATEGORIES_TITLE = "--------------------------------\nAvailable Categories\n--------------------------------\n";
    public static final String SHOWING_ITEMS_MENU = "-------------------------------- \n Showing all menu items for: %s";
    public static final String CLOSE_MESSAGE_ERROR = "You did not write correct answer. ";
    public static final String COMPLETED_MESSAGE = "The process was completed ";
    public static final String CLOSE_PROGRAM_MESSAGE = "If you want to close the program press ENTER, else write anything to start again";
    public static final String NO_TABLE_AVAILABLE = "There is no table available. ";
    public static final String NO_ORDERS_AVAILABLE = "There is no order available. ";
    public static final String CHEF_FILE_NAME = "chef.json";
    public static final String CHEF_JOB_NAME = "chef";
    public static final String CASHIER_JOB_NAME = "cashier";
    public static final String WAITER_JOB_NAME = "waiter";
    public static final String WAITER_FILE_NAME ="waiter.json";
    public static final String CASHIER_FILE_NAME = "cashier.json";
    public static final String MENU_FILE_NAME = "menu.json";
    public static final String TABLE_FILE_NAME = "table.json";
    public static final String FILE_NOT_FOUND = "File not found! ";
    public static final String CHARSET = "UTF-8";
    public static final String KEEP_RUNNING_PROGRAM_INITIAL = "continue";
    public static final String AVAILABLE_TABLES_TITLE = "Available Tables:";
    public static final String WRITE_NUMBER_TEXT = "Please write a number from above to assign table:";
    public static final String TABLE_ASSIGNED = "Table Assigned ";
    public static final String WAITER_ASSIGNED_TEXT = "Waiter Assigned ";
    public static final String NO_SELECTED_TABLE = "Table was not selected. ";
    public static final String CHOOSE_NUMBER_TABLE_TEXT = "Write %s to choose the table Number %s\n";
    public static final String NO_NUMBER_RECEIVED = "The answer received is not a number. ";
    public static final String CHOSEN_TABLE_TEXT = "You have chosen table: ";
    public static final String ORDER_DOES_NOT_EXISTS = "The order selected does not exists. ";
    public static final String WRITE_NUMBER_CATEGORY = "Write the number corresponding to the category you want to see: ";
    public static final String WRITE_ID_TO_CHOOSE_ITEM = "Write the ID corresponding to the item you want to choose";
    public static final String ITEM_DOES_NOT_EXISTS = "The item selected does not exists";
    public static final String WRITE_QUANTITY_ORDER = "Write the quantity you want to add to your order: ";
    public static final String ADDED_ITEM_TEXT = "You added: %s %s\n";
    public static final String SELECT_ITEM_TO_UPDATE = "--------------------------------\nSelect an order to update from the list below: ";
    public static final String ORDER_EMPTY ="This order is empty. ";
    public static final String COMPLETED_TEXT = "Completed";
    public static final String NOT_COMPLETED_TEXT = "In Progress";
    public static final String SELECT_BILLING_ORDER = "--------------------------------\nSelect an order to start billing process from the list below:";
    public static final String WRITE_CUSTOMER_INFO_TEXT = "Write the %s of the customer\n";
    public static final String NO_NUMBER_FOR_AGE = "You can only use number for customer's age";
    public static final String BILL_PAID_QUESTION = "\nwas the bill paid? y/n";
    public static final String BILL_NOT_PAID = "Bill was not paid, you can add more items to the order or start again billing process. ";
    public static final String CATEGORY_DOES_NOT_EXISTS = "The category does not exists. ";
    public static final String PAYMENT_COMPLETED = "Payment process successfully completed ";
    public static final String REG_EXP_FOR_NUMBER = "\\d+";
    public static final String YES_ANSWER = "y";
    public static final String NO_ANSWER = "n";
    public static final String SPACE_SEPARATOR = " ";
    public static final String FIRST_NAME_LABEL = "first name";
    public static final String LAST_NAME_LABEL = "last name";
    public static final String EMAIL_LABEL = "email";
    public static final String AGE_LABEL = "age";
    public static final String PHONE_NUMBER_LABEL = "phone number";
    public static final String TAX_NAME_LABEL = "TaxID";
    public static final String ASSIGN_TABLE_OPTION = "1";
    public static final String ADD_TO_ORDER_OPTION = "2";
    public static final String GET_ORDER_INFO_OPTION = "3";
    public static final String START_BILLING_PROCESS_OPTION = "4";
    public static final int DEFAULT_BUFFER_SIZE = 8192;

}

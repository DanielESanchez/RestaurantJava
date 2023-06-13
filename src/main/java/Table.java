public class Table {
    private int tableNumber;
    private boolean isEmpty;

    public Table(int tableNumber, boolean isEmpty) {
        this.tableNumber = tableNumber;
        this.isEmpty = isEmpty;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public boolean getIsEmpty() {
        return isEmpty;
    }

    public void changeStatusTable() {
        isEmpty = !isEmpty;
    }
}


public class OrderItem {
    private MenuItem menuItem;
    private boolean isBeingCooked;
    private Employee chefAssigned;
    private boolean isCompleted =false;
    private int quantity;

     public OrderItem(Employee chefAssigned, MenuItem menuItem){
        super();
        this.menuItem = menuItem;
        this.chefAssigned = chefAssigned;
    }

    public boolean isBeingCooked() {
        return isBeingCooked;
    }

    public Employee getChefAssigned() {
        return chefAssigned;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void changeStatusBeingCooked() {
        isBeingCooked = !isBeingCooked;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

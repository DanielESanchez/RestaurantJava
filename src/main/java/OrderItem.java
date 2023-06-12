public class OrderItem {
    private MenuItem menuItem;
    private boolean isBeingCooked;
    private Employee chefAssigned;
    private boolean isCompleted =false;

     public OrderItem(Employee chefAssigned, MenuItem menuItem){
        super();
        this.menuItem = menuItem;
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

    public void setChefAssigned(Employee chefAssigned) {
        this.chefAssigned = chefAssigned;
    }
}

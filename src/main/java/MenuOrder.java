public class MenuOrder {
    private Menu order;
    private boolean isBeingCooked;
    private Employee chefAssigned;
    private boolean isCompleted;

    public boolean isBeingCooked() {
        return isBeingCooked;
    }

    public Employee getChefAssigned() {
        return chefAssigned;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setOrder(Menu order) {
        this.order = order;
    }
}

public class OrderItem extends MenuItem {
    private boolean isBeingCooked;
    private Employee chefAssigned;
    private boolean isCompleted =false;

     public OrderItem(){
        super();
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

    public void setBeingCooked(boolean beingCooked) {
        isBeingCooked = beingCooked;
    }

    public void setChefAssigned(Employee chefAssigned) {
        this.chefAssigned = chefAssigned;
    }
}

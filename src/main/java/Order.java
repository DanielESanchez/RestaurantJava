import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<MenuOrder> orderList;
    private Employee waiterAssigned;
    private boolean isCompleted;


    public void setOrderList() {
        this.orderList = new ArrayList<>();
    }

    public void addItemToOrder(MenuOrder menuItem){
        this.orderList.add(menuItem);
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    public List<MenuOrder> getOrderList(){
        return this.orderList;
    }
}

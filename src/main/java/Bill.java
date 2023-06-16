import java.util.Date;
import java.util.List;

public class Bill {
    private final Date dateBill;
    private float subtotal = 0;
    private int taxesPercentage = 15;
    private float taxesTotal;
    private int discount;
    private float discountTotal;
    private float total;
    private final List<OrderItem> items;
    private final Table table;
    private final Customer customer;
    private final Employee cashier;

    public Bill( List<OrderItem> items, Table table, Customer customer, int discount, Employee cashier) {
        this.dateBill = new Date();
        this.discount = discount;
        this.items = items;
        this.table = table;
        this.customer = customer;
        this.cashier = cashier;
        if(this.customer.getIsSenior()){
            this.discount += StaticVariables.SENIOR_DISCOUNT;
        }
        if(this.customer.getIsEmployee()){
            this.discount += StaticVariables.EMPLOYEE_DISCOUNT;
        }
    }

    public String generateBill(){
        this.calculateSubtotal();
        this.calculateTaxes();
        this.calculateDiscount();
        this.calculateTotal();
        return StaticVariables.BILL_FORMAT.formatted(
                        this.dateBill.toString(), this.customer.getFullName(),
                        this.customer.getTaxId(), this.table.getTableNumber(),
                        this.getStringProducts(), this.subtotal, this.taxesTotal,
                        this.discountTotal, this.total, this.cashier.getFullName()
                );
    }

    private String getStringProducts(){
        StringBuilder stringProducts = new StringBuilder();
        for(OrderItem orderItem: items){
            stringProducts.append(orderItem.getMenuItem().getName()).append("   ").append(orderItem.getMenuItem().getPrice()).append(" $\n");
        }
        return stringProducts.toString();
    }

    private void calculateSubtotal(){
        for(OrderItem orderItem: items){
            this.subtotal += orderItem.getMenuItem().getPrice();
        }
    }

    private void calculateTaxes(){
        this.taxesTotal = this.subtotal * (this.taxesPercentage / (float)100);
    }

    private void calculateDiscount(){
        this.discountTotal = this.subtotal * (this.discount / (float)100);
    }

    private void calculateTotal(){
        this.total = this.subtotal + this.taxesTotal - this.discountTotal;
    }

}

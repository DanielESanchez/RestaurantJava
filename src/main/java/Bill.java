import java.util.Date;
import java.util.List;

public class Bill {
    private Date dateBill;
    private float subtotal = 0;
    private int taxesPercentage = 15;
    private float taxesTotal;
    private int discount;
    private float discountTotal;
    private float total;
    private List<OrderItem> items;
    private Table table;
    private Customer customer;

    public Bill( List<OrderItem> items, Table table, Customer customer, int discount) {
        this.dateBill = new Date();
        this.discount = discount;
        this.items = items;
        this.table = table;
        this.customer = customer;
        if(this.customer.getIsSenior()){
            this.discount += 15;
        }
        if(this.customer.getIsEmployee()){
            this.discount += 5;
        }
    }

    public String generateBill(){
        this.calculateSubtotal();
        this.calculateTaxes();
        this.calculateDiscount();
        this.calculateTotal();
        return """
                -------------------------
                Date %s 
                Name: %s 
                TaxID: %s
                Table: %s\n
                Products: 
                -------------------------
                %s 
                Subtotal: %s 
                Taxes: %s 
                Discount: %s 
                Total: %s \n
                -------------------------
                """.formatted(
                        this.dateBill.toString(), this.customer.getFullName(),
                        this.customer.getTaxId(), this.table.getTableNumber(),
                        this.getStringProducts(), this.subtotal, this.taxesTotal,
                        this.discountTotal, this.total
                );
    }

    private String getStringProducts(){
        String stringProductos = "";
        for(OrderItem orderItem: items){
            stringProductos += orderItem.getMenuItem().getName() + "   " + orderItem.getMenuItem().getPrice() + " $\n";
        }
        return stringProductos;
    }

    private void calculateSubtotal(){
        for(OrderItem orderItem: items){
            this.subtotal += orderItem.getMenuItem().getPrice();
        }
    }

    private void calculateTaxes(){
        System.out.println(this.subtotal);
        System.out.println((this.taxesPercentage / 100));
        this.taxesTotal = this.subtotal * (this.taxesPercentage / (float)100);
    }

    private void calculateDiscount(){
        this.discountTotal = this.subtotal * (this.discount / (float)100);
    }

    private void calculateTotal(){
        this.total = this.subtotal + this.taxesTotal - this.discountTotal;
    }

}

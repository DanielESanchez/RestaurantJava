import java.util.Date;

public class Customer extends Person{
    private String visitDate;
    private boolean isSenior;

    public Customer(){
        super();
        this.isSenior = (this.getAge() > 65) ? true: false;
        this.setVisitDate();
    }

    private void setVisitDate() {
        String actualDateString = new Date().toString();
        this.visitDate = actualDateString;
    }

    public String getVisitDate(){
        return this.visitDate;
    }

    public boolean getIsSenior(){
        return this.isSenior;
    }

}

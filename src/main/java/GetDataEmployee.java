import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GetDataEmployee {
    GetFileData getFileData;
    public GetDataEmployee(GetFileData getFileData){
        this.getFileData = getFileData;
    }

    public List<Chef> getDataChef(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String data = getFileData.getFileFromResourceAsStream(fileName);
        return Arrays.asList(mapper.readValue(data, Chef[].class));
    }

    public List<Waiter> getDataWaiter(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String data = getFileData.getFileFromResourceAsStream(fileName);
        return Arrays.asList(mapper.readValue(data, Waiter[].class));
    }

    public List<Cashier> getDataCashier(String fileName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        String data = getFileData.getFileFromResourceAsStream(fileName);
        return Arrays.asList(mapper.readValue(data, Cashier[].class));
    }

    public Chef getChefToAssign(List<Chef> chefs, String jobToFind, int chefCount){
        int randomNumber = (int)(Math.random() * chefCount );
        Chef chefToAssign = chefs.get(randomNumber);
        for(Chef chef : chefs){
            if(!chef.getIsWorking() && chef.getJob().equals(jobToFind)){
                chefToAssign = chef;
            }
        }
        return chefToAssign;
    }

    public Waiter getWaiterToAssign(List<Waiter> waiters, String jobToFind, int waitersCount){
        int randomNumber = (int)(Math.random() * waitersCount );
        Waiter waiterToAssign = waiters.get(randomNumber);
        for(Waiter waiter : waiters){
            if(!waiter.getIsWorking() && waiter.getJob().equals(jobToFind)){
                waiterToAssign = waiter;
            }
        }
        return waiterToAssign;
    }

    public Cashier getCashierToAssign(List<Cashier> cashiers, String jobToFind, int cashiersCount){
        int randomNumber = (int)(Math.random() * cashiersCount );
        Cashier cashierToAssign = cashiers.get(randomNumber);
        for(Cashier cashier : cashiers){
            if(!cashier.getIsWorking() && cashier.getJob().equals(jobToFind)){
                cashierToAssign = cashier;
            }
        }
        return cashierToAssign;
    }
}

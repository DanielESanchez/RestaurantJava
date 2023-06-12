import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class GetDataEmployee {

    public List<Employee> getData(String fileName) throws IOException {
        GetFileData app = new GetFileData();
        ObjectMapper mapper = new ObjectMapper();
        String data = app.getFileFromResourceAsStream(fileName);
        List<Employee> list = Arrays.asList(mapper.readValue(data, Employee[].class));
        return list;
    }

    public Employee getEmployeeToAssign(List<Employee> employees, String jobToFind, int employeesCount){
        int randomNumber = (int)(Math.random() * employeesCount );
        Employee employeeToAssign = employees.get(randomNumber);
        for(Employee employee : employees){
            if(!employee.getIsWorking() && employee.getJob().equals(jobToFind)){
                employeeToAssign = employee;
            }
        }
        return employeeToAssign;
    }
}

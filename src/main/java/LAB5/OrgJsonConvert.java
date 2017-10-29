package LAB5;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OrgJsonConvert {
    private final static String baseFile = "Company_1.json";


    public static void toJSON(Employeer employeer) throws IOException {

        FileWriter writer = new FileWriter(baseFile);
        JSONObject obj=new JSONObject(employeer);
        obj.put("id",employeer.id);
        obj.put("firstName",employeer.firstName);
        obj.put("secondName", employeer.secondName);
        obj.put("lastName", employeer.lastName);
        obj.put("number",employeer.number);
        obj.put("address", employeer.address);

        JSONObject companyObj = new JSONObject();
        companyObj.put("id", employeer.getCompany().id);
        companyObj.put("name", employeer.getCompany().name);
        companyObj.put("address", employeer.getCompany().address);
        companyObj.put("phone", employeer.getCompany().phone);

        obj.put("company",companyObj);

        JSONArray employeeArray = new JSONArray();
        for (Employee employee: employeer.getEmployees()) {
            JSONObject employeeObj = new JSONObject();
            employeeObj.put("id", employee.id);
            employeeObj.put("firstName", employee.firstName);
            employeeObj.put("secondName", employee.secondName);
            employeeObj.put("lastName", employee.lastName);
            employeeObj.put("address", employee.address);
            employeeObj.put("number",employee.number);
            employeeArray.put(employeeObj);
        }
        obj.put("employees", employeeArray);
        obj.write(writer);
        System.out.println("json created!");
        writer.close();
    }

    public static Employeer toJavaObject() throws IOException {
        Employeer employeer = new Employeer();
        File reader = new File(baseFile);
        String content = FileUtils.readFileToString(reader, "utf-8");
        JSONObject object =  new JSONObject(content);
        employeer.id = object.getInt("id");
        employeer.firstName = object.getString("firstName");
        employeer.secondName = object.getString("secondName");
        employeer.lastName = object.getString("lastName");
        employeer.address = object.getString("address");
        employeer.number = object.getString("number");

        JSONObject companyObject = object.getJSONObject("company");
        Company company = new Company();
        company.id = companyObject.getInt("id");
        company.name = companyObject.getString("name");
        company.phone = companyObject.getString("phone");
        company.address = companyObject.getString("address");
        employeer.setCompany(company);


        JSONArray employeesArray = object.getJSONArray("employees");
        for (int i = 0; i < employeesArray.length(); i++){
            JSONObject employeeObj = employeesArray.getJSONObject(i);
            Employee emp = new Employee();
            emp.id = employeeObj.getInt("id");
            emp.firstName = employeeObj.getString("firstName");
            emp.secondName = employeeObj.getString("secondName");
            emp.lastName = employeeObj.getString("lastName");
            emp.address = employeeObj.getString("address");
            emp.number = employeeObj.getString("number");
            employeer.addEmployee(emp);
        }
        return employeer;
    }

}

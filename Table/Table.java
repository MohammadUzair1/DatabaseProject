
package Table;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;


public class Table {
    private final SimpleIntegerProperty emp_id;
    
    private final SimpleStringProperty first_name;
    private final SimpleStringProperty last_name;
    private final SimpleStringProperty gender;
    private final SimpleStringProperty hiredate;
    private final SimpleIntegerProperty age;
    private final SimpleIntegerProperty salary;
    private final SimpleStringProperty job;
    public Table(int emp_id, String first_name, String last_name , String gender, String hiredate, int age, int salary, String job){
        this.emp_id = new SimpleIntegerProperty(emp_id);
        
        this.first_name = new SimpleStringProperty(first_name);
        this.last_name = new SimpleStringProperty(last_name);
        this.gender = new SimpleStringProperty(gender);
        this.hiredate = new SimpleStringProperty(hiredate);
        this.age = new SimpleIntegerProperty(age);
        this.salary = new SimpleIntegerProperty(salary);
        this.job = new SimpleStringProperty(job);
        
    }
   
    public void setEmp_id(Integer v)
    {  
        emp_id.set(v);
    }
    public void setJob(String v)
    {  
        job.set(v);
    }
    public void setFirst_name(String v)
    {  
        first_name.set(v);
    }
    public void setLast_name(String v)
    {  
        last_name.set(v);
    }
    public void setGender(String v)
    {  
        gender.set(v);
    }
    public void setAge(Integer v)
    {  
        age.set(v);
    }
    public void setSalary(Integer v)
    {  
        salary.set(v);
    }
    public void setHiredate(String v)
    {
        hiredate.set(v);
    }
    
    
    
    
    public Integer getEmp_id() {
        return emp_id.get();
    }

    public String getJob() {
        return job.get();
    }

    public String getFirst_name() {
        return first_name.get();
    }

    public String getLast_name() {
        return last_name.get();
    }

    public String getGender() {
        return gender.get();
    }

    public Integer getAge() {
        return age.get();
    }

    public Integer getSalary() {
        return salary.get();
    }
    public String getHiredate(){
        return hiredate.get();
    }
    
}

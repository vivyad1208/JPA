package hbm.persistence.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity

@NamedQueries({
	@NamedQuery(name="GetDepartment",query="FROM Department e WHERE e.departmentName=:departmentName")
})
public class Department implements EntityInterface {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;

	private int active;
	private String departmentName ;

    @OneToMany(mappedBy="department")
	private List<Employee> employees;

	@Override
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int getId() {
		return id;
	}
	
	@Override
	public void setActive(int active) {
		this.active = active;
	}
	@Override
	public int getActive() {
		return active;
	}

	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public List<Employee> getEmployees() {
		return employees;
	}
	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}

	public String toString() {
		return "[Department:"+departmentName+", "+(active==1?"Active":"InActive")+"]";
	}

}

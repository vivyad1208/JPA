package hbm.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/*
 * No need to define annotations entity and table if mapped in XML
 */
@Entity

@NamedQueries({
	@NamedQuery(name="MAX ID",query="SELECT MAX(e.id) FROM Employee e"),
	@NamedQuery(name="ACTIVE",query="FROM Employee e WHERE e.active=1"),
	@NamedQuery(name="INACTIVE",query="FROM Employee e WHERE e.active=0"),
	@NamedQuery(name="CONDITIONAL_ACTIVE",query="FROM Employee e WHERE e.active=:active")
})

public class Employee implements EntityInterface {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;

	private int active;
	private String name ;
	private int age;

	@ManyToOne(targetEntity=Department.class)
	@JoinColumn(name="departmentId", nullable=false)
    // @PrimaryKeyJoinColumn -> Doesn't Work -> [Field 'departmentId' doesn't have a default value]
	private Department department;

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

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}

	public String toString() {
		return "[Name:"+name+", Age:"+age+", Id:"+id+", "+department+" "+(active==1?"Active":"InActive")+"]";
	}
}

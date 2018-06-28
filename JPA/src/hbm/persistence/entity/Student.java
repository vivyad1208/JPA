package hbm.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="getStudentByName",query="FROM Student WHERE name=:name")
public class Student implements EntityInterface {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;
	private int active;
	private String name;
	private int age;

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

	public String toString() {
		return "Name:"+name+", Age:"+age+", Id:"+id+", "+(active==1?"Active":"InActive");
	}

}

package hbm.persistence.entity;

import java.sql.Time;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.hibernate.collection.internal.PersistentBag;

@Entity
@NamedQueries({
	@NamedQuery(name="getCourseByNames",query="FROM Course WHERE courseName LIKE :courseName"),
	@NamedQuery(name="getCourseByName",query="FROM Course WHERE courseName=:courseName")
})
public class Course implements EntityInterface {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;

	private int active;
	private String courseName;
	private Time startTime;
	private Time endTime;
	private int fees;

	@ManyToMany(targetEntity=Student.class, fetch=FetchType.EAGER)
	@JoinTable(name="Enrolled", joinColumns={@JoinColumn(name="courseId")}, inverseJoinColumns={@JoinColumn(name="studentId")})
	private List<Student> students;

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

	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public Time getStartTime() {
		return startTime;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public int getFees() {
		return fees;
	}
	public void setFees(int fees) {
		this.fees = fees;
	}

	public List<Student> getMapStudents() {
		return students;
	}
	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public String toString() {
		String student;
		if(students!=null && !students.getClass().isInstance(PersistentBag.class))
			student = "\nStudents: "+students;
		else
			student = "";
		return "Course Name:"+courseName+", Fees:"+fees+", StartTime:"+startTime+", EndTime:"+endTime+ ", Status: "+
					(active==1?"OnGoing":"Halted") + student;
	}

	
}

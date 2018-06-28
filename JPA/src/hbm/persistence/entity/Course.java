package hbm.persistence.entity;

import java.sql.Time;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name="getCourseByName",query="FROM Course WHERE courseName=:courseName")
public class Course implements EntityInterface {

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private int id;

	private int active;
	private String courseName;
	private Time startTime;
	private Time endTime;
	private int fees;

	@ManyToMany(targetEntity=Student.class)
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
		return "Course Name:"+courseName+", Fees:"+fees+", StartTime:"+startTime+", EndTime:"+endTime+", "+
				"Students: "+students+
				(active==1?" Active":" InActive");
	}

	
}
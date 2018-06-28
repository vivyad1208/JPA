package hbm.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Message {

	@Id
	@GeneratedValue
	private Long id;
	private String text;

	public String getText() {
		return text;
	}

	public Message setText(String text) {
		this.text = text;
		return this;
	}
}
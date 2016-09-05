package parking.model;

import java.sql.Time;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PriceSchema {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Time highFrom;
	private Time highUntil;

	PriceSchema() {}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public Time getHighFrom() {
		return highFrom;
	}

	public void setHighFrom(Time highFrom) {
		this.highFrom = highFrom;
	}

	public Time getHighUntil() {
		return highUntil;
	}

	public void setHighUntil(Time highUntil) {
		this.highUntil = highUntil;
	}
  
}
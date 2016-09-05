package parking.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ParkingHouse {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String address;
	@ManyToOne
	@JoinColumn(name="PRICE_SCHEMA")
	@JsonIgnore
	private PriceSchema priceSchema;
	@OneToMany(mappedBy="parkingHouse")
	@JsonIgnore
	private List<Parking> parkings;

	ParkingHouse() {}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public PriceSchema getPriceSchema() {
		return priceSchema;
	}

	public void setPriceSchema(PriceSchema priceSchema) {
		this.priceSchema = priceSchema;
	}
	
	public List<Parking> getParkings() {
		return parkings;
	}
	
	public void setParkings(List<Parking> parkings) {
		this.parkings = parkings;
	}

}
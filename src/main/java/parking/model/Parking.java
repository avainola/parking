package parking.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Parking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Timestamp startTime;
	private Timestamp endTime;
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="CLIENT")
	@JsonIgnore
	private Client client;
	@ManyToOne
	@JoinColumn(name="PARKING_HOUSE")
	private ParkingHouse parkingHouse;
	private Double fee;
	@ManyToOne
	@JoinColumn(name="INVOICE")
	@JsonIgnore
	private Invoice invoice;

	public Parking() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
		//client.getParkings().add(this);
	}

	public ParkingHouse getParkingHouse() {
		return parkingHouse;
	}

	public void setParkingHouse(ParkingHouse parkingHouse) {
		this.parkingHouse = parkingHouse;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Double calculateFee() {
		Double fee = 0.0;
		Double highPrice = this.client.getClientType().getPrice().getHighPrice();
		Double lowPrice = this.client.getClientType().getPrice().getLowPrice();
		LocalTime highFrom = this.parkingHouse.getPriceSchema().getHighFrom().toLocalTime();
		LocalTime highUntil = this.parkingHouse.getPriceSchema().getHighUntil().toLocalTime();
		LocalDateTime chargeTime = this.startTime.toLocalDateTime();
		while (chargeTime.isBefore(this.endTime.toLocalDateTime())) {
			if (chargeTime.toLocalTime().isBefore(highFrom) || highUntil.isBefore(chargeTime.toLocalTime())) {
				fee += lowPrice;
			} else {
				fee += highPrice;
			}
			chargeTime = chargeTime.plusMinutes(30);
		}
		this.fee = fee;
		return this.fee;
	}

}

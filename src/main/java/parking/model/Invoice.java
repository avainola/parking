package parking.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Invoice {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	@JoinColumn(name="CLIENT")
	@JsonIgnore
	private Client client;
	private Date created;
	@OneToMany(mappedBy="invoice", cascade=CascadeType.PERSIST)
	private List<Parking> parkings;
	private Double monthlyFee;
    private Double maxFee;
	private Double total;
	private String reference;

	public Invoice() {}

	public Invoice(Client client, List<Parking> parkings) {
		this.client = client;
		this.parkings = parkings;
		this.monthlyFee = client.getClientType().getMonthlyFee();
        this.maxFee = client.getClientType().getMaxFee();
		Double total = 0.0;

		for (Parking parking : this.parkings) {
			if (parking.getFee() == null) {
				total += parking.calculateFee();
			}
			else {
				total += parking.getFee();
			}
			parking.setInvoice(this);
		}
		if (this.monthlyFee != null) {
			total += this.monthlyFee;
		}
		if (this.client.getClientType().getMaxFee() != null && this.client.getClientType().getMaxFee() < total) {
			this.total = this.client.getClientType().getMaxFee();
		}
		else {
			this.total = total;
		}
		this.created = new Date(Calendar.getInstance().getTime().getTime());
		this.reference = this.client.getId().toString() + "-" + this.created.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public List<Parking> getParkings() {
		return parkings;
	}

	public void setParkings(List<Parking> parkings) {
		this.parkings = parkings;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public Double getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(Double monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

    public Double getMaxFee() {
		return maxFee;
	}

	public void setMaxFee(Double monthlyFee) {
		this.maxFee = maxFee;
	}

}
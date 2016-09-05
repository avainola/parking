package parking.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class ClientType {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double monthlyFee;
	private Double maxFee;
	@OneToOne
	@JoinColumn(name="PRICE")
	private Price price;

	ClientType() {}

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

	public Double getMonthlyFee() {
		return monthlyFee;
	}

	public void setMonthlyFee(Double monthlyFee) {
		this.monthlyFee = monthlyFee;
	}

	public Double getMaxFee() {
		return maxFee;
	}

	public void setMaxFee(Double maxFee) {
		this.maxFee = maxFee;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

}
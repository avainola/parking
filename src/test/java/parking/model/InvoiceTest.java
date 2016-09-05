package parking.model;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class InvoiceTest {
	Client client;
	ParkingHouse parkingHouse;

	@Before
	public void setUp() throws Exception {
		Price price = new Price();
		price.setLowPrice(1.0);
		price.setHighPrice(1.5);
		ClientType clientType = new ClientType();
		clientType.setName("Regular");
		clientType.setMaxFee(null);
		clientType.setPrice(price);
		this.client = new Client();
		this.client.setId(12);
		this.client.setClientType(clientType);
		PriceSchema priceSchema = new PriceSchema();
		priceSchema.setHighFrom(Time.valueOf("7:00:00"));
		priceSchema.setHighUntil(Time.valueOf("18:59:59"));
		this.parkingHouse = new ParkingHouse();
		this.parkingHouse.setPriceSchema(priceSchema);
	}

	@Test
	public void testInvoiceClientListOfParking() {
		Parking parking1 = new Parking();
		parking1.setClient(this.client);
		parking1.setParkingHouse(this.parkingHouse);
		parking1.setStartTime(Timestamp.valueOf("2016-8-10 15:00:00"));
		parking1.setEndTime(Timestamp.valueOf("2016-8-10 20:59:00"));
		double fee1 = parking1.calculateFee();
		assertEquals(16.0, fee1, 0);
		Parking parking2 = new Parking();
		parking2.setClient(this.client);
		parking2.setParkingHouse(this.parkingHouse);
		parking2.setStartTime(Timestamp.valueOf("2016-8-11 6:00:00"));
		parking2.setEndTime(Timestamp.valueOf("2016-8-11 21:01:00"));
		double fee2 = parking2.calculateFee();
		assertEquals(43.0, fee2, 0);
		Parking parking3 = new Parking();
		parking3.setClient(this.client);
		parking3.setParkingHouse(this.parkingHouse);
		parking3.setStartTime(Timestamp.valueOf("2016-8-12 14:00:00"));
		parking3.setEndTime(Timestamp.valueOf("2016-8-13 21:06:00"));
		double fee3 = parking3.calculateFee();
		assertEquals(80.0, fee3, 0);
		Parking parking4 = new Parking();
		parking4.setClient(this.client);
		parking4.setParkingHouse(this.parkingHouse);
		parking4.setStartTime(Timestamp.valueOf("2016-8-15 10:00:00"));
		parking4.setEndTime(Timestamp.valueOf("2016-8-16 19:54:00"));
		double fee4 = parking4.calculateFee();
		assertEquals(89.0, fee4, 0);;
		Parking parking5 = new Parking();
		parking5.setClient(this.client);
		parking5.setParkingHouse(this.parkingHouse);
		parking5.setStartTime(Timestamp.valueOf("2016-8-18 14:00:00"));
		parking5.setEndTime(Timestamp.valueOf("2016-8-22 8:29:00"));
		double fee5 = parking5.calculateFee();
		assertEquals(223.5, fee5, 0);
		List<Parking> parkings = new ArrayList<Parking>();
		parkings.add(parking1);
		parkings.add(parking2);
		parkings.add(parking3);
		parkings.add(parking4);
		parkings.add(parking5);
		Invoice invoice = new Invoice(this.client, parkings);
		assertEquals(451.5, invoice.getTotal(), 0);
	}

}

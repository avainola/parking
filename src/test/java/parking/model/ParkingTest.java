package parking.model;

import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

public class ParkingTest {
	Client client;
	ParkingHouse parkingHouse;

	@Before
	public void setUp() throws Exception {
		Price price = new Price();
		price.setLowPrice(1.0);
		price.setHighPrice(1.5);
		ClientType clientType = new ClientType();
		clientType.setName("Regular");
		clientType.setPrice(price);
		client = new Client();
		client.setClientType(clientType);
		PriceSchema priceSchema = new PriceSchema();
		priceSchema.setHighFrom(Time.valueOf("7:00:00"));
		priceSchema.setHighUntil(Time.valueOf("18:59:59"));
		parkingHouse = new ParkingHouse();
		parkingHouse.setPriceSchema(priceSchema);
	}

	@Test
	public void testCalculateFee() {
		Parking parking = new Parking();
		parking.setClient(client);
		parking.setParkingHouse(parkingHouse);
		parking.setStartTime(Timestamp.valueOf("2016-8-10 15:00:00"));
		parking.setEndTime(Timestamp.valueOf("2016-8-10 20:59:00"));
		double fee1 = parking.calculateFee();
		assertEquals(16.0, fee1, 0);
		parking.setStartTime(Timestamp.valueOf("2016-8-11 6:00:00"));
		parking.setEndTime(Timestamp.valueOf("2016-8-11 21:01:00"));
		double fee2 = parking.calculateFee();
		assertEquals(43.0, fee2, 0);
		parking.setStartTime(Timestamp.valueOf("2016-8-12 14:00:00"));
		parking.setEndTime(Timestamp.valueOf("2016-8-13 21:06:00"));
		double fee3 = parking.calculateFee();
		assertEquals(80.0, fee3, 0);
		parking.setStartTime(Timestamp.valueOf("2016-8-13 21:06:00"));
		parking.setEndTime(Timestamp.valueOf("2016-8-13 14:00:00"));
		double fee4 = parking.calculateFee();
		assertEquals(0.0, fee4, 0);
	}

}

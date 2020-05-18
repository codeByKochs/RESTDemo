import com.codeByKochs.RestDemo.service.DatabaseManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class RestDemoApplicationTests {

	@Autowired
	private DatabaseManager testDatabaseManager;

	//TODO use Mockito and Beans to avoid loading AddressService!

	@Test
	void addAddressToDatabase(){

//		Address testAddress = new Address();
//
////		testDatabaseManager.loadDataBase();
//
//		testAddress.setCity("TestCity");
//		testAddress.setName("TestName");
//		testAddress.setStreet("TestStreet 1");
//		testAddress.setZipcode("TestZipCode");
//
//		Address returnedAddress = testDatabaseManager.addAddress(testAddress);
//

	}

	@Test
	void contextLoads() {
	}

	@Test
	void loadDatabase(){
//		TODO implement
	}

	@Test
	void saveDatabase(){
//		TODO implement
	}
}

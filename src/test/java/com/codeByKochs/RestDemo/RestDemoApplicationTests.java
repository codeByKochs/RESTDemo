package com.codeByKochs.RestDemo;

import com.codeByKochs.RestDemo.common.Address;
import com.codeByKochs.RestDemo.service.AddressService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class RestDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void mapJsonObjects(){
		List<Address> list = new ArrayList<Address>();
		list.add(new Address(1, "name_1", "street_1", "city_1", "zicode_1"));
		list.add(new Address(2, "name_2", "street_2", "city_2", "zicode_2"));

		AddressService service = new AddressService();
		service.mapObjects(list);
	}
	@Test
	void loadDatabase(){
		//TODO implement using Mockito and Beans!
		AddressService service = new AddressService();
		service.loadDataBase();
	}
}

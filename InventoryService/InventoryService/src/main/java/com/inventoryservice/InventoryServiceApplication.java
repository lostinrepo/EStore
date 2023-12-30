package com.inventoryservice;

import com.inventoryservice.model.Inventory;
import com.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iphone_11");
			inventory.setQuantity(50);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("iphone_14");
			inventory1.setQuantity(30);

			Inventory inventory2 = new Inventory();
			inventory2.setSkuCode("samsung_galaxy_s4");
			inventory2.setQuantity(30);

			Inventory inventory3 = new Inventory();
			inventory3.setSkuCode("samsung_galaxy_s4_ultra");
			inventory3.setQuantity(30);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
			inventoryRepository.save(inventory3);



		};
	}

}

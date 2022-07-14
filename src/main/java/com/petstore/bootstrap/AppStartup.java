package com.petstore.bootstrap;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.petstore.domain.Category;
import com.petstore.domain.Order;
import com.petstore.domain.Pet;
import com.petstore.reference.OrderStatus;
import com.petstore.reference.PetStatus;
import com.petstore.repository.CategoryRepository;
import com.petstore.repository.OrderRepository;
import com.petstore.repository.PetRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppStartup implements CommandLineRunner {

	private final CategoryRepository categoryRepository;

	private final PetRepository petRepository;

	private final OrderRepository orderRepository;

	public AppStartup(CategoryRepository categoryRepository, PetRepository petRepository,
			OrderRepository orderRepository) {
		this.petRepository = petRepository;
		this.categoryRepository = categoryRepository;
		this.orderRepository = orderRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		createCategories();
		createPets();
		createOrders();

	}

	public void createCategories() {
		Category dog = new Category();
		dog.setName("Dog");
		categoryRepository.save(dog);
		Category cat = new Category();
		cat.setName("Cat");
		categoryRepository.save(cat);
		log.info("Count of Categories present:" + categoryRepository.findAll().size());
	}

	public void createPets() {
		Category dog = categoryRepository.findByName("Dog").get(0);
		Category cat = categoryRepository.findByName("Cat").get(0);
		Pet fiona = new Pet();
		fiona.setName("Fiona");
		fiona.setCategory(dog);
		fiona.setPetStatus(PetStatus.AVAILABLE);
		petRepository.save(fiona);

		Pet axel = new Pet();
		axel.setName("Axel");
		axel.setPetStatus(PetStatus.PENDING);
		axel.setCategory(cat);
		petRepository.save(axel);
		
		Pet jimmy = new Pet();
		jimmy.setName("Jimmy");
		jimmy.setPetStatus(PetStatus.PENDING);
		jimmy.setCategory(dog);
		petRepository.save(jimmy);
		
		Pet roscoe = new Pet();
		roscoe.setName("Roscoe");
		roscoe.setCategory(cat);
		roscoe.setPetStatus(PetStatus.AVAILABLE);
		petRepository.save(roscoe);

	}

	public void createOrders() {
		Pet fiona = petRepository.findByName("Fiona").get(0);
		Order order = new Order();
		order.setIsComplete(true);
		order.setStatus(OrderStatus.PLACED);
		order.setQuantity(2);
		order.setShipDate(LocalDate.now().minusDays(5));
		order.setPetId(fiona);
		orderRepository.save(order);
		
		
		Pet roscoe = petRepository.findByName("Roscoe").get(0);
		Order order2 = new Order();
		order2.setIsComplete(false);
		order2.setStatus(OrderStatus.PLACED);
		order2.setQuantity(2);
		order2.setShipDate(LocalDate.now().minusDays(6));
		order2.setPetId(roscoe);
		orderRepository.save(order2);
		
		
		Pet jimmy = petRepository.findByName("Jimmy").get(0);
		Order order3 = new Order();
		order3.setIsComplete(true);
		order3.setStatus(OrderStatus.APPROVED);
		order3.setQuantity(2);
		order3.setShipDate(LocalDate.now().minusDays(6));
		order3.setPetId(jimmy);
		orderRepository.save(order3);
		
		Pet axel = petRepository.findByName("Axel").get(0);
		Order order4 = new Order();
		order4.setIsComplete(false);
		order4.setStatus(OrderStatus.DELIVERED);
		order4.setQuantity(2);
		order4.setShipDate(LocalDate.now().minusDays(8));
		order4.setPetId(axel);
		orderRepository.save(order4);
		
	}

}

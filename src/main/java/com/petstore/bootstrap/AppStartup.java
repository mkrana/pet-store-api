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

	}

	public void createOrders() {
		Pet fiona = petRepository.findByName("Fiona").get(0);
		Order order = new Order();
		order.setIsComplete(false);
		order.setStatus(OrderStatus.PLACED);
		order.setQuantity(2);
		order.setShipDate(LocalDate.now().minusDays(5));
		order.setPetId(fiona);
		orderRepository.save(order);

	}

}

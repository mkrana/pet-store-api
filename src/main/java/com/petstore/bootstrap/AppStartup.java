package com.petstore.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.petstore.domain.Category;
import com.petstore.repository.CategoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AppStartup implements CommandLineRunner {

	private final CategoryRepository categoryRepository;

	public AppStartup(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		createCategories();

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

}

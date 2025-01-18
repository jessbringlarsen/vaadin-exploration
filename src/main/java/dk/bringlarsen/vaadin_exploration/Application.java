package dk.bringlarsen.vaadin_exploration;

import dk.bringlarsen.vaadin_exploration.person.PersonInputModel;
import dk.bringlarsen.vaadin_exploration.person.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner loadData(PersonRepository repository) {
		return (args) -> {
			repository.save(new PersonInputModel("Bill", "Gates", 45));
			repository.save(new PersonInputModel("Mark", "Zuckerberg", 35));
			repository.save(new PersonInputModel("Sundar", "Pichai", 67));
			repository.save(new PersonInputModel("Jeff", "Bezos", 50));
		};
	}
}

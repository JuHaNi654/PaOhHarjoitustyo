package fi.hh.PaOh.Harjoitustyo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.PaOh.Harjoitustyo.model.Car;
import fi.hh.PaOh.Harjoitustyo.model.CarClass;
import fi.hh.PaOh.Harjoitustyo.model.CarClassRepository;
import fi.hh.PaOh.Harjoitustyo.model.CarRepository;
import fi.hh.PaOh.Harjoitustyo.model.Track;
import fi.hh.PaOh.Harjoitustyo.model.TrackRepository;
import fi.hh.PaOh.Harjoitustyo.model.User;
import fi.hh.PaOh.Harjoitustyo.model.UserRepository;

@SpringBootApplication
public class HarjoitustyoApplication {
	public static void main(String[] args) {
		SpringApplication.run(HarjoitustyoApplication.class, args);
	}
	
	@Bean
	@Transactional
	public CommandLineRunner demolisting(CarRepository crepository, CarClassRepository ccrepository, TrackRepository trepository, UserRepository urepository) {
		return(args) -> {
			
		
			User user1 = new User("admin", "$2a$04$PSC69MYJAV0YzZUR1hcJ1eDpM22cOb/fWqn6.KHr1CMWxjxXCOss.", "testi@testi.testi", "ADMIN");
			urepository.save(user1);
		
			ccrepository.save(new CarClass("X"));
			ccrepository.save(new CarClass("P"));
			ccrepository.save(new CarClass("R"));
			ccrepository.save(new CarClass("S"));
			ccrepository.save(new CarClass("A"));
			ccrepository.save(new CarClass("B"));
			ccrepository.save(new CarClass("C"));
			ccrepository.save(new CarClass("D"));
			ccrepository.save(new CarClass("E"));
			
			trepository.save(new Track("Suzuka Circuit", "Full Circuit", "Day"));
			trepository.save(new Track("Nurburring", "Nordchleife", "Night"));
			
			
			crepository.save(new Car("Nissan", "350Z", 2008, "RWD", 270.0, 6.5, 5.4, 7.5, 5.4, "02:35.321", ccrepository.findBycarClass("B").get(0), trepository.findBytrackId(1L).get(0)));
			crepository.save(new Car("Mazda", "RX8", 2004, "RWD", 230.0, 6.3, 5.7, 6.5, 5.9, "8:20.456", ccrepository.findBycarClass("B").get(0), trepository.findBytrackId(2L).get(0)));
			



		};
	}
}

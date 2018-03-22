package fi.hh.PaOh.Harjoitustyo;


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
			trepository.save(new Track("Nurburring", "Nodschleife", "Night"));
			trepository.save(new Track("Virginia International Raceway", "Grand East", "Day"));
			trepository.save(new Track("Maple Valley Raceway", "Full Circuit", "Rain"));
			
			
			crepository.save(new Car("BMW", "M3", 2008, "RWD", 733.0, 7.8, 6.1, 9.1, 6.1, "07:21.613", ccrepository.findBycarClassId(4L).get(0), trepository.findBytrackId(2L).get(0)));
			crepository.save(new Car("Audi", "RS6", 2013, "AWD", 629.0, 7.4, 5.7, 9.3, 6.4, "07:27.562", ccrepository.findBycarClassId(4L).get(0), trepository.findBytrackId(2L).get(0)));
			crepository.save(new Car("Nissan", "Fairlady '69", 1969, "RWD", 379.0, 6.0, 6.0, 8.6, 6.0, "2:20.834", ccrepository.findBycarClassId(5L).get(0), trepository.findBytrackId(1L).get(0)));
			crepository.save(new Car("Mazda", "RX-7", 1997, "RWD", 373.0, 7.7, 5.7, 8.3, 5.5, "2:56.236", ccrepository.findBycarClassId(5L).get(0), trepository.findBytrackId(3L).get(0)));
			crepository.save(new Car("Nissan", "240SX", 1993, "RWD", 271.0, 6.3, 5.8, 7.4, 5.7, "1:56.633", ccrepository.findBycarClassId(6L).get(0), trepository.findBytrackId(4L).get(0)));



		};
	}
}

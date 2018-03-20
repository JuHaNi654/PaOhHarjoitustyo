package fi.hh.PaOh.Harjoitustyo.webControll;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fi.hh.PaOh.Harjoitustyo.model.Car;
import fi.hh.PaOh.Harjoitustyo.model.CarClassRepository;
import fi.hh.PaOh.Harjoitustyo.model.CarRepository;
import fi.hh.PaOh.Harjoitustyo.model.Track;
import fi.hh.PaOh.Harjoitustyo.model.TrackRepository;


@Controller
public class WebController {
	
	@Autowired
	private CarRepository crepository;
	@Autowired
	private CarClassRepository ccrepository;
	@Autowired
	private TrackRepository trepository;
	
	//Ohjaa kirjautumisivulle, josta käyttäjä kirjautuu sisään, jotta voi saada lisä toimintoja
	@RequestMapping(value="/login")
	public String userLogin() {
		return "login";
	}
	//Listaa kaikki tallennetut radat esille
	@RequestMapping(value="/tracklist")
	public String trackList(Model model) {
		model.addAttribute("tracks", trepository.findAll());
		return "trackList";
	}

	//Listaa valitusta radasta kaikki autot jotka on liitetty tähän rataan.
	@RequestMapping(value="/tracklist/{trackId}/carlist")
	public String carList(@PathVariable("trackId") Long trackId, Model model) {
		model.addAttribute("track", trepository.findOne(trackId));
		model.addAttribute("cars", crepository.findAll());
		return "carList";
	}
	//Voi lisätä uuden auton listaan
	@GetMapping(value="/addcar")
	public String addCar(Model model) {
		model.addAttribute("tracks", trepository.findAll());
		model.addAttribute("car", new Car());
		model.addAttribute("carclass", ccrepository.findAll());
		return "addNewCar";
	}
	//Toiminto tallentaa lisätyn auton tietokantaan, myös sama toiminto tallentaa, jos muuttaa tietyn auton tietoja.
	@PostMapping(value="/tracklist/{trackId}/carlist/save")
	public String saveCar(Car car,@Valid Car car2, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "addNewCar";
		}
		crepository.save(car);
		return "redirect:/tracklist";
	}
	//Methodi tuo sivun esille, jossa voi muokata tietyn auton tietoja.
	@RequestMapping(value ="/tracklist/{trackId}/carlist/edit-car/{carId}")
	public String ediCarData(@PathVariable("carId") Long carId, Model model) {
		model.addAttribute("car", crepository.findOne(carId));
		model.addAttribute("carclass", ccrepository.findAll());
		model.addAttribute("tracks", trepository.findAll());
		return "editCarInfo";
	}
	//Poistaa listatun auton radasta ja ohjaa takaisin sille autolistalle
	@GetMapping(value="/tracklist/{trackId}/carlist/delete-car/{carId}")
	public String deleteCarData(@PathVariable("carId") Long carId, Model model) {
		crepository.delete(carId);
		return "redirect:/tracklist/{trackId}/carlist";
	}

	@GetMapping("/addtrack")
	public String addNewTrack(Model model) {
		model.addAttribute("track", new Track());
		return "addTrackInfo";
	}
	@PostMapping("/save-track")
	public String saveNewTrack(Track track) {
		trepository.save(track);
		return "redirect:/tracklist";
	}
	//Toiminto tuo tietyn auton tiedot esille.
	@GetMapping(value="/tracklist/{trackId}/carlist/car-info/{carId}")
	public String getCarInfo(@PathVariable("carId") Long carId, Model model) {
		model.addAttribute("car", crepository.findOne(carId));
		return "carInfo";
	}
}
	

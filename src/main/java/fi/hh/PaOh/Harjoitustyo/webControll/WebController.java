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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.PaOh.Harjoitustyo.model.Car;
import fi.hh.PaOh.Harjoitustyo.model.CarClass;
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
	

	@RequestMapping(value="/login")
	public String userLogin() {
		return "login";
	}

	@RequestMapping(value="/tracklist")
	public String trackList(Model model) {
		model.addAttribute("tracks", trepository.findAll());
		return "trackList";
	}
	
	@GetMapping("/addtrack")
	public String addNewTrack(Model model) {
		model.addAttribute("track", new Track());
		return "addTrackInfo";
	}
	@PostMapping("/save-track")
	public String saveNewTrack(@Valid Track track, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "addTrackInfo";
		}
		trepository.save(track);
		return "redirect:/tracklist";
	}

	@RequestMapping(value="/tracklist/{trackId}/carlist")
	public String carList(@PathVariable("trackId") Long trackId, Model model) {
		model.addAttribute("track", trepository.findOne(trackId));
		model.addAttribute("cars", crepository.findAll());
		return "carList";
	}
	

	@GetMapping(value="/tracklist/{trackId}/carlist/car-info/{carId}")
	public String getCarInfo(@PathVariable("carId") Long carId, Model model) {
		model.addAttribute("car", crepository.findOne(carId));
		return "carInfo";
	}

	@GetMapping(value="/addcar")
	public String addCar(Model model) {
		model.addAttribute("tracks", trepository.findAll());
		model.addAttribute("car", new Car());
		model.addAttribute("carclass", ccrepository.findAll());
		return "addNewCar";
	}

	@PostMapping(value="/tracklist/{trackId}/carlist/save")
	public String saveCar(@Valid Car car, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("tracks", trepository.findAll());
			model.addAttribute("carclass", ccrepository.findAll());
			return "addNewCar";
		}
		crepository.save(car);
		return "redirect:/tracklist";
	}

	@RequestMapping(value ="/tracklist/{trackId}/carlist/edit-car/{carId}")
	public String ediCarData(@PathVariable("carId") Long carId, Model model) {
		model.addAttribute("car", crepository.findOne(carId));
		model.addAttribute("carclass", ccrepository.findAll());
		model.addAttribute("tracks", trepository.findAll());
		return "editCarInfo";
	}

	@GetMapping(value="/tracklist/{trackId}/carlist/delete-car/{carId}")
	public String deleteCarData(@PathVariable("carId") Long carId, Model model) {
		crepository.delete(carId);
		return "redirect:/tracklist/{trackId}/carlist";
	}
	
	
	
	// Rest service: koodit
	//Methodi tuo esille kaikki tietokannasa olevat autot Jsonina.
	@GetMapping(value="/cars")
	public @ResponseBody List<Car> carListRest() {
		return (List<Car>) crepository.findAll();
	}
	//Methodi tuo esille yhden auton Jsonina
	//Esim. cars/1
	@GetMapping(value="/cars/{carId}")
	public @ResponseBody Car findCarRest(@PathVariable("carId") Long carId) {
		return crepository.findOne(carId);
	}
	//Methodi tuo esille listan kaikista radoista Jsonina.
	@GetMapping(value="/tracks")
	public @ResponseBody List<Track> trackListRest() {
		return (List<Track>) trepository.findAll();
	}
	//Methodi Tuo esille yksittäisen radan id avulla
	//Esim. tracks/2
	@GetMapping(value="/tracks/{trackId}")
	public @ResponseBody Track findTrackRest(@PathVariable("trackId") Long trackId) {
		return trepository.findOne(trackId);
	}
	//Methodi Tuo esile kaikki autoluokat Jsonina
	@GetMapping(value="/carclass")
	public @ResponseBody List<CarClass> carclassListRest() {
		return (List<CarClass>) ccrepository.findAll();
	}
	//Methodi tuo esille yksittäisen luokan, kun pistää sen luoka kirjainen hakuun.
	//Esim. /carclas/s
	@GetMapping(value="/carclass/{carClass}")
	public @ResponseBody CarClass findCarClassRest(@PathVariable("carClass") String carClass) {
		return ccrepository.findBycarClassIgnoreCase(carClass);
	}
}
	

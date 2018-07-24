package fi.hh.PaOh.Harjoitustyo.webControll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.PaOh.Harjoitustyo.model.Car;
import fi.hh.PaOh.Harjoitustyo.model.CarClass;
import fi.hh.PaOh.Harjoitustyo.model.CarClassRepository;
import fi.hh.PaOh.Harjoitustyo.model.CarRepository;
import fi.hh.PaOh.Harjoitustyo.model.Track;
import fi.hh.PaOh.Harjoitustyo.model.TrackRepository;


@org.springframework.web.bind.annotation.RestController
public class RestController {
	
	@Autowired
	private CarRepository crepository;
	@Autowired
	private CarClassRepository ccrepository;
	@Autowired
	private TrackRepository trepository;
	
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
	//Esim. /carclass/s
	@GetMapping(value="/carclass/{carClass}")
	public @ResponseBody CarClass findCarClassRest(@PathVariable("carClass") String carClass) {
		return ccrepository.findBycarClassIgnoreCase(carClass);
	}
	
}

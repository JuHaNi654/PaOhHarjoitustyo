package fi.hh.PaOh.Harjoitustyo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class CarClass {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long carClassId;
	private String carClass;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "carclass")
	private List<Car> cars;
	 
	public CarClass() {
		super();
	}

	public CarClass(String carClass) {
		super();
		this.carClass = carClass;

	}

	public long getCarClassId() {
		return carClassId;
	}

	public void setCarClassId(long carClassId) {
		this.carClassId = carClassId;
	}

	public String getCarClass() {
		return carClass;
	}

	public void setCarClass(String carClass) {
		this.carClass = carClass;
	}

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	@Override
	public String toString() {
		return "CarClass [carClassId=" + carClassId + ", carClass=" + carClass + "]";
	}
	
	
	
	 
	 
	 
	 
}

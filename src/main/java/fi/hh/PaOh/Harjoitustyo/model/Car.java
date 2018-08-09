package fi.hh.PaOh.Harjoitustyo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;



/**
 * @author jturp
 * @id object id in database
 * @GeneratedValue system set value as id on created object
 * @Column Set database table column given name
 * @Size Set Bean text size. If its over given max. value or lower given min. value, then system wont accept give value to database and 
 * user message what causes error
 * @Min Set min. value, that if user gives under that value, then program wont accept and give user error message
 * @Max Set max. value, that if user gives over that value, then program wont accept and give user error message
 * @NotNull Given value cannot be empty
 * @JsonIgnore wont let program cause infite loop
 * 
 */
@Entity
public class Car {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long carId;
	
	@Column(name="Brand")
	@Size(min=2, max=20, message="{validation.brand.textSize}")
	private String merkki;
	
	@Column(name="Model")
	@Size(min=2, max=60, message="{validation.model.textSize}")
	private String malli;
	
	@Column(name="Year")
	@Min(value = 1900, message="{validation.year.min}")
	@Max(value = 2020, message="{validation.year.max}")
	private int vuosimalli;
	
	@NotEmpty(message="{validation.drive.notEmpty}")
	@NotNull
	@Column(name="Drive")
	private String veto;
	
	@Column(name="Horsepower")
	@Max(value = 1000, message="{validation.horsepower.maxValue}")
	@Min(value = 30, message="{validation.horsepower.minValue}")
	private double hevosvoima;
	
	@Column(name="Speed")
	@Max(value = 10, message="{validation.speed.maxValue}")
	@Min(value = 1, message="{validation.speed.minValue}")
	private double nopeus;
	
	@Column(name="Handling")
	@Max(value = 10, message="{validation.handling.maxValue}")
	@Min(value = 1, message="{validation.handling.minValue}")
	private double ohjattavuus;
	
	@Column(name="Acceleration")
	@Max(value = 10, message="{validation.acceleration.maxValue}")
	@Min(value = 1, message="{validation.acceleration.minValue}")
	private double kiihtyvyys;
	
	@Column(name="Braking")
	@Max(value = 10, message="{validation.braking.maxValue}")
	@Min(value = 1, message="{validation.braking.minValue}")
	private double jarrutusvoima;
	
	@Column(name="LapTime")
	private String aika;
	
	@JsonIgnore
	@NotNull(message = "{validation.carClass.notEmpty}")
	@ManyToOne
	@JoinColumn(name = "carClassId")
	private CarClass carclass;
	
	@JsonIgnore
	@NotNull (message = "{validation.track.notEmpty}")
	@ManyToOne
	@JoinColumn(name = "trackId")
	private Track track;
	
	public Car() {}

	public Car(String merkki, String malli, int vuosimalli, String veto, double hevosvoima, double nopeus,
			double ohjattavuus, double kiihtyvyys, double jarrutusvoima, String aika, CarClass carclass, Track track) {
		super();
		this.merkki = merkki;
		this.malli = malli;
		this.vuosimalli = vuosimalli;
		this.veto = veto;
		this.hevosvoima = hevosvoima;
		this.nopeus = nopeus;
		this.ohjattavuus = ohjattavuus;
		this.kiihtyvyys = kiihtyvyys;
		this.jarrutusvoima = jarrutusvoima;
		this.aika = aika;
		this.carclass = carclass;
		this.track = track;
	}
	
	public long getCarId() {
		return carId;
	}

	public void setCarId(long carId) {
		this.carId = carId;
	}

	public String getMerkki() {
		return merkki;
	}

	public void setMerkki(String merkki) {
		this.merkki = merkki;
	}

	public String getMalli() {
		return malli;
	}

	public void setMalli(String malli) {
		this.malli = malli;
	}

	public int getVuosimalli() {
		return vuosimalli;
	}

	public void setVuosimalli(int vuosimalli) {
		this.vuosimalli = vuosimalli;
	}

	public String getVeto() {
		return veto;
	}

	public void setVeto(String veto) {
		this.veto = veto;
	}

	public double getHevosvoima() {
		return hevosvoima;
	}

	public void setHevosvoima(double hevosvoima) {
		this.hevosvoima = hevosvoima;
	}

	public double getNopeus() {
		return nopeus;
	}

	public void setNopeus(double nopeus) {
		this.nopeus = nopeus;
	}

	public double getOhjattavuus() {
		return ohjattavuus;
	}

	public void setOhjattavuus(double ohjattavuus) {
		this.ohjattavuus = ohjattavuus;
	}

	public double getKiihtyvyys() {
		return kiihtyvyys;
	}

	public void setKiihtyvyys(double kiihtyvyys) {
		this.kiihtyvyys = kiihtyvyys;
	}

	public double getJarrutusvoima() {
		return jarrutusvoima;
	}

	public void setJarrutusvoima(double jarrutusvoima) {
		this.jarrutusvoima = jarrutusvoima;
	}

	public CarClass getCarclass() {
		return carclass;
	}

	public void setCarclass(CarClass carclass) {
		this.carclass = carclass;
	}
	
	public String getAika() {
		return aika;
	}

	public void setAika(String aika) {
		this.aika = aika;
	}	
	
	public Track getTrack() {
		return track;
	}
	public void setTrack(Track track) {
		this.track = track;
	}

	@Override
	public String toString() {
		if(this.carclass == null)
			return "Car [carId=" + carId + ", merkki=" + merkki + ", malli=" + malli + ", vuosimalli=" + vuosimalli
					+ ", veto=" + veto + ", hevosvoima=" + hevosvoima + ", nopeus=" + nopeus + ", ohjattavuus="
					+ ohjattavuus + ", kiihtyvyys=" + kiihtyvyys + ", jarrutusvoima=" + jarrutusvoima + ", aika=" + aika +
					"]";

		else
			return "Car [carId=" + carId + ", merkki=" + merkki + ", malli=" + malli + ", vuosimalli=" + vuosimalli
					+ ", veto=" + veto + ", hevosvoima=" + hevosvoima + ", nopeus=" + nopeus + ", ohjattavuus="
					+ ohjattavuus + ", kiihtyvyys=" + kiihtyvyys + ", jarrutusvoima=" + jarrutusvoima + ", aika=" + aika +
					", carclass=" + carclass + "]";

	}
	
	
	
}

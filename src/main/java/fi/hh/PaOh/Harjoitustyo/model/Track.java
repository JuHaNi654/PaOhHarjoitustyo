package fi.hh.PaOh.Harjoitustyo.model;



import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;



@Entity
public class Track {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long trackId;
	
	@Column(name="TrackName")
	@NotNull
	@Size(min=5, max=30, message ="Track name size must be between 5 and 30")
	private String trackName;
	@Column(name="TrackType")
	@NotNull
	@Size(min=5, max=30, message="Track Type size must be between 5 and 30")
	private String trackType;
	
	@Column(name="Scenario")
	@NotEmpty(message = "You must pick scenario Day, Night or Rain")
	@NotNull
	private String scenario;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "track")
	private List<Car> cars;
	
	public Track() {
	}

	public Track(String trackName, String trackType, String scenario) {
		super();
		this.trackName = trackName;
		this.trackType = trackType;
		this.scenario = scenario;
	}
	

	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public long getTrackId() {
		return trackId;
	}

	public void setTrackId(long trackId) {
		this.trackId = trackId;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public String getScenario() {
		return scenario;
	}

	public void setScenario(String scenario) {
		this.scenario = scenario;
	}
	
	public String getTrackType() {
		return trackType;
	}

	public void setTrackType(String trackType) {
		this.trackType = trackType;
	}


	@Override
	public String toString() {
		return "Track [trackId=" + trackId + ", trackName=" + trackName + ", trackType=" + trackType + ", scenario="
				+ scenario + "]";
	}
	
	
	
}

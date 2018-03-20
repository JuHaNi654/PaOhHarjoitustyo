package fi.hh.PaOh.Harjoitustyo.model;

import java.util.List;


import org.springframework.data.repository.CrudRepository;


public interface TrackRepository extends CrudRepository<Track, Long> {
	List<Track> findBytrackId(Long trackId);
	Track findOne(Long trackId);

}

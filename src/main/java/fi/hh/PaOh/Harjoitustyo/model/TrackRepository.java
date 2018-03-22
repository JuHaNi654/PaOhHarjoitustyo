package fi.hh.PaOh.Harjoitustyo.model;

import java.util.List;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface TrackRepository extends CrudRepository<Track, Long> {
	List<Track> findBytrackId(Long trackId);
	Track findOne(Long trackId);
	List<Track> findBytrackNameIgnoreCase(@Param("trackName") String trackName);
}

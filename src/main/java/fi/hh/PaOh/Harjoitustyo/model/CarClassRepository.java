package fi.hh.PaOh.Harjoitustyo.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource
public interface CarClassRepository extends CrudRepository<CarClass, Long> {
	
	//Program can find cars from database by given carclass id
	List<CarClass> findBycarClassId(Long carClassId);
	//Program can find cars from database by given carclass char
	CarClass findBycarClassIgnoreCase(@Param("carClass") String carClass);
	
}

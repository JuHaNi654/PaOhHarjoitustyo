package fi.hh.PaOh.Harjoitustyo.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CarClassRepository extends CrudRepository<CarClass, Long> {
	
	List<CarClass> findBycarClass(String carclass);
	CarClass findOne(Long carClassId);
}

package fi.hh.PaOh.Harjoitustyo.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;



public interface CarRepository extends CrudRepository<Car, Long> {
	List<Car> findBycarId(Long carId);

}

package co.ceiba.repository;

import org.springframework.data.repository.CrudRepository;

import co.ceiba.model.Estacionamiento;

public interface EstacionamientoRepository extends CrudRepository<Estacionamiento, Integer> {
	Estacionamiento findByIdVehiculo(int idVehiculo);
}

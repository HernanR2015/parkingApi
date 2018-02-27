package co.ceiba.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import co.ceiba.model.Vehiculo;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Integer> {
	
    Vehiculo findByPlaca (String placa); 
	
//	List<Vehiculo> findByTipoVehiculo(int tipoVehiculo);
	
	List<Vehiculo> findByEstado (int estado);
	
	List<Vehiculo> findByTipoVehiculoAndEstado(int tipoVehiculo, int estado);

}

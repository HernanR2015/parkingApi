package co.ceiba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import co.ceiba.model.Vehiculo;
import co.ceiba.repository.VehiculoRepository;

public class VehiculoServiceImpl implements VehiculoService{
	
	@Autowired
	VehiculoRepository vehiculoRepository;
	
//	@Override
//	public List<Vehiculo> listAllVehiculo() {
//		return (List<Vehiculo>) vehiculoRepository.findAll();
//	}

//	@Override
//	public List<Vehiculo> getVehiculoByTipoVehiculo(int tipoVehiculo) {
//		return vehiculoRepository.findByTipoVehiculo(tipoVehiculo);
//	}

	@Override
	public List<Vehiculo> getByTipoVehiculoAndEstado(int tipoVehiculo, int estado) {
		return vehiculoRepository.findByTipoVehiculoAndEstado(tipoVehiculo, estado);
	}

	@Override
	public Vehiculo getVehiculoById(int id) {
		return vehiculoRepository.findOne(id);
	}

	@Override
	public Vehiculo saveVehiculo(Vehiculo vehiculo) {
		return vehiculoRepository.save(vehiculo);
	}

	@Override
	public Vehiculo getVehiculoByPlaca(String placa) {
		return vehiculoRepository.findByPlaca(placa);	
	}

//	@Override
//	public void deleteVehiculo(int id) {
//		vehiculoRepository.delete(id);	
//	}

	@Override
	public List<Vehiculo> getByEstado(int estado) {
		return vehiculoRepository.findByEstado(estado);
		
	}
}

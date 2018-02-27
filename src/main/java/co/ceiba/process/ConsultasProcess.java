package co.ceiba.process;

import java.util.List;

import co.ceiba.model.TipoVehiculo;
import co.ceiba.model.Vehiculo;
import co.ceiba.service.TipoVehiculoServiceImpl;
import co.ceiba.service.VehiculoService;

public class ConsultasProcess {
	
	public List<TipoVehiculo> consultarTipoVehiculo() {
		TipoVehiculoServiceImpl tipos = new TipoVehiculoServiceImpl();
		List<TipoVehiculo> lista = (List<TipoVehiculo>) tipos.listAllTiposVehiculo();
		System.out.println("SIZE --->"+lista.size());
		return lista;
	}
	
	public int consultarVehiculosActivosPorTipo(VehiculoService vehiculoService, int tipoVehiculo, int estado){
		List<Vehiculo> listaVehiculo = vehiculoService.getByTipoVehiculoAndEstado(tipoVehiculo, estado);
		return listaVehiculo.size();
	}

}

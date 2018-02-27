package co.ceiba.web;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import co.ceiba.model.Estacionamiento;
import co.ceiba.model.Vehiculo;
import co.ceiba.process.RegistrarIngresoProcess;
import co.ceiba.process.RegistrarSalidaProcess;
import co.ceiba.service.EstacionamientoService;
import co.ceiba.service.VehiculoService;
import co.ceiba.service.TipoVehiculoService;

@RestController
@RequestMapping("/Estacionamiento")
public class EstacionamientoController {
	
	@Autowired
	private EstacionamientoService estacionamientoService;
	
	@Autowired
	private VehiculoService vehiculoService;
	
	@Autowired
	private TipoVehiculoService tipoVehiculoService;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST, value = "/asignarEstacionamiento")
	public Estacionamiento asignarEstacionamiento(@RequestBody Vehiculo vehiculo) {
		RegistrarIngresoProcess ingresoProcess = new RegistrarIngresoProcess();
		return ingresoProcess.registrarIngresoProcess(estacionamientoService, vehiculoService, vehiculo);    
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET, value = "/liberarEstacionamiento/{placa}")
	public Estacionamiento liberarEstacionamiento(@PathVariable String placa) throws JSONException {
		RegistrarSalidaProcess parqueaderoProcess = new RegistrarSalidaProcess();
		return parqueaderoProcess.registrarSalidaProcess(tipoVehiculoService,vehiculoService,estacionamientoService,placa);
	}
	
	

}

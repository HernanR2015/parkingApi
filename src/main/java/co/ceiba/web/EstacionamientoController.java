package co.ceiba.web;

import java.text.ParseException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	@JsonIgnoreProperties
	public Map<Object,Object> asignarEstacionamiento(@RequestBody Vehiculo vehiculo) {
		RegistrarIngresoProcess ingresoProcess = new RegistrarIngresoProcess();
		Estacionamiento estacionamiento = new Estacionamiento();
		return ingresoProcess.registrarIngresoProcess(estacionamientoService, vehiculoService, vehiculo,estacionamiento);    
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST, value = "/liberarEstacionamiento")
	
	public Map<Object, Object> liberarEstacionamiento(@RequestBody String placa) throws ParseException  {
		
		RegistrarSalidaProcess parqueaderoProcess = new RegistrarSalidaProcess();
		return parqueaderoProcess.registrarSalidaProcess(tipoVehiculoService,vehiculoService,estacionamientoService,placa);
	}
	
	

}

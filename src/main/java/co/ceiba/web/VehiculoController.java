package co.ceiba.web;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import co.ceiba.process.ParqueaderoProcess;
import co.ceiba.service.VehiculoService;

@RestController
@RequestMapping("/Vehiculo")
public class VehiculoController {

	@Autowired
	private VehiculoService vehiculoService;

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET, value = "/Vehiculos")
	public String listarVehiculosActivos() throws JSONException {
		ParqueaderoProcess parqueaderoProcess = new ParqueaderoProcess();
		return parqueaderoProcess.listarVehiculosActivos(vehiculoService);
	}
	
//	@CrossOrigin(origins = "*")
//	@RequestMapping(method = RequestMethod.GET, value = "/Vehiculos/{placa}")
//	public String buscarVehiculoPorPlaca() throws JSONException {
//		ParqueaderoProcess parqueaderoProcess = new ParqueaderoProcess();
//		return parqueaderoProcess.listarVehiculosActivos(vehiculoService);
//	}

}

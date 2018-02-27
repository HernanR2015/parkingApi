package co.ceiba.process;

import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import co.ceiba.model.TipoVehiculo;
import co.ceiba.model.Vehiculo;
import co.ceiba.service.TipoVehiculoService;
import co.ceiba.service.VehiculoService;

public class ParqueaderoProcess {
//	@Autowired
//	TipoVehiculoService tipovehiculoService;
	
	static final int ESTADO_VEHICULO_ACTIVO = 1;

	public String agregarTipoVehiculo(TipoVehiculo tipoVehiculo, TipoVehiculoService tipovehiculoService)throws JSONException {
		JSONObject response = new JSONObject();
		try {
			TipoVehiculo tvehiculo = tipovehiculoService.saveTipoVehiculo(tipoVehiculo);
			if (tvehiculo.getIdTipoVehiculo() != 0) {
				response.put("tipovehiculo", tvehiculo);
				response.put("message", "El nuevo Tipo de Vehiculo se registro correctamente");
			} else
				response.put("message", "No se Guardo el nuevo Tipo de Vehiculo");
		} catch (JSONException jexcepcion) {
			response.put("Error", "error al crear el Tipo Vehiculo");
		}

		return response.toString();
	}
	
	public String listarTiposVehiculos(TipoVehiculoService tipovehiculoService ) throws JSONException {
		JSONObject response = new JSONObject();
		try {
			List<TipoVehiculo> tvehiculo = tipovehiculoService.listAllTiposVehiculo();
			if (tvehiculo.size()>0) {
				response.put("listvehiculos", convertListToJSON(tvehiculo));
				response.put("message", "Lista Obtenida Satisfactoriamente");
			}else {
				response.put("error","no se obtuvieron resultados");
			}
			return response.toString();
			
		}catch (JSONException jexcepcion) {
			response.put("Error", "error al obtener Tipos de Vehiculos");
		}
		return null;
		
	}
	
	public String listarVehiculosActivos(VehiculoService vehiculoService ) throws JSONException {
		JSONObject response = new JSONObject();
		try {
			List<Vehiculo> vehiculos = vehiculoService.getByEstado(ESTADO_VEHICULO_ACTIVO);
			if (vehiculos.size()>0) {
				response.put("VehiculosActivos", convertListToJSON(vehiculos));
				response.put("message", "Lista Obtenida Satisfactoriamente");
			}else {
				response.put("error","no se obtuvieron resultados");
			}
			return response.toString();
			
		}catch (JSONException jexcepcion) {
			response.put("Error", "error al obtener Tipos de Vehiculos");
		}
		return null;
		
	}
	
	public Vehiculo obtenerVehiculoPorPlaca (String placa, VehiculoService vehiculoService) {
		Vehiculo vehiculo = vehiculoService.getVehiculoByPlaca(placa);
		return vehiculo;
	}
	
	private String convertListToJSON(List lista) {
		Gson gson = new Gson();
		return gson.toJson(lista);
		
	}

}

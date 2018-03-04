package co.ceiba.process;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.TestDataBuilder.EstacionamientoTestDataBuilder;
import co.ceiba.TestDataBuilder.TipoVehiculoTestDataBuilder;
import co.ceiba.TestDataBuilder.VehiculoTestDataBuilder;
import co.ceiba.model.Estacionamiento;
import co.ceiba.model.TipoVehiculo;
import co.ceiba.model.Vehiculo;
import co.ceiba.service.EstacionamientoService;
import co.ceiba.service.EstacionamientoServiceImpl;
import co.ceiba.service.TipoVehiculoServiceImpl;
import co.ceiba.service.TipoVehiculoService;

import co.ceiba.service.VehiculoService;
import co.ceiba.service.VehiculoServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class RegistrarSalidaProcessTest {
	
	@TestConfiguration
	static class EstacionamientoServiceImplTestContextConfiguration {

		@Bean
		public EstacionamientoService estacionamientoService() {
			return new EstacionamientoServiceImpl();
		}
	}

	@TestConfiguration
	static class VehiculoServiceImplTestContextConfiguration {

		@Bean
		public VehiculoService VehiculoService() {
			return new VehiculoServiceImpl();
		}
	}
	
	@TestConfiguration
	static class TipoVehiculoServiceImplTestContextConfiguration {

		@Bean
		public TipoVehiculoService TipoVehiculoService() {
			return new TipoVehiculoServiceImpl();
		}
	}
	
	@MockBean
	private VehiculoService vehiculoService;
	
	@MockBean
	private EstacionamientoService estacionamientoService;
	
	@MockBean
	private TipoVehiculoService tipoVehiculoService;
	
	@Test
	public void validarSalidaVehiculoExtitosa() throws ParseException {
		
		RegistrarSalidaProcess registrarSalida = new RegistrarSalidaProcess();
		String fechaingresoString = "2018-03-03 13:00:00";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date fechaIngreso = format.parse(fechaingresoString);
		
//		String fechaSalidaString = "2018-03-03 10:00:00";
		Date fechaSalida =  new Date(); //format.parse(fechaSalidaString);
		Vehiculo vehiculoTest = new VehiculoTestDataBuilder().withPlaca("ABC123").withEstado(1).withCilindraje("1000").withTipoVehiculo(4).build();
		vehiculoTest.setIdVehiculo(1);
		
		Mockito.when(vehiculoService.getVehiculoByPlaca(vehiculoTest.getPlaca())).thenReturn(vehiculoTest);
		
		Estacionamiento estacionamientoTest = new EstacionamientoTestDataBuilder().withFechaIngreso(fechaIngreso).withFechaRetiro(fechaSalida).withIdVehiculo(vehiculoTest.getIdVehiculo()).build();
		estacionamientoTest.setIdEstacionamiento(1);
		Mockito.when(estacionamientoService.getEstacionamientoByIdVehiculo(vehiculoTest.getIdVehiculo())).thenReturn(estacionamientoTest);
		
		TipoVehiculo tipoVehiculoTest = new TipoVehiculoTestDataBuilder().build();
		tipoVehiculoTest.setIdTipoVehiculo(4);
		
		Mockito.when(tipoVehiculoService.getTipoVehiculoById(tipoVehiculoTest.getIdTipoVehiculo())).thenReturn(tipoVehiculoTest);
		
		Mockito.when(estacionamientoService.saveEstacionamiento(estacionamientoTest)).thenReturn(estacionamientoTest);
		
		Map<Object,Object> estacionamientoRec = registrarSalida.registrarSalidaProcess(tipoVehiculoService, vehiculoService, estacionamientoService, "ABC123");  // registrarIngresoProcess(estacionamientoService,vehiculoService, vehiculoBuild, estacionamientoTest);

		
		Assert.assertTrue(estacionamientoRec.containsValue(00)); // NotNull();

	}
	
	@Test
	public void validarVehiculoNoEstacionado() throws ParseException {
		
		RegistrarSalidaProcess registrarSalida = new RegistrarSalidaProcess();
		String fechaingresoString = "2018-03-03 08:00:00";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date fechaIngreso = format.parse(fechaingresoString);
		
		String fechaSalidaString = "2018-03-03 10:00:00";
		Date fechaSalida = format.parse(fechaSalidaString);
		Vehiculo vehiculoTest = new VehiculoTestDataBuilder().withPlaca("ABC123").withEstado(1).withTipoVehiculo(3).build();
		vehiculoTest.setIdVehiculo(1);
		
		Mockito.when(vehiculoService.getVehiculoByPlaca(vehiculoTest.getPlaca())).thenReturn(vehiculoTest);
		
		Estacionamiento estacionamientoTest = new EstacionamientoTestDataBuilder().withFechaIngreso(fechaIngreso).withFechaRetiro(fechaSalida).withIdVehiculo(vehiculoTest.getIdVehiculo()).build();
		estacionamientoTest.setIdEstacionamiento(0);
		Mockito.when(estacionamientoService.getEstacionamientoByIdVehiculo(vehiculoTest.getIdVehiculo())).thenReturn(estacionamientoTest);
		
		TipoVehiculo tipoVehiculoTest = new TipoVehiculoTestDataBuilder().build();
		tipoVehiculoTest.setIdTipoVehiculo(3);
		
		Mockito.when(tipoVehiculoService.getTipoVehiculoById(tipoVehiculoTest.getIdTipoVehiculo())).thenReturn(tipoVehiculoTest);
		
		Mockito.when(estacionamientoService.saveEstacionamiento(estacionamientoTest)).thenReturn(estacionamientoTest);
		
		Map<Object,Object> estacionamientoRec = registrarSalida.registrarSalidaProcess(tipoVehiculoService, vehiculoService, estacionamientoService, "ABC123");  // registrarIngresoProcess(estacionamientoService,vehiculoService, vehiculoBuild, estacionamientoTest);

		
		Assert.assertTrue(estacionamientoRec.containsValue(99)); // NotNull();

	}
	
	@Test
	public void validarVehiculoRegistradoInactivo() throws ParseException {
		
		RegistrarSalidaProcess registrarSalida = new RegistrarSalidaProcess();
		String fechaingresoString = "2018-03-03 08:00:00";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date fechaIngreso = format.parse(fechaingresoString);
		
		String fechaSalidaString = "2018-03-03 10:00:00";
		Date fechaSalida = format.parse(fechaSalidaString);
		Vehiculo vehiculoTest = new VehiculoTestDataBuilder().withPlaca("ABC123").withEstado(0).withTipoVehiculo(3).build();
		vehiculoTest.setIdVehiculo(1);
		
		Mockito.when(vehiculoService.getVehiculoByPlaca(vehiculoTest.getPlaca())).thenReturn(vehiculoTest);
		
		Estacionamiento estacionamientoTest = new EstacionamientoTestDataBuilder().withFechaIngreso(fechaIngreso).withFechaRetiro(fechaSalida).withIdVehiculo(vehiculoTest.getIdVehiculo()).build();
		estacionamientoTest.setIdEstacionamiento(0);
		Mockito.when(estacionamientoService.getEstacionamientoByIdVehiculo(vehiculoTest.getIdVehiculo())).thenReturn(estacionamientoTest);
		
		TipoVehiculo tipoVehiculoTest = new TipoVehiculoTestDataBuilder().build();
		tipoVehiculoTest.setIdTipoVehiculo(3);
		
		Mockito.when(tipoVehiculoService.getTipoVehiculoById(tipoVehiculoTest.getIdTipoVehiculo())).thenReturn(tipoVehiculoTest);
		
		Mockito.when(estacionamientoService.saveEstacionamiento(estacionamientoTest)).thenReturn(estacionamientoTest);
		
		Map<Object,Object> estacionamientoRec = registrarSalida.registrarSalidaProcess(tipoVehiculoService, vehiculoService, estacionamientoService, "ABC123");  // registrarIngresoProcess(estacionamientoService,vehiculoService, vehiculoBuild, estacionamientoTest);

		
		Assert.assertTrue(estacionamientoRec.containsValue(99)); // NotNull();

	}
	
	@Test
	public void validarVehiculoNoRegistrado() throws ParseException {
		
		RegistrarSalidaProcess registrarSalida = new RegistrarSalidaProcess();
		String fechaingresoString = "2018-03-03 08:00:00";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date fechaIngreso = format.parse(fechaingresoString);
		 
		String fechaSalidaString = "2018-03-03 10:00:00";
		Date fechaSalida = format.parse(fechaSalidaString);
		Vehiculo vehiculoTest = new VehiculoTestDataBuilder().withPlaca("ABC123").withEstado(0).withTipoVehiculo(3).build();
		vehiculoTest.setIdVehiculo(1);
		
		Mockito.when(vehiculoService.getVehiculoByPlaca(vehiculoTest.getPlaca())).thenReturn(null);
		
		Estacionamiento estacionamientoTest = new EstacionamientoTestDataBuilder().withFechaIngreso(fechaIngreso).withFechaRetiro(fechaSalida).withIdVehiculo(vehiculoTest.getIdVehiculo()).build();
		estacionamientoTest.setIdEstacionamiento(1);
		Mockito.when(estacionamientoService.getEstacionamientoByIdVehiculo(vehiculoTest.getIdVehiculo())).thenReturn(estacionamientoTest);
		
		TipoVehiculo tipoVehiculoTest = new TipoVehiculoTestDataBuilder().build();
		tipoVehiculoTest.setIdTipoVehiculo(3);
		
		Mockito.when(tipoVehiculoService.getTipoVehiculoById(tipoVehiculoTest.getIdTipoVehiculo())).thenReturn(tipoVehiculoTest);
		
		Mockito.when(estacionamientoService.saveEstacionamiento(estacionamientoTest)).thenReturn(estacionamientoTest);
		
		Map<Object,Object> estacionamientoRec = registrarSalida.registrarSalidaProcess(tipoVehiculoService, vehiculoService, estacionamientoService, "ABC123");  // registrarIngresoProcess(estacionamientoService,vehiculoService, vehiculoBuild, estacionamientoTest);

		
		Assert.assertTrue(estacionamientoRec.containsValue(99)); // NotNull();

	}
	
	
	
	
}
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertNotNull;
//
////import java.text.DateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import co.ceiba.TestDataBuilder.EstacionamientoTestDataBuilder;
//import co.ceiba.TestDataBuilder.TipoVehiculoTestDataBuilder;
//import co.ceiba.TestDataBuilder.VehiculoTestDataBuilder;
//import co.ceiba.model.Estacionamiento;
//import co.ceiba.model.TipoVehiculo;
//import co.ceiba.model.Vehiculo;
//import co.ceiba.repository.EstacionamientoRepository;
//import co.ceiba.repository.TipoVehiculoRepository;
//import co.ceiba.repository.VehiculoRepository;
//import co.ceiba.service.EstacionamientoService;
//import co.ceiba.service.EstacionamientoServiceImpl;
//import co.ceiba.service.TipoVehiculoService;
//import co.ceiba.service.TipoVehiculoServiceImpl;
//import co.ceiba.service.VehiculoService;
//import co.ceiba.service.VehiculoServiceImpl;
//
//public class RegistrarSalidaProcessTest {
//	
//	
//	@TestConfiguration
//	static class VehiculoServiceImplTestContextConfiguration {
//
//		@Bean
//		public VehiculoService vehiculoService() {
//			return new VehiculoServiceImpl();
//		}
//	}
//	
//	@TestConfiguration
//	static class EstacionamientoServiceImplTestContextConfiguration {
//
//		@Bean
//		public EstacionamientoService vehiculoService() {
//			return new EstacionamientoServiceImpl();
//		}
//	}
//	
//	@TestConfiguration
//	static class TipoVehiculoServiceImplTestContextConfiguration {
//
//		@Bean
//		public TipoVehiculoService tipoVehiculoService() {
//			return new TipoVehiculoServiceImpl();
//		}
//	}
//	
//	@Autowired
//	private TipoVehiculoService tipoVehiculoService;
//
//	@Autowired
//	private VehiculoService vehiculoService;
//	
//	@Autowired
//	private EstacionamientoService estacionamientoService;
//	
//	@MockBean
//	private TipoVehiculoRepository tipoVehiculoRepository;
//	
//	@MockBean
//	private VehiculoRepository vehiculoRepository;
//	
//	
//	@MockBean
//	private EstacionamientoRepository estacionamientoRepository;
//	
//	@Before
//	public void setUp() throws ParseException {
//		String ingreso ="2018-02-27 08:00:00";	
//		String salida ="2018-02-27 16:00:00";
//		
//		SimpleDateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date fechaIngreso = hourdateFormat.parse(ingreso);
//		Date fechaSalida = hourdateFormat.parse(salida);//   format(fechaIngreso);
//		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder().withNombreTipoVehiculo("carro").withValorHora(1000).withValorDia(8000).build();
//		tipoVehiculo.setIdTipoVehiculo(1);
//		Vehiculo vehiculoTest = new VehiculoTestDataBuilder().withPlaca("XYZ123").withEstado(1).withTipoVehiculo(tipoVehiculo.getIdTipoVehiculo()).build();
//		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withIdVehiculo(vehiculoTest.getIdVehiculo()).withFechaIngreso(fechaIngreso).withFechaRetiro(fechaSalida).build();
//		System.out.println(vehiculoTest.getPlaca());
//		
////		Mockito.when(vehiculoRepository.findByPlaca(vehiculoTest.getPlaca())).thenReturn(vehiculoTest);
////		
////		Mockito.when(vehiculoRepository.findByPlaca(vehiculoTest.getPlaca())).thenReturn(vehiculoTest);
////		Mockito.when(estacionamientoRepository.findByIdVehiculo(vehiculoTest.getIdVehiculo())).thenReturn(estacionamiento);
////		Mockito.when(tipoVehiculoRepository.findOne(vehiculoTest.getTipoVehiculo())).thenReturn(tipoVehiculo);
////		
//		
//		Mockito.when(vehiculoService.getVehiculoByPlaca(vehiculoTest.getPlaca())).thenReturn(vehiculoTest);
//		Mockito.when(estacionamientoService.getEstacionamientoByIdVehiculo(vehiculoTest.getIdVehiculo())).thenReturn(estacionamiento);
//		Mockito.when(tipoVehiculoService.getTipoVehiculoById(vehiculoTest.getTipoVehiculo())).thenReturn(tipoVehiculo);
//		
//		
//	}
//	
//	
//	
//	
//	@Test 
//	public void registrarSalidaProcess() throws ParseException {
//		String ingreso ="2018-02-27 08:00:00";	
//		String salida ="2018-02-27 16:00:00";
//		
//		SimpleDateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date fechaIngreso = hourdateFormat.parse(ingreso);
//		Date fechaSalida = hourdateFormat.parse(salida);//   format(fechaIngreso);
//		TipoVehiculo tipoVehiculo = new TipoVehiculoTestDataBuilder().withNombreTipoVehiculo("carro").withValorHora(1000).withValorDia(8000).build();
//		tipoVehiculo.setIdTipoVehiculo(1);
//		Vehiculo vehiculoTest = new VehiculoTestDataBuilder().withPlaca("XYZ123").withEstado(1).withTipoVehiculo(tipoVehiculo.getIdTipoVehiculo()).build();
//		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withIdVehiculo(vehiculoTest.getIdVehiculo()).withFechaIngreso(fechaIngreso).withFechaRetiro(fechaSalida).build();
//		
//		RegistrarSalidaProcess registrarSalidaTest = new RegistrarSalidaProcess();
//		Estacionamiento estacionamientoObtenidoTest = registrarSalidaTest.registrarSalidaProcess(tipoVehiculoService, vehiculoService, estacionamientoService, vehiculoTest.getPlaca());
//		
//		assertNotNull(estacionamientoObtenidoTest);
//	
//		
//	}
//	
//	@Test
//	public void calcularCobro() throws ParseException {
//		String ingreso ="2018-02-27 08:00:00";	
//		String salida ="2018-02-27 16:00:00";
//		
//		SimpleDateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date fechaIngreso = hourdateFormat.parse(ingreso);
//		Date fechaSalida = hourdateFormat.parse(salida);//   format(fechaIngreso);
//
//		
//		System.out.println("fechaIngreso "+fechaIngreso);
//		Estacionamiento estacionamientoTest = new EstacionamientoTestDataBuilder().withFechaIngreso(fechaIngreso).build();
//		RegistrarSalidaProcess registro = new RegistrarSalidaProcess();
//		double valorRetornado = registro.calcularCobro(estacionamientoTest, 2000, 5000, fechaSalida);	
//		assertEquals(16000, valorRetornado,0);
//	}
//}

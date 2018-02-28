//package co.ceiba.process;
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

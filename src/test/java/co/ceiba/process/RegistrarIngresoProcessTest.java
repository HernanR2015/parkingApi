package co.ceiba.process;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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
import co.ceiba.repository.EstacionamientoRepository;
import co.ceiba.repository.VehiculoRepository;
import co.ceiba.service.EstacionamientoService;
import co.ceiba.service.EstacionamientoServiceImpl;
import co.ceiba.service.TipoVehiculoService;
import co.ceiba.service.TipoVehiculoServiceImpl;
import co.ceiba.service.VehiculoService;
import co.ceiba.service.VehiculoServiceImpl;

@RunWith(SpringRunner.class)
@DataJpaTest
public class RegistrarIngresoProcessTest {

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

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private EstacionamientoService estacionamientoService;

	@MockBean
	private VehiculoService vehiculoService;
	
	@MockBean
	private VehiculoRepository vehiculoRepository;

	@MockBean
	private EstacionamientoRepository estacionamientoRepository;

	@Before
	public void setUp() {
		Date fechaActual = new Date();
//		TipoVehiculo tipoVehiculoBuild = new TipoVehiculoTestDataBuilder().withNombreTipoVehiculo("carro")
//				.withValorHora(1000).withValorDia(8000).build();
//		tipoVehiculoBuild.setIdTipoVehiculo(1);
//		Mockito.when(tipoVehiculoService.getTipoVehiculoById(tipoVehiculoBuild.getIdTipoVehiculo()))
//				.thenReturn(tipoVehiculoBuild);

		Vehiculo vehiculoBuild = new VehiculoTestDataBuilder().withPlaca("XYZ123").withTipoVehiculo(1).withEstado(1)
				.build();
		vehiculoBuild.setIdVehiculo(1);
		List<Vehiculo> listVehiculos = new ArrayList<Vehiculo>();
		Mockito.when(vehiculoService.getByTipoVehiculoAndEstado(vehiculoBuild.getTipoVehiculo(), 1))
				.thenReturn(listVehiculos);
		
		Estacionamiento estacionamientoTest = new EstacionamientoTestDataBuilder().withFechaIngreso(fechaActual).withIdVehiculo(vehiculoBuild.getIdVehiculo()).build();
		estacionamientoTest.setIdEstacionamiento(1);
		Mockito.when(vehiculoService.saveVehiculo(vehiculoBuild)).thenReturn(vehiculoBuild);
		Mockito.when(estacionamientoService.saveEstacionamiento(estacionamientoTest)).thenReturn(estacionamientoTest);
		
		
	}

	@Test
	public void realizarIngreso() {
		Date fechaActual = new Date();
		RegistrarIngresoProcess ingresarVehiculo = new RegistrarIngresoProcess();
		Vehiculo vehiculoTest = new VehiculoTestDataBuilder().withPlaca("XYZ123").withTipoVehiculo(1).withEstado(0).build();
//		vehiculoTest.setIdVehiculo(1);
		entityManager.persist(vehiculoTest);
		entityManager.flush();
//		
		Estacionamiento estacionamientoRec =ingresarVehiculo.registrarIngresoProcess(estacionamientoService, vehiculoService, vehiculoTest);
		
		Assert.assertNotNull(estacionamientoRec);
		
		
		
		
		
		
		
		
		
		
//		Estacionamiento estacionamientoTest = new EstacionamientoTestDataBuilder().withFechaIngreso(fechaActual).withIdVehiculo(vehiculoTest.getIdVehiculo()).build();
//		
//		 = ingresarVehiculo.registrarIngresoProcess(estacionamientoService, vehiculoService, vehiculoTest);
//		
		
	}
	
	
	
	
	
	
	
	
	
	
	
//	
//	EstacionamientoService estacionamientoService,
//	VehiculoService vehiculoService, Vehiculo vehiculo

}
//
// @TestConfiguration
// static class TipoVehiculoServiceImplTestContextConfiguration {
//
// @Bean
// public EstacionamientoService estacionamientoService() {
// return new EstacionamientoServiceImpl();
// }
//
// @Bean
// public VehiculoService vehiculoService() {
// return new VehiculoServiceImpl();
// }
// }
//
// @MockBean
// private TipoVehiculoServiceImpl tipoVehiculoServiceImpl;
//
//
// @MockBean
// private VehiculoServiceImpl VehiculoServiceImpl;
//
//
// @MockBean
// private EstacionamientoServiceImpl EstacionamientoServiceImpl;
//
// @Before
// public void setUp() {
//// TipoVehiculo tvehiculo = new
// TipoVehiculoTestDataBuilder().withNombreTipoVehiculo("carro").build();
// Vehiculo vehiculo = new
// VehiculoTestDataBuilder().withTipoVehiculo(1).withPlaca("ABC123").build();
// List<Vehiculo> listaVehiculo = new ArrayList<Vehiculo>();
// listaVehiculo.add(vehiculo);
// //Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().with
//
// Mockito.when(VehiculoServiceImpl.getByTipoVehiculoAndEstado(1,
// 1)).thenReturn(listaVehiculo);
//
// }
//
// @Test
// public void validarRegistroIngreso() {
//
// }
//
// }
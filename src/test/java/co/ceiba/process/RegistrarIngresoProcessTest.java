package co.ceiba.process;

import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
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
import co.ceiba.TestDataBuilder.VehiculoTestDataBuilder;
import co.ceiba.model.Estacionamiento;
import co.ceiba.model.Vehiculo;
import co.ceiba.service.EstacionamientoService;
import co.ceiba.service.EstacionamientoServiceImpl;
import co.ceiba.service.VehiculoServiceImpl;

//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.test.context.junit4.SpringRunner;
//import co.ceiba.TestDataBuilder.EstacionamientoTestDataBuilder;
//import co.ceiba.TestDataBuilder.VehiculoTestDataBuilder;
//import co.ceiba.model.Estacionamiento;
//import co.ceiba.model.Vehiculo;
//import co.ceiba.repository.EstacionamientoRepository;
//import co.ceiba.repository.VehiculoRepository;
//import co.ceiba.service.EstacionamientoService;
//import co.ceiba.service.EstacionamientoServiceImpl;
import co.ceiba.service.VehiculoService;
//import co.ceiba.service.VehiculoServiceImpl;
//
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
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

	@MockBean
	private VehiculoService vehiculoService;
	

	@MockBean
	private EstacionamientoService estacionamientoService;
	
	@MockBean
	private ReglasParqueaderoProcess reglasProcess;


	@Before
	public void setUp() {

	}

	@Test
	public void realizarIngresoExitoso() {
//		Date fechaActual = new Date();
		Vehiculo vehiculoBuild = new VehiculoTestDataBuilder().withPlaca("XYZ123").withTipoVehiculo(3).withEstado(1)
				.build();
		vehiculoBuild.setIdVehiculo(1);
//		ReglasParqueaderoProcess reglas = new ReglasParqueaderoProcess();
		
		Mockito.when(reglasProcess.validarCupoTipoVehiculo(10, 3)).thenReturn(true);
		Estacionamiento estacionamientoTest = new EstacionamientoTestDataBuilder().withIdEstacionamiento(0).withIdVehiculo(1).withFechaIngreso(null).withFechaRetiro(null).build();
		Mockito.when(estacionamientoService.saveEstacionamiento(estacionamientoTest)).thenReturn(estacionamientoTest);
		RegistrarIngresoProcess ingresarVehiculo = new RegistrarIngresoProcess();

		Mockito.when(vehiculoService.saveVehiculo(vehiculoBuild)).thenReturn(vehiculoBuild);
		
		
		
		Map<Object,Object> estacionamientoRec = ingresarVehiculo.registrarIngresoProcess(estacionamientoService,vehiculoService, vehiculoBuild, estacionamientoTest);
		
		Assert.assertTrue(estacionamientoRec.containsValue(00)); // NotNull();
	}
	
	@Test
	public void ingresoPlacaInvalida() {
//		Date fechaActual = new Date();
		Vehiculo vehiculoBuild = new VehiculoTestDataBuilder().withPlaca("ABC123").withEstado(1)
				.build();
		vehiculoBuild.setIdVehiculo(1);
//		ReglasParqueaderoProcess reglas = new ReglasParqueaderoProcess();
		
		RegistrarIngresoProcess ingresarVehiculo = new RegistrarIngresoProcess();

		Mockito.when(reglasProcess.validarCupoTipoVehiculo(10, 3)).thenReturn(true);
		Mockito.when(reglasProcess.validarPlaca("ABC123")).thenReturn(false);
		Estacionamiento estacionamientoTest = new EstacionamientoTestDataBuilder().withIdEstacionamiento(0).withIdVehiculo(1).withFechaIngreso(null).withFechaRetiro(null).build();
//		Mockito.when(estacionamientoService.saveEstacionamiento(estacionamientoTest)).thenReturn(estacionamientoTest);
		//RegistrarIngresoProcess ingresarVehiculo = new RegistrarIngresoProcess();
		//Mockito.when(vehiculoService.saveVehiculo(vehiculoBuild)).thenReturn(vehiculoBuild);
		Map<Object,Object> estacionamientoRec = ingresarVehiculo.registrarIngresoProcess(estacionamientoService,vehiculoService, vehiculoBuild, estacionamientoTest);
		Assert.assertTrue(estacionamientoRec.containsValue(99)); // NotNull();
	}
	
	@Test
	public void realizarIngresoSinDisponibilidadAutos() {
//		Date fechaActual = new Date();
		Vehiculo vehiculoBuild = new VehiculoTestDataBuilder().withPlaca("XYZ123").withTipoVehiculo(3).withEstado(1)
				.build();
		vehiculoBuild.setIdVehiculo(1);
//		ReglasParqueaderoProcess reglas = new ReglasParqueaderoProcess();
		
		Mockito.when(reglasProcess.validarCupoTipoVehiculo(20, 3)).thenReturn(false);
		Estacionamiento estacionamientoTest = new EstacionamientoTestDataBuilder().withIdEstacionamiento(0).withIdVehiculo(1).withFechaIngreso(null).withFechaRetiro(null).build();
//		Mockito.when(estacionamientoService.saveEstacionamiento(estacionamientoTest)).thenReturn(estacionamientoTest);
		RegistrarIngresoProcess ingresarVehiculo = new RegistrarIngresoProcess();

//		Mockito.when(vehiculoService.saveVehiculo(vehiculoBuild)).thenReturn(vehiculoBuild);
		
		
		
		Map<Object,Object> estacionamientoRec = ingresarVehiculo.registrarIngresoProcess(estacionamientoService,vehiculoService, vehiculoBuild, estacionamientoTest);
		
		Assert.assertTrue(estacionamientoRec.containsValue(99)); // NotNull();
	}
	
	@Test
	public void realizarIngresoSinDisponibilidadMotos() {
//		Date fechaActual = new Date();
		Vehiculo vehiculoBuild = new VehiculoTestDataBuilder().withPlaca("XYZ123").withTipoVehiculo(4).withEstado(1)
				.build();
		vehiculoBuild.setIdVehiculo(1);
//		ReglasParqueaderoProcess reglas = new ReglasParqueaderoProcess();
		
		Mockito.when(reglasProcess.validarCupoTipoVehiculo(15, 4)).thenReturn(false);
		Estacionamiento estacionamientoTest = new EstacionamientoTestDataBuilder().withIdEstacionamiento(0).withIdVehiculo(1).withFechaIngreso(null).withFechaRetiro(null).build();
//		Mockito.when(estacionamientoService.saveEstacionamiento(estacionamientoTest)).thenReturn(estacionamientoTest);
		RegistrarIngresoProcess ingresarVehiculo = new RegistrarIngresoProcess();

//		Mockito.when(vehiculoService.saveVehiculo(vehiculoBuild)).thenReturn(vehiculoBuild);
		
		
		
		Map<Object,Object> estacionamientoRec = ingresarVehiculo.registrarIngresoProcess(estacionamientoService,vehiculoService, vehiculoBuild, estacionamientoTest);
		
		Assert.assertTrue(estacionamientoRec.containsValue(99)); // NotNull();
	}
}
package co.ceiba.service;

import static org.assertj.core.api.Assertions.assertThat;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.TestDataBuilder.EstacionamientoTestDataBuilder;

import co.ceiba.model.Estacionamiento;
import co.ceiba.repository.EstacionamientoRepository;

@RunWith(SpringRunner.class)
public class EstacionamientoServiceImplTest {

	@TestConfiguration
	static class EstacionamientoServiceImplTestContextConfiguration {

		@Bean
		public EstacionamientoService estacionamientoService() {
			return new EstacionamientoServiceImpl();
		}
	}

	@Autowired
	private EstacionamientoService estacionamientoService;

	@MockBean
	private EstacionamientoRepository estacionamientoRepository;

	@Before
	public void setUp() {
		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withIdVehiculo(1).build();
		
		Mockito.when(estacionamientoRepository.findByIdVehiculo(estacionamiento.getIdVehiculo()))
				.thenReturn(estacionamiento);
		
	}

	@Test
	public void getEstacionamientoByIdVehiculo() {
		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withIdVehiculo(1).build();
		Estacionamiento estacionamientoQuery = new Estacionamiento();
		estacionamientoQuery = estacionamientoService.getEstacionamientoByIdVehiculo(estacionamiento.getIdVehiculo());
		assertThat(estacionamientoQuery.getIdVehiculo()).isEqualTo(estacionamiento.getIdVehiculo());

	}

	@Test
	public void saveEstacionamiento() {
		// Arrange
				Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().build();
				Mockito.when(estacionamientoRepository.save(estacionamiento)).thenReturn(estacionamiento);
				// Act
				Estacionamiento estacionamientoGuardado = new Estacionamiento();
				estacionamientoGuardado = estacionamientoService.saveEstacionamiento(estacionamiento);
				// Assert
				Assert.assertNotNull(estacionamientoGuardado);

	}
}

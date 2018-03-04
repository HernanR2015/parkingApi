package co.ceiba.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.TestDataBuilder.EstacionamientoTestDataBuilder;
import co.ceiba.model.Estacionamiento;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class EstacionamientoRepositorioTest {

//	@Autowired
//	private TestEntityManager entityManager;

	@MockBean
	EstacionamientoRepository estacionamientoRepository;
	
	@Before
	public void setUp () {
		Estacionamiento estacionamiento = new EstacionamientoTestDataBuilder().withIdVehiculo(1).build();
		estacionamiento.setIdEstacionamiento(3);
		
		Mockito.when(estacionamientoRepository.findByIdVehiculo(1)).thenReturn(estacionamiento);
	}
	
	@Test
	public void buscarPorIdVehiculo() {

		Estacionamiento estacionamientoBuild = new EstacionamientoTestDataBuilder().withIdVehiculo(1).build();
//		entityManager.persist(estacionamientoBuild);
//		entityManager.flush();
		Estacionamiento estacionamientoRec = estacionamientoRepository.findByIdVehiculo((estacionamientoBuild.getIdVehiculo()));
		System.out.println(estacionamientoRec.getIdEstacionamiento());
		Assert.assertEquals(estacionamientoBuild.getIdVehiculo(), estacionamientoRec.getIdVehiculo());
	}

}

package co.ceiba.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import co.ceiba.TestDataBuilder.EstacionamientoTestDataBuilder;
import co.ceiba.model.Estacionamiento;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace=Replace.NONE)
public class EstacionamientoRepositorioTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	EstacionamientoRepository estacionamientoRepository;

	@Test
	public void buscarPorIdVehiculo() {

		Estacionamiento estacionamientoBuild = new EstacionamientoTestDataBuilder().withValor(8500).build();
		entityManager.persist(estacionamientoBuild);
		entityManager.flush();
		Estacionamiento estacionamientoRec = estacionamientoRepository.findByIdVehiculo((estacionamientoBuild.getIdVehiculo()));
		
		Assert.assertEquals(estacionamientoBuild, estacionamientoRec);
	}

}

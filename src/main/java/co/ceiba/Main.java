package co.ceiba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run( Main.class, args );
//		String[] beansNames = context.getBeanDefinitionNames();
//		Arrays.sort( beansNames );
		
//		TipoVehiculoRepository repository = context.getBean(TipoVehiculoRepository.class);
//		 Iterable<TipoVehiculo> todos = repository.findAll();
//	        System.out.println("Listar todos los Usuarios:");
//	        System.out.println("");
//	        for (TipoVehiculo usr : todos) {
//	            System.out.println("\t" + usr.getNombreTipoVehiculo());
//	        }
//	        System.out.println();
	}
}

package co.ceiba.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the vehiculo database table.
 * 
 */
@Entity
@Table(name = "vehiculo")
@NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_vehiculo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idVehiculo;
	
	@Column(name = "placa")
	private String placa;
	
	@Column(name = "cilindraje")
	private String cilindraje;
	
	@Column (name="estado")
	private int estado;
	
	@Column(name = "tipovehiculo")
	private int tipoVehiculo;
	
    @ManyToOne()
	@JoinColumn(name = "id_tipovehiculo")
	private TipoVehiculo id_tipoVehiculo;
    
    public Vehiculo () {}
    

	public Vehiculo(int idVehiculo, String placa, String cilindraje, int estado, int tipoVehiculo) {
		this.idVehiculo = idVehiculo;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.estado = estado;
		this.tipoVehiculo = tipoVehiculo;
	}


	public int getEstado() {
		return estado;
	}


	public void setEstado(int estado) {
		this.estado = estado;
	}


	public int getIdVehiculo() {
		return idVehiculo;
	}


	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getCilindraje() {
		return cilindraje;
	}


	public void setCilindraje(String cilindraje) {
		this.cilindraje = cilindraje;
	}


	public int getTipoVehiculo() {
		return tipoVehiculo;
	}


	public void setTipoVehiculo(int tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}


	public TipoVehiculo getId_tipoVehiculo() {
		return id_tipoVehiculo;
	}


	public void setId_tipoVehiculo(TipoVehiculo id_tipoVehiculo) {
		this.id_tipoVehiculo = id_tipoVehiculo;
	}
}

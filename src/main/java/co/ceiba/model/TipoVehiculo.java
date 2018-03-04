package co.ceiba.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the tipo_vehiculo database table.
 * 
 */
@Entity
@Table(name = "tipo_vehiculo")
@NamedQuery(name = "TipoVehiculo.findAll", query = "SELECT t FROM TipoVehiculo t")
public class TipoVehiculo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id_tipovehiculo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idTipoVehiculo;
	
	@Column(name = "nombre_tipovehiculo")
	private String nombreTipoVehiculo;

	@Column(name = "valor_dia")
	private double valorDia;

	@Column(name = "valor_hora")
	private double valorHora;

	public TipoVehiculo() {}
	
	public TipoVehiculo(int idTipoVehiculo, String nombreTipoVehiculo, double valorDia, double valorHora) { 
		this.idTipoVehiculo = idTipoVehiculo;
		this.nombreTipoVehiculo = nombreTipoVehiculo;
		this.valorDia = valorDia;
		this.valorHora = valorHora;
	}

	public int getIdTipoVehiculo() {
		return idTipoVehiculo;
	}

	public void setIdTipoVehiculo(int idTipoVehiculo) {
		this.idTipoVehiculo = idTipoVehiculo;
	}

	public String getNombreTipoVehiculo() {
		return nombreTipoVehiculo;
	}

	public void setNombreTipoVehiculo(String nombreTipoVehiculo) {
		this.nombreTipoVehiculo = nombreTipoVehiculo;
	}

	public double getValorDia() {
		return valorDia;
	}

	public double getValorHora() {
		return valorHora;
	}
}
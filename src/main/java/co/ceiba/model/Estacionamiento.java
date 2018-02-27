package co.ceiba.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * The persistent class for the vehiculo database table.
 * 
 */
@Entity
@Table(name = "estacionamiento")
@NamedQuery(name = "Estacionamiento.findAll", query = "SELECT v FROM Estacionamiento v")
public class Estacionamiento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_estacionamiento")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idEstacionamiento;

	@Column(name = "valor")
	private double valor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_ingreso")
	private Date fechaIngreso;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fecha_salida")
	private Date fechaSalida;

	@Column(name = "idvehiculo")
	private int idVehiculo;

	@ManyToOne()
	@JoinColumn(name = "id_vehiculo")
	private Vehiculo id_Vehiculo;

	public Estacionamiento() {

	}

	public Estacionamiento(int idEstacionamiento, double valor, Date fechaIngreso, Date fechaSalida,int idVehiculo) {
		this.idEstacionamiento = idEstacionamiento;
		this.valor = valor;
		this.fechaIngreso = fechaIngreso;
		this.fechaSalida = fechaSalida;
		this.idVehiculo = idVehiculo;
	}

	public int getIdEstacionamiento() {
		return idEstacionamiento;
	}

	public void setIdEstacionamiento(int idEstacionamiento) {
		this.idEstacionamiento = idEstacionamiento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public int getIdVehiculo() {
		return idVehiculo;
	}

	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}

	public Vehiculo getId_Vehiculo() {
		return id_Vehiculo;
	}

	public void setId_Vehiculo(Vehiculo id_Vehiculo) {
		this.id_Vehiculo = id_Vehiculo;
	}
}
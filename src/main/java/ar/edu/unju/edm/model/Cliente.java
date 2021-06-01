	package ar.edu.unju.edm.model;

import java.time.LocalDate;
//import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;



@Entity
@Component
public class Cliente {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native", strategy="native")
	@Column
	private Integer idCliente;
	
	@Column 
	//@NotNull(message="debe incluir un documento")
	@Min(1000)
	private int nroDocumento;
	
	@Column
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	
	//Calendar
	//private Date fechaUltimCompra = new Date();
	//@GeneratedValue(strategy=GenerationTye.IDENTITY)
	
	@Column
	@NotBlank(message="debe incluir un Tipo de Documento")
	private String tipoDocumento;
	@Column
	private int codigoAreaTelefono;
	@Column
	private int numTelefono;
	
	@Column
	//@NotBlank(message="El Apellido no puede quedar en blanco")
	private String email;
	
	@Column
	private Boolean activa;
	
	@Column
	private byte[] fotografia;
	
	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	
	public Integer getIdCliente() {
		return idCliente;
	}


	public void setIdCliente(Integer idCliente) {
		this.idCliente = idCliente;
	}


	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getCodigoAreaTelefono() {
		return codigoAreaTelefono;
	}

	public void setCodigoAreaTelefono(int codigoAreaTelefono) {
		this.codigoAreaTelefono = codigoAreaTelefono;
	}

	public int getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(int numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isActiva() {
		return activa;
	}


	public void setActiva(boolean activa) {
		this.activa = activa;
	}


	public Boolean getActiva() {
		return activa;
	}


	public void setActiva(Boolean activa) {
		this.activa = activa;
	}


	public byte[] getFotografia() {
		return fotografia;
	}


	public void setFotografia(byte[] fotografia) {
		this.fotografia = fotografia;
	}	

}

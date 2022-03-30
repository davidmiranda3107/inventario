package inventario.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the inv_cierre_det database table.
 * 
 */
@Entity
@Table(name="inv_cierre_det", schema="santamaria")
@NamedQuery(name="InvCierreDet.findAll", query="SELECT i FROM InvCierreDet i")
public class InvCierreDet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="DCIERRE_ID", schema="santamaria", table="contador", pkColumnName="cnt_nombre",valueColumnName="cnt_valor", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="DCIERRE_ID")
	@Column(name="dcierre_id")
	private long dcierreId;

	@Column(name="dcierre_cant_entradas")
	private BigDecimal dcierreCantEntradas;

	@Column(name="dcierre_cant_reservas")
	private BigDecimal dcierreCantReservas;

	@Column(name="dcierre_cant_salidas")
	private BigDecimal dcierreCantSalidas;

	@Column(name="dcierre_cat_id")
	private BigDecimal dcierreCatId;

	@Column(name="dcierre_costo_fin")
	private BigDecimal dcierreCostoFin;

	@Column(name="dcierre_costo_ini")
	private BigDecimal dcierreCostoIni;

	@Column(name="dcierre_existencia_fin")
	private BigDecimal dcierreExistenciaFin;

	@Column(name="dcierre_existencia_ini")
	private BigDecimal dcierreExistenciaIni;

	@Column(name="dcierre_monto_entradas")
	private BigDecimal dcierreMontoEntradas;

	@Column(name="dcierre_monto_fin")
	private BigDecimal dcierreMontoFin;

	@Column(name="dcierre_monto_ini")
	private BigDecimal dcierreMontoIni;

	@Column(name="dcierre_monto_reservas")
	private BigDecimal dcierreMontoReservas;

	@Column(name="dcierre_monto_salidas")
	private BigDecimal dcierreMontoSalidas;

	@Column(name="dcierre_precio_venta")
	private BigDecimal dcierrePrecioVenta;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_crea")
	private Date fecCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_modi")
	private Date fecModi;

	@Column(name="reg_activo")
	private BigDecimal regActivo;

	@Column(name="usu_crea")
	private String usuCrea;

	@Column(name="usu_modi")
	private String usuModi;

	//bi-directional many-to-one association to InvCierre
	@ManyToOne
	@JoinColumn(name="dcierre_cierre_id")
	private InvCierre invCierre;

	//bi-directional many-to-one association to InvInventarioDet
	@ManyToOne
	@JoinColumn(name="dcierre_invd_id")
	private InvInventarioDet invInventarioDet;

	//bi-directional many-to-one association to InvPlanta
	@ManyToOne
	@JoinColumn(name="dcierre_plan_id")
	private InvPlanta invPlanta;

	public InvCierreDet() {
	}

	public long getDcierreId() {
		return this.dcierreId;
	}

	public void setDcierreId(long dcierreId) {
		this.dcierreId = dcierreId;
	}

	public BigDecimal getDcierreCantEntradas() {
		return this.dcierreCantEntradas;
	}

	public void setDcierreCantEntradas(BigDecimal dcierreCantEntradas) {
		this.dcierreCantEntradas = dcierreCantEntradas;
	}

	public BigDecimal getDcierreCantReservas() {
		return this.dcierreCantReservas;
	}

	public void setDcierreCantReservas(BigDecimal dcierreCantReservas) {
		this.dcierreCantReservas = dcierreCantReservas;
	}

	public BigDecimal getDcierreCantSalidas() {
		return this.dcierreCantSalidas;
	}

	public void setDcierreCantSalidas(BigDecimal dcierreCantSalidas) {
		this.dcierreCantSalidas = dcierreCantSalidas;
	}

	public BigDecimal getDcierreCatId() {
		return this.dcierreCatId;
	}

	public void setDcierreCatId(BigDecimal dcierreCatId) {
		this.dcierreCatId = dcierreCatId;
	}

	public BigDecimal getDcierreCostoFin() {
		return this.dcierreCostoFin;
	}

	public void setDcierreCostoFin(BigDecimal dcierreCostoFin) {
		this.dcierreCostoFin = dcierreCostoFin;
	}

	public BigDecimal getDcierreCostoIni() {
		return this.dcierreCostoIni;
	}

	public void setDcierreCostoIni(BigDecimal dcierreCostoIni) {
		this.dcierreCostoIni = dcierreCostoIni;
	}

	public BigDecimal getDcierreExistenciaFin() {
		return this.dcierreExistenciaFin;
	}

	public void setDcierreExistenciaFin(BigDecimal dcierreExistenciaFin) {
		this.dcierreExistenciaFin = dcierreExistenciaFin;
	}

	public BigDecimal getDcierreExistenciaIni() {
		return this.dcierreExistenciaIni;
	}

	public void setDcierreExistenciaIni(BigDecimal dcierreExistenciaIni) {
		this.dcierreExistenciaIni = dcierreExistenciaIni;
	}

	public BigDecimal getDcierreMontoEntradas() {
		return this.dcierreMontoEntradas;
	}

	public void setDcierreMontoEntradas(BigDecimal dcierreMontoEntradas) {
		this.dcierreMontoEntradas = dcierreMontoEntradas;
	}

	public BigDecimal getDcierreMontoFin() {
		return this.dcierreMontoFin;
	}

	public void setDcierreMontoFin(BigDecimal dcierreMontoFin) {
		this.dcierreMontoFin = dcierreMontoFin;
	}

	public BigDecimal getDcierreMontoIni() {
		return this.dcierreMontoIni;
	}

	public void setDcierreMontoIni(BigDecimal dcierreMontoIni) {
		this.dcierreMontoIni = dcierreMontoIni;
	}

	public BigDecimal getDcierreMontoReservas() {
		return this.dcierreMontoReservas;
	}

	public void setDcierreMontoReservas(BigDecimal dcierreMontoReservas) {
		this.dcierreMontoReservas = dcierreMontoReservas;
	}

	public BigDecimal getDcierreMontoSalidas() {
		return this.dcierreMontoSalidas;
	}

	public void setDcierreMontoSalidas(BigDecimal dcierreMontoSalidas) {
		this.dcierreMontoSalidas = dcierreMontoSalidas;
	}

	public BigDecimal getDcierrePrecioVenta() {
		return this.dcierrePrecioVenta;
	}

	public void setDcierrePrecioVenta(BigDecimal dcierrePrecioVenta) {
		this.dcierrePrecioVenta = dcierrePrecioVenta;
	}

	public Date getFecCrea() {
		return this.fecCrea;
	}

	public void setFecCrea(Date fecCrea) {
		this.fecCrea = fecCrea;
	}

	public Date getFecModi() {
		return this.fecModi;
	}

	public void setFecModi(Date fecModi) {
		this.fecModi = fecModi;
	}

	public BigDecimal getRegActivo() {
		return this.regActivo;
	}

	public void setRegActivo(BigDecimal regActivo) {
		this.regActivo = regActivo;
	}

	public String getUsuCrea() {
		return this.usuCrea;
	}

	public void setUsuCrea(String usuCrea) {
		this.usuCrea = usuCrea;
	}

	public String getUsuModi() {
		return this.usuModi;
	}

	public void setUsuModi(String usuModi) {
		this.usuModi = usuModi;
	}

	public InvCierre getInvCierre() {
		return this.invCierre;
	}

	public void setInvCierre(InvCierre invCierre) {
		this.invCierre = invCierre;
	}

	public InvInventarioDet getInvInventarioDet() {
		return this.invInventarioDet;
	}

	public void setInvInventarioDet(InvInventarioDet invInventarioDet) {
		this.invInventarioDet = invInventarioDet;
	}

	public InvPlanta getInvPlanta() {
		return this.invPlanta;
	}

	public void setInvPlanta(InvPlanta invPlanta) {
		this.invPlanta = invPlanta;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (dcierreId ^ (dcierreId >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvCierreDet other = (InvCierreDet) obj;
		if (dcierreId != other.dcierreId)
			return false;
		return true;
	}

}
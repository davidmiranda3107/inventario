package inventario.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_inventario_det database table.
 * 
 */
@Entity
@Table(name="inv_inventario_det", schema="santamaria")
@NamedQuery(name="InvInventarioDet.findAll", query="SELECT i FROM InvInventarioDet i")
public class InvInventarioDet implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="INVD_ID", schema="santamaria", table="contador", pkColumnName="cnt_nombre",valueColumnName="cnt_valor", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="INVD_ID")
	@Column(name="invd_id")
	private long invdId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_crea")
	private Date fecCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_modi")
	private Date fecModi;

	@Column(name="invd_cant_entradas")
	private BigDecimal invdCantEntradas;

	@Column(name="invd_cant_reserva")
	private BigDecimal invdCantReserva;

	@Column(name="invd_cant_salidas")
	private BigDecimal invdCantSalidas;

	@Column(name="invd_cat_id")
	private BigDecimal invdCatId;

	@Column(name="invd_costo_actual")
	private BigDecimal invdCostoActual;

	@Column(name="invd_costo_ini")
	private BigDecimal invdCostoIni;

	@Column(name="invd_existencia")
	private BigDecimal invdExistencia;

	@Column(name="invd_existencia_ini")
	private BigDecimal invdExistenciaIni;

	@Column(name="invd_monto")
	private BigDecimal invdMonto;

	@Column(name="invd_monto_entradas")
	private BigDecimal invdMontoEntradas;

	@Column(name="invd_monto_ini")
	private BigDecimal invdMontoIni;

	@Column(name="invd_monto_reserva")
	private BigDecimal invdMontoReserva;

	@Column(name="invd_monto_salidas")
	private BigDecimal invdMontoSalidas;

	@Column(name="invd_precio_venta")
	private BigDecimal invdPrecioVenta;

	@Column(name="reg_activo")
	private BigDecimal regActivo;

	@Column(name="usu_crea")
	private String usuCrea;

	@Column(name="usu_modi")
	private String usuModi;

	//bi-directional many-to-one association to InvCierreDet
	@OneToMany(mappedBy="invInventarioDet")
	private List<InvCierreDet> invCierreDets;

	//bi-directional many-to-one association to InvInventario
	@ManyToOne
	@JoinColumn(name="invd_inv_id")
	private InvInventario invInventario;

	//bi-directional many-to-one association to InvPlanta
	@ManyToOne
	@JoinColumn(name="invd_plan_id")
	private InvPlanta invPlanta;

	//bi-directional many-to-one association to InvInventarioMov
	@OneToMany(mappedBy="invInventarioDet")
	private List<InvInventarioMov> invInventarioMovs;

	public InvInventarioDet() {
	}

	public long getInvdId() {
		return this.invdId;
	}

	public void setInvdId(long invdId) {
		this.invdId = invdId;
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

	public BigDecimal getInvdCantEntradas() {
		return this.invdCantEntradas;
	}

	public void setInvdCantEntradas(BigDecimal invdCantEntradas) {
		this.invdCantEntradas = invdCantEntradas;
	}

	public BigDecimal getInvdCantReserva() {
		return this.invdCantReserva;
	}

	public void setInvdCantReserva(BigDecimal invdCantReserva) {
		this.invdCantReserva = invdCantReserva;
	}

	public BigDecimal getInvdCantSalidas() {
		return this.invdCantSalidas;
	}

	public void setInvdCantSalidas(BigDecimal invdCantSalidas) {
		this.invdCantSalidas = invdCantSalidas;
	}

	public BigDecimal getInvdCatId() {
		return this.invdCatId;
	}

	public void setInvdCatId(BigDecimal invdCatId) {
		this.invdCatId = invdCatId;
	}

	public BigDecimal getInvdCostoActual() {
		return this.invdCostoActual;
	}

	public void setInvdCostoActual(BigDecimal invdCostoActual) {
		this.invdCostoActual = invdCostoActual;
	}

	public BigDecimal getInvdCostoIni() {
		return this.invdCostoIni;
	}

	public void setInvdCostoIni(BigDecimal invdCostoIni) {
		this.invdCostoIni = invdCostoIni;
	}

	public BigDecimal getInvdExistencia() {
		return this.invdExistencia;
	}

	public void setInvdExistencia(BigDecimal invdExistencia) {
		this.invdExistencia = invdExistencia;
	}

	public BigDecimal getInvdExistenciaIni() {
		return this.invdExistenciaIni;
	}

	public void setInvdExistenciaIni(BigDecimal invdExistenciaIni) {
		this.invdExistenciaIni = invdExistenciaIni;
	}

	public BigDecimal getInvdMonto() {
		return this.invdMonto;
	}

	public void setInvdMonto(BigDecimal invdMonto) {
		this.invdMonto = invdMonto;
	}

	public BigDecimal getInvdMontoEntradas() {
		return this.invdMontoEntradas;
	}

	public void setInvdMontoEntradas(BigDecimal invdMontoEntradas) {
		this.invdMontoEntradas = invdMontoEntradas;
	}

	public BigDecimal getInvdMontoIni() {
		return this.invdMontoIni;
	}

	public void setInvdMontoIni(BigDecimal invdMontoIni) {
		this.invdMontoIni = invdMontoIni;
	}

	public BigDecimal getInvdMontoReserva() {
		return this.invdMontoReserva;
	}

	public void setInvdMontoReserva(BigDecimal invdMontoReserva) {
		this.invdMontoReserva = invdMontoReserva;
	}

	public BigDecimal getInvdMontoSalidas() {
		return this.invdMontoSalidas;
	}

	public void setInvdMontoSalidas(BigDecimal invdMontoSalidas) {
		this.invdMontoSalidas = invdMontoSalidas;
	}

	public BigDecimal getInvdPrecioVenta() {
		return this.invdPrecioVenta;
	}

	public void setInvdPrecioVenta(BigDecimal invdPrecioVenta) {
		this.invdPrecioVenta = invdPrecioVenta;
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

	public List<InvCierreDet> getInvCierreDets() {
		return this.invCierreDets;
	}

	public void setInvCierreDets(List<InvCierreDet> invCierreDets) {
		this.invCierreDets = invCierreDets;
	}

	public InvCierreDet addInvCierreDet(InvCierreDet invCierreDet) {
		getInvCierreDets().add(invCierreDet);
		invCierreDet.setInvInventarioDet(this);

		return invCierreDet;
	}

	public InvCierreDet removeInvCierreDet(InvCierreDet invCierreDet) {
		getInvCierreDets().remove(invCierreDet);
		invCierreDet.setInvInventarioDet(null);

		return invCierreDet;
	}

	public InvInventario getInvInventario() {
		return this.invInventario;
	}

	public void setInvInventario(InvInventario invInventario) {
		this.invInventario = invInventario;
	}

	public InvPlanta getInvPlanta() {
		return this.invPlanta;
	}

	public void setInvPlanta(InvPlanta invPlanta) {
		this.invPlanta = invPlanta;
	}

	public List<InvInventarioMov> getInvInventarioMovs() {
		return this.invInventarioMovs;
	}

	public void setInvInventarioMovs(List<InvInventarioMov> invInventarioMovs) {
		this.invInventarioMovs = invInventarioMovs;
	}

	public InvInventarioMov addInvInventarioMov(InvInventarioMov invInventarioMov) {
		getInvInventarioMovs().add(invInventarioMov);
		invInventarioMov.setInvInventarioDet(this);

		return invInventarioMov;
	}

	public InvInventarioMov removeInvInventarioMov(InvInventarioMov invInventarioMov) {
		getInvInventarioMovs().remove(invInventarioMov);
		invInventarioMov.setInvInventarioDet(null);

		return invInventarioMov;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (invdId ^ (invdId >>> 32));
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
		InvInventarioDet other = (InvInventarioDet) obj;
		if (invdId != other.invdId)
			return false;
		return true;
	}

}
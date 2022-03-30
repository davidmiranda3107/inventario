package inventario.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_inventario database table.
 * 
 */
@Entity
@Table(name="inv_inventario", schema="santamaria")
@NamedQuery(name="InvInventario.findAll", query="SELECT i FROM InvInventario i")
public class InvInventario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="INV_ID", schema="santamaria", table="contador", pkColumnName="cnt_nombre",valueColumnName="cnt_valor", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="INV_ID")
	@Column(name="inv_id")
	private long invId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_crea")
	private Date fecCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_modi")
	private Date fecModi;

	@Column(name="inv_descripcion")
	private String invDescripcion;

	@Column(name="inv_nombre")
	private String invNombre;

	@Column(name="inv_produccion")
	private Boolean invProduccion;

	@Column(name="inv_venta")
	private Boolean invVenta;

	@Column(name="reg_activo")
	private BigDecimal regActivo;

	@Column(name="usu_crea")
	private String usuCrea;

	@Column(name="usu_modi")
	private String usuModi;

	//bi-directional many-to-one association to InvCierre
	@OneToMany(mappedBy="invInventario")
	private List<InvCierre> invCierres;

	//bi-directional many-to-one association to InvInventarioDet
	@OneToMany(mappedBy="invInventario")
	private List<InvInventarioDet> invInventarioDets;

	public InvInventario() {
	}

	public long getInvId() {
		return this.invId;
	}

	public void setInvId(long invId) {
		this.invId = invId;
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

	public String getInvDescripcion() {
		return this.invDescripcion;
	}

	public void setInvDescripcion(String invDescripcion) {
		this.invDescripcion = invDescripcion;
	}

	public String getInvNombre() {
		return this.invNombre;
	}

	public void setInvNombre(String invNombre) {
		this.invNombre = invNombre;
	}

	public Boolean getInvProduccion() {
		return this.invProduccion;
	}

	public void setInvProduccion(Boolean invProduccion) {
		this.invProduccion = invProduccion;
	}

	public Boolean getInvVenta() {
		return this.invVenta;
	}

	public void setInvVenta(Boolean invVenta) {
		this.invVenta = invVenta;
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

	public List<InvCierre> getInvCierres() {
		return this.invCierres;
	}

	public void setInvCierres(List<InvCierre> invCierres) {
		this.invCierres = invCierres;
	}

	public InvCierre addInvCierre(InvCierre invCierre) {
		getInvCierres().add(invCierre);
		invCierre.setInvInventario(this);

		return invCierre;
	}

	public InvCierre removeInvCierre(InvCierre invCierre) {
		getInvCierres().remove(invCierre);
		invCierre.setInvInventario(null);

		return invCierre;
	}

	public List<InvInventarioDet> getInvInventarioDets() {
		return this.invInventarioDets;
	}

	public void setInvInventarioDets(List<InvInventarioDet> invInventarioDets) {
		this.invInventarioDets = invInventarioDets;
	}

	public InvInventarioDet addInvInventarioDet(InvInventarioDet invInventarioDet) {
		getInvInventarioDets().add(invInventarioDet);
		invInventarioDet.setInvInventario(this);

		return invInventarioDet;
	}

	public InvInventarioDet removeInvInventarioDet(InvInventarioDet invInventarioDet) {
		getInvInventarioDets().remove(invInventarioDet);
		invInventarioDet.setInvInventario(null);

		return invInventarioDet;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (invId ^ (invId >>> 32));
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
		InvInventario other = (InvInventario) obj;
		if (invId != other.invId)
			return false;
		return true;
	}

}
package inventario.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the inv_grupo database table.
 * 
 */
@Entity
@Table(name="inv_grupo", schema="santamaria")
@NamedQuery(name="InvGrupo.findAll", query="SELECT i FROM InvGrupo i")
public class InvGrupo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@TableGenerator(name="GRP_ID", schema="santamaria", table="contador", pkColumnName="cnt_nombre",valueColumnName="cnt_valor", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.TABLE, generator="GRP_ID")
	@Column(name="grp_id")
	private long grpId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_crea")
	private Date fecCrea;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fec_modi")
	private Date fecModi;

	@Column(name="grp_codigo")
	private String grpCodigo;

	@Column(name="grp_descripcion")
	private String grpDescripcion;

	@Column(name="grp_nombre")
	private String grpNombre;

	@Column(name="reg_activo")
	private BigDecimal regActivo;

	@Column(name="usu_crea")
	private String usuCrea;

	@Column(name="usu_modi")
	private String usuModi;

	//bi-directional many-to-one association to InvPlanta
	@OneToMany(mappedBy="invGrupo")
	private List<InvPlanta> invPlantas;

	public InvGrupo() {
	}

	public long getGrpId() {
		return this.grpId;
	}

	public void setGrpId(long grpId) {
		this.grpId = grpId;
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

	public String getGrpCodigo() {
		return this.grpCodigo;
	}

	public void setGrpCodigo(String grpCodigo) {
		this.grpCodigo = grpCodigo;
	}

	public String getGrpDescripcion() {
		return this.grpDescripcion;
	}

	public void setGrpDescripcion(String grpDescripcion) {
		this.grpDescripcion = grpDescripcion;
	}

	public String getGrpNombre() {
		return this.grpNombre;
	}

	public void setGrpNombre(String grpNombre) {
		this.grpNombre = grpNombre;
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

	public List<InvPlanta> getInvPlantas() {
		return this.invPlantas;
	}

	public void setInvPlantas(List<InvPlanta> invPlantas) {
		this.invPlantas = invPlantas;
	}

	public InvPlanta addInvPlanta(InvPlanta invPlanta) {
		getInvPlantas().add(invPlanta);
		invPlanta.setInvGrupo(this);

		return invPlanta;
	}

	public InvPlanta removeInvPlanta(InvPlanta invPlanta) {
		getInvPlantas().remove(invPlanta);
		invPlanta.setInvGrupo(null);

		return invPlanta;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (grpId ^ (grpId >>> 32));
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
		InvGrupo other = (InvGrupo) obj;
		if (grpId != other.grpId)
			return false;
		return true;
	}
}
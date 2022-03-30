package inventario.converters;

import inventario.entities.InvGrupo;
import inventario.sessions.InventarioSBSLLocal;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class GrupoConverter implements Converter {

	@EJB(name = "ejb/InventarioSBSL")
	InventarioSBSLLocal inventarioLocal;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String submittedValue) {
		try {
			if(submittedValue.trim().equals("")){
				return null;
			}else {
				long id = Long.parseLong(submittedValue);
				InvGrupo registro = (InvGrupo) inventarioLocal.findByPk(InvGrupo.class, id);
				if (registro == null){
					throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"Error de Conversión", "No se encontro el registro"));
				}
				return registro;
	
			}
		}
		
		catch (Exception e) {
			e.printStackTrace();
			throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Error de Conversión", "No se pudo Convertir el registro"));
		}
	}
	
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		InvGrupo registro = null;
		String var = "";
		if(value != null && !value.equals("")){
			registro = (InvGrupo) value;
			var = String.valueOf(registro.getGrpId());
		}
		
		return var;
	}
}

package br.gov.to.secad.seg.util;

import br.gov.to.secad.seg.domain.OrgaoSetor;
import br.gov.to.secad.seg.service.OrgaoSetorService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author alex.santos
 */
@Component(value = "orgaoSetorConverter")
public class OrgaoSetorConverter implements Converter {

    @Autowired
    private OrgaoSetorService orgaoSetorService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        if (!value.trim().equals("")) {
            try {
                return orgaoSetorService.findAllOrgaoSetorId(Integer.valueOf(value));
            } catch (NumberFormatException e) {
                throw new ConverterException("Elemento Organizacional não encontrado. Mensagem: " + e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return String.valueOf(((OrgaoSetor) value).getCdorgao());
        }
    }

}

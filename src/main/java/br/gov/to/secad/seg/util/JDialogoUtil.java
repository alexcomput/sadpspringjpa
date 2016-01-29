/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.to.secad.seg.util;

import java.util.HashMap;
import java.util.Map;
import org.primefaces.context.RequestContext;

/**
 *
 * @author alex.santos
 */
public class JDialogoUtil {

    public static void abrirDialogo(String descricaoJDialog, Integer contentHeigt,
            boolean Modal, boolean resizable) {
        Map<String, Object> opcao = new HashMap<>();
        opcao.put("modal", Modal);
        opcao.put("resizable", resizable);
        opcao.put("contentHeight", contentHeigt);
        //opcao.put("contentWidth", contentWidth);
        /*
         Referencia o nome do arquivo sem o Xhtml
         */
        RequestContext.getCurrentInstance().openDialog(descricaoJDialog, opcao, null);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.gov.to.secad.seg.util;

import org.primefaces.component.datatable.DataTable;

/**
 *
 * @author alex.santos
 */
public class Tabela extends DataTable{
    
    private Integer coluns;
    private Integer rows;
    
    
    public void init(){
    Tabela Tabela= new Tabela();
    
    
    //Tabela.(this.rows);
    Tabela.setRows(this.rows);
    }
}

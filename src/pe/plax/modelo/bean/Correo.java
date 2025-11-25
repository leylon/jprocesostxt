/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.plax.modelo.bean;

/**
 *
 * @author Cristian Villafana
 */
public class Correo {
    
    //Variables de Entrada
    private String in_empresa;
    private String in_ruc;
        
    //Variables de Salida
    private String nrolinmail;
    private String mailenvi;
    private String mailcc;
    private String mailcco;

    public String getIn_empresa() {
        return in_empresa;
    }

    public void setIn_empresa(String in_empresa) {
        this.in_empresa = in_empresa;
    }

    public String getIn_ruc() {
        return in_ruc;
    }

    public void setIn_ruc(String in_ruc) {
        this.in_ruc = in_ruc;
    }

    public String getNrolinmail() {
        return nrolinmail;
    }

    public void setNrolinmail(String nrolinmail) {
        this.nrolinmail = nrolinmail;
    }

    public String getMailenvi() {
        return mailenvi;
    }

    public void setMailenvi(String mailenvi) {
        this.mailenvi = mailenvi;
    }

    public String getMailcc() {
        return mailcc;
    }

    public void setMailcc(String mailcc) {
        this.mailcc = mailcc;
    }

    public String getMailcco() {
        return mailcco;
    }

    public void setMailcco(String mailcco) {
        this.mailcco = mailcco;
    }
    
    
    
}

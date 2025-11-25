/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.plax.modelo.bean;

/**
 *
 * @author Andy Villafana
 */
public class DetalleAdicionalItem {
    private Integer NroLinDet;
    private String CodConTrib;
    private String NomConTrib;
    private String ValConTrib;
    /*Nuevas variables. BAVALOS 14.04.23*/
    private String FechIniProp;
    private String CantConcepto;

    public Integer getNroLinDet() {
        return NroLinDet;
    }

    public void setNroLinDet(Integer NroLinDet) {
        this.NroLinDet = NroLinDet;
    }

    public String getCodConTrib() {
        return CodConTrib;
    }

    public void setCodConTrib(String CodConTrib) {
        this.CodConTrib = CodConTrib;
    }

    public String getNomConTrib() {
        return NomConTrib;
    }

    public void setNomConTrib(String NomConTrib) {
        this.NomConTrib = NomConTrib;
    }

    public String getValConTrib() {
        return ValConTrib;
    }

    public void setValConTrib(String ValConTrib) {
        this.ValConTrib = ValConTrib;
    }
    
    public String getFechIniProp() {
        return FechIniProp;
    }

    public void setFechIniProp(String FechIniProp) {
        this.FechIniProp = FechIniProp;
    }

    public String getCantConcepto() {
        return CantConcepto;
    }

    public void setCantConcepto(String CantConcepto) {
        this.CantConcepto = CantConcepto;
    }
    
}

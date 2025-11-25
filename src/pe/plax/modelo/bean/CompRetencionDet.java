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
public class CompRetencionDet {
  
    //Variables de Entrada:
    private String IN_EMPRESA;
    private String IN_CODIGO;
    private String IN_TIPO;
    
    //Variables de Salida:
    private String OUT_NROLINREF;
    private String OUT_TPODOCREF;
    private String OUT_SERIEREF;
    private String OUT_FOLIOREF;
    private String OUT_FECHEMISDOCREF;
    private double OUT_MNTTOTALDOCREF;
    private String OUT_MONEDADOCREF;
       //Datos de Pago
    private String OUT_FECHOPERACION;
    private int OUT_NROOPERACION;
    private double OUT_IMPORTEOPERACION;
    private String OUT_MONEDAOPERACION;
        
       //Datos de Retencion
    private double OUT_IMPORTEMOVIMIENTO;
    private String OUT_MONEDAMOVIMIENTO;
    private String OUT_FECHAMOVIMIENTO;
    private double OUT_TOTALMOVIMIENTO;
    private String OUT_MONEDA;
            
       //Tipo de Cambio
    private String OUT_MONEDAREFERENCIA;
    private String OUT_MONEDAOBJETIVO;
    private double OUT_TIPOCAMBIO;
    private String OUT_FECHTIPOCAMBIO;

    public String getIN_EMPRESA() {
        return IN_EMPRESA;
    }

    public void setIN_EMPRESA(String IN_EMPRESA) {
        this.IN_EMPRESA = IN_EMPRESA;
    }

    public String getIN_CODIGO() {
        return IN_CODIGO;
    }

    public void setIN_CODIGO(String IN_CODIGO) {
        this.IN_CODIGO = IN_CODIGO;
    }

    public String getIN_TIPO() {
        return IN_TIPO;
    }

    public void setIN_TIPO(String IN_TIPO) {
        this.IN_TIPO = IN_TIPO;
    }

    public String getOUT_NROLINREF() {
        return OUT_NROLINREF;
    }

    public void setOUT_NROLINREF(String OUT_NROLINREF) {
        this.OUT_NROLINREF = OUT_NROLINREF;
    }

    public String getOUT_TPODOCREF() {
        return OUT_TPODOCREF;
    }

    public void setOUT_TPODOCREF(String OUT_TPODOCREF) {
        this.OUT_TPODOCREF = OUT_TPODOCREF;
    }

    public String getOUT_SERIEREF() {
        return OUT_SERIEREF;
    }

    public void setOUT_SERIEREF(String OUT_SERIEREF) {
        this.OUT_SERIEREF = OUT_SERIEREF;
    }

    public String getOUT_FOLIOREF() {
        return OUT_FOLIOREF;
    }

    public void setOUT_FOLIOREF(String OUT_FOLIOREF) {
        this.OUT_FOLIOREF = OUT_FOLIOREF;
    }

    public String getOUT_FECHEMISDOCREF() {
        return OUT_FECHEMISDOCREF;
    }

    public void setOUT_FECHEMISDOCREF(String OUT_FECHEMISDOCREF) {
        this.OUT_FECHEMISDOCREF = OUT_FECHEMISDOCREF;
    }

    public double getOUT_MNTTOTALDOCREF() {
        return OUT_MNTTOTALDOCREF;
    }

    public void setOUT_MNTTOTALDOCREF(double OUT_MNTTOTALDOCREF) {
        this.OUT_MNTTOTALDOCREF = OUT_MNTTOTALDOCREF;
    }

    public String getOUT_MONEDADOCREF() {
        return OUT_MONEDADOCREF;
    }

    public void setOUT_MONEDADOCREF(String OUT_MONEDADOCREF) {
        this.OUT_MONEDADOCREF = OUT_MONEDADOCREF;
    }

    public String getOUT_FECHOPERACION() {
        return OUT_FECHOPERACION;
    }

    public void setOUT_FECHOPERACION(String OUT_FECHOPERACION) {
        this.OUT_FECHOPERACION = OUT_FECHOPERACION;
    }

    public int getOUT_NROOPERACION() {
        return OUT_NROOPERACION;
    }

    public void setOUT_NROOPERACION(int OUT_NROOPERACION) {
        this.OUT_NROOPERACION = OUT_NROOPERACION;
    }

    public double getOUT_IMPORTEOPERACION() {
        return OUT_IMPORTEOPERACION;
    }

    public void setOUT_IMPORTEOPERACION(double OUT_IMPORTEOPERACION) {
        this.OUT_IMPORTEOPERACION = OUT_IMPORTEOPERACION;
    }

    public String getOUT_MONEDAOPERACION() {
        return OUT_MONEDAOPERACION;
    }

    public void setOUT_MONEDAOPERACION(String OUT_MONEDAOPERACION) {
                      
        this.OUT_MONEDAOPERACION = OUT_MONEDAOPERACION;    
                
    }

    public double getOUT_IMPORTEMOVIMIENTO() {
        return OUT_IMPORTEMOVIMIENTO;
    }

    public void setOUT_IMPORTEMOVIMIENTO(double OUT_IMPORTEMOVIMIENTO) {
        this.OUT_IMPORTEMOVIMIENTO = OUT_IMPORTEMOVIMIENTO;
    }

    public String getOUT_MONEDAMOVIMIENTO() {
        return OUT_MONEDAMOVIMIENTO;
    }

    public void setOUT_MONEDAMOVIMIENTO(String OUT_MONEDAMOVIMIENTO) {
        this.OUT_MONEDAMOVIMIENTO = OUT_MONEDAMOVIMIENTO;
    }

    public String getOUT_FECHAMOVIMIENTO() {
        return OUT_FECHAMOVIMIENTO;
    }

    public void setOUT_FECHAMOVIMIENTO(String OUT_FECHAMOVIMIENTO) {
        this.OUT_FECHAMOVIMIENTO = OUT_FECHAMOVIMIENTO;
    }

    public double getOUT_TOTALMOVIMIENTO() {
        return OUT_TOTALMOVIMIENTO;
    }

    public void setOUT_TOTALMOVIMIENTO(double OUT_TOTALMOVIMIENTO) {
        this.OUT_TOTALMOVIMIENTO = OUT_TOTALMOVIMIENTO;
    }

    public String getOUT_MONEDA() {
        return OUT_MONEDA;
    }

    public void setOUT_MONEDA(String OUT_MONEDA) {
        this.OUT_MONEDA = OUT_MONEDA;
    }

    public String getOUT_MONEDAREFERENCIA() {
        return OUT_MONEDAREFERENCIA;
    }

    public void setOUT_MONEDAREFERENCIA(String OUT_MONEDAREFERENCIA) {
        
        this.OUT_MONEDAREFERENCIA = OUT_MONEDAREFERENCIA;
          
        
    }

    public String getOUT_MONEDAOBJETIVO() {
        return OUT_MONEDAOBJETIVO;
    }

    public void setOUT_MONEDAOBJETIVO(String OUT_MONEDAOBJETIVO) {
        this.OUT_MONEDAOBJETIVO = OUT_MONEDAOBJETIVO;
    }

    public double getOUT_TIPOCAMBIO() {
        return OUT_TIPOCAMBIO;
    }

    public void setOUT_TIPOCAMBIO(double OUT_TIPOCAMBIO) {
        this.OUT_TIPOCAMBIO = OUT_TIPOCAMBIO;
    }

    public String getOUT_FECHTIPOCAMBIO() {
        return OUT_FECHTIPOCAMBIO;
    }

    public void setOUT_FECHTIPOCAMBIO(String OUT_FECHTIPOCAMBIO) {
        this.OUT_FECHTIPOCAMBIO = OUT_FECHTIPOCAMBIO;
    }
    
    
    
    
    
}

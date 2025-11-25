/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.plax.modelo.bean;

/**
 *
 * @author Alex Echavarria
 */
public class PubGestionCorreos {
    private String ID_CORREO;         //NUMERIC(15,0) NOT NULL,
    private String FECHAHORA;         //TIMESTAMP,
    private String DE;                //VARCHAR(70) NOT NULL,
    private String PARA;              //VARCHAR(300),
    private String CC;                //VARCHAR(300),
    private String CCO;               //VARCHAR(300),
    private String ASUNTO;            //VARCHAR(250),
    private String CUERPO;            //VARCHAR(2500),
    private String TIPO_CUERPO;       //VARCHAR(5),
    private String IMG_FIRMA;         //VARCHAR(80),
    private String IP_EQUIPO;         //VARCHAR(30),
    private String NOM_EQUIPO;        //VARCHAR(25),
    private String FLAG_PROCESADO;    //INTEGER,
    private String FEC_PROCESADO;     //TIMESTAMP,
    private String CARPETA_ADJUNTOS;  //VARCHAR(250),
    private String ADJUNTOS;

    public String getID_CORREO() {
        return ID_CORREO;
    }

    public void setID_CORREO(String ID_CORREO) {
        this.ID_CORREO = ID_CORREO;
    }

    public String getFECHAHORA() {
        return FECHAHORA;
    }

    public void setFECHAHORA(String FECHAHORA) {
        this.FECHAHORA = FECHAHORA;
    }

    public String getDE() {
        return DE;
    }

    public void setDE(String DE) {
        this.DE = DE;
    }

    public String getPARA() {
        return PARA;
    }

    public void setPARA(String PARA) {
        this.PARA = PARA;
    }

    public String getCC() {
        return CC;
    }

    public void setCC(String CC) {
        this.CC = CC;
    }

    public String getCCO() {
        return CCO;
    }

    public void setCCO(String CCO) {
        this.CCO = CCO;
    }

    public String getASUNTO() {
        return ASUNTO;
    }

    public void setASUNTO(String ASUNTO) {
        this.ASUNTO = ASUNTO;
    }

    public String getCUERPO() {
        return CUERPO;
    }

    public void setCUERPO(String CUERPO) {
        this.CUERPO = CUERPO;
    }

    public String getTIPO_CUERPO() {
        return TIPO_CUERPO;
    }

    public void setTIPO_CUERPO(String TIPO_CUERPO) {
        this.TIPO_CUERPO = TIPO_CUERPO;
    }

    public String getIMG_FIRMA() {
        return IMG_FIRMA;
    }

    public void setIMG_FIRMA(String IMG_FIRMA) {
        this.IMG_FIRMA = IMG_FIRMA;
    }

    public String getIP_EQUIPO() {
        return IP_EQUIPO;
    }

    public void setIP_EQUIPO(String IP_EQUIPO) {
        this.IP_EQUIPO = IP_EQUIPO;
    }

    public String getNOM_EQUIPO() {
        return NOM_EQUIPO;
    }

    public void setNOM_EQUIPO(String NOM_EQUIPO) {
        this.NOM_EQUIPO = NOM_EQUIPO;
    }

    public String getFLAG_PROCESADO() {
        return FLAG_PROCESADO;
    }

    public void setFLAG_PROCESADO(String FLAG_PROCESADO) {
        this.FLAG_PROCESADO = FLAG_PROCESADO;
    }

    public String getFEC_PROCESADO() {
        return FEC_PROCESADO;
    }

    public void setFEC_PROCESADO(String FEC_PROCESADO) {
        this.FEC_PROCESADO = FEC_PROCESADO;
    }

    public String getCARPETA_ADJUNTOS() {
        return CARPETA_ADJUNTOS;
    }

    public void setCARPETA_ADJUNTOS(String CARPETA_ADJUNTOS) {
        this.CARPETA_ADJUNTOS = CARPETA_ADJUNTOS;
    }

    public String getADJUNTOS() {
        return ADJUNTOS;
    }

    public void setADJUNTOS(String ADJUNTOS) {
        this.ADJUNTOS = ADJUNTOS;
    }
    

}

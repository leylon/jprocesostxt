/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.plax.modelo.bean;

/**
 *
 * @author Brecia Avalos
 */
public class DetalleCuotas {
  private int NroLinDet;
  private Double MontoCuota;
  private String FechaVecCuota;
  private String Idcuota;
  
  public int getNroLinDet() {
        return NroLinDet;
    }

  public void setNroLinDet(int NroLinDet) {
        this.NroLinDet = NroLinDet;
    }
    
  public Double getMontoCuota() {
        return MontoCuota;
    }

  public void setMontoCuota(Double MontoCuota) {
        this.MontoCuota = MontoCuota;
    }
  
  public String getFechaVecCuota() {
        return FechaVecCuota;
    }

  public void setFechaVecCuota(String FechaVecCuota) {
        this.FechaVecCuota = FechaVecCuota;
    }
   public String getIdcuota() {
        return Idcuota;
    }

  public void setIdcuota(String Idcuota) {
        this.Idcuota = Idcuota;
    }  
}

package PUBLIKACIJE;

import java.sql.Date;

public class PozajmicaKnjige {

	private int idpozajmiceKnjige,idCitaoca,idPrimerkaKnjige;
	private Date danUzim,danVrac;
	
	
	public int getIdpozajmiceKnjige() {
		return idpozajmiceKnjige;
	}
	public void setIdpozajmiceKnjige(int idpozajmiceKnjige) {
		this.idpozajmiceKnjige = idpozajmiceKnjige;
	}
	public int getIdCitaoca() {
		return idCitaoca;
	}
	public void setIdCitaoca(int idCitaoca) {
		this.idCitaoca = idCitaoca;
	}
	public int getIdPrimerkaKnjige() {
		return idPrimerkaKnjige;
	}
	public void setIdPrimerkaKnjige(int idPrimerkaKnjige) {
		this.idPrimerkaKnjige = idPrimerkaKnjige;
	}
	public Date getDanUzim() {
		return danUzim;
	}
	public void setDanUzim(Date danUzim) {
		this.danUzim = danUzim;
	}
	public Date getDanVrac() {
		return danVrac;
	}
	public void setDanVrac(Date danVrac) {
		this.danVrac = danVrac;
	}
	
	
	
	
}

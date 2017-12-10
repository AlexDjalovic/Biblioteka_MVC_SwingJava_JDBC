package OSOBE;

public class Citalac {

	private int idcitaoca;
	private String imecitaoca,pol,mail,telefon;
	
	
	public int getIdcitaoca() {
		return idcitaoca;
	}
	public void setIdcitaoca(int idcitaoca) {
		this.idcitaoca = idcitaoca;
	}
	public String getImecitaoca() {
		return imecitaoca;
	}
	public void setImecitaoca(String imecitaoca) {
		this.imecitaoca = imecitaoca;
	}
	public String getPol() {
		return pol;
	}
	public void setPol(String pol) {
		this.pol = pol;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelefon() {
		return telefon;
	}
	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}
	public String obavestime(){
		return this.getImecitaoca()+",vreme je da vratite knjigu";
		
	}
	
}

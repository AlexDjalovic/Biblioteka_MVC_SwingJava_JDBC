package KONTROLER;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;

import BAZA.DBKomunikacija;
import LOGIN_I_GLAVNA_FORMA.GlavnaForma;
import OSOBE.Autor;
import OSOBE.Citalac;
import OSOBE.Izdavac;
import OSOBE.User;
import PUBLIKACIJE.KategorijaKnjige;
import PUBLIKACIJE.Knjiga;
import PUBLIKACIJE.PozajmicaKnjige;
import PUBLIKACIJE.PrimerakKnjige;

public class Kontroler {

	private static Kontroler instanca;
	private ArrayList<User>useri=new ArrayList<User>();
	private ArrayList <Autor>autori=new ArrayList<>();
	private ArrayList<Autor>autoripretraga=new ArrayList<>();
	private ArrayList<Izdavac>izdavaci=new ArrayList<>(); 
	private ArrayList<Knjiga>books=new ArrayList<>();
	ArrayList<KategorijaKnjige>kkl=new ArrayList<>();
	ArrayList<Citalac>citaoci=new ArrayList<>();
	ArrayList<Citalac>citaocipretraga=new ArrayList<>();
	ArrayList<PrimerakKnjige>primerciKnjiga=new ArrayList<>();
	ArrayList<PozajmicaKnjige>pozajmice=new ArrayList<>();
	
	
	public static Kontroler getInstanca(){
		if(instanca==null){
			instanca=new Kontroler();
		}
		return instanca;
	}
	public void upisiNovogKorisnika(String us,String pas){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().upisiNovogKorisnika(us, pas);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public ArrayList<User> vratiUsere(){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		useri=DBKomunikacija.getInstance().vratiUsere();
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
		return useri;
	}
	public void unesiautora(String prezime,String ime,String zemlja){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().unesiautora(prezime, ime, zemlja);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public ArrayList<Autor>vratiAutore(){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		autori=DBKomunikacija.getInstance().vratiAutore();
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
		return autori;
	}
	public void promeniAutora(String prezime,String ime,String zemlja,int id){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().promeniAutora(prezime, ime, zemlja, id);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public void obrisiAutora(int id){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().obrisiAutora(id);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public ArrayList<Autor>pretraziautorePo(String vrZaPretragu){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		autoripretraga=DBKomunikacija.getInstance().pretraziautorePo(vrZaPretragu);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
		return autoripretraga;
	}
	public void unesiIzdavaca(String naziv){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().unesiIzdavaca(naziv);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public ArrayList<Izdavac>vratiIzdavace() {
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		izdavaci=DBKomunikacija.getInstance().vratiIzdavace();
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
		return izdavaci;
	}
	public void promeniImeIzdavaca(String naziv,int id){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().promeniImeIzdavaca(naziv, id);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public void unesiKnjigu(String naziv,String ISBN,int godina,String jezik,int tiraz,int idautora,int idizdavaca){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().unesiKnjigu(naziv, ISBN, godina, jezik, tiraz, idautora, idizdavaca);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public ArrayList<Knjiga>vratiKnjige(){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		books=DBKomunikacija.getInstance().vratiKnjige();
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
		return books;
	}
	public void promenipodatkeoknjizi(String naslov,String isbn,int god,String jezik,int tiraz,int idautora,int idizdavaca,int idknjige){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().promenipodatkeoknjizi(naslov, isbn, god, jezik, tiraz, idautora, idizdavaca, idknjige);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public void obrisipodatkeOKnjizi(int id){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().obrisipodatkeOKnjizi(id);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public void upisikategoriju(String nazivKateg){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().upisikategoriju(nazivKateg);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public ArrayList<KategorijaKnjige>vratiKategorijuKnjige(){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		kkl=DBKomunikacija.getInstance().vratiKategorijuKnjige();
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
		return kkl;
	}
	public void poveziKategoriju_i_Knjigu(int idknjige,int idkategorije){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().poveziKategoriju_i_Knjigu(idknjige, idkategorije);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public ResultSet prikaziknjigepozadatojKategoriji(String kateg){
		ResultSet rs;
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		rs=DBKomunikacija.getInstance().prikaziknjigepozadatojKategoriji(kateg);
		
		return rs;
	}
	public int prikazibrojkopija(int idknjige){
		int count;
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		count=DBKomunikacija.getInstance().prikazibrojkopija(idknjige);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
		return count;
	}
	public void napraviKopijuKnjige(int idknjige,int brojKopije,String status){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().napraviKopijuKnjige(idknjige, brojKopije, status);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public void unesinovogkorisnika(String ime,String pol,String mail,String telef){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().unesinovogkorisnika(ime, pol, mail, telef);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public ArrayList<Citalac>vratiCitaoca(){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		citaoci=DBKomunikacija.getInstance().vratiCitaoca();
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
		return citaoci;
	}
	public void promenicitaoca(String ime,String mail,String telefon,int id){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().promenicitaoca(ime, mail, telefon, id);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public void obrisipodatkeOKorisniku(int id){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().obrisipodatkeOKorisniku(id);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public ArrayList<Citalac>pretrazicitaocePo(String vrZaPretragu){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		citaocipretraga=DBKomunikacija.getInstance().pretrazicitaocePo(vrZaPretragu);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
		return citaocipretraga;
	}
	public ArrayList<PrimerakKnjige>vratiPrimerkeKnjige(){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		primerciKnjiga=DBKomunikacija.getInstance().vratiPrimerkeKnjige();
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
		return primerciKnjiga;
	}
	public void pozajmiKnjigu(int idprimerka,int idcitaoca,Date datuz,Date datvr){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().pozajmiKnjigu(idprimerka, idcitaoca, datuz, datvr);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public void promenistatusPrimerkaKnjige(String status,int idPrimerka){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().promenistatusPrimerkaKnjige(status, idPrimerka);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	public ArrayList<PozajmicaKnjige>vratiPozajmiceKnjige(){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		pozajmice=DBKomunikacija.getInstance().vratiPozajmiceKnjige();
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
		return pozajmice;
	}
	public ResultSet zaVracanjeKnjige(){
		ResultSet rs;
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		rs=DBKomunikacija.getInstance().zaVracanjeKnjige();
		
		return rs;
	}
	public void vracanjeKnjigeIBrisanje(int id){
		DBKomunikacija.getInstance().otvoriKomunikaciju();
		DBKomunikacija.getInstance().vracanjeKnjigeIBrisanje(id);
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
	}
	
	
	
}

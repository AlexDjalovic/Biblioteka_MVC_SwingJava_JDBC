package BAZA;

import java.sql.*;
import java.util.ArrayList;

import OSOBE.Autor;
import OSOBE.Citalac;
import OSOBE.Izdavac;
import OSOBE.User;
import PUBLIKACIJE.KategorijaKnjige;
import PUBLIKACIJE.Knjiga;
import PUBLIKACIJE.KnjigaKateg;
import PUBLIKACIJE.PozajmicaKnjige;
import PUBLIKACIJE.PrimerakKnjige;


public class DBKomunikacija {

	private Connection con=null;
	private static DBKomunikacija broker;
	
	public DBKomunikacija(){
		ucitajDrajver();
	}
	public static DBKomunikacija getInstance(){
		if(broker==null){
			broker=new DBKomunikacija();
		}
		return broker;
	}
	public void ucitajDrajver(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void otvoriKomunikaciju(){
		try {
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteka","root","");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void zatvoriKomunikaciju(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void upisiNovogKorisnika(String us,String pas){
		String sql="INSERT INTO user(username,password)VALUES(?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, us);
			ps.setString(2, pas);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<User> vratiUsere(){
		ResultSet rs=null;
		Statement s=null;
		ArrayList<User>users=new ArrayList<>();
		
		String upit="SELECT idusera,username,password FROM user";
		try {
			s=con.createStatement();
			rs=s.executeQuery(upit);
			while(rs.next()){
				User u=new User();
				int id=rs.getInt("idusera");
				String uname=rs.getString("username");
				String pas=rs.getString("password");
				u.setIdUsera(id); u.setUsername(uname);
				u.setPassword(pas);
				users.add(u);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
		
	}
	public void unesiautora(String prezime,String ime,String zemlja){
		String unos="INSERT INTO autor(prezimeAutora,imeAutora,zemljaAutora)VALUES(?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(unos);
			ps.setString(1, prezime);
			ps.setString(2, ime);
			ps.setString(3, zemlja);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public ArrayList<Autor>vratiAutore(){
		String upit="SELECT * FROM autor";
		ArrayList <Autor>autori=new ArrayList<>();
		try {
			
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery(upit);
			while(rs.next()){
				Autor a=new Autor();
				a.setIdautora(rs.getInt("idAutora"));
				a.setPrezimeAutora(rs.getString("prezimeAutora"));
				a.setImeautora(rs.getString("imeAutora"));
				a.setZemljaAutora(rs.getString("zemljaAutora"));
				autori.add(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autori;
	}
	public void promeniAutora(String prezime,String ime,String zemlja,int id){
		String update="UPDATE autor SET prezimeAutora=?,imeAutora=?,zemljaAutora=? WHERE idAutora=?";
		try {
			PreparedStatement ps=con.prepareStatement(update);
			ps.setString(1, prezime);
			ps.setString(2, ime);
			ps.setString(3, zemlja);
			ps.setInt(4, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void obrisiAutora(int id){
		String del="DELETE FROM autor WHERE idAutora=?";
		try {
			PreparedStatement ps=con.prepareStatement(del);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Autor>pretraziautorePo(String vrZaPretragu){
		ArrayList<Autor>autori=new ArrayList<>();
		String pretraga="SELECT * FROM autor WHERE CONCAT(prezimeAutora,imeAutora,zemljaAutora)LIKE'%"+vrZaPretragu+"%'";
		try {
			Statement ps=con.createStatement();
			
			ResultSet rs=ps.executeQuery(pretraga);
			while(rs.next()){
				int id=rs.getInt("IdAutora");
				String prez=rs.getString("prezimeAutora");
				String ime=rs.getString("imeAutora");
				String zemlja=rs.getString("zemljaAutora");
				Autor st=new Autor();
				st.setIdautora(id);
				st.setPrezimeAutora(prez);st.setZemljaAutora(zemlja);
				st.setImeautora(ime);
				//System.out.println(st);
				autori.add(st);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return autori;	
}
	public void unesiIzdavaca(String naziv){
		String naz="INSERT INTO izdavac(nazivIzdavaca)VALUES(?)";
		try {
			PreparedStatement ps=con.prepareStatement(naz);
			ps.setString(1, naziv);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Izdavac>vratiIzdavace() {
		ArrayList<Izdavac>izd=new ArrayList<>();
		String see="SELECT * FROM izdavac";
		try {
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery(see);
			while(rs.next()){
				Izdavac i=new Izdavac();
				i.setIdizdavaca(rs.getInt("idIzdavaca"));
				i.setNazivizdavaca(rs.getString("nazivIzdavaca"));
				izd.add(i);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return izd;
	}
	public void promeniImeIzdavaca(String naziv,int id){
		String upd="UPDATE izdavac SET nazivIzdavaca=? WHERE idIzdavaca=?";
		try {
			PreparedStatement ps=con.prepareStatement(upd);
			ps.setString(1, naziv);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void unesiKnjigu(String naziv,String ISBN,int godina,String jezik,int tiraz,int idautora,int idizdavaca){
		String unos="INSERT INTO knjiga(naslovKnjige,ISBN,godinaIzdanja,jezik,tiraz,idAutora,idIzdavaca)VALUES(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(unos);
			ps.setString(1, naziv);
			ps.setString(2, ISBN);
			ps.setInt(3, godina);
			ps.setString(4, jezik);
			ps.setInt(5, tiraz);
			ps.setInt(6, idautora);
			ps.setInt(7, idizdavaca);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Knjiga>vratiKnjige(){
		ArrayList<Knjiga>books=new ArrayList<>();
		String upit="SELECT*FROM knjiga";
		try {
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery(upit);
			while(rs.next()){
				Knjiga k=new Knjiga();
				k.setIdKnjige(rs.getInt("idKnjige"));
				k.setNaslovKnjige(rs.getString("naslovKnjige"));
				k.setISBN(rs.getString("ISBN"));
				k.setGodinaIzdanja(rs.getInt("godinaizdanja"));
				k.setJezik(rs.getString("jezik"));
				k.setTiraz(rs.getInt("tiraz"));
				k.setIdAutora(rs.getInt("idAutora"));
				k.setIdIzdavaca(rs.getInt("idIzdavaca"));
				books.add(k);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return books;
	}
	public void promenipodatkeoknjizi(String naslov,String isbn,int god,String jezik,int tiraz,int idautora,int idizdavaca,int idknjige){
		String upd="UPDATE knjiga SET naslovKnjige=?,ISBN=?,godinaIzdanja=?,jezik=?,tiraz=?,idAutora=?,idIzdavaca=? WHERE idKnjige=?";
		try {
			PreparedStatement ps=con.prepareStatement(upd);
			ps.setString(1, naslov);
			ps.setString(2, isbn);
			ps.setInt(3, god);
			ps.setString(4, jezik);
			ps.setInt(5, tiraz);
			ps.setInt(6, idautora);
			ps.setInt(7, idizdavaca);
			ps.setInt(8, idknjige);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void obrisipodatkeOKnjizi(int id){
		String del="DELETE FROM knjiga WHERE idKnjige=?";
		try {
			PreparedStatement ps=con.prepareStatement(del);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void upisikategoriju(String nazivKateg){
		String upis="INSERT INTO kategorijaknjige(kategorijaKnjige)VALUES(?)";
		
		try {
			PreparedStatement ps=con.prepareStatement(upis);
			ps.setString(1, nazivKateg);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<KategorijaKnjige>vratiKategorijuKnjige(){
		String upit="SELECT*FROM kategorijaknjige";
		ArrayList<KategorijaKnjige>kkl=new ArrayList<>();
		try {
			Statement ps=con.createStatement();
			ResultSet rs=ps.executeQuery(upit);
			while(rs.next()){
				KategorijaKnjige kk=new KategorijaKnjige();
				kk.setIdkategorijeknjige(rs.getInt("idKategorijeKnjige"));
				kk.setKategorija(rs.getString("kategorijaKnjige"));
				kkl.add(kk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kkl;
	}
	public void poveziKategoriju_i_Knjigu(int idknjige,int idkategorije){
		String upis="INSERT INTO knjigakateg(idKnjige,idKategorijeKnjige)VALUES(?,?)";
		
		try {
			PreparedStatement ps=con.prepareStatement(upis);
			ps.setInt(1, idknjige);
			ps.setInt(2, idkategorije);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<KnjigaKateg>vratiKnjigaKateg(){
		
		String sl="SELECT *FROM knjigakateg";
		ArrayList<KnjigaKateg>kkl=new ArrayList<>();
		try {
			Statement ps=con.createStatement();
			ResultSet rs=ps.executeQuery(sl);
			while(rs.next()){
				KnjigaKateg kk=new KnjigaKateg();
				kk.setIdKategorijeKnjige(rs.getInt("idKnjigaKateg"));
				kk.setIdKnjige(rs.getInt("idKnjige"));
				kk.setIdKategorijeKnjige(rs.getInt("idKategorijeKnjige"));
				kkl.add(kk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kkl;
		
	}
	public ResultSet prikaziknjigepozadatojKategoriji(String kateg){
		String upit="SELECT knjiga.naslovKnjige FROM knjiga INNER JOIN knjigakateg ON knjiga.idKnjige=knjigakateg.idKnjige INNER JOIN kategorijaknjige ON knjigakateg.idKategorijeKnjige=kategorijaknjige.idKategorijeKnjige WHERE kategorijaknjige=?";
	ResultSet rs=null;
		try {
		PreparedStatement ps=con.prepareStatement(upit);
		ps.setString(1, kateg);
		rs=ps.executeQuery();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		return rs;
	}
	public int prikazibrojkopija(int idknjige){
		int count=0;
		String upit="SELECT COUNT(*) FROM primerakknjige WHERE idKnjige=?";
		try {
			PreparedStatement ps=con.prepareStatement(upit);
			ps.setInt(1, idknjige);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				count=rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	public void napraviKopijuKnjige(int idknjige,int brojKopije,String status){
		String upis="INSERT INTO primerakknjige(idKnjige,rednibrojprimerka,status)VALUES(?,?,?)";
		try {
			PreparedStatement ps=con.prepareStatement(upis);
			ps.setInt(1, idknjige);
			ps.setInt(2, brojKopije);
			ps.setString(3, status);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void unesinovogkorisnika(String ime,String pol,String mail,String telef){
		String upis="INSERT INTO citalac(prezimeCitaoca,pol,mail,telefon)VALUES(?,?,?,?)";
		
		try {
			PreparedStatement ps=con.prepareStatement(upis);
			ps.setString(1, ime);
			ps.setString(2, pol);
			ps.setString(3, mail);
			ps.setString(4, telef);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Citalac>vratiCitaoca(){
		String upit="SELECT*FROM citalac";
		ArrayList<Citalac>citaoci=new ArrayList<>();
		try {
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery(upit);
			while(rs.next()){
				Citalac c=new Citalac();
				c.setIdcitaoca(rs.getInt("idCitaoca"));
				c.setImecitaoca(rs.getString("prezimeCitaoca"));
				c.setPol(rs.getString("pol"));
				c.setMail(rs.getString("mail"));
				c.setTelefon(rs.getString("telefon"));
				citaoci.add(c);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return citaoci;
	}
	public void promenicitaoca(String ime,String mail,String telefon,int id){
		String upd="UPDATE citalac SET prezimeCitaoca=?,mail=?,telefon=?WHERE idCitaoca=?";
		try {
			PreparedStatement ps=con.prepareStatement(upd);
			ps.setString(1, ime);
			ps.setString(2, mail);
			ps.setString(3, telefon);
			ps.setInt(4, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void obrisipodatkeOKorisniku(int id){
		String del="DELETE FROM citalac WHERE idCitaoca=?";
		try {
			PreparedStatement ps=con.prepareStatement(del);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Citalac>pretrazicitaocePo(String vrZaPretragu){
		ArrayList<Citalac>citaoci=new ArrayList<>();
		String pretraga="SELECT * FROM citalac WHERE CONCAT(prezimeCitaoca,mail,telefon)LIKE'%"+vrZaPretragu+"%'";
		try {
			Statement ps=con.createStatement();
			
			ResultSet rs=ps.executeQuery(pretraga);
			while(rs.next()){
				int id=rs.getInt("IdCitaoca");
				String prez=rs.getString("prezimeCitaoca");
				String mail=rs.getString("mail");
				String telef=rs.getString("telefon");
				Citalac st=new Citalac();
				st.setIdcitaoca(id);
				st.setImecitaoca(prez);st.setMail(mail);
				st.setTelefon(telef);
				//System.out.println(st);
				citaoci.add(st);	
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return citaoci;	
}
	public ArrayList<PrimerakKnjige>vratiPrimerkeKnjige(){
		ArrayList<PrimerakKnjige>primerciKnjiga=new ArrayList<>();
		String pretrafa="SELECT * FROM primerakKnjige";
		
		try {
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery(pretrafa);
			while(rs.next()){
				int idPRIMERKA=rs.getInt("idPrimerkaKnjige");
				int idKnjige=rs.getInt("idKnjige");
				int rednibr=rs.getInt("rednibrojprimerka");
				String status=rs.getString("status");
				PrimerakKnjige pk=new PrimerakKnjige();
				pk.setIdKnjige(idKnjige);pk.setIdPrimerkaKnjige(idPRIMERKA);
				pk.setRedniBrojPrimerka(rednibr);pk.setStatus(status);
				primerciKnjiga.add(pk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return primerciKnjiga;
		}
	public void pozajmiKnjigu(int idprimerka,int idcitaoca,Date datuz,Date datvr){
		String upis="INSERT INTO pozajmicaknjige(idPrimerkaKnjige,idCitaoca,datumUzimanja,datumVracanja)VALUES(?,?,?,?)";
		
		try {
			PreparedStatement ps=con.prepareStatement(upis);
			ps.setInt(1, idprimerka);
			ps.setInt(2, idcitaoca);
			ps.setDate(3, datuz);
			ps.setDate(4, datvr);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void promenistatusPrimerkaKnjige(String status,int idPrimerka){
		
		String promena="UPDATE primerakKnjige SET status=? WHERE idPrimerkaKnjige=?";
		try {
			PreparedStatement ps=con.prepareStatement(promena);
			ps.setString(1, status);
			ps.setInt(2, idPrimerka);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<PozajmicaKnjige>vratiPozajmiceKnjige(){
		ArrayList<PozajmicaKnjige>pozajmice=new ArrayList<>();
		String sqll="SELECT *FROM pozajmicaknjige";
		
		try {
			Statement s=con.createStatement();
			ResultSet rs=s.executeQuery(sqll);
			while(rs.next()){
				PozajmicaKnjige pk=new PozajmicaKnjige();
				pk.setIdpozajmiceKnjige(rs.getInt("idPozajmiceKnjige"));
				pk.setIdPrimerkaKnjige(rs.getInt("idPrimerkaKnjige"));
				pk.setIdCitaoca(rs.getInt("idCitaoca"));
				pk.setDanUzim(rs.getDate("datumUzimanja"));
				pk.setDanVrac(rs.getDate("datumVracanja"));
				pozajmice.add(pk);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pozajmice;
	}
	public ResultSet zaVracanjeKnjige(){
		String upit="SELECT idPozajmiceKnjige,naslovKnjige,prezimeCitaoca FROM pozajmicaknjige AS pz INNER JOIN primerakknjige as pk ON pz.idPrimerkaKnjige=pk.idPrimerkaKnjige INNER JOIN knjiga AS k ON pk.idKnjige=k.idKnjige INNER JOIN citalac AS c ON pz.idCitaoca=c.idCitaoca";
		ResultSet rs=null;
		try {
			Statement s=con.createStatement();
			 rs=s.executeQuery(upit);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public void vracanjeKnjigeIBrisanje(int id){
		String del="DELETE FROM pozajmicaKnjige WHERE idPozajmiceKnjige=?";
		
		try {
			PreparedStatement  ps=con.prepareStatement(del);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
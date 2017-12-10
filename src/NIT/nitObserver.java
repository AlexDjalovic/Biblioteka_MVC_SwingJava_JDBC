package NIT;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JLabel;
import javax.swing.JTextArea;

import KONTROLER.Kontroler;
import OSOBE.Citalac;
import PUBLIKACIJE.PozajmicaKnjige;

public class nitObserver extends Thread {

	private JTextArea ta;
	private JLabel l;
	
	public nitObserver(JLabel la,JTextArea tar){
		ta=tar;
		l=la;
		start();
	}
	public void run(){
		while(true){
		String tek=l.getText();
		//System.out.println(tek);
		//Date tekuci=Date.valueOf(tek);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localDate = LocalDate.parse(tek, formatter);
	//	System.out.println(localDate);
		Date tekuci= Date.valueOf(localDate);
		
		int idCitaoca=0;
		for(PozajmicaKnjige pk:Kontroler.getInstanca().vratiPozajmiceKnjige()){
			if(pk.getDanVrac().equals(tekuci)){
				idCitaoca=pk.getIdCitaoca();
				
			}
		}
		for(Citalac c:Kontroler.getInstanca().vratiCitaoca()){
			if(c.getIdcitaoca()==idCitaoca){
				ta.setText(c.obavestime());
				//System.out.println(c.obavestime());
			}
		}
		
		}
		
	}
}

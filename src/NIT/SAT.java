package NIT;


import java.time.LocalDate;

import javax.swing.JLabel;

public class SAT extends Thread {

	private JLabel lab;
	public SAT(JLabel L){
		lab=L;
		start();
	}
	
	LocalDate danas=LocalDate.now();
	int dan= danas.getDayOfMonth();
	int mes=danas.getMonthValue();
	int god=danas.getYear();
	LocalDate tr;
	
	public void run(){
		while(true){
			
			
			try {
				 tr=LocalDate.of(god, mes, dan);
				lab.setText( String.valueOf(tr));
				dan++;
				sleep(3000);
				
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

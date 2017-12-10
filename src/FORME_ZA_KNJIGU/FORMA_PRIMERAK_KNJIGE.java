package FORME_ZA_KNJIGU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import KONTROLER.Kontroler;
import PUBLIKACIJE.Knjiga;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FORMA_PRIMERAK_KNJIGE extends JFrame {

	private JPanel contentPane;
	private JTextField TFREDBR;
	private JTextField TFSTATUS;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FORMA_PRIMERAK_KNJIGE frame = new FORMA_PRIMERAK_KNJIGE();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FORMA_PRIMERAK_KNJIGE() {
		setTitle("PRIMERAK KNJIGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 567, 427);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 525, 354);
		contentPane.add(panel);
		panel.setLayout(null);
		
		 comboBox = new JComboBox();
		 for(Knjiga k:Kontroler.getInstanca().vratiKnjige()){
				comboBox.addItem(k.getNaslovKnjige());
			}
		 
		 comboBox.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent arg0) {
		 		String naslov=(String) comboBox.getSelectedItem();
		 		int idknjige=0;
		 		 for(Knjiga k:Kontroler.getInstanca().vratiKnjige()){
		 			 if(k.getNaslovKnjige().equalsIgnoreCase(naslov)){
		 				 idknjige=k.getIdKnjige();
		 			 }
		 		 }
		 		 int brojkopija=Kontroler.getInstanca().prikazibrojkopija(idknjige)+1;
		 		TFREDBR.setText(String.valueOf(brojkopija));
		 	}
		 });
		comboBox.setBounds(235, 86, 253, 28);
		panel.add(comboBox);
		
		
		
		
		TFREDBR = new JTextField();
		TFREDBR.setBounds(235, 143, 253, 22);
		panel.add(TFREDBR);
		TFREDBR.setColumns(10);
		
		TFSTATUS = new JTextField();
		TFSTATUS.setBounds(235, 205, 253, 22);
		panel.add(TFSTATUS);
		TFSTATUS.setColumns(10);
		TFSTATUS.setText("slobodna");
		
		JButton btnNewButton = new JButton("NAPRAVI KOPIJU KNJIGE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				String status=TFSTATUS.getText();
				String naslov=(String) comboBox.getSelectedItem();
		 		int idknjige=0;
		 		 for(Knjiga k:Kontroler.getInstanca().vratiKnjige()){
		 			 if(k.getNaslovKnjige().equalsIgnoreCase(naslov)){
		 				 idknjige=k.getIdKnjige();
		 			 }
		 		 }
		 		 int brojKopije=Integer.valueOf(TFREDBR.getText());
				Kontroler.getInstanca().napraviKopijuKnjige(idknjige, brojKopije, status);
				JOptionPane.showMessageDialog(null, "NAPRAVLJENA KOPIJA ZADATE KNJIGE");
				
			}
		});
		btnNewButton.setBounds(235, 275, 253, 25);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("NASLOV KNJIGE");
		lblNewLabel.setBounds(12, 98, 148, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("REDNI BROJ KOPIJE");
		lblNewLabel_1.setBounds(12, 146, 120, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("STATUS KNJIGE");
		lblNewLabel_2.setBounds(12, 208, 120, 16);
		panel.add(lblNewLabel_2);
	}
}

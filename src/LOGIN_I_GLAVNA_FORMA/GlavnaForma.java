package LOGIN_I_GLAVNA_FORMA;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import FORME_KORISNIK_AUTOR_IZDAVAC_KORISNIK.AUTOR_FORMA;
import FORME_KORISNIK_AUTOR_IZDAVAC_KORISNIK.FORMA_ZA_POZAJMLJIVANJE;
import FORME_KORISNIK_AUTOR_IZDAVAC_KORISNIK.FormaKorisnik;
import FORME_KORISNIK_AUTOR_IZDAVAC_KORISNIK.IZDAVAC_FORMA;
import FORME_ZA_KNJIGU.FORMA_KATEGORIJA_KNJIGE;
import FORME_ZA_KNJIGU.FORMA_PRIMERAK_KNJIGE;
import FORME_ZA_KNJIGU.FormaKnjiga;
import FORME_ZA_KNJIGU.formObserver;
import KONTROLER.Kontroler;
import NIT.SAT;
import NIT.nitObserver;
import OSOBE.Citalac;
import PUBLIKACIJE.PozajmicaKnjige;

import javax.swing.JTabbedPane;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class GlavnaForma extends JFrame {

	private JPanel contentPane;
	public  JLabel lbldan;
	public JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavnaForma frame = new GlavnaForma();
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
	public GlavnaForma() {
		setTitle("GLAVNA FORMA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 720);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 102));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 773, 526);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 250));
		tabbedPane.addTab("PODACI O CLANOVIMA", null, panel, null);
		tabbedPane.setBackgroundAt(0, new Color(255, 255, 255));
		panel.setLayout(null);
		
		JButton btnNewButton_3 = new JButton("UNOS,PROMENA I BRISANJE PODATAKA VEZANIH ZA CLANOVE BIBLIOTEKE");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormaKorisnik fk=new FormaKorisnik();
				fk.setVisible(true);
				fk.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_3.setBounds(139, 143, 482, 55);
		panel.add(btnNewButton_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 250, 250));
		tabbedPane.addTab("PODACI O KNJIGAMA", null, panel_1, null);
		tabbedPane.setBackgroundAt(1, new Color(204, 255, 255));
		panel_1.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("UPIS,PROMENA I BRISANJE PODATAKA VEZANIH ZA KNJIGE");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FormaKnjiga fk=new FormaKnjiga();
				fk.setVisible(true);
				fk.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_2.setBounds(197, 47, 402, 57);
		panel_1.add(btnNewButton_2);
		
		JButton btnUnosKategorijeKnjige = new JButton("UNOS KATEGORIJE KNJIGE");
		btnUnosKategorijeKnjige.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FORMA_KATEGORIJA_KNJIGE FKK=new FORMA_KATEGORIJA_KNJIGE();
				FKK.setVisible(true);
				FKK.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnUnosKategorijeKnjige.setBounds(197, 183, 402, 57);
		panel_1.add(btnUnosKategorijeKnjige);
		
		JButton btnUnosPodatakaO = new JButton("UNOS PODATAKA O KOPIJAMA KNJIGE I NJIHOVA DOSTUPNOST");
		btnUnosPodatakaO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FORMA_PRIMERAK_KNJIGE fpk=new FORMA_PRIMERAK_KNJIGE();
				fpk.setVisible(true);
				fpk.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnUnosPodatakaO.setBounds(197, 301, 402, 57);
		panel_1.add(btnUnosPodatakaO);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 250, 250));
		tabbedPane.addTab("PODACI O AUTORIMA I IZDAVACIMA", null, panel_4, null);
		tabbedPane.setBackgroundAt(2, new Color(204, 51, 204));
		panel_4.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("UNOS,PROMENA I BRISANJE AUTORA");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AUTOR_FORMA af=new AUTOR_FORMA();
				af.setVisible(true);
				af.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_1.setBounds(219, 96, 368, 77);
		panel_4.add(btnNewButton_1);
		
		JButton btnUnospromenaIBrisanje = new JButton("UNOS,PROMENA I BRISANJE IZDAVACA");
		btnUnospromenaIBrisanje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IZDAVAC_FORMA ifo=new IZDAVAC_FORMA();
				ifo.setVisible(true);
				ifo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnUnospromenaIBrisanje.setBounds(219, 243, 368, 85);
		panel_4.add(btnUnospromenaIBrisanje);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 250, 250));
		tabbedPane.addTab("PODACI O POZAJMICAMA I VRACANJIMA", null, panel_3, null);
		tabbedPane.setBackgroundAt(3, new Color(204, 255, 153));
		panel_3.setLayout(null);
		
		JButton btnNewButton = new JButton("PODACI O UZIMANJU I VRACANJU KNJIGA");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FORMA_ZA_POZAJMLJIVANJE fzp=new FORMA_ZA_POZAJMLJIVANJE();
				fzp.setVisible(true);
				fzp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton.setBounds(149, 67, 429, 46);
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_4 = new JButton("pogledaj Ko Vraca knjige");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				formObserver fo=new formObserver();
				fo.setVisible(true);
				fo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}
		});
		btnNewButton_4.setBounds(149, 160, 429, 25);
		panel_3.add(btnNewButton_4);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBackground(new Color(255, 250, 250));
		panel_5.setBounds(12, 552, 279, 108);
		contentPane.add(panel_5);
		panel_5.setLayout(null);
		
		 lbldan = new JLabel();
		lbldan.setBounds(41, 24, 139, 16);
		panel_5.add(lbldan);
		//SAT s=new SAT(lbldan);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(325, 552, 460, 108);
		contentPane.add(scrollPane);
		
		 textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
	//	nitObserver no=new nitObserver(lbldan, textArea);
	
		
	}
}

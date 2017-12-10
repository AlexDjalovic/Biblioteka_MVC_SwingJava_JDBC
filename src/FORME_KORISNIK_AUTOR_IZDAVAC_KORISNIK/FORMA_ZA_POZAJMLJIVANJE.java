package FORME_KORISNIK_AUTOR_IZDAVAC_KORISNIK;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BAZA.DBKomunikacija;
import KONTROLER.Kontroler;
import OSOBE.Citalac;
import PUBLIKACIJE.Knjiga;
import PUBLIKACIJE.PozajmicaKnjige;
import PUBLIKACIJE.PrimerakKnjige;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FORMA_ZA_POZAJMLJIVANJE extends JFrame {

	private JPanel contentPane;
	private JTextField tfdanDU;
	private JTextField tfmesDU;
	private JTextField tfgodDU;
	JComboBox comboKorisnik,combonaslov,comboKopija;
	private JButton btnNewButton_1;
	private int dan,mes,god;
	private int danvr,mesvr,godvr;
	private LocalDate datumuzimanja,datumvracanja;
	private JTable table;
	DefaultTableModel dtm=new DefaultTableModel();
	private JButton btnNewButton_2;
	private int globid;
	
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FORMA_ZA_POZAJMLJIVANJE frame = new FORMA_ZA_POZAJMLJIVANJE();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public FORMA_ZA_POZAJMLJIVANJE() {
		setTitle("POZAJMLJIVANJE  I  VRACANJE KNJIGE");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 867, 743);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(12, 13, 825, 326);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("POZAJMI KNJIGU\r\n");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 dan=Integer.parseInt(tfdanDU.getText());
				 mes=Integer.parseInt(tfmesDU.getText());
				 god=Integer.parseInt(tfgodDU.getText());
				
				 datumuzimanja=LocalDate.of(god, mes, dan);
				 datumvracanja=datumuzimanja.plusDays(3);
				
				 danvr=datumvracanja.getDayOfMonth();
				 mesvr=datumvracanja.getMonthValue();
				 godvr=datumvracanja.getYear();
				
				 System.out.println(danvr+" "+mesvr+" "+godvr);
				 System.out.println(datumvracanja);
				
				 Date datuz=Date.valueOf(datumuzimanja);
				 Date datvr=Date.valueOf(datumvracanja);
				 
				 String ime=(String) comboKorisnik.getSelectedItem();
				 int idcitaoca=0;
				 for(Citalac c:Kontroler.getInstanca().vratiCitaoca()){
					 if(c.getImecitaoca().equalsIgnoreCase(ime)){
						 idcitaoca=c.getIdcitaoca();
					 }
				 }
				 
				int rednibrojkopije= (int) comboKopija.getSelectedItem();
				
				String naslov=(String) combonaslov.getSelectedItem();
				int idknjige=0;
				for(Knjiga k:Kontroler.getInstanca().vratiKnjige()){
					if(k.getNaslovKnjige().equalsIgnoreCase(naslov)){
						idknjige=k.getIdKnjige();
					}
				}
				int idprimerka=0;
				for(PrimerakKnjige pk:Kontroler.getInstanca().vratiPrimerkeKnjige()){
					if(pk.getRedniBrojPrimerka()==rednibrojkopije&&pk.getIdKnjige()==idknjige){
						idprimerka=pk.getIdPrimerkaKnjige();
					}
				}
				 
				 Kontroler.getInstanca().pozajmiKnjigu(idprimerka, idcitaoca, datuz, datvr);
				 String status="zauzeta";
				 Kontroler.getInstanca().promenistatusPrimerkaKnjige(status, idprimerka);
				 JOptionPane.showMessageDialog(null, "uzeta knjiga");
				 popuniTabelu();
			}
		});
		btnNewButton.setBounds(414, 281, 243, 25);
		panel.add(btnNewButton);
		
		comboKorisnik = new JComboBox();
		comboKorisnik.setBounds(414, 47, 243, 22);
		panel.add(comboKorisnik);
		
		for(Citalac c:Kontroler.getInstanca().vratiCitaoca()){
			comboKorisnik.addItem(c.getImecitaoca());
		}
		
		combonaslov = new JComboBox();
		combonaslov.setBounds(414, 105, 243, 22);
		panel.add(combonaslov);
		
		for(Knjiga k:Kontroler.getInstanca().vratiKnjige()){
			combonaslov.addItem(k.getNaslovKnjige());
		}
		combonaslov.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String naslov=(String) combonaslov.getSelectedItem();
				int idknjige=0;
				for(Knjiga k:Kontroler.getInstanca().vratiKnjige()){
					if(k.getNaslovKnjige().equalsIgnoreCase(naslov)){
						idknjige=k.getIdKnjige();
					}
				}
				comboKopija.removeAllItems();//to se trazi jer inace svaki put kad kliknem na novu knjigu ucitava mi kopije prethodnih i nove
				for(PrimerakKnjige pk:Kontroler.getInstanca().vratiPrimerkeKnjige()){
					if(pk.getIdKnjige()==idknjige){
						if(pk.getStatus().equals("slobodna")){
							//comboKopija.removeAllItems();//
							comboKopija.addItem(pk.getRedniBrojPrimerka());
						}
					}
				}
			}
			
		});
		JLabel lblNewLabel = new JLabel("IME KORISNIKA");
		lblNewLabel.setBounds(65, 53, 131, 16);
		panel.add(lblNewLabel);
		
		JLabel lblNaslovKnjige = new JLabel("NASLOV KNJIGE");
		lblNaslovKnjige.setBounds(65, 111, 131, 16);
		panel.add(lblNaslovKnjige);
		
		JLabel lblNewLabel_1 = new JLabel("KOPIJA KNJIGE");
		lblNewLabel_1.setBounds(65, 164, 109, 16);
		panel.add(lblNewLabel_1);
		
		comboKopija = new JComboBox();
		comboKopija.setBounds(414, 161, 243, 22);
		panel.add(comboKopija);
		
		JLabel lblDatumUzimanja = new JLabel("DATUM UZIMANJA");
		lblDatumUzimanja.setBounds(65, 235, 131, 16);
		panel.add(lblDatumUzimanja);
		
		tfdanDU = new JTextField();
		tfdanDU.setBounds(414, 232, 66, 22);
		panel.add(tfdanDU);
		tfdanDU.setColumns(10);
		
		tfmesDU = new JTextField();
		tfmesDU.setColumns(10);
		tfmesDU.setBounds(503, 232, 66, 22);
		panel.add(tfmesDU);
		
		tfgodDU = new JTextField();
		tfgodDU.setColumns(10);
		tfgodDU.setBounds(591, 232, 66, 22);
		panel.add(tfgodDU);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 359, 825, 257);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red=table.getSelectedRow();
				globid=(int) table.getModel().getValueAt(red, 0);
				System.out.println(globid);
			}
		});
		scrollPane.setViewportView(table);
		
		btnNewButton_2 = new JButton("VRATI KNJIGU");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String status="slobodna";
				int idPrimerka=0;
				for(PozajmicaKnjige pk:Kontroler.getInstanca().vratiPozajmiceKnjige()){
					if(pk.getIdpozajmiceKnjige()==globid){
						idPrimerka=pk.getIdPrimerkaKnjige();
					}
				}
				Kontroler.getInstanca().promenistatusPrimerkaKnjige(status, idPrimerka);
				Kontroler.getInstanca().vracanjeKnjigeIBrisanje(globid);
				JOptionPane.showMessageDialog(null, "VRACENA KNJIGA");
				popuniTabelu();
			}
		});
		btnNewButton_2.setBounds(425, 640, 243, 25);
		contentPane.add(btnNewButton_2);
		
		tfdanDU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent kk) {
				//dozvoli samo unos brojeva
				if(!Character.isDigit(kk.getKeyChar())){
					kk.consume();
				}
				if (tfdanDU.getText().length() >= 2 ) // limit textfield to 2 characters
		            kk.consume();
			}
		});
		tfmesDU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent kk) {
				//dozvoli samo unos brojeva
				if(!Character.isDigit(kk.getKeyChar())){
					kk.consume();
				}
				if (tfmesDU.getText().length() >= 2 ) // limit textfield to 2 characters
		            kk.consume();
			}
		});
		tfgodDU.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent kk) {
				//dozvoli samo unos brojeva
				if(!Character.isDigit(kk.getKeyChar())){
					kk.consume();
				}
				if (tfgodDU.getText().length() >= 4 ) // limit textfield to 2 characters
		            kk.consume();
				
				
			}
		});
		Object[]kolone=new Object[3];
		kolone[0]="id pozajmice knjige";
		kolone[1]="naslov knjige";
		kolone[2]="ime i prezime citaoca";
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
	
		popuniTabelu();
	}
	public void popuniTabelu(){
		dtm.setRowCount(0);
		Object [] redovi=new Object[3];
		ResultSet rs=Kontroler.getInstanca().zaVracanjeKnjige();
		int idpozajmice;
		String naslov;
		String prezime;
		try {
			while(rs.next()){
				 idpozajmice=rs.getInt(1);
				 naslov=rs.getString(2);
				 prezime=rs.getString(3);
				 redovi[0]=idpozajmice;
				 redovi[1]=naslov;
				 redovi[2]=prezime;
				 dtm.addRow(redovi);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DBKomunikacija.getInstance().zatvoriKomunikaciju();
		}
}

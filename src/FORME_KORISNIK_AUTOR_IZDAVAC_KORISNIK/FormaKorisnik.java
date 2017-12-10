package FORME_KORISNIK_AUTOR_IZDAVAC_KORISNIK;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import KONTROLER.Kontroler;
import OSOBE.Autor;
import OSOBE.Citalac;

import java.awt.Color;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormaKorisnik extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField TFIME;
	private JTextField tfmail;
	private JTextField tftelefon;
	private JRadioButton rbMuski, rbZenski;
	ButtonGroup BG=new ButtonGroup();
	DefaultTableModel dtm=new DefaultTableModel();
	private JTextField textField;
	int glid;
	String glime,glpol,glmail,gltelefon;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormaKorisnik frame = new FormaKorisnik();
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
	public FormaKorisnik() {
		setTitle("KORISNICI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 965, 744);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(12, 13, 923, 308);
		contentPane.add(panel);
		panel.setLayout(null);
		
		TFIME = new JTextField();
		TFIME.setBounds(211, 47, 249, 22);
		panel.add(TFIME);
		TFIME.setColumns(10);
		
		tfmail = new JTextField();
		tfmail.setColumns(10);
		tfmail.setBounds(211, 194, 249, 22);
		panel.add(tfmail);
		
		tftelefon = new JTextField();
		tftelefon.setColumns(10);
		tftelefon.setBounds(211, 260, 249, 22);
		panel.add(tftelefon);
		
		tftelefon.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent kk) {
				//dozvoli samo unos brojeva
				if(!Character.isDigit(kk.getKeyChar())){
					kk.consume();
				}
			}
		});
		
		JLabel lblNewLabel = new JLabel("PREZIME I IME KORISNIKA");
		lblNewLabel.setBounds(23, 50, 159, 16);
		panel.add(lblNewLabel);
		
		JLabel lblPol = new JLabel("POL");
		lblPol.setBounds(23, 132, 56, 16);
		panel.add(lblPol);
		
		JLabel lblMail = new JLabel("MAIL");
		lblMail.setBounds(23, 197, 56, 16);
		panel.add(lblMail);
		
		JLabel lblKontaktTelefon = new JLabel("KONTAKT TELEFON");
		lblKontaktTelefon.setBounds(23, 263, 139, 16);
		panel.add(lblKontaktTelefon);
		
		JButton btnUnos = new JButton("UNESI");
		btnUnos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String ime=TFIME.getText();
				String mail=tfmail.getText();
				String telef=tftelefon.getText();
				
				String pol="";
				if(rbMuski.isSelected()){
					pol="muski";
				}
				if(rbZenski.isSelected()){
					pol="zenski";
				}
				
				
				
				
				if((TFIME.getText().equals("")&&tftelefon.getText().equals(""))||TFIME.getText().equals("")){
					JOptionPane.showMessageDialog(null, "da bi ste uneli korisnika morate uneti ime i telefon");
				}else{
					Kontroler.getInstanca().unesinovogkorisnika(ime, pol, mail, telef);
					JOptionPane.showMessageDialog(null, "uneli ste novog korisnika");
					TFIME.setText("");tfmail.setText("");tftelefon.setText("");
				}
				popunitabelu();
			}
		});
		btnUnos.setBounds(690, 46, 198, 25);
		panel.add(btnUnos);
		
		JButton btnUPDATE = new JButton("PROMENI");
		btnUPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String ime=TFIME.getText();
				String mail=tfmail.getText();
				String telef=tftelefon.getText();
				
				Kontroler.getInstanca().promenicitaoca(ime, mail, telef, glid);
				JOptionPane.showMessageDialog(null, "promenili  ste podatke o korisniku");
				popunitabelu();
				TFIME.setText("");tfmail.setText("");tftelefon.setText("");
			}
		});
		btnUPDATE.setBounds(690, 123, 198, 25);
		panel.add(btnUPDATE);
		
		JButton btnDELETE = new JButton("OBRISI");
		btnDELETE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kontroler.getInstanca().obrisipodatkeOKorisniku(glid);
				JOptionPane.showMessageDialog(null, "obrisan korisnik");
				popunitabelu();
			}
		});
		btnDELETE.setBounds(695, 193, 193, 25);
		panel.add(btnDELETE);
		
		 rbMuski = new JRadioButton("MUSKI",true);
		rbMuski.setBounds(211, 128, 127, 25);
		panel.add(rbMuski);
		
		rbZenski = new JRadioButton("ZENSKI",false);
		rbZenski.setBounds(350, 128, 127, 25);
		panel.add(rbZenski);
		
		BG.add(rbMuski);BG.add(rbZenski);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 465, 923, 219);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red=table.getSelectedRow();
			 glid=(int) (	table.getModel().getValueAt(red, 0));
			 glime=(String) table.getModel().getValueAt(red,1);
				glpol=	 (String) table.getModel().getValueAt(red, 2);
				glmail=	 (String) table.getModel().getValueAt(red, 3);
				gltelefon=	 (String) table.getModel().getValueAt(red, 4);
				TFIME.setText(glime);
				tfmail.setText(glmail);
				tftelefon.setText(gltelefon);
				
				if(glpol.equalsIgnoreCase("muski")){
					rbMuski.setSelected(true);
				}
				if(glpol.equalsIgnoreCase("zenski")){
					rbZenski.setSelected(true);
				}
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "PRETRAGA ", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 334, 923, 120);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(212, 41, 249, 22);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("PRETRAZI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String pretraga=textField.getText();
				if(pretraga.equals("")){
					popunitabelu();
				}else{
					prikaziTrazenoNaTabeli();
				}
				textField.setText("");
			}
			
		});
		btnNewButton.setBounds(702, 40, 185, 25);
		panel_1.add(btnNewButton);
		
		Object[]kolone=new Object[5];
		kolone[0]="id korisnika";
		kolone[1]="ime i prezime ";
		kolone[2]="pol";
		kolone[3]="mail";
		kolone[4]="telefon";
		dtm.addColumn(kolone[0]);dtm.addColumn(kolone[1]);dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);dtm.addColumn(kolone[4]);
		popunitabelu();
	}
	public void popunitabelu(){
		dtm.setRowCount(0);
		Object[]redovi=new Object[5];
		for(Citalac c:Kontroler.getInstanca().vratiCitaoca()){
			redovi[0]=c.getIdcitaoca();
			redovi[1]=c.getImecitaoca();
			redovi[2]=c.getPol();
			redovi[3]=c.getMail();
			redovi[4]=c.getTelefon();
			dtm.addRow(redovi);
		}
	}
	public void prikaziTrazenoNaTabeli(){
		String pretraga=textField.getText();
		dtm.setRowCount(0);
		Object [] redovi=new Object[7];
		for(Citalac c:Kontroler.getInstanca().pretrazicitaocePo(pretraga)){
			redovi[0]=c.getIdcitaoca();
			redovi[1]=c.getImecitaoca();
			redovi[2]=c.getPol();
			redovi[3]=c.getMail();
			redovi[4]=c.getTelefon();
			dtm.addRow(redovi);
		}
}
}
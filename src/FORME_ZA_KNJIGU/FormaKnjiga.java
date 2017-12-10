package FORME_ZA_KNJIGU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import KONTROLER.Kontroler;
import OSOBE.Autor;
import OSOBE.Izdavac;
import PUBLIKACIJE.Knjiga;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormaKnjiga extends JFrame {

	private JPanel contentPane;
	private JTextField tfnaslov;
	private JTextField tfisbn;
	private JTextField tfgod;
	private JTextField tfjezik;
	private JTextField tftiraz;
	private JTextField tfnaslov2;
	private JTextField tfisbn2;
	private JTextField tfgod2;
	private JTextField tfjezik2;
	private JTextField tftiraz2;
	private JTable table;
	private JTextField tfidknjige;
	private JComboBox comboautor,comboautor2,comboizdavac,comboizdavac2;
	DefaultTableModel dtm=new DefaultTableModel();
	int glidknjige,glgod,gltiraz;
	String glnaslov,glisbn,gljezik,glautor,glizdavac;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormaKnjiga frame = new FormaKnjiga();
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
	public FormaKnjiga() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 967, 705);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 925, 632);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setBorder(new TitledBorder(null, "UNOS PODATAKA O KNJIZI", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		tabbedPane.addTab("UNOS PODATAKA O KNJIZI", null, panel, null);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NASLOV KNJIGE");
		lblNewLabel.setBounds(42, 59, 142, 16);
		panel.add(lblNewLabel);
		
		JLabel lblIsbn = new JLabel("ISBN");
		lblIsbn.setBounds(42, 118, 56, 16);
		panel.add(lblIsbn);
		
		JLabel lblGodinaIzdanja = new JLabel("GODINA IZDANJA");
		lblGodinaIzdanja.setBounds(42, 165, 142, 16);
		panel.add(lblGodinaIzdanja);
		
		JLabel lblJezik = new JLabel("JEZIK");
		lblJezik.setBounds(42, 221, 104, 16);
		panel.add(lblJezik);
		
		JLabel lblTiraz = new JLabel("TIRAZ");
		lblTiraz.setBounds(42, 276, 56, 16);
		panel.add(lblTiraz);
		
		JLabel lblAutor = new JLabel("AUTOR");
		lblAutor.setBounds(42, 329, 56, 16);
		panel.add(lblAutor);
		
		JLabel lblIzdavac = new JLabel("IZDAVAC");
		lblIzdavac.setBounds(42, 392, 56, 16);
		panel.add(lblIzdavac);
		
		tfnaslov = new JTextField();
		tfnaslov.setBounds(230, 56, 402, 22);
		panel.add(tfnaslov);
		tfnaslov.setColumns(10);
		
		tfisbn = new JTextField();
		tfisbn.setColumns(10);
		tfisbn.setBounds(230, 115, 402, 22);
		panel.add(tfisbn);
		
		tfgod = new JTextField();
		tfgod.setColumns(10);
		tfgod.setBounds(230, 162, 402, 22);
		panel.add(tfgod);
		
		tfjezik = new JTextField();
		tfjezik.setColumns(10);
		tfjezik.setBounds(230, 218, 402, 22);
		panel.add(tfjezik);
		
		tftiraz = new JTextField();
		tftiraz.setColumns(10);
		tftiraz.setBounds(230, 273, 402, 22);
		panel.add(tftiraz);
		
		comboautor = new JComboBox();
		comboautor.setBounds(230, 326, 402, 22);
		panel.add(comboautor);
		for (Autor a : Kontroler.getInstanca().vratiAutore()) {
			comboautor.addItem(a.getPrezimeAutora() + " " + a.getImeautora());
		}

		comboizdavac = new JComboBox();
		comboizdavac.setBounds(230, 389, 402, 22);
		panel.add(comboizdavac);
		for (Izdavac i : Kontroler.getInstanca().vratiIzdavace()) {
			comboizdavac.addItem(i.getNazivizdavaca());
		}

		//moja igranka sa stringovima
	/*	comboautor.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String aab=(String) comboautor.getSelectedItem();
				String ime=aab.substring(aab.lastIndexOf(" ")+1);
				String prezime=aab.substring(0, aab.lastIndexOf(" "));
				System.out.println("ime "+ime);
				System.out.println("prezime "+prezime);
				int idautora=0;
		for(Autor a:Kontroler.getInstanca().vratiAutore()){
			if(a.getImeautora().equalsIgnoreCase(ime)){
				idautora=a.getIdautora();
				System.out.println(idautora);
			}
		}
			}
			
		});*/
		
		
		JButton btnUnesi = new JButton("UNESI");
		btnUnesi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String naziv=tfnaslov.getText();
				String ISBN=tfisbn.getText();
				int godina=Integer.parseInt(tfgod.getText());
				String jezik=tfjezik.getText();
				int tiraz=Integer.valueOf(tftiraz.getText());
				
				String izdavac = (String) comboizdavac.getSelectedItem();
				int idizdavaca = 0;
				for (Izdavac i : Kontroler.getInstanca().vratiIzdavace()) {
					if (i.getNazivizdavaca().equalsIgnoreCase(izdavac)) {
						idizdavaca = i.getIdizdavaca();
					}
				}
				int idautora = 0;
				String ukupni = (String) comboautor.getSelectedItem();
				String ime = ukupni.substring(ukupni.lastIndexOf(" ") + 1);
				for (Autor a : Kontroler.getInstanca().vratiAutore()) {
					if (a.getImeautora().equalsIgnoreCase(ime)) {
						idautora = a.getIdautora();
					}
				}
				Kontroler.getInstanca().unesiKnjigu(naziv, ISBN, godina, jezik, tiraz, idautora, idizdavaca);
				JOptionPane.showMessageDialog(null, "upisani podaci o knjizi");
				tfnaslov.setText("");
				tfisbn.setText("");
				tfgod.setText("");
				tfjezik.setText("");
				tftiraz.setText("");
			}
		});
		btnUnesi.setBounds(230, 459, 159, 25);
		panel.add(btnUnesi);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		tabbedPane.addTab("PROMENA I BRISANJE PODATAKA O KNJIZI", null, panel_1, null);
		
		JLabel label = new JLabel("NASLOV KNJIGE");
		label.setBounds(42, 88, 142, 16);
		panel_1.add(label);
		
		JLabel label_1 = new JLabel("ISBN");
		label_1.setBounds(42, 142, 56, 16);
		panel_1.add(label_1);
		
		JLabel label_2 = new JLabel("GODINA IZDANJA");
		label_2.setBounds(42, 171, 142, 16);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("JEZIK");
		label_3.setBounds(44, 212, 104, 16);
		panel_1.add(label_3);
		
		JLabel label_4 = new JLabel("TIRAZ");
		label_4.setBounds(42, 247, 56, 16);
		panel_1.add(label_4);
		
		JLabel label_5 = new JLabel("AUTOR");
		label_5.setBounds(42, 282, 56, 16);
		panel_1.add(label_5);
		
		JLabel label_6 = new JLabel("IZDAVAC");
		label_6.setBounds(42, 327, 56, 16);
		panel_1.add(label_6);
		
		tfnaslov2 = new JTextField();
		tfnaslov2.setColumns(10);
		tfnaslov2.setBounds(230, 85, 283, 22);
		panel_1.add(tfnaslov2);
		
		tfisbn2 = new JTextField();
		tfisbn2.setColumns(10);
		tfisbn2.setBounds(230, 133, 283, 22);
		panel_1.add(tfisbn2);
		
		tfgod2 = new JTextField();
		tfgod2.setColumns(10);
		tfgod2.setBounds(230, 168, 283, 22);
		panel_1.add(tfgod2);
		
		tfjezik2 = new JTextField();
		tfjezik2.setColumns(10);
		tfjezik2.setBounds(230, 209, 283, 22);
		panel_1.add(tfjezik2);
		
		tftiraz2 = new JTextField();
		tftiraz2.setColumns(10);
		tftiraz2.setBounds(230, 244, 283, 22);
		panel_1.add(tftiraz2);
		
		 comboautor2 = new JComboBox();
		comboautor2.setBounds(230, 279, 283, 22);
		panel_1.add(comboautor2);
		for(Autor a:Kontroler.getInstanca().vratiAutore()){
			comboautor2.addItem(a.getPrezimeAutora()+" "+a.getImeautora());
		}
		
		 comboizdavac2 = new JComboBox();
		comboizdavac2.setBounds(230, 324, 283, 22);
		panel_1.add(comboizdavac2);
		for(Izdavac i:Kontroler.getInstanca().vratiIzdavace()){
			comboizdavac2.addItem(i.getNazivizdavaca());	
		}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 376, 896, 213);
		panel_1.add(scrollPane);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red=table.getSelectedRow();
				glidknjige=(int) table.getModel().getValueAt(red, 0);
				glnaslov=(String) table.getModel().getValueAt(red, 1);
				glisbn=(String) table.getModel().getValueAt(red, 2);
				glgod=(int) table.getModel().getValueAt(red, 3);
				gljezik= (String) table.getModel().getValueAt(red, 4);
				gltiraz=(int) table.getModel().getValueAt(red, 5);
				glautor=(String) table.getModel().getValueAt(red, 6);
				glizdavac=(String) table.getModel().getValueAt(red, 7);
				
				tfidknjige.setText(String.valueOf(glidknjige));
				tfnaslov2.setText(glnaslov);
				tfisbn2.setText(glisbn);
				tfgod2.setText(String.valueOf(glgod));
				tfjezik2.setText(gljezik);
				tftiraz2.setText(String.valueOf(gltiraz));
				comboautor2.setSelectedItem(glautor);
				comboizdavac2.setSelectedItem(glizdavac);
				
			}
		});
		scrollPane.setViewportView(table);
		
		JLabel lblIdKnjige = new JLabel("ID KNJIGE");
		lblIdKnjige.setBounds(42, 59, 142, 16);
		panel_1.add(lblIdKnjige);
		
		tfidknjige = new JTextField();
		tfidknjige.setBounds(230, 50, 283, 22);
		panel_1.add(tfidknjige);
		tfidknjige.setColumns(10);
		
		JButton btnpromeni = new JButton("PROMENI");
		btnpromeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String naslov=tfnaslov2.getText();
				String isbn=tfisbn2.getText();
				int god=Integer.valueOf(tfgod2.getText());
				String jezik=tfjezik2.getText();
				int tiraz=Integer.valueOf(tftiraz2.getText());
				
				String autor=(String) comboautor2.getSelectedItem();
				int idautora=0;
				
				String ime=autor.substring(autor.lastIndexOf(" ")+1);
				for(Autor a:Kontroler.getInstanca().vratiAutore()){
					if(a.getImeautora().equalsIgnoreCase(ime)){
						idautora=a.getIdautora();
					}
				}
				
				String izdavac=(String) comboizdavac2.getSelectedItem();
				int idizdavaca=0;
				for(Izdavac i:Kontroler.getInstanca().vratiIzdavace()){
					if(i.getNazivizdavaca().equalsIgnoreCase(izdavac)){
						idizdavaca=i.getIdizdavaca();
					}
				}
				Kontroler.getInstanca().promenipodatkeoknjizi(naslov, isbn, god, jezik, tiraz, idautora, idizdavaca, glidknjige);
				JOptionPane.showMessageDialog(null, "PROMENJENI PODACI");
				popuniTabelu();
				tfidknjige.setText("");
				tfnaslov2.setText("");
				tfisbn2.setText("");
				tfgod2.setText("");
				tfjezik2.setText("");
				tftiraz2.setText("");
				
			}
		});
		btnpromeni.setBounds(596, 49, 182, 25);
		panel_1.add(btnpromeni);
		
		JButton btnObrisi = new JButton("OBRISI");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Kontroler.getInstanca().obrisipodatkeOKnjizi(glidknjige);
				JOptionPane.showMessageDialog(null, "OBRISANI PODACI O KNJIZI");
				popuniTabelu();
			}
		});
		btnObrisi.setBounds(596, 162, 182, 25);
		panel_1.add(btnObrisi);
		
		JButton btnNewButton = new JButton("OSVEZI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				popuniTabelu();
			}
		});
		btnNewButton.setBounds(596, 278, 182, 25);
		panel_1.add(btnNewButton);

		Object[]kolone=new Object[8];
		kolone[0]="id knjige";
		kolone[1]="naslov";
		kolone[2]="isbn";
		kolone[3]="godina";
		kolone[4]="jezik";
		kolone[5]="tiraz";
		kolone[6]="autor";
		kolone[7]="izdavac";
		dtm.addColumn(kolone[0]);dtm.addColumn(kolone[1]);dtm.addColumn(kolone[2]);dtm.addColumn(kolone[3]);dtm.addColumn(kolone[4]);
		dtm.addColumn(kolone[5]);dtm.addColumn(kolone[6]);dtm.addColumn(kolone[7]);
		popuniTabelu();
	}
	public void popuniTabelu(){
		dtm.setRowCount(0);
		Object[]redovi=new Object[8];
		for(Knjiga k:Kontroler.getInstanca().vratiKnjige()){
			redovi[0]=k.getIdKnjige();
			redovi[1]=k.getNaslovKnjige();
			redovi[2]=k.getISBN();
			redovi[3]=k.getGodinaIzdanja();
			redovi[4]=k.getJezik();
			redovi[5]=k.getTiraz();
			int idautora=k.getIdAutora();
			int idizdavaca=k.getIdIzdavaca();
			for(Autor a:Kontroler.getInstanca().vratiAutore()){
				if(a.getIdautora()==idautora){
					redovi[6]=a.getPrezimeAutora()+" "+a.getImeautora();
				}
			}
			for(Izdavac i:Kontroler.getInstanca().vratiIzdavace()){
				if(i.getIdizdavaca()==idizdavaca){
					redovi[7]=i.getNazivizdavaca();
				}
			}
			dtm.addRow(redovi);
		}
	}
}

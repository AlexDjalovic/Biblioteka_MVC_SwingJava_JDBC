package FORME_KORISNIK_AUTOR_IZDAVAC_KORISNIK;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import KONTROLER.Kontroler;
import OSOBE.Autor;


import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;

public class AUTOR_FORMA extends JFrame {

	private JPanel contentPane;
	private JTextField TFID;
	private JTextField TFPREZIME;
	private JTextField TFIME;
	private JTextField TFZEMLJA;
	private JTable table;
	DefaultTableModel dtm= new DefaultTableModel();
	private int glid;
	private String glime,glprezime,glzemlja;
	private JTextField TFPRETRAGA;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AUTOR_FORMA frame = new AUTOR_FORMA();
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
	public AUTOR_FORMA() {
		setTitle("AUTORI");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 814, 683);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "UNOS,PROMENA I BRISANJE PODATAKA O AUTORU", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(5, 5, 769, 320);
		contentPane.add(panel);
		panel.setLayout(null);
		
		TFID = new JTextField();
		TFID.setBounds(255, 86, 199, 22);
		panel.add(TFID);
		TFID.setColumns(10);
		
		TFPREZIME = new JTextField();
		TFPREZIME.setColumns(10);
		TFPREZIME.setBounds(255, 145, 199, 22);
		panel.add(TFPREZIME);
		
		TFIME = new JTextField();
		TFIME.setColumns(10);
		TFIME.setBounds(255, 205, 199, 22);
		panel.add(TFIME);
		
		TFZEMLJA = new JTextField();
		TFZEMLJA.setColumns(10);
		TFZEMLJA.setBounds(255, 262, 199, 22);
		panel.add(TFZEMLJA);
		
		JButton btnUNESI = new JButton("UNESI");
		btnUNESI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String prezime=TFPREZIME.getText();
				String ime=TFIME.getText();
				String zemlja=TFZEMLJA.getText();
				Kontroler.getInstanca().unesiautora(prezime, ime, zemlja);
				JOptionPane.showMessageDialog(null, "upisan autor");
				TFPREZIME.setText("");
				TFIME.setText("");
				TFZEMLJA.setText("");
				popunitabelu();
			}
		});
		btnUNESI.setBounds(546, 85, 199, 25);
		panel.add(btnUNESI);
		
		JButton btnUPDATE = new JButton("PROMENI");
		btnUPDATE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ime=TFIME.getText();
				String prezime=TFPREZIME.getText();
				String zemlja=TFZEMLJA.getText();
				Kontroler.getInstanca().promeniAutora(prezime, ime, zemlja, glid);
				JOptionPane.showMessageDialog(null, "promenjeni podaci o autoru");
				popunitabelu();
			}
		});
		btnUPDATE.setBounds(546, 144, 199, 25);
		panel.add(btnUPDATE);
		
		JButton btnObrisi = new JButton("OBRISI");
		btnObrisi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Kontroler.getInstanca().obrisiAutora(glid);
				JOptionPane.showMessageDialog(null, "Izbrisani podaci");
				popunitabelu();
			}
		});
		btnObrisi.setBounds(546, 204, 199, 25);
		panel.add(btnObrisi);
		
		JLabel lblNewLabel = new JLabel("ID AUTORA");
		lblNewLabel.setBounds(34, 89, 97, 16);
		panel.add(lblNewLabel);
		
		JLabel lblPrezimeAutora = new JLabel("PREZIME AUTORA");
		lblPrezimeAutora.setBounds(34, 148, 125, 16);
		panel.add(lblPrezimeAutora);
		
		JLabel lblImeAutora = new JLabel("IME AUTORA");
		lblImeAutora.setBounds(34, 208, 112, 16);
		panel.add(lblImeAutora);
		
		JLabel lblZemljaAutora = new JLabel("ZEMLJA AUTORA");
		lblZemljaAutora.setBounds(34, 265, 112, 16);
		panel.add(lblZemljaAutora);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 437, 769, 186);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red=table.getSelectedRow();
				glid=(int) table.getModel().getValueAt(red, 0);
				glprezime=(String) table.getModel().getValueAt(red, 1);
				glime=(String) table.getModel().getValueAt(red, 2);
				glzemlja=(String) table.getModel().getValueAt(red, 3);
				
				TFID.setText(String.valueOf(glid));
				TFIME.setText(glime);
				TFPREZIME.setText(glprezime);
				TFZEMLJA.setText(glzemlja);
				
		
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "PRETRAGA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(5, 338, 769, 86);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnPretraga = new JButton("PRETRAGA");
		btnPretraga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String pretraga=TFPRETRAGA.getText();
				if(pretraga.equals("")){
					popunitabelu();
				}else{
					prikaziTrazenoNaTabeli();
				}
				TFPRETRAGA.setText("");
			}
			
		});
		btnPretraga.setBounds(553, 29, 199, 25);
		panel_1.add(btnPretraga);
		
		TFPRETRAGA = new JTextField();
		TFPRETRAGA.setBounds(12, 30, 451, 22);
		panel_1.add(TFPRETRAGA);
		TFPRETRAGA.setColumns(10);
		
		Object []kolone=new Object[4];
		kolone[0]="id autora";
		kolone[1]="prezime autora";
		kolone[2]="ime autora";
		kolone[3]="zemlja autora";
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		dtm.addColumn(kolone[2]);
		dtm.addColumn(kolone[3]);
		popunitabelu();
		
		
	}
	public void popunitabelu(){
		dtm.setRowCount(0);
		Object[]redovi=new Object[4];
		for(Autor a:Kontroler.getInstanca().vratiAutore()){
			redovi[0]=a.getIdautora();
			redovi[1]=a.getPrezimeAutora();
			redovi[2]=a.getImeautora();
			redovi[3]=a.getZemljaAutora();
			dtm.addRow(redovi);
		}
	}
	public void prikaziTrazenoNaTabeli(){
		String pretraga=TFPRETRAGA.getText();
		dtm.setRowCount(0);
		Object [] redovi=new Object[7];
		for(Autor s:Kontroler.getInstanca().pretraziautorePo(pretraga)){
			redovi[0]=s.getIdautora();
			redovi[1]=s.getPrezimeAutora();
			redovi[2]=s.getImeautora();
			redovi[3]=s.getZemljaAutora();
			
			dtm.addRow(redovi);
		}
	}
}

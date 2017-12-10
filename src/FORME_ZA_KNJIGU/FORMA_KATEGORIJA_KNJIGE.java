package FORME_ZA_KNJIGU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import BAZA.DBKomunikacija;
import KONTROLER.Kontroler;
import PUBLIKACIJE.KategorijaKnjige;
import PUBLIKACIJE.Knjiga;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class FORMA_KATEGORIJA_KNJIGE extends JFrame {

	private JPanel contentPane;
	private JTextField tfkateg;
	private JComboBox combonaslov;
    private JComboBox combokateg;
    private JTextField textField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FORMA_KATEGORIJA_KNJIGE frame = new FORMA_KATEGORIJA_KNJIGE();
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
	public FORMA_KATEGORIJA_KNJIGE() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 984, 772);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 250, 240));
		panel.setBorder(new TitledBorder(null, "UNOS KATEGORIJE KNJIGE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 450, 324);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("KATEGORIJA KNJIGE");
		lblNewLabel.setBounds(12, 69, 132, 16);
		panel.add(lblNewLabel);
		
		tfkateg = new JTextField();
		tfkateg.setBounds(183, 66, 230, 22);
		panel.add(tfkateg);
		tfkateg.setColumns(10);
		
		JButton btnNewButton = new JButton("UNESI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String kateg=tfkateg.getText();
				Kontroler.getInstanca().upisikategoriju(kateg);
				JOptionPane.showMessageDialog(null, "upisana kategorija");
				tfkateg.setText("");
				combokateg.addItem(kateg);
				
			}
		});
		btnNewButton.setBounds(183, 197, 230, 25);
		panel.add(btnNewButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 250, 240));
		panel_1.setBorder(new TitledBorder(null, "UNOS PODATAKA O KATEGORIJI KONKRETNE KNJIGE", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(474, 13, 480, 327);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		 combonaslov = new JComboBox();
		combonaslov.setBounds(204, 71, 221, 22);
		panel_1.add(combonaslov);
		
		for(Knjiga k:Kontroler.getInstanca().vratiKnjige()){
			combonaslov.addItem(k.getNaslovKnjige());
		}
		 combokateg = new JComboBox();
		combokateg.setBounds(204, 148, 221, 22);
		panel_1.add(combokateg);
		for(KategorijaKnjige kk:Kontroler.getInstanca().vratiKategorijuKnjige()){
			combokateg.addItem(kk.getKategorija());
		}
		
		JLabel lblNewLabel_1 = new JLabel("NASLOV KNJIGE");
		lblNewLabel_1.setBounds(12, 74, 129, 16);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("KATEGORIJA KNJIGE");
		lblNewLabel_2.setBounds(12, 151, 129, 16);
		panel_1.add(lblNewLabel_2);
		
		JButton btnNewButton_1 = new JButton("UNESI");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String kategorija=(String) combokateg.getSelectedItem();
			//	System.out.println(kategorija);
				int idkategorije=0;
				for(KategorijaKnjige kk:Kontroler.getInstanca().vratiKategorijuKnjige()){
					if(kk.getKategorija().equalsIgnoreCase(kategorija)){
						idkategorije=kk.getIdkategorijeknjige();
						//System.out.println(idkategorije);
					}
				}
				String knjiga=(String) combonaslov.getSelectedItem();
				//System.out.println(knjiga);
				int idknjige=0;
				for(Knjiga kk:Kontroler.getInstanca().vratiKnjige()){
					if(kk.getNaslovKnjige().equalsIgnoreCase(knjiga)){
						idknjige=kk.getIdKnjige();
						//System.out.println(idknjige);
					}
				}
				
				Kontroler.getInstanca().poveziKategoriju_i_Knjigu(idknjige, idkategorije);
				JOptionPane.showMessageDialog(null, "povezane knjiga i zanr");
			}
		});
		btnNewButton_1.setBounds(204, 251, 222, 25);
		panel_1.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 250, 240));
		panel_2.setBounds(12, 350, 942, 325);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(460, 13, 457, 299);
		panel_2.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton_2 = new JButton("PRETRAZI");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				String pretraga=textField.getText();
			ResultSet rs=Kontroler.getInstanca().prikaziknjigepozadatojKategoriji(pretraga);
			try {
				while(rs.next()){
					String naslov=rs.getString("naslovKnjige");
					//System.out.println(naslov);
					textArea.append(naslov+"\n");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DBKomunikacija.getInstance().zatvoriKomunikaciju();
			textField.setText("");
			
			}
		});
		btnNewButton_2.setBounds(137, 249, 97, 25);
		panel_2.add(btnNewButton_2);
		
		textField = new JTextField();
		textField.setBounds(37, 166, 358, 22);
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("PRIKAZI KNJIGE PO ZADATOJ KATEGORIJI");
		lblNewLabel_3.setBounds(59, 105, 336, 16);
		panel_2.add(lblNewLabel_3);
	}
}

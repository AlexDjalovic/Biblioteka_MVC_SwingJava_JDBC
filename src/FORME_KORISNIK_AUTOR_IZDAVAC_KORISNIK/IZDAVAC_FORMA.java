package FORME_KORISNIK_AUTOR_IZDAVAC_KORISNIK;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import KONTROLER.Kontroler;
import OSOBE.Izdavac;

import javax.swing.UIManager;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IZDAVAC_FORMA extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField textField;
	DefaultTableModel dtm=new DefaultTableModel();
	int glid;
	String gnaziv;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IZDAVAC_FORMA frame = new IZDAVAC_FORMA();
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
	public IZDAVAC_FORMA() {
		setTitle("IZDAVAC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 561, 471);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "PODACI O IZDAVACU", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel.setBounds(12, 13, 519, 191);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("NAZIV IZDAVACA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(22, 63, 124, 16);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(158, 60, 196, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("UNESI");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv=textField.getText();
				Kontroler.getInstanca().unesiIzdavaca(naziv);
				JOptionPane.showMessageDialog(null, "upisan izdavac");
				textField.setText("");
				prikaziTabelu();
				
			}
		});
		btnNewButton.setBounds(410, 54, 97, 25);
		panel.add(btnNewButton);
		
		JButton btnPromeni = new JButton("PROMENI");
		btnPromeni.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String naziv=textField.getText();
				Kontroler.getInstanca().promeniImeIzdavaca(naziv, glid);
				JOptionPane.showMessageDialog(null, "upisan izdavac");
				textField.setText("");
				prikaziTabelu();
			}
		});
		btnPromeni.setBounds(410, 123, 97, 25);
		panel.add(btnPromeni);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 230, 519, 181);
		contentPane.add(scrollPane);
		
		table = new JTable(dtm);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int red=table.getSelectedRow();
				glid=(int) table.getModel().getValueAt(red, 0);
				gnaziv=(String) table.getModel().getValueAt(red, 1);
				
				textField.setText(gnaziv);
			}
		});
		scrollPane.setViewportView(table);
		
		Object[]kolone=new Object[2];
		kolone[0]="id izdavaca";
		kolone[1]="naziv izdavaca";
		dtm.addColumn(kolone[0]);
		dtm.addColumn(kolone[1]);
		prikaziTabelu();
		
	}
	public void prikaziTabelu(){
		dtm.setRowCount(0);
		Object[]redovi=new Object[2];
		for(Izdavac i:Kontroler.getInstanca().vratiIzdavace()){
			redovi[0]=i.getIdizdavaca();
			redovi[1]=i.getNazivizdavaca();
			dtm.addRow(redovi);
		}
	}
}

package LOGIN_I_GLAVNA_FORMA;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import KONTROLER.Kontroler;
import OSOBE.User;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField tfuser;
	private JTextField tfpass;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 649, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("CheckBox.light"));
		panel.setBounds(12, 13, 607, 402);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tfuser = new JTextField();
		tfuser.setColumns(10);
		tfuser.setBounds(195, 107, 322, 22);
		panel.add(tfuser);
		
		tfpass = new JTextField();
		tfpass.setColumns(10);
		tfpass.setBounds(195, 189, 322, 22);
		panel.add(tfpass);
		
		JLabel label = new JLabel("UserName");
		label.setFont(new Font("Tahoma", Font.BOLD, 15));
		label.setBounds(56, 110, 83, 16);
		panel.add(label);
		
		JLabel label_1 = new JLabel("Password");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		label_1.setBounds(56, 192, 83, 16);
		panel.add(label_1);
		
		JButton btnPrijava = new JButton("ULOGUJ SE");
		btnPrijava.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String user=tfuser.getText();
				String password=tfpass.getText();
			
				boolean provera = false;
				for (User u : Kontroler.getInstanca().vratiUsere()) {

					if (u.getPassword().equalsIgnoreCase(password)&&u.getUsername().equalsIgnoreCase(user)) {
						provera = true;
						break;
						

					}
				}
				if (provera == true) {
					JOptionPane.showMessageDialog(null, "IMATE PRAVO PRISTUPA APLIKACIJI");
					GlavnaForma gf=new GlavnaForma();
					gf.setVisible(true);
					gf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					
				}else{
					
					JOptionPane.showMessageDialog(null, "NEMATE PRAVO PRISTUPA APLIKACIJI,REGISTRUJTE SE");
				}
				tfuser.setText("");
				tfpass.setText("");
			}
		});
		btnPrijava.setForeground(SystemColor.infoText);
		btnPrijava.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnPrijava.setBounds(368, 299, 149, 25);
		panel.add(btnPrijava);
		
		JButton btnSignUp = new JButton("REGISTRUJ SE");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String user=tfuser.getText();
				String password=tfpass.getText();
				Kontroler.getInstanca().upisiNovogKorisnika(user, password);
				JOptionPane.showMessageDialog(null, "IMATE PRISTUP APLIKACIJI");
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnSignUp.setBounds(195, 299, 149, 25);
		panel.add(btnSignUp);
		
		JLabel label_2 = new JLabel("LOGIN FORMA");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Times New Roman", Font.BOLD, 24));
		label_2.setBounds(199, 25, 205, 52);
		panel.add(label_2);
	}
}

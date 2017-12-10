package FORME_ZA_KNJIGU;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import NIT.SAT;
import NIT.nitObserver;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Font;

public class formObserver extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					formObserver frame = new formObserver();
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
	public formObserver() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 733, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblsat = new JLabel("New label");
		lblsat.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblsat.setHorizontalAlignment(SwingConstants.CENTER);
		lblsat.setBounds(12, 13, 691, 77);
		contentPane.add(lblsat);
		
		SAT s=new SAT(lblsat);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 107, 691, 297);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		nitObserver no=new nitObserver(lblsat, textArea);
	}
}

package sniffer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;

public class KnowMyIp extends JFrame {

	private JPanel contentPane;
	JTextArea txtrWruii = new JTextArea();
	JButton btnKnowMyIp = new JButton("Know My IP");
	private final JButton btnNewButton = new JButton("BACK");
	private final JLabel lblNewLabel = new JLabel("\r\n");
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KnowMyIp frame = new KnowMyIp();
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
	public KnowMyIp() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 503);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		btnKnowMyIp.setBackground(new Color(255, 160, 122));
		btnKnowMyIp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btnKnowMyIpActionPerformed(e);
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnKnowMyIp.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 20));
		btnKnowMyIp.setForeground(new Color(0, 0, 128));
		btnKnowMyIp.setBounds(316, 11, 169, 33);
		contentPane.add(btnKnowMyIp);
		txtrWruii.setToolTipText("");
		txtrWruii.setForeground(new Color(0, 0, 128));
		txtrWruii.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
		
		
		txtrWruii.setBackground(new Color(230, 230, 250));
		txtrWruii.setBounds(224, 55, 346, 60);
		contentPane.add(txtrWruii);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				backButtonActionPerformed(e);
			
			}
		});
		btnNewButton.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 16));
		btnNewButton.setBackground(new Color(255, 160, 122));
		btnNewButton.setForeground(new Color(25, 25, 112));
		btnNewButton.setBounds(10, 408, 89, 40);
		
		contentPane.add(btnNewButton);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\DELL1\\Desktop\\ip.jpg"));
		lblNewLabel.setBounds(0, 0, 794, 459);
		
		contentPane.add(lblNewLabel);
	}

	protected void backButtonActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.setVisible(false);
		new IPTracker().setVisible(true);
	}

	protected void btnKnowMyIpActionPerformed(ActionEvent e) throws UnknownHostException {
		// TODO Auto-generated method stub
	     InetAddress localhost = InetAddress.getLocalHost(); 
//	     txtrWruii.setText("System IP Address : " + 
//	                      (localhost.getHostAddress()).trim()); 
//	        // Find public IP address 
	        String systemipaddress = ""; 
	        try
	        { 
	            URL url_name = new URL("http://bot.whatismyipaddress.com"); 
	  
	            BufferedReader sc = 
	            new BufferedReader(new InputStreamReader(url_name.openStream())); 
	  
	            // reads system IPAddress 
	            systemipaddress = sc.readLine().trim(); 
	        } 
	        catch (Exception e1) 
	        { 
	            systemipaddress = "Network Error"; 
	        } 
	        txtrWruii.append("  Public IP Address: " + systemipaddress +"\n  System IP Address : "+localhost.getHostAddress()); 
	        btnKnowMyIp.setEnabled(false);
	    } 
	
}

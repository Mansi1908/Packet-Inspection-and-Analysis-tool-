package sniffer;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.border.EmptyBorder;

import java.awt.Label;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.border.TitledBorder;

import sniffer.SnifferWindow;
import sniffer.Welcome;

//import com.maildemos.MailingDemo;

import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.*;
public class Welcome extends JFrame {
	
	
	static Welcome welcome;
	private JFrame frame;
	private JPanel contentPane;
	public static String uemail;
	/**
	 * Launch the application.
	 */
	
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Welcome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        welcome = new Welcome();
        welcome.setVisible(true);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {

                    Thread.sleep(5000);
                    new SnifferWindow().setVisible(true);
                    welcome.setVisible(false);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Welcome.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }


	/**
	 * Create the frame.
	 */
	public 	Welcome() {
		this.setResizable(true);
		setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 20));
		setTitle("Welcome");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 812, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PACKET INSPECTION AND ANALYSIS TOOL FOR ");
		lblNewLabel.setBackground(new Color(255, 240, 245));
		lblNewLabel.setForeground(new Color(255, 255, 0));
		lblNewLabel.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 32));
		lblNewLabel.setBounds(66, 190, 675, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("MONITORING NETWORK TRAFFIC");
		lblPassword.setForeground(new Color(255, 255, 0));
		lblPassword.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 32));
		lblPassword.setBounds(157, 243, 486, 29);
		contentPane.add(lblPassword);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBackground(new Color(240, 240, 240));
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\DELL1\\Desktop\\llllllll.jpg"));
		lblNewLabel_1.setBounds(0, 0, 800, 460);
		contentPane.add(lblNewLabel_1);
	}
	 public void close(){
		 
		 WindowEvent winClosingEvent = new WindowEvent(this,WindowEvent.WINDOW_CLOSING);
		 Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(winClosingEvent);
		 
		 }
}

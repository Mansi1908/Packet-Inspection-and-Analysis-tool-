package sniffer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.NetworkInterfaceAddress;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.JobAttributes;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class Windo extends JFrame {

	PkPirate_CaptureThread CAPTAIN;	// reference variable of abstract class PKPirate_capture_thread
	JLabel lblNewLabel = new JLabel("Interface");
	public static JTextArea textArea_1 = new JTextArea();
	private JPanel contentPane;
	NetworkInterface[] NETWORK_INTERFACES;
	JpcapCaptor CAP;// reference variable of JPCAPCAPTOR
	int index=0;// indexing of array of network interfaces
	int counter=0;//
	boolean captureState=false;	// initially we are not capturing the packets
	private JTextField textField;
	JScrollPane scrollPane = new JScrollPane();
	private final JButton capture = new JButton("Capture");
	private final JButton stop = new JButton("Stop");
	JLabel lblNewLabel_1 = new JLabel("Port Filter Status");
	JButton select = new JButton("Select");
	
	JLabel L_FilterStatusBox = new JLabel("DISABLED ALL PORTS");
	ButtonGroup BG_FILTER_ENABLE_DISABLE  = new ButtonGroup();	// it consists of two button groups one is  to enable and disable
	ButtonGroup BG_PORTS  = new ButtonGroup();// other button grp consists of ports
	
	JRadioButton RB_FILTER_ENABLE = new JRadioButton("Enable");
	
	JRadioButton RB_FILTER_DISABLE = new JRadioButton("Disable");
	JRadioButton RB_PORT_SMTP = new JRadioButton("SMTP(25)");
	
	JButton filter = new JButton("Filter");
	
	JLabel lblPortFilterpresets = new JLabel("Port Filter Presets");
	
	JRadioButton RB_PORT_HTTP = new JRadioButton("HTTP(80)");
	
	JRadioButton RB_PORT_DNS = new JRadioButton("DNS(53)");
	private final JButton btnExit = new JButton("Exit");
	private final JRadioButton RB_PORT_SSL = new JRadioButton("HTTP SSL(443)");
	private final JButton back = new JButton("Back");
	private final JButton save = new JButton("Save");
	JButton load = new JButton("Load");
	private final JButton help = new JButton("Help");
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {	// run method is called for the current thread
				try {
					Windo frame = new Windo();
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
	public Windo() {
		help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ACTION_B_HELP(arg0);
			}
		});
		help.setForeground(Color.WHITE);
		help.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 12));
		help.setEnabled(true);
		help.setBackground(new Color(0, 0, 128));
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ACTION_B_SAVE(arg0);
			}
		});
		save.setForeground(Color.WHITE);
		save.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 12));
		save.setEnabled(false);
		save.setBackground(new Color(0, 0, 128));
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ACTION_B_BACK(arg0);
			}
		});
		back.setForeground(Color.WHITE);
		back.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 12));
		back.setEnabled(true);
		back.setBackground(new Color(0, 0, 128));
		setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		setTitle("Network Packet Sniffer");
		btnExit.setBackground(new Color(0, 0, 128));
		btnExit.setEnabled(true);
		btnExit.setForeground(new Color(240, 248, 255));
		btnExit.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 12));
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ACTION_B_EXIT(e);
			}
		});
		stop.setBackground(new Color(0, 0, 128));
		stop.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 12));
		stop.setForeground(new Color(255, 255, 255));
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ACTION_B_STOP(e);
			}
		});
		capture.setBackground(new Color(0, 0, 128));
		capture.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 12));
		capture.setForeground(new Color(255, 255, 255));
		capture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ACTION_b_CAPTURE(e);
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 875, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		
		JButton List = new JButton("List");
		List.setBackground(new Color(0, 0, 128));
		List.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 12));
		List.setForeground(new Color(255, 255, 255));
		List.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				ACTION_B_LIST(arg0);
			}
		});
		
		textField = new JTextField();
		textField.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 13));
		textField.setColumns(10);
		
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setForeground(new Color(25, 25, 112));
		select.setBackground(new Color(25, 25, 112));
		
		select.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ACTION_B_SELECT(arg0);
			}
		});
		select.setForeground(new Color(255, 255, 255));
		select.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 12));
		select.setEnabled(false);
		
		load.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ACTION_B_LOAD(arg0);
			}
		});
		load.setForeground(Color.WHITE);
		load.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 12));
		load.setEnabled(true);
		load.setBackground(new Color(0, 0, 128));
		
		JButton Clear = new JButton("clear");
		Clear.setEnabled(true);
		Clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ACTION_ON_CLEAR(arg0);
			}
		});
		Clear.setForeground(new Color(240, 248, 255));
		Clear.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 12));
		Clear.setEnabled(true);
		Clear.setBackground(new Color(0, 0, 128));
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 849, Short.MAX_VALUE)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 71, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(select)
							.addGap(18)
							.addComponent(capture, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addComponent(textField)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addContainerGap()
								.addComponent(List, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(stop, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(39)
									.addComponent(back, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(26)
									.addComponent(save, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(load, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
								.addComponent(help, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(L_FilterStatusBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
								.addComponent(RB_FILTER_ENABLE)
								.addComponent(RB_FILTER_DISABLE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(filter, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(Clear, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(59)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(RB_PORT_DNS)
										.addComponent(RB_PORT_HTTP)
										.addComponent(RB_PORT_SMTP)
										.addComponent(RB_PORT_SSL)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(58)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblPortFilterpresets, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE))))))
					.addGap(19))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(23)))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(List, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
									.addComponent(stop, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
									.addComponent(save, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
									.addComponent(load, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
								.addGap(28)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(lblPortFilterpresets)
										.addGap(60))
									.addGroup(gl_contentPane.createSequentialGroup()
										.addComponent(RB_PORT_HTTP)
										.addPreferredGap(ComponentPlacement.UNRELATED)
										.addComponent(RB_PORT_DNS)))
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(RB_PORT_SMTP)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(RB_PORT_SSL)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(43)
									.addComponent(RB_FILTER_ENABLE))
								.addComponent(L_FilterStatusBox))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(RB_FILTER_DISABLE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(43)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(filter, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(Clear))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(2)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(select, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
								.addComponent(capture, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
								.addComponent(back, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
								.addComponent(help, GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
							.addGap(10)))
					.addGap(11))
		);
		RB_PORT_HTTP.setForeground(new Color(25, 25, 112));
		RB_PORT_HTTP.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 13));
		RB_PORT_SMTP.setForeground(new Color(25, 25, 112));
		RB_PORT_SMTP.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 13));
		RB_PORT_DNS.setForeground(new Color(25, 25, 112));
		RB_PORT_DNS.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 13));
		RB_PORT_SSL.setForeground(new Color(25, 25, 112));
		RB_PORT_SSL.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 13));
		filter.setBackground(new Color(0, 0, 128));
		L_FilterStatusBox.setBackground(new Color(102, 205, 170));
		filter.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 12));
		filter.setForeground(new Color(255, 255, 255));
		RB_FILTER_ENABLE.setForeground(new Color(0, 0, 0));
		RB_FILTER_ENABLE.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		RB_FILTER_DISABLE.setForeground(new Color(25, 25, 112));
		RB_FILTER_DISABLE.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		lblPortFilterpresets.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
		lblPortFilterpresets.setForeground(new Color(178, 34, 34));
		L_FilterStatusBox.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 12));
		L_FilterStatusBox.setForeground(new Color(165, 42, 42));
		filter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ACTION_B_FILTER(e);
			}
		});
		textArea_1.setBackground(new Color(255, 222, 173));
		textArea_1.setForeground(new Color(0, 0, 139));
		textArea_1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
		textArea_1.setEditable(false);
		
		scrollPane.setViewportView(textArea_1);
		contentPane.setLayout(gl_contentPane);
		
		
		//------------------------------------------------
		BG_FILTER_ENABLE_DISABLE.add(RB_FILTER_ENABLE);
		RB_FILTER_ENABLE.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ACTION_B_ENABLE(e);
			}
		});
		
		//------------------------------------------------
		
		BG_FILTER_ENABLE_DISABLE.add(RB_FILTER_DISABLE);
		RB_FILTER_DISABLE.setSelected(true);
		RB_FILTER_DISABLE.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				ACTION_B_DISABLE(arg0);
			}
		});
		
		//---------------------------------------------------
		BG_PORTS.add(RB_PORT_HTTP);
		BG_PORTS.add(RB_PORT_DNS);
		BG_PORTS.add(RB_PORT_SMTP);
		BG_PORTS.add(RB_PORT_SSL);
		
		disableButtons();
	}

	protected void ACTION_ON_CLEAR(ActionEvent arg0) {
		// TODO Auto-generated method stub
		textArea_1.setText("");
	}

	protected void ACTION_B_HELP(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		String msg = "Network Packet Sniffer is JAva packet capturing and traffic analysis application"
				+ "\n CAPTURE : Start capturing packets on the interface"
				+ "\nSTOP : Stop capturing packets on the interface"
				+ "\n LIST : List Network Interfaces on the host"
				+ "\n SELECT : Select Interface to capture Packets with"
				+ "\n FILTER : Filter on the selected port when filtering is enabled"
				+ "\n ENABLE : Enable Port Filtering"
				+ "\n HELP : Displays the help screen"
				+ "\n SAVE : Save the information about the packet"
				+ "\n LOAD : Load the saved data on the interface "
				+ "\n ";
		
		JOptionPane.showMessageDialog(null, msg);
	}

	protected void ACTION_B_LOAD(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String capturedata="";
		try {
			File DATA = new File("capturedata.txt");
			
			FileInputStream datastream = new FileInputStream(DATA);
			InputStreamReader input = new InputStreamReader(datastream);
			BufferedReader reader = new BufferedReader(input);
			
			while(reader.read() != -1)
			{
				capturedata=capturedata+reader.readLine();
				capturedata=capturedata + "\n";
			}
			
			reader.close();
			input.close();
			datastream.close();
			
			textArea_1.setText(capturedata);
			
			JOptionPane.showMessageDialog(null, "Data Loaded Successfully");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "File Access Error !! Unable to Load data");
			e.printStackTrace();
		}
		
	}

	protected void ACTION_B_SAVE(ActionEvent arg0) {
		// TODO Auto-generated method stub
		String capturedata = textArea_1.getText();
		try
		{
			File DATA = new File("capturedata.txt");
			FileOutputStream datastream = new FileOutputStream(DATA);
			PrintStream out = new PrintStream(datastream);
			out.print(capturedata);
			out.close();
			datastream.close();
			JOptionPane.showMessageDialog(null, "Data Saved Successfully");
			
		}
		catch (Exception e) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "File Access Error ! Could not save data");
		}
	}

	protected void ACTION_B_BACK(ActionEvent arg0) {
		// TODO Auto-generated method stub
		this.setVisible(false);
		new SnifferWindow().setVisible(true);
	}

	protected void ACTION_B_SELECT(ActionEvent arg0) {
		// TODO Auto-generated method stub
		choseInterface();
	}

	protected void ACTION_B_FILTER(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			
			if(RB_FILTER_ENABLE.isSelected())
			{
				if(RB_PORT_HTTP.isSelected())
				{
					CAP.setFilter("ip and tcp", true);
				}
				else if(RB_PORT_DNS.isSelected())
				{
					CAP.setFilter("udp dst port 53", true);
				}else if(RB_PORT_SMTP.isSelected())
				{
					CAP.setFilter("smtp dst port 25", true);
				}else if(RB_PORT_SSL.isSelected())
				{
					CAP.setFilter("port 443", true);
				}
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Filtering is Disabled");
			}
			
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2);
		}
		
	}

	protected void ACTION_B_EXIT(ActionEvent e) {
		// TODO Auto-generated method stub
		setVisible(false);
		dispose();
	}

	protected void ACTION_B_DISABLE(ActionEvent arg0) {
		// TODO Auto-generated method stub

		L_FilterStatusBox.setText("Disabled (All Ports)");
	}

	protected void ACTION_B_ENABLE(ActionEvent e) {
		// TODO Auto-generated method stub
		L_FilterStatusBox.setText("Enabled (Selected Port)");
	}

	protected void ACTION_B_STOP(ActionEvent e) {
		// TODO Auto-generated method stub
		captureState=false;
		CAPTAIN.finished();
	}

	protected void ACTION_b_CAPTURE(ActionEvent e) {
		// TODO Auto-generated method stub
		textArea_1.setText("");
		captureState=true;
		CapturePackets();
		save.setEnabled(true);
		//load.setEnabled(true);
		
	}

	public void CapturePackets() {
		// TODO Auto-generated method stub
		// pk pirate capture thread is a detailed version of swing worker class 
		// for capturing the thread at the background a new thread (sub-process will be created
		CAPTAIN= new PkPirate_CaptureThread() { 		
			
			@Override
			public Object construct() {
				// TODO Auto-generated method stub
				textArea_1.setText("\n Now Capturing on Interface "+index+ ".... "+"\n --------------------------------------------"+
						"---------------------------------------------\n\n ");
				try {
					CAP=JpcapCaptor.openDevice(NETWORK_INTERFACES[index], 65535, true, 20);
					while(captureState)
					{
						CAP.processPacket(1, new PkPirate_packetContents());
					}
					CAP.close();
					
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println(e);
				}
				
				
				return 0;
			}
			public void finished()
			{
				this.interrupt();
			}
		};
			CAPTAIN.start();
	}

	protected void choseInterface() {
		// TODO Auto-generated method stub
		int temp=Integer.parseInt(textField.getText());
		if(temp>0 && temp<=counter)
		{
			index=temp; // index is he index of network inerface object to see which object need to be opened
			enableButtons();
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Outsde of Range. # interfaces = 1-"+(counter)+".");
		}
		textField.setText("");
	}

	private void enableButtons() {
		// TODO Auto-generated method stub
		//back.setEnabled(true);
		capture.setEnabled(true);
		stop.setEnabled(true);
		select.setEnabled(true);
		filter.setEnabled(true);
	}

	public void disableButtons() {
		// TODO Auto-generated method stub
		
		capture.setEnabled(false);
		stop.setEnabled(false);
		filter.setEnabled(false);
		save.setEnabled(false);
		//load.setEnabled(false);
	}

	protected void ACTION_B_LIST(ActionEvent arg0) {
		// TODO Auto-generated method stub
		ListNetworkInerface();
		select.setEnabled(true);
		back.setEnabled(true);
		help.setEnabled(true);
		load.setEnabled(true);
		textField.requestFocus();
	}

	public void ListNetworkInerface() {
		// TODO Auto-generated method stub
		NETWORK_INTERFACES=JpcapCaptor.getDeviceList(); 
		textArea_1.setText("");
		for(int i=0;i<NETWORK_INTERFACES.length;i++)
		{
				textArea_1.append("\n----------------------------------------------Interface "+(i+1)+" Info------------------------------------------");
				
				textArea_1.append("\n Interface Number "+(i+1));
				textArea_1.append("\n Description "+NETWORK_INTERFACES[i].name + "( "+NETWORK_INTERFACES[i].description+" )");

				textArea_1.append("\n DataLink Name : "+NETWORK_INTERFACES[i].datalink_name + "( "+NETWORK_INTERFACES[i].datalink_description+" )");
				textArea_1.append("\n MAC address : ");
				byte R[]=NETWORK_INTERFACES[i].mac_address;
				
				for(int A=0;A<6;A++)	// 
				{
					textArea_1.append(Integer.toHexString(R[A] & 0xff)+ ":");
				}
				NetworkInterfaceAddress []INT =NETWORK_INTERFACES[i].addresses;
				// there are n no of ip address of any particular network interface and we are calculating the info of one only
				textArea_1.append("\n IP Address : "+INT[0].address);
				//textArea_1.append("\n Subnet Mask  : "+INT[0].subnet);
				//textArea_1.append("\n Broadcast Address  : "+INT[0].broadcast+" \n");
				
				counter++;
		}
	}
}

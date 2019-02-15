/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class IPTracker extends javax.swing.JFrame {

    public IPTracker() {
    	setForeground(new Color(178, 34, 34));
    	setTitle("Ip Tracker");
        this.setResizable(true);
    	initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jTextField1.setBackground(new Color(255, 255, 240));
        jTextField1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
        jButton1 = new javax.swing.JButton();
        jButton1.setBackground(new Color(0, 0, 128));
        jButton1.setForeground(new Color(255, 255, 255));
        jButton1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextArea1.setBackground(new Color(255, 222, 173));
        jTextArea1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 18));
        jTextArea1.setForeground(new Color(0, 0, 128));
        jButton2 = new javax.swing.JButton();
        jButton2.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
        jButton2.setForeground(new Color(255, 255, 255));
        jButton2.setBackground(new Color(0, 0, 128));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextField1.setToolTipText("Enter ip address to track");

        jButton1.setText("Track");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("Enter Ip Address to search about an IP.");
        jScrollPane1.setViewportView(jTextArea1);

        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        
        btnKnowMyIp = new JButton("Know My Ip ");
        btnKnowMyIp.setBackground(new Color(0, 0, 128));
        btnKnowMyIp.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		btnKnowMyIpActionPerformed(e);
        	}
        });
        btnKnowMyIp.setForeground(new Color(255, 255, 255));
        btnKnowMyIp.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(30)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 773, GroupLayout.PREFERRED_SIZE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jTextField1, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
        					.addGap(44)
        					.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 99, Short.MAX_VALUE)
        					.addGap(214)
        					.addComponent(btnKnowMyIp)
        					.addGap(50)
        					.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        			.addGap(22))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jButton2)
        				.addComponent(jButton1)
        				.addComponent(btnKnowMyIp))
        			.addGap(31)
        			.addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 451, GroupLayout.PREFERRED_SIZE)
        			.addContainerGap(21, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    protected void btnKnowMyIpActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
    	this.setVisible(false);
    	new KnowMyIp().setVisible(true);
		
	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new SnifferWindow().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String ipaddress = jTextField1.getText();
        jTextArea1.setText("");
        jTextArea1.append(findData(ipaddress));
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
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
            java.util.logging.Logger.getLogger(IPTracker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IPTracker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IPTracker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IPTracker.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IPTracker().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
String result = "";
private JButton btnKnowMyIp;

    private String findData(String ipaddress) {
        StringBuilder sb = new StringBuilder();
        // abstract class to perform the lengthy GUI operations
        SwingWorker<Void, Void> sw = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    URL url = new URL("https://ipapi.co/" + ipaddress + "/json/");
                    HttpsURLConnection huc = (HttpsURLConnection) url.openConnection();
                    huc.setRequestMethod("GET");
                    huc.connect();
                    InputStream is = huc.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(is));
                    String temp = "";

                    while ((temp = br.readLine()) != null) {
                        sb.append(temp + "\n");
                    }

                } catch (Exception e) {

                }
                return null;
            }

            @Override
            protected void done() {
                try {
                    result = sb.toString();
                    
                    JSONParser parser = new JSONParser();

                    Object jsonObj = parser.parse(result);

                    JSONObject jsonObject = (JSONObject) jsonObj;
                    StringBuilder sb = new StringBuilder();
                    sb.append("\n\n\n");
                    sb.append("ip address: "+jsonObject.get("ip") + "\n");
                    sb.append("city: "+jsonObject.get("city") + "\n");
                    sb.append("Region: "+jsonObject.get("region") + "\n");
                    sb.append("Region code: "+jsonObject.get("region_code") + "\n");
                    sb.append("Country: "+jsonObject.get("country") + "\n");
                    sb.append("Country Name: "+jsonObject.get("country_name") + "\n");
                    sb.append("Continent Code: "+jsonObject.get("continent_code") + "\n");
                    sb.append("In EU: "+jsonObject.get("in_eu") + "\n");
                    sb.append("Postal: "+jsonObject.get("postal") + "\n");
                    sb.append("Lattitude: "+jsonObject.get("latitude") + "\n");
                    sb.append("Longitude: "+jsonObject.get("longitude") + "\n");
                    sb.append("Timezone: "+jsonObject.get("timezone") + "\n");
                    sb.append("UTC Offset: "+jsonObject.get("utc_offset") + "\n");
                    sb.append("Country calling code: "+jsonObject.get("country_calling_code") + "\n");
                    sb.append("Currency: "+jsonObject.get("currency") + "\n");
                    sb.append("Languages: "+jsonObject.get("languages") + "\n");
                    sb.append("ASN: "+jsonObject.get("asn") + "\n");
                    sb.append("Organization: "+jsonObject.get("org") + "\n");
                    jTextArea1.append(sb.toString());
                } catch (Exception e) {

                }
            }

        };
        sw.execute();
        return result;
    }
}

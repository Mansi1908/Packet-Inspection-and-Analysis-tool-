/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;

import sniffer.IPTracker;
import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import org.jfree.data.time.Millisecond;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JComboBox;


public class SnifferWindow extends javax.swing.JFrame implements ListSelectionListener {

   
    ListSelectionModel listSelectionModel;
    NetworkInterface[] list;
    JpcapCaptor captor;
    String str, info;
    int x, choice;

    public SnifferWindow() {
        initComponents();
        list = JpcapCaptor.getDeviceList();
        jButton1.setEnabled(false);
        DefaultListModel<String> l1 = new DefaultListModel<>();
        for (int x = 0; x < list.length; x++) {
            l1.add(x, (x + 1) + ". " + list[x].name+" ("+list[x].description+") "+"\n  "+list[x].datalink_name+ " "+list[x].datalink_description);
                    }

        jList1.setModel(l1);
        listSelectionModel = jList1.getSelectionModel();
        listSelectionModel.addListSelectionListener(this);

        try {
            Enumeration<java.net.NetworkInterface> nets = null;
            nets = java.net.NetworkInterface.getNetworkInterfaces();

            for (java.net.NetworkInterface netint : Collections.list(nets)) {

                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {

                    jLabel1.setText("currently connected:" + netint.getName() + "\n");

                }

            }
            String username = System.getProperty("user.name");
            String os = System.getProperty("os.name");
            jLabel2.setText("user: " + username + "    \t\t      operating on: " + os);

        } catch (SocketException ex) {
            Logger.getLogger(SnifferWindow.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jTextArea1.setForeground(new Color(0, 0, 128));
        jTextArea1.setBackground(new Color(255, 239, 213));
        jTextArea1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 13));
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jList1.setBackground(new Color(255, 255, 255));
        jList1.setForeground(new Color(0, 0, 128));
        jList1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 15));
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 16));
        jLabel1.setForeground(new Color(0, 0, 128));
        jButton1 = new javax.swing.JButton();
        jButton1.setBackground(new Color(0, 0, 139));
        jButton1.setForeground(Color.WHITE);
        jButton1.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
        jButton3 = new javax.swing.JButton();
        jButton3.setBackground(new Color(0, 0, 139));
        jButton3.setForeground(Color.WHITE);
        jButton3.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));
        jLabel2 = new javax.swing.JLabel();
        jLabel2.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 17));
        jLabel2.setForeground(new Color(0, 0, 128));
        jButton2 = new javax.swing.JButton();
        jButton2.setBackground(new Color(0, 0, 139));
        jButton2.setForeground(Color.WHITE);
        jButton2.setFont(new Font("Cambria", Font.BOLD | Font.ITALIC, 14));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        jLabel1.setText("jLabel1");

        jButton1.setText("VISUALIZE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setText("Filter");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel2.setText("jLabel2");

        jButton2.setText("IP TRACKER");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(37, 37, 37)
                        .addComponent(jButton2)
                        .addGap(110, 110, 110))
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1062, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3)
                            .addComponent(jButton2))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
       // System.exit(0);
    	this.setVisible(true);
    	new Windo().setVisible(true);
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        LineChart.main(new String[]{String.valueOf(mIndex)});


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:

        this.setVisible(false);
        new IPTracker().setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed
    boolean flipper = false;

    public void doWork(int mIndex) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (flipper) {

                        try {

                            captor = JpcapCaptor.openDevice(list[mIndex], 65535, true, 20);

                            captor.setFilter("ip and tcp", true);
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                        Packet info = captor.getPacket();

                        if (info != null) {
                            TCPPacket p = (TCPPacket) info;
                            jButton1.setEnabled(true);
                            LineChart.lastValue = (double) p.length;
                            jTextArea1.append("Source Ip: " + p.src_ip.getHostAddress()
                                    + "\t        Destination Ip: " + p.dst_ip.getHostAddress()
                                    + "  \t             Hop Limit: " + p.hop_limit
                                    + "\t         Packet Length: " + p.length
                                    + "\t              Protocol: " + p.protocol
                                    + "\t           Source Port: " + p.src_port
                                    + "\t      Destination Port: " + p.dst_port
                                    + "\n");

                        }

                        //output.append(newline);
                        isWorking = true;
                        jTextArea1.setCaretPosition(jTextArea1.getDocument().getLength());
                        jTextArea1.setLineWrap(true);

                    }

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }).start();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SnifferWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SnifferWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SnifferWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SnifferWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SnifferWindow().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
boolean isWorking = false;
    int mIndex = 0;
    /**
     * @wbp.nonvisual location=235,-31
     */
    private final JComboBox comboBox = new JComboBox();

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {

            String data = jList1.getSelectedValue();
            mIndex = jList1.getSelectedIndex();
            flipper = true;
            // jTextArea1.append(data + " selected from " + (mIndex + 1) + " position");
            doWork(mIndex);
        }
    }
}

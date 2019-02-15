/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sniffer;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;
import jpcap.JpcapCaptor;
import jpcap.NetworkInterface;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.time.TimeSeriesDataItem;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

public class LineChart extends ApplicationFrame implements ActionListener {

	// In TimeSeries class data is represented in the form of a period(day,date,year,month,time,hr,seconds)
    public static TimeSeries series;

    public static double lastValue = 0.0;

    public LineChart(final String title) {
        
        super(title);
        this.series = new TimeSeries("Packet Length", Millisecond.class);
        //This is a class used as a collection of time series objects.
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);//It constructs a data set containing a single series tied to a default time zone
        final JFreeChart chart = createChart(dataset);
        // a jfreechart will be crated for the values of dataset
        final ChartPanel chartPanel = new ChartPanel(chart);
        
       //  ChartPanel class is used for displaying jfree chart objects
        final JPanel content = new JPanel(new BorderLayout());
        content.add(chartPanel);
       
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
        
        setContentPane(content);
        
    }

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                "Time Vs Packet Length",
                "Time",
                "Packet Length",
                dataset,
                true,
                true,
                false
        );
        final XYPlot plot = result.getXYPlot();
        //ValueAxis : The base class for axes that display value data
        ValueAxis axis = plot.getDomainAxis();// Domain axis is the x axis : Returns the domain axis with index 0
        axis.setAutoRange(true);
        axis.setFixedAutoRange(60000.0);  // 60 seconds
        axis = plot.getRangeAxis();// range axis is the y axis
        axis.setRange(0.0, 2000.0);// maximum packet length can be 2000
        
        return result;
    }
    public void actionPerformed(final ActionEvent e) {
        
    }

    /**
     * Starting point for the demonstration application.
     */
    public static void main(String[] args) {
        
        final LineChart demo = new LineChart("Time Vs PacketLength");
        demo.pack();//The pack method sizes the frame so that all its contents are at or above their preferred sizes.
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
        
        demo.doWork(Integer.parseInt(args[0]));// this args[0] it is the value which is passed as argument when visualize button is been clicked
    }
    NetworkInterface[] list = JpcapCaptor.getDeviceList();
    ;
    JpcapCaptor captor;
    boolean flipper = false;
    
    public void doWork(int mIndex) {
        flipper = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                // here inside the rrun method while this thread is running everytime a new packet is recieved it is adding the packet length in the y axis so the graph can be easily plotted
            	while (true) {
                    if (flipper) {
                        
                        try {
                            
                            captor = JpcapCaptor.openDevice(list[mIndex], 65535, false, 20);
                            
                            captor.setFilter("ip and tcp", true);
                        } catch (IOException ioe) {
                            ioe.printStackTrace();
                        }
                        Packet info = captor.getPacket();
                        
                        if (info != null) {
                            TCPPacket p = (TCPPacket) info;
                            
                            lastValue = (double) p.length;
                            series.add(new TimeSeriesDataItem(new Millisecond(), lastValue));// last value 
                        }

                        //output.append(newline);
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
}

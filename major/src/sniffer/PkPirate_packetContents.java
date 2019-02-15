package sniffer;
import jpcap.PacketReceiver;
import jpcap.packet.*;
public class PkPirate_packetContents implements PacketReceiver {

	@Override
	public void receivePacket(Packet packet) {
		// TODO Auto-generated method stub
			Windo.textArea_1.append(packet.toString()+"\n ----------------------------------------------------------------------"+
		"----------------------------------------------------------------------\n\n");
	}

	
}

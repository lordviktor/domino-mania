/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Casa
 */
public class SenderOfReceiver implements Sender, Acknowledger {

	private final DatagramSocket receiverSocket;
	private InetAddress adress;
	private Packet[] packets;
	private int portToSend;

	public SenderOfReceiver(DatagramSocket socket, Packet[] packets,
			int portToSend) {
		receiverSocket = socket;
		this.packets = packets;
		this.portToSend = portToSend;
	}

	public void setPackets(Packet[] packets) {
		this.packets = packets;
	}

	@Override
	public void sendSmallest() {
		for (int i = 0; i < packets.length - 1; i++) {
			if (packets[i] != null & packets[i + 1] == null) {
				sendACK(packets[i]);
				return;
			}
		}
	}

	@Override
	public void sendCumulative(Packet packet1, Packet packet2) {
		sendACK(packet1);
		sendACK(packet2);
	}

	@Override
	public void sendACK(Packet packet) {
		DatagramPacket sendPacket = new DatagramPacket(packet.getBytes(),
				packet.getBytes().length, adress, portToSend);
		try {
			receiverSocket.send(sendPacket);
		} catch (IOException ex) {
			Logger.getLogger(SenderOfReceiver.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

	public void setPort(int port) {
		this.portToSend = port;
	}

	public void setIP(String addr) {
		try {
			adress = InetAddress.getByName(addr);
		} catch (UnknownHostException ex) {
			Logger.getLogger(SenderOfReceiver.class.getName()).log(
					Level.SEVERE, null, ex);
		}
	}

}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Casa
 */
public class UDPSenderImp implements UDPWelcomeSender {

	private ListOfPackets list;
	private Timer timer;
	private byte[] aux;
	private DatagramSocket senderSocket;
	private InetAddress adress;
	private int portToSend;
	private int portToListen;

	public UDPSenderImp(String host, int portToSend, int portToListen)
			throws SocketException, UnknownHostException {
		timer = new Timer(1000000000, this);
		this.portToSend = portToSend;
		senderSocket = new DatagramSocket(portToListen);
		adress = InetAddress.getByName(host);
	}

	public UDPSenderImp(String host, int portToListen) throws SocketException,
			UnknownHostException {
		timer = new Timer(1000000000, this);
		senderSocket = new DatagramSocket(portToListen);
		adress = InetAddress.getByName(host);
	}

	@Override
	public void send(final String message) {
		aux = message.getBytes();
		this.list = new ListOfPacketArray(aux.length / 1020
				+ (aux.length % 1020 == 0 ? 0 : 1));
		ListennerOfACK listener = new ListennerOfACKImp(timer, this, list,
				senderSocket);
		listener.startToListen();
		/*
		 * Thread filler = new Thread(new Runnable() {
		 * 
		 * @Override public void run() { list.fill(message); } });
		 * filler.start();
		 */
		list.fill(message);
		for (int i = 0; i < list.getSize(); i++) {
			if (!timer.isRunning()) {
				//
				timer.start();
			}
			sendPacket(list.getPacket(i));
			list.nextSeqNum();

		}
	}

	@Override
	public void setPortToSend(int port) {
		this.portToSend = port;
	}

	public void setPortToListen(int port) {
		this.portToListen = port;
	}

	@Override
	public void sendSmallest() {
		sendPacket(list.getSmallestSeqNum());
	}

	private void sendPacket(Packet packet) {
		System.out.println("Enviando " + packet.toString());
		DatagramPacket dataPacket = new DatagramPacket(packet.getBytes(),
				packet.getBytes().length, adress, portToSend);
		try {
			senderSocket.send(dataPacket);
		} catch (IOException ex) {
			System.out.println("Não conseguiu");
			;
		}
	}

	@Override
	public void send(int id) {
		sendPacket(list.getPacket(id));
	}

	@Override
	public void setIPtoSend(String ip) {
		try {
			this.adress = InetAddress.getByName(ip);
		} catch (UnknownHostException ex) {
			Logger.getLogger(UDPSenderImp.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}
}

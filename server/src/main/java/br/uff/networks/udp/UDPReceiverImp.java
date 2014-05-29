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
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Casa
 */
public class UDPReceiverImp implements UDPWelcomeReceiver {

	private Timer timer;
	private int expectedNum;
	private final LinkedList<Gap> gaps;
	private Packet[] list;
	private DealWithPacket dealer;
	private final DatagramSocket receiverSocket;
	private InetAddress adress;
	private final Acknowledger ack;
	private final int portToListen;
	private int portOfSender;

	@Override
	public int getPortOfSender() {
		return portOfSender;
	}

	public void setPortOfSender(int portOfSender) {
		this.portOfSender = portOfSender;
	}

	public void setIPOfSender(String addr) {
		try {
			adress = InetAddress.getByName(addr);
		} catch (UnknownHostException ex) {
			Logger.getLogger(UDPReceiverImp.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	public UDPReceiverImp(int portToListen) throws SocketException,
			UnknownHostException {
		gaps = new LinkedList<>();
		this.portToListen = portToListen;
		receiverSocket = new DatagramSocket(portToListen);
		ack = new SenderOfReceiver(receiverSocket, list, 0);
		dealer = new Beginning(timer, this, (SenderOfReceiver) ack);
		expectedNum = 0;
	}

	@Override
	public String receive() throws IOException {
		while (!isOver()) {
			// Ler Packet
			byte[] data = new byte[1024];
			DatagramPacket dataPacket = new DatagramPacket(data, data.length);
			receiverSocket.receive(dataPacket);
			// System.out.println(dataPacket.getLength());
			Packet packet = new Packet(dataPacket.getData());
			list = dealer.deal(packet, list, expectedNum, dataPacket.getPort(),
					dataPacket.getAddress().getHostName());
			if (list[expectedNum] != null) {
				expectedNum++;
			}
		}
		timer.stop();
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 1; i < list.length; i++) {
			stringBuilder.append(new String(list[i].getData()));
		}
		return stringBuilder.toString();
	}

	public void changeDealer() {
		dealer = new AfterFirtsPacket(timer, gaps, ack);
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public boolean isOver() {
		if (list == null)
			return false;
		for (Packet list1 : list) {
			if (list1 == null) {
				return false;
			}
		}
		return true;
	}

}

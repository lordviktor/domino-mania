/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * @author Casa
 */
public class ListennerOfACKImp implements ListennerOfACK {

	private Thread thread;
	private boolean permitToListen = true;
	private Timer timer;
	private UDPSender sender;
	private ListOfPackets list;
	private DatagramSocket socket;

	public ListennerOfACKImp(Timer timer, UDPSender sender, ListOfPackets list,
			DatagramSocket socket) {
		this.timer = timer;
		this.sender = sender;
		this.list = list;
		this.socket = socket;
	}

	@Override
	public void startToListen() {
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				// Ler packet
				while (isOver()) {
					byte[] data = new byte[1024];
					DatagramPacket dataPacket = new DatagramPacket(data,
							data.length);
					try {
						socket.receive(dataPacket);
						Packet packet = new Packet(dataPacket.getData());
						/*
						 * ler esse pacote de forma q se o pacote começa com 0
						 * então ele assinala a chegada do zero, já se começa
						 * com outro você os outros 4 bytes para ver se dão
						 * ACK de outro pacote
						 */
						if (packet.getInt() >= list.getSendBase()) {
							list.getPacket(packet.getInt()).acknowledge();
							list.setSendBase(packet.getInt());
							System.out.println("recebeu ACK de "
									+ packet.getInt());
							timer.stop();
							if (list.existNotACK()) {
								System.out.println("recebeu ACK de "
										+ packet.getInt()
										+ "mesmo depois de receber");
								timer.start();
							}
						} else {
							if (list.incDuplicateNum(packet.getInt())) {
								sender.send(packet.getInt());
							}
						}

					} catch (IOException ex) {
						Logger.getLogger(ListennerOfACKImp.class.getName())
								.log(Level.SEVERE, null, ex);
					}
				}
			}
		});
		thread.start();

	}

	@Override
	public void stopListenning() {
		permitToListen = false;
	}

	public boolean isOver() {
		return (list.getSendBase() != list.getSize() - 1);
	}
}

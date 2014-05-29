/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

import java.nio.ByteBuffer;

/**
 * 
 * @author Casa
 */
public class Beginning extends DealWithPacket {

	private UDPReceiverImp receiver;
	private SenderOfReceiver sender;

	public Beginning(Timer timer, UDPReceiverImp receiver, SenderOfReceiver ack) {
		super(timer, ack);
		this.receiver = receiver;
		this.sender = ack;
	}

	@Override
	public Packet[] deal(Packet packet, Packet[] received, int expected,
			int port, String addr) {
		if (packet.getInt() == expected) {
			int other = Auxiliar.getInt(packet.getData());
			int size = (int) Math.ceil(ByteBuffer.wrap(packet.getData())
					.getInt() / 1020.0) + 1;
			// int size2=(int)
			// Math.ceil(Auxiliar.getInt(packet.getData())/1020.0)+1;
			received = new Packet[size];
			received[expected] = packet;
			sender.setPackets(received);
			sender.setPort(port);
			sender.setIP(addr);
			receiver.setIPOfSender(addr);
			receiver.setPortOfSender(port);
			timer = new Timer(500000000, sender);
			receiver.setTimer(timer);
			timer.start();
			receiver.changeDealer();
		} else {
			ack.sendCumulative(packet, packet);
		}
		return received;
	}
}

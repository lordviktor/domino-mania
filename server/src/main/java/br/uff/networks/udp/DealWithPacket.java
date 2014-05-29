/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

/**
 * 
 * @author Casa
 */
public abstract class DealWithPacket {

	protected Timer timer;
	protected Acknowledger ack;

	public DealWithPacket(Timer timer, Acknowledger ack) {
		this.timer = timer;
		this.ack = ack;
	}

	public abstract Packet[] deal(Packet packet, Packet[] received,
			int expected, int port, String addr);
}

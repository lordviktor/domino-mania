/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

/**
 * 
 * @author Casa
 */
public interface Acknowledger {
	public void sendCumulative(Packet packet1, Packet packet2);

	public void sendACK(Packet packet);
}

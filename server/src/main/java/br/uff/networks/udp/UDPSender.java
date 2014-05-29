/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

/**
 * 
 * @author Casa
 */
public interface UDPSender extends Sender {
	public void send(String message);

	public void send(int id);
}

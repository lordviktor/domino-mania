/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

import java.io.IOException;

/**
 * 
 * @author Casa
 */
public interface UDPReceiver {
	public String receive() throws IOException;
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import br.uff.networks.Transport;

/**
 * 
 * @author Casa
 */
public class UDPTransport implements Transport {

	private UDPReceiver receiver;
	private UDPSender sender;
	private String hostName;
	private int portToListen, portToSend;
	private boolean open;

	public UDPTransport(String hostName, int portToListen, int portToSend) {
		this.hostName = hostName;
		this.portToListen = portToListen;
		this.portToSend = portToSend;
	}

	@Override
	public void open() {
		try {
			open = true;
			receiver = new UDPReceiverImp(portToListen);
			sender = new UDPSenderImp(hostName, portToSend, 0);
		} catch (SocketException ex) {
			Logger.getLogger(UDPTransport.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (UnknownHostException ex) {
			Logger.getLogger(UDPTransport.class.getName()).log(Level.SEVERE,
					null, ex);
		}
	}

	@Override
	public void close() {
		open = false;
	}

	@Override
	public String receive() {
		try {
			return receiver.receive();
		} catch (UnknownHostException ex) {
			Logger.getLogger(UDPTransport.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (SocketException ex) {
			Logger.getLogger(UDPTransport.class.getName()).log(Level.SEVERE,
					null, ex);
		} catch (IOException ex) {
			Logger.getLogger(UDPTransport.class.getName()).log(Level.SEVERE,
					null, ex);
		}
		return null;
	}

	@Override
	public void send(String message) {
		sender.send(message);
	}

	@Override
	public boolean isOpen() {
		return open;
	}
}

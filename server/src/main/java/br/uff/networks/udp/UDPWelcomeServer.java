package br.uff.networks.udp;

import java.io.IOException;
import java.net.DatagramSocket;

import br.uff.networks.Transport;
import br.uff.networks.WelcomeServer;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UDPWelcomeServer extends WelcomeServer {

	private UDPWelcomeReceiver receiver;
	private UDPWelcomeSender sender;

	public UDPWelcomeServer(int udpReceive, int udpListen) {
		try {
			receiver = new UDPReceiverImp(udpReceive);
			sender = new UDPSenderImp(null, udpListen);
		} catch (SocketException | UnknownHostException ex) {
			Logger.getLogger(UDPWelcomeServer.class.getName()).log(
					Level.SEVERE, null, ex);
		}

	}

	@Override
	public Transport acceptClient() throws IOException {
		String ip = receiver.receive();
		sender.setIPtoSend(ip);
		sender.setPortToSend(receiver.getPortOfSender());
		// sender.send("mensagem para trocar porta");
		System.out.println("enviou mensagem de troca");
		return new UDPTransport("endereço do client", 0, 0);
		/*
		 * determina qual porta ele vai passar a enviar e cria o receiver para
		 * ela.
		 */
	}
}

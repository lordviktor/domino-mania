package br.uff.networks.domino_mania;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import br.uff.networks.WelcomeServer;
import br.uff.networks.tcp.TCPWelcomeServer;
import br.uff.networks.udp.UDPTransport;
import br.uff.networks.udp.UDPWelcomeServer;

public class DominoManiaServerLauncher {

	public static void main(String[] args) throws IOException {
		args = new String[] { "55555", "55556" };

		int tcpPort = Integer.parseInt(args[0]);
		int udpPort = Integer.parseInt(args[1]);

		WelcomeServer tcpServer = new TCPWelcomeServer(tcpPort);
		//WelcomeServer udpServer = new UDPWelcomeServer(udpPort, udpPort);

		MatchStarter matchStarter = new MatchStarter();
		tcpServer.onNewClient(matchStarter);
		//udpServer.onNewClient(matchStarter);

		new Thread(tcpServer).start();
		// new Thread(udpServer).start();

		for (int i = 1; i <= 4; i++)
			new Thread(new FakeClientTCP("client " + i)).start();
	}

	private static class FakeClientTCP implements Runnable {

		private final String name;

		public FakeClientTCP(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			try {
				try (Socket client = new Socket("127.0.0.1", 55555)) {
					client.getOutputStream().write((name + "\n").getBytes());
					if (name.equals("client 1")) {
						BufferedReader in = new BufferedReader(
								new InputStreamReader(client.getInputStream()));
						System.out.println(in.readLine());
						System.out.println(in.readLine());
						System.out.println(in.readLine());
						System.out.println(in.readLine());
						System.out.println(in.readLine());
					}
				}
			} catch (IOException e) {
			}
		}
	}

	private static class FakeClientUDP implements Runnable {

		private final String name;

		public FakeClientUDP(String name) {
			this.name = name;
		}

		@Override
		public void run() {
			try (UDPTransport client = new UDPTransport("127.0.0.1", 55554,
					55556)) {
				client.open();
				client.send(name + "\n");
			}
		}
	}
}

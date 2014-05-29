package br.uff.networks.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

import br.uff.networks.Transport;

public class TCPTransport implements Transport {

	private final Socket socket;
	private Scanner input;
	private PrintStream output;

	public TCPTransport(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void open() throws IOException {
		input = new Scanner(socket.getInputStream());
		output = new PrintStream(socket.getOutputStream());
	}

	@Override
	public boolean isOpen() {
		return !socket.isClosed();
	}

	@Override
	public void send(String message) {
		output.println(message);
	}

	@Override
	public String receive(){
		return input.nextLine();
	}

	@Override
	public void close() throws IOException {
		socket.close();
		input.close();
		output.close();
	}
}

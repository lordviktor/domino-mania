/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

/**
 * 
 * @author Casa
 */
public class Packet {
	private byte[] data;
	private int id;
	private int duplicateNumber;
	private boolean acknowledged;

	public Packet(byte[] data, int id) {
		this.data = data;
		this.id = id;
		duplicateNumber = 0;
		acknowledged = false;
	}

	public Packet(byte[] data) {
		this.id = Auxiliar.getInt(data);
		this.data = new byte[1020];
		System.arraycopy(data, 4, this.data, 0, this.data.length);
	}

	@Override
	public String toString() {
		return id + " " + String.valueOf(data);
	}

	public void acknowledge() {
		acknowledged = true;
	}

	public boolean wasAcknowledged() {
		return acknowledged;
	}

	public byte[] getBytes() {
		byte[] resp = new byte[1024];
		System.arraycopy(data, 0, resp, 4, data.length);
		resp[3] = (byte) (id & 0xFF);
		resp[2] = (byte) ((id >> 8) & 0xFF);
		resp[1] = (byte) ((id >> 16) & 0xFF);
		resp[0] = (byte) ((id >> 24) & 0xFF);
		return resp;
	}

	public void incrementDuplicate() {
		duplicateNumber++;
	}

	public int getNumberDuplicate() {
		return duplicateNumber;
	}

	public int getInt() {
		return id;
	}

	public byte[] getData() {
		return data;
	}
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

/**
 * 
 * @author Casa
 */
public class ListOfPacketArray implements ListOfPackets {

	private Packet[] packets;
	private int sendBase;
	private int nextSeqNum;
	private int size;
	private int index;

	public ListOfPacketArray() {

	}

	public ListOfPacketArray(int size) {
		packets = new Packet[size + 1];
		sendBase = 0;
		nextSeqNum = 0;
		this.size = size + 1;
		index = 0;
	}

	@Override
	public synchronized void fill(String message) {
		int sizeOfMes = message.length();
		this.addPacket(new Packet(getBytes(sizeOfMes), index), index);
		index++;
		byte[] data = message.getBytes();
		byte[] dataPacket;
		int generalindex = 0;
		while (generalindex < data.length) {
			dataPacket = new byte[1020];
			int indexOfArray = 0;
			for (int i = generalindex; i < data.length && indexOfArray < 1020; i++) {
				dataPacket[indexOfArray] = data[i];
				indexOfArray++;
			}
			System.out.println();
			for (int i = indexOfArray; i < dataPacket.length; i++) {
				dataPacket[indexOfArray] = 0;
				indexOfArray++;
			}
			generalindex += indexOfArray;
			this.addPacket(new Packet(dataPacket, index), index);
			index++;
		}
	}

	public byte[] getBytes(int id) {
		byte[] resp = new byte[4];
		resp[3] = (byte) (id & 0xFF);
		resp[2] = (byte) ((id >> 8) & 0xFF);
		resp[1] = (byte) ((id >> 16) & 0xFF);
		resp[0] = (byte) ((id >> 24) & 0xFF);
		return resp;
	}

	@Override
	public int getSendBase() {
		return sendBase;
	}

	@Override
	public void nextSeqNum() {
		nextSeqNum++;
	}

	@Override
	public Packet getSmallestSeqNum() {
		for (int i = 0; i < sendBase; i++) {
			if (this.getPacket(i).getNumberDuplicate() == 0) {
				return this.getPacket(i);
			}
		}
		return this.getPacket(sendBase);
	}

	@Override
	public boolean existNotACK() {
		for (int i = 0; i < sendBase; i++) {
			if (!packets[i].wasAcknowledged()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean incDuplicateNum(int id) {
		packets[id].incrementDuplicate();
		if (packets[id].getNumberDuplicate() > 2)
			return true;
		return false;
	}

	@Override
	public Packet getPacket(int id) {
		while (index <= id) {
		}
		return packets[id];
	}

	@Override
	public boolean isOver() {
		return sendBase == size;
	}

	@Override
	public void addPacket(Packet packet, int index) {
		packets[index] = packet;
	}

	@Override
	public int getSize() {
		return size;
	}

	@Override
	public void setSendBase(int sendBase) {
		this.sendBase = sendBase;
	}

}

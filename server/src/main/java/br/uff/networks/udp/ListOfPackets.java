/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

/**
 * 
 * @author Casa
 */
public interface ListOfPackets {
	public int getSendBase();

	public void nextSeqNum();

	public Packet getSmallestSeqNum();

	public boolean existNotACK();

	public boolean incDuplicateNum(int id);

	public Packet getPacket(int id);

	public boolean isOver();

	public void addPacket(Packet packet, int index);

	public int getSize();

	public void fill(String message);

	public void setSendBase(int sendBase);
}

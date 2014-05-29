/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

import java.util.List;

/**
 * 
 * @author Casa
 */
public class AfterFirtsPacket extends DealWithPacket {

	private List<Gap> gaps;

	public AfterFirtsPacket(Timer timer, List<Gap> gaps, Acknowledger ack) {
		super(timer, ack);
		this.gaps = gaps;
	}

	@Override
	public Packet[] deal(Packet packet, Packet[] data, int expected, int port,
			String addr) {
		if (packet.getInt() == expected) {
			data[expected] = packet;
			if (timer.isRunning()) {
				System.out.println("duplicado " + data[expected - 1].getInt()
						+ " " + packet.getInt());
				timer.stop();
				System.out.println("depois do stop " + timer.isRunning());
				ack.sendCumulative(data[expected - 1], packet);
			} else {
				timer.start();
			}
		} else {
			boolean inseriu = false;
			for (Gap gap : gaps) {
				if (packet.getInt() >= gap.beginGap
						&& packet.getInt() <= gap.endGap) {
					inseriu = true;
					if (packet.getInt() == gap.beginGap) {
						gap.beginGap++;
					} else if (packet.getInt() == gap.endGap) {
						gap.endGap--;
					}
					if (gap.beginGap > gap.endGap)
						gaps.remove(gap);
					else {
						Gap newGap = new Gap(packet.getInt() + 1, gap.endGap);
						gaps.add(newGap);
						gap.endGap = packet.getInt();
					}
					// Envia ACK com id gap.beginGap;
					ack.sendACK(data[gap.beginGap]);
				}
			}
			if (!inseriu) {
				gaps.add(new Gap(expected, packet.getInt()));
				// Envia ACK com expected.
				ack.sendACK(data[expected]);
			}
		}
		return data;
	}
}

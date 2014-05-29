/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.uff.networks.udp;

/**
 * 
 * @author Casa
 */
public class Auxiliar {
	public synchronized static byte[] getBytes(char[] data, int id) {
		byte[] resp = new byte[1024];
		System.arraycopy(data, 0, resp, 0, data.length);
		resp[1023] = (byte) (id & 0xFF);
		resp[1022] = (byte) ((id >> 8) & 0xFF);
		resp[1021] = (byte) ((id >> 16) & 0xFF);
		resp[1020] = (byte) ((id >> 24) & 0xFF);
		return resp;
	}

	public synchronized static int getInt(byte[] data) {
		int resp = 0;
		int aux;
		for (int i = 3; i < data.length && i > -1; i--) {
			aux = data[i];
			resp += (aux << ((3 - i) * 8));
		}
		return resp;
	}
}

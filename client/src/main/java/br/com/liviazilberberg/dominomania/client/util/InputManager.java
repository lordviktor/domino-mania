package br.com.liviazilberberg.dominomania.client.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputManager {
	
	public void readInputForTime(int milis) {

		long end = System.currentTimeMillis() + milis;
		InputStreamReader fileInputStream = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(fileInputStream);
		try {
			while ((System.currentTimeMillis() < end)) {
				if (bufferedReader.ready()) {
					System.out.println(bufferedReader.readLine());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

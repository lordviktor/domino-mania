package br.com.liviazilberberg.dominomania.client.util;

import br.com.liviazilberberg.dominomania.client.objects.base.BaseObject;

public class ConsoleOutput {

	private String[] output;
	private Point size;

	public ConsoleOutput(Point size) {
		this.size = size;
		this.output = new String[size.getY()];
		for (int i = 0; i < size.getY(); i++) {
			output[i] = new String(new char[size.getX()]).replace('\0', ' ');
		}
	}

	public void write(BaseObject objectToDraw) {
		Point objectPosition = objectToDraw.getPosition();
		Point objectSize = objectToDraw.getSize();
		
		if(objectPosition.getX() > this.size.getX() - 1 ||
				objectPosition.getY() > this.size.getY() - 1) {
			return; //desenhando fora da tela para infinit x || y
		}
		
		if(objectPosition.getX() + objectSize.getX() < 0
			|| objectPosition.getY() + objectSize.getY() < 0) {
			return; // desenhando fora da tela para -infinit x || y
		}
		
		//loop para as linhas do desenho
		for (int i = 0; i < objectSize.getY(); i++) {
			int lineNumber = i + objectPosition.getY();
			if (lineNumber < 0 || lineNumber > size.getY() - 1) {
				continue; //desenho fora da area de desenho
			}
			
			String line = output[lineNumber];
			String result = new String(); // resultado a substituir a linha
			if(objectPosition.getX() > 0) {
				result += line.substring(0, objectPosition.getX());
			}
			
			if(objectPosition.getX() + objectSize.getX() - 1 > this.size.getX() -1 ) {
				result = result += objectToDraw.getTexture()[i].substring(0, size.getX() - objectPosition.getX());
			} else if(objectPosition.getX() < 0) {
				result = result += objectToDraw.getTexture()[i].substring(1 + objectSize.getX() - objectPosition.getX() * - 1, objectSize.getX());
				result = result += line.substring(objectSize.getX() - objectPosition.getX() * - 1, size.getX());
			} else {
				result = result += objectToDraw.getTexture()[i];
				int position = objectPosition.getX() + objectSize.getX();
				result += line.substring(position, size.getX());
			}
			output[lineNumber] = result;
		}
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (String line : output) {
			builder.append(line + "\n");
		}
		return builder.toString();
	}
}

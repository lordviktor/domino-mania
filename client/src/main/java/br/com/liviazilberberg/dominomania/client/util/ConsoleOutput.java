package br.com.liviazilberberg.dominomania.client.util;

public class ConsoleOutput {

    private String[] output;

    public ConsoleOutput(Point size) {
	this.output = new String[size.y];
	for (int i = 0; i < size.y; i++) {
	    output[i] = new String(new char[size.x]).replace('\0', ' ');
	}
    }

    public void write(Point position, String[] texture) {
	for (int i = 0; i < texture.length; i++) {
	    int lineNumber = i + position.y;
	    String line = output[lineNumber];
	    output[lineNumber] = line.substring(0, position.x) + texture[i]
		    + line.substring(position.x + texture[i].length(), line.length());
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

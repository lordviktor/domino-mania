package br.com.liviazilberberg.dominomania.client.objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertEquals;
import br.com.liviazilberberg.dominomania.client.objects.Label.TextAlign;
import br.com.liviazilberberg.dominomania.client.util.ConsoleOutput;
import br.com.liviazilberberg.dominomania.client.util.Point;

@RunWith(JUnit4.class)
public class BorderTest {

	@Test
	public void tryToConstructMinimunSquareBorder() {
		Border border = new Border(new Point(0, 0), new Point(3, 3));
		ConsoleOutput consoleOutput = new ConsoleOutput(new Point(3, 3));

		border.draw(consoleOutput);

		String expected = 	"╔═╗\n" + 
							"║ ║\n" + 
							"╚═╝\n";
		String actual = consoleOutput.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void tryToConstructAnotherBorder() {
		Border border = new Border(new Point(0, 0), new Point(5, 3));
		ConsoleOutput consoleOutput = new ConsoleOutput(new Point(6, 3));

		border.draw(consoleOutput);

		String expected = "╔═══╗ \n" 
						+ "║   ║ \n" 
						+ "╚═══╝ \n";
		String actual = consoleOutput.toString();

		assertEquals(expected, actual);
	}

	@Test
	public void tryToDrawTextInsideBorder(){
		Border border = new Border(new Point(0, 0), new Point(5, 3));
		Label label = new Label(new Point (1, 1), new Point(3,1), "Olá", TextAlign.CENTER);
		ConsoleOutput consoleOutput = new ConsoleOutput(new Point(6, 3));
		
		border.draw(consoleOutput);
		label.draw(consoleOutput);
		
		String expected = "╔═══╗ \n" 
						+ "║Olá║ \n" 
						+ "╚═══╝ \n";
		String actual = consoleOutput.toString();
		
		assertEquals(expected, actual);
	}
}

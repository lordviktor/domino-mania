package br.com.liviazilberberg.dominomania.client.objects;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import br.com.liviazilberberg.dominomania.client.util.ConsoleOutput;
import br.com.liviazilberberg.dominomania.client.util.Point;

@RunWith(JUnit4.class)
public class LabelTest {

	@Test
	public void tryToFormatTextLeft() {
		Label label = new Label(new Point(0, 0), new Point(15, 1), "Ola Mundo",
				Label.TextAlign.LEFT);

		ConsoleOutput output = new ConsoleOutput(new Point(17, 1));

		label.draw(output);

		String expected = "Ola Mundo        \n";
		String actual = output.toString();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void tryToFormatTextRight() {
		Label label = new Label(new Point(0, 0), new Point(15, 1), "Ola Mundo",
				Label.TextAlign.RIGTH);

		ConsoleOutput output = new ConsoleOutput(new Point(17, 1));

		label.draw(output);

		String expected = "      Ola Mundo  \n";
		String actual = output.toString();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void tryToFormatTextCenter() {
		Label label = new Label(new Point(0, 0), new Point(15, 1), "Ola Mundo",
				Label.TextAlign.CENTER);

		ConsoleOutput output = new ConsoleOutput(new Point(17, 1));

		label.draw(output);

		String expected = "   Ola Mundo     \n";
		String actual = output.toString();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void tryToFormatTruncatedText() {
		Label label = new Label(new Point(0, 0), new Point(6, 1), "Ola Mundo",
				Label.TextAlign.CENTER);

		ConsoleOutput output = new ConsoleOutput(new Point(17, 1));

		label.draw(output);

		String expected = "Ola Mu           \n";
		String actual = output.toString();
		Assert.assertEquals(expected, actual);
	}
}

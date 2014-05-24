package br.com.liviazilberberg.dominomania.client.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PointTest {

	@Test
	public void canConstructPointTestCase() {

		int x = 4;
		int y = 5;

		Point point = new Point(x, y);

		Assert.assertEquals(x, point.getX());
		Assert.assertEquals(y, point.getY());
	}
}

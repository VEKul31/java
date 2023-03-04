package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PointTests {

	@Test
	public void testMain() {
		Point p1 = new Point(11,2.3);
		Point	p2 = new Point(9, 0);
		Assert.assertEquals(p1.distance(p2), 3.047950130825634);
	}

	@Test
	public void testNegative() {
		Point p1 = new Point(0,-0.5);
		Point	p2 = new Point(0, 1.5);
		Assert.assertEquals(p1.distance(p2), 2.0 );
	}

	@Test
	public void testZero() {
		Point p1 = new Point(0,0);
		Point	p2 = new Point(0, 0);
		Assert.assertEquals(p1.distance(p2), 0.0);
	}



}

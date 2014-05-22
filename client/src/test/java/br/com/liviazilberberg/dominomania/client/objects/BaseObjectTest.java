package br.com.liviazilberberg.dominomania.client.objects;

import static org.junit.Assert.*;

import org.junit.Test;

import br.com.liviazilberberg.dominomania.client.util.ConsoleOutput;
import br.com.liviazilberberg.dominomania.client.util.Point;

public class BaseObjectTest {

    @Test
    public void tryToConstructWithPositionSizeAndTexture() {
	
	Point size = new Point(9, 3);
	Point position = new Point(0, 0);
	String[] texture = new String[]{
					"┌───┬───┐", 
					"│ 3 │ 3 │", 
					"└───┴───┘" 
					};
		
	BaseObject baseObject = new BaseObject(position, size, texture);
	
	assertEquals(position, baseObject.getPosition());
	assertEquals(size, baseObject.getSize());
	assertArrayEquals(texture, baseObject.getTexture());
    }
    
    @Test
    public void canDrawOnPositionZeroZero(){
	Point size = new Point(9, 3);
	Point position = new Point(0, 0);
	String[] texture = new String[]{
					"┌───┬───┐", 
					"│ 3 │ 3 │", 
					"└───┴───┘" 
					};
		
	BaseObject baseObject = new BaseObject(position, size, texture);
	
	ConsoleOutput consoleOutput = new ConsoleOutput(new Point(200, 3));
	baseObject.draw(consoleOutput);
	
	
	String spacement = new String(new char[191]).replace('\0', ' ');
	String expected = "┌───┬───┐" + spacement + "\n" + 
			  "│ 3 │ 3 │" + spacement + "\n" +  
			  "└───┴───┘" + spacement + "\n";
	
	assertEquals(expected, consoleOutput.toString());
    }
    
    @Test
    public void canDrawAnotherPosition(){
	Point size = new Point(9, 3);
	Point position = new Point(5, 0);
	String[] texture = new String[]{
					"┌───┬───┐", 
					"│ 3 │ 3 │", 
					"└───┴───┘" 
					};
		
	BaseObject baseObject = new BaseObject(position, size, texture);
	
	ConsoleOutput consoleOutput = new ConsoleOutput(new Point(200, 3));
	baseObject.draw(consoleOutput);
	
	
	String firstSpacement = new String(new char[5]).replace('\0', ' ');
	String secondSpacement = new String(new char[186]).replace('\0', ' ');
	String expected = firstSpacement + "┌───┬───┐" + secondSpacement + "\n" + 
		          firstSpacement + "│ 3 │ 3 │" + secondSpacement + "\n" +  
		          firstSpacement + "└───┴───┘" + secondSpacement + "\n";
	
	assertEquals(expected, consoleOutput.toString());
    }
    
    @Test
    public void canDrawTwoObjectsAtSameTime(){
	Point size = new Point(9, 3);
	Point position1 = new Point(5, 0);
	String[] texture = new String[]{
					"┌───┬───┐", 
					"│ 3 │ 3 │", 
					"└───┴───┘" 
					};
		
	BaseObject baseObject1 = new BaseObject(position1, size, texture);

	Point position2 = new Point(20, 0);
	BaseObject baseObject2 = new BaseObject(position1, size, texture);

	
	ConsoleOutput consoleOutput = new ConsoleOutput(new Point(200, 3));
	baseObject1.draw(consoleOutput);
	baseObject2.draw(consoleOutput);
	
	
	String firstSpacement = new String(new char[5]).replace('\0', ' ');
	String secondSpacement = new String(new char[186]).replace('\0', ' ');
	String expected = firstSpacement + "┌───┬───┐" + secondSpacement + "\n" + 
		          firstSpacement + "│ 3 │ 3 │" + secondSpacement + "\n" +  
		          firstSpacement + "└───┴───┘" + secondSpacement + "\n";
	
	assertEquals(expected, consoleOutput.toString());
    }

}

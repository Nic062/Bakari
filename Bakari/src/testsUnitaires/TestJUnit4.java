package testsUnitaires;

import entities.BoardGame;
import entities.Colour;
import entities.Pawn;
import entities.Player;
import game.Game;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

public class TestJUnit4 extends TestCase {
	
	public Game g;
	public BoardGame bg;
	public Player p1;
	public Player p2;
	public Pawn pa1;
	public Pawn pa2;
	public Pawn pa3;
	public Colour c1;
	public Colour c2;
	public List<String> authorizedPosTest = new LinkedList<String>();
	
	
	public void init(){
		g = new Game();
		p1 = new Player();
		p2 = new Player();
		c1 = Colour.GREEN;
		c2 = Colour.ORANGE;
		// g.takedCard = new Card(Colour.GREEN);
		pa1 = new Pawn(0, 0);
		pa2 = new Pawn(4, 1);
		pa3 = new Pawn(5,12);
	}		
	

	@Test
	public void testPossibility() {
		init();
		g.possibility(pa2);
		List<String> authorizedPosTest = new LinkedList<String>();
		authorizedPosTest.add("(5,1)");
		authorizedPosTest.add("(6,1)");
		authorizedPosTest.add("(4,2)");
		authorizedPosTest.add("(4,3)");
		authorizedPosTest.add("(4,4)");
		authorizedPosTest.add("(4,5)");
		assertEquals(g.authorizedPos, authorizedPosTest);	
	}

	@Test
	public void testMovePawnSucces() {
		init();
		assertTrue(g.movePawn(p1, pa1, 0, 0));
	}
	@Test
	public void testMovePawnFail() {
		init();
		assertFalse(g.movePawn(p1, pa1, 1, 1));
	}
	
	@Test
	public void testCheckWinSucess() {
		init();
		p1.addPawn(pa3);
		assertTrue(g.checkWin(p1, pa3));
	}
	public void testCheckWinFail() {
		init();
		p1.addPawn(pa2);
		assertFalse(g.checkWin(p1, pa2));
	}
	
	@Test
	public void testTakeCard() {
		init();
		// g.listCard.add(c1);
		// g.listCard.add(c2);
		g.takeCard();
		g.takeCard();
		// assertEquals(g.takeCard(), c1);
	}

	
	

}

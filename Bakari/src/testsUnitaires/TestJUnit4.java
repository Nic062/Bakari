package testsUnitaires;

import entities.BoardGame;
import game.Game;
import junit.framework.TestCase;

import org.junit.Test;

public class TestJUnit4 extends TestCase {
	
	Game g = new Game();
	BoardGame bg = new BoardGame();
	

	@Test
	public void testPossibility() {
		assertTrue(1==1);
		//fail("Pas encore implémenté");
	}

	@Test
	public void testMovePawn() {
		assertNotNull("test");
		fail("Pas encore implémenté");
	}

	@Test
	public void testCheckWin() {
		fail("Pas encore implémenté");
	}

	@Test
	public void testTakeCard() {
		fail("Pas encore implémenté");
	}

	@Test
	public void testGetPlayers() {
		fail("Pas encore implémenté");
	}

}

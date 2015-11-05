package testsUnitaires;

import entities.BoardGame;
import entities.Colour;
import entities.Pawn;
import entities.Player;
import game.Game;
import gui.frames.MainWindow;
import gui.frames.WelcomeWindow;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Ignore;
import org.junit.Test;

public class TestJUnit4
{

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

	public void init()
	{
		g = new Game();
		p1 = new Player();
		p2 = new Player();
		c1 = Colour.GREEN;
		c2 = Colour.ORANGE;
		pa1 = new Pawn(0, 0);
		pa2 = new Pawn(4, 1);
		pa3 = new Pawn(5, 12);
		g.setCurrentCard(c1);
	}

	@Test
	public void testPossibility()
	{
		init();
		g.possibility(pa1);
		List<String> authorizedPosTest = new LinkedList<String>();
		authorizedPosTest.add("(0,1)");
		assertEquals(g.authorizedPos, authorizedPosTest);
	}

	@Ignore // Car utilisation de addText fesant référence à de l'interface graphique, si addText commenté : test OK
    @Test
	public void testMovePawnSucces()
	{
		init();
		assertTrue(g.movePawn(p1, pa1, 0, 1));
	}

	@Test
	@Ignore // Car utilisation de addText fesant référence à de l'interface graphique, si addText commenté : test OK
	public void testMovePawnFail()
	{
		init();
		assertFalse(g.movePawn(p1, pa1, 0, 0));
	}

	@Test
	@Ignore // Car utilisation de méthodes fesant référence à de l'interface graphique, si addText commenté : test OK
	public void testCheckWinSucess()
	{
		init();
		p1.addPawn(pa3);
		p2.addPawn(pa2);
		assertTrue(g.checkWin(p1, pa3));
	}

	public void testCheckWinFail()
	{
		init();
		p1.addPawn(pa2);
		assertFalse(g.checkWin(p1, pa2));
	}

	@Test
	public void testTakeCard()
	{
		init();
		g.takeCard();
		assertEquals(g.listCard.size(),3); // 3 Car initialisation de 4 cartes puis suppression d'une car affecté à la variable currentCard
	}
}

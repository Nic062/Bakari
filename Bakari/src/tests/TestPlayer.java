package tests;

import entities.Player;

public class TestPlayer
{
	public static void main(String[] args)
	{
		Player p1 = new Player();
		System.out.println(p1.getNom());
		
		Player p2 = new Player();
		System.out.println(p2.getNom());
	}
}

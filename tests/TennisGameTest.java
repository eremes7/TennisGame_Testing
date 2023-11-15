import static org.junit.Assert.*;

import org.junit.Test;

public class TennisGameTest {

// Here is the format of the scores: "player1Score - player2Score"
// "love - love"
// "15 - 15"
// "30 - 30"
// "deuce"
// "15 - love", "love - 15"
// "30 - love", "love - 30"
// "40 - love", "love - 40"
// "30 - 15", "15 - 30"
// "40 - 15", "15 - 40"
// "player1 has advantage"
// "player2 has advantage"
// "player1 wins"
// "player2 wins"
	@Test
	public void testTennisGame_Start() {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("Initial score incorrect", "love - love", score);
	}

	@Test
	public void testTennisGame_EahcPlayerWin4Points_Score_Deuce() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();

		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		game.player1Scored();
		game.player2Scored();
		// Act
		String score = game.getScore();
		// Assert
		assertEquals("Tie score incorrect", "deuce", score);
	}

	@Test(expected = TennisGameException.class)
	public void testTennisGame_Player1WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		// Act
		// This statement should cause an exception
		game.player1Scored();
	}

	@Test(expected = TennisGameException.class)
	public void testTennisGame_Player2WinsPointAfterGameEnded_ResultsException() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		game.player2Scored();
		// Act
		// This statement should cause an exception
		game.player2Scored();
	}

	@Test
	public void testTennisGame_Pelaaja2Voittaa() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored(); // Pelaaja kaks etu

		game.player2Scored(); // Pelaaja kaks tulisi voittaa
		// Assert
		assertEquals("Pelaajan kaksi voitto väärin", "player2 wins", game.getScore());
	}

	@Test
	public void testTennisGame_PelaajienPisteetLasketaanOikein() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player2Scored();
		game.player2Scored(); // Pelaaja2 pisteet = 30

		game.player1Scored(); // Pelaaja1 pisteet = 15

		// Assert
		assertEquals("Pelaajien pisteet ilmoitetaan väärin", "15 - 30", game.getScore());
	}

	@Test
	public void testTennisGame_Pelaajan2pisteet40Pelaajan1pisteet0() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player2Scored();
		game.player2Scored();
		game.player2Scored(); // Pelaaja2 pisteet = 40

		// Assert
		assertEquals("Pelaajien pisteet ilmoitetaan väärin", "40 - love", game.getScore());
	}

	@Test
	public void testTennisGame_Pelaajan1Etu() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		// Assert
		assertEquals("Pelaajan yksi voitto kirjataan väärin", "player1 wins", game.getScore());
	}

	@Test
	public void testTennisGame_Pelaajan1etu() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act
		game.player1Scored();
		game.player1Scored();
		game.player1Scored();

		game.player2Scored();
		game.player2Scored();
		game.player2Scored();

		game.player1Scored();
		// Assert
		assertEquals("Pelaajan yksi etu ilmoitetaan väärin", "player1 has advantage", game.getScore());
	}

	@Test
	public void testTennisGame_Pelaajan2etu() throws TennisGameException {
		// Arrange
		TennisGame game = new TennisGame();
		// Act

		game.player2Scored();
		game.player2Scored();
		game.player2Scored(); // pelaajalla 2 on 40 pistettä

		game.player1Scored();
		game.player1Scored();
		game.player1Scored(); // pelaajalla 1 on 40 pistettä

		System.out.println(game.getScore());
		game.player2Scored(); // pelaajalla 2 on etu

		// Assert
		System.out.println(game.getScore());
		assertEquals("Pelaajan kaksi etu ilmoitetaan väärin", "player2 has advantage", game.getScore());
	}
}

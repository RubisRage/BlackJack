package model;

import model.cards.Ace;
import model.cards.Card;
import model.cards.PipCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BlackJackTest {

    Player p1, p2, p3, croupier;
    BlackJack bj;

    @BeforeEach
    void setUp(){
        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        p3 = new Player("Player 3");

        bj = new BlackJack(p1, p2, p3);
    }

    @Test
    void onePlayerHasBlackJackAndCroupierNotTest(){
        ArrayList<Card> player1Cards = new ArrayList<>(Arrays.asList(new PipCard(), new Ace()));
        ArrayList<Card> player2Cards = new ArrayList<>(Arrays.asList(new PipCard(), new Card(5),
                                        new Card(6)));
        ArrayList<Card> player3Cards = new ArrayList<>(Arrays.asList(new Card(3), new Card(6), new Ace(),
                                        new Card(3), new Ace(), new PipCard()));
        ArrayList<Card> croupierCards = new ArrayList<>(Arrays.asList(new Card(9), new Card(7)));
        ArrayList<Card> deck = new ArrayList<>(Arrays.asList(new Card(5), new Card(4), new PipCard(),
                                new Card(2)));

        List<Player> actual = Arrays.asList(bj.getWinners(croupierCards, deck, player1Cards, player2Cards, player3Cards));
        List<Player> expected = Arrays.asList(p1);

        assertTrue(actual.size() == expected.size()
                && expected.containsAll(actual)
                && actual.containsAll(expected));
    }

    @Test
    void twoPlayersHaveBetterScoreThanCroupierTest(){
        ArrayList<Card> player1Cards = new ArrayList<>(Arrays.asList(new PipCard(), new PipCard()));
        ArrayList<Card> player2Cards = new ArrayList<>(Arrays.asList(new PipCard(), new Card(2), new Card(6)));
        ArrayList<Card> player3Cards = new ArrayList<>(Arrays.asList(new Card(8), new Card(8), new Card(5)));
        ArrayList<Card> croupierCards = new ArrayList<>(Arrays.asList(new Card(5), new PipCard()));
        ArrayList<Card> deck = new ArrayList<>(Arrays.asList(new Ace(), new Card(3), new PipCard(), new Card(2)));

        List<Player> actual = Arrays.asList(bj.getWinners(croupierCards, deck, player1Cards, player2Cards, player3Cards));
        List<Player> expected = Arrays.asList(p1, p3);

        assertTrue(actual.size() == expected.size()
                && expected.containsAll(actual)
                && actual.containsAll(expected));
    }

    @Test
    void noPlayerWinsCroupierHasBlackJackTest(){
        ArrayList<Card> player1Cards = new ArrayList<>(Arrays.asList(new PipCard(), new Ace()));
        ArrayList<Card> player2Cards = new ArrayList<>(Arrays.asList(new PipCard(), new Card(2), new Card(6)));
        ArrayList<Card> player3Cards = new ArrayList<>(Arrays.asList(new Card(8), new Card(8), new Card(5)));
        ArrayList<Card> croupierCards = new ArrayList<>(Arrays.asList(new PipCard(), new Ace()));
        ArrayList<Card> deck = new ArrayList<>(Arrays.asList(new Ace(), new Card(3), new PipCard(), new Card(2)));

        List<Player> actual = Arrays.asList(bj.getWinners(croupierCards, deck, player1Cards, player2Cards, player3Cards));
        List<Player> expected = new ArrayList<>();

        assertTrue(actual.size() == expected.size()
                && expected.containsAll(actual)
                && actual.containsAll(expected));
    }

    @Test
    void noPlayerWinsCroupierHasBestScoreTest(){
        ArrayList<Card> player1Cards = new ArrayList<>(Arrays.asList(new Card(2), new Card(3)));
        ArrayList<Card> player2Cards = new ArrayList<>(Arrays.asList(new Card(7), new PipCard()));
        ArrayList<Card> player3Cards = new ArrayList<>(Arrays.asList(new PipCard(), new Card(6), new Card(5)));
        ArrayList<Card> croupierCards = new ArrayList<>(Arrays.asList(new Card(8), new Card(8)));
        ArrayList<Card> deck = new ArrayList<>(Arrays.asList(new Card(5), new PipCard(), new Card(2)));

        List<Player> actual = Arrays.asList(bj.getWinners(croupierCards, deck, player1Cards, player2Cards, player3Cards));
        List<Player> expected = new ArrayList<>();

        assertTrue(actual.size() == expected.size()
                && expected.containsAll(actual)
                && actual.containsAll(expected));
    }
}
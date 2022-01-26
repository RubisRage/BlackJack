package model;

import model.cards.Card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BlackJack {
    private final List<Player> players;

    public BlackJack(Player... players){
        if(players.length != 3) {
            throw new IllegalArgumentException(
                    String.format("The number of players must be 3: Number of players passed %d", players.length)
            );
        }

        this.players = Arrays.asList(players);
    }

    @SafeVarargs
    public final Player[] getWinners(ArrayList<Card> croupierCards, ArrayList<Card> deck, ArrayList<Card>... playersCards){
        if(playersCards.length != 3){
            throw new IllegalArgumentException(
                    String.format("The number of players must be 3: Number of players passed %d", playersCards.length)
            );
        }

        int croupierScore = computeScore(croupierCards);;

        while(croupierScore < 17 && deck.size() > 0) {
            croupierScore = computeScore(croupierCards);;
            croupierCards.add(deck.remove(0));
        }

        if(isBlackJack(croupierCards)){
            return new Player[]{};
        }

        List<Player> winners = new ArrayList<>();

        for(int i = 0; i < playersCards.length; i++){
            int playerScore = computeScore(playersCards[i]);
            if( playerScore <= 21 && playerScore > croupierScore  || isBlackJack(playersCards[i])){
                winners.add(players.get(i));
            }
        }

        return winners.toArray(Player[]::new);
    }

    private Boolean isBlackJack(List<Card> cards){
        return cards.size() == 2 && computeScore(cards) == 21;
    }

    private Integer computeScore(List<Card> cards){
        int score = 0, aces = 0;

        for(Card c : cards) {
            if(c.isAce()){
                aces++;
            }

            score += c.getValue();
        }

        while(score > 21 && aces > 0) {
            score -= 10;
            aces--;
        }

        return score;
    }
}
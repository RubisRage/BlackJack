package model.cards;

public class Card {
    private final Integer value;

    public Card(Integer value) {
        this.value = value;
    }

    public Integer getValue(){
        return value;
    }


    public Boolean isAce(){
        return false;
    }
}

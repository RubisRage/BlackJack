package model;

public class Player {
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Player){
            return this.name.equals(((Player) obj).name);
        }

        return false;
    }
}

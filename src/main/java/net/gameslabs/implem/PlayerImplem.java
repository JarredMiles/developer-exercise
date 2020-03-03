package net.gameslabs.implem;

import assignment.api.Item;
import net.gameslabs.api.Player;

import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class PlayerImplem implements Player {

    private String id;
    private String name;

    private List<Item> inventory = new ArrayList<>();

    private PlayerImplem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Item> getInventory() {
        return this.inventory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerImplem that = (PlayerImplem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(inventory, that.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, inventory);
    }

    @Override
    public String toString() {
        return "(" + id + ", " + name + ")";
    }

    private static int players;
    public static Player newPlayer(String name) {
        return new PlayerImplem("player-" + (++players), name);
    }
}

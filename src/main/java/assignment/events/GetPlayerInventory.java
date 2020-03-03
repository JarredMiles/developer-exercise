package assignment.events;

import assignment.api.Item;
import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;

import java.util.List;

public class GetPlayerInventory extends PlayerEvent {
    List<Item> inventory;

    public GetPlayerInventory(Player player) {
        super(player);
        this.inventory = player.getInventory();
    }

    public List<Item> getInventory() { return this.inventory; }
}

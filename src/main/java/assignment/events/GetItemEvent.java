package assignment.events;

import assignment.api.Item;
import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;

public class GetItemEvent extends PlayerEvent {
    private String itemId;

    public GetItemEvent(Player player, String itemId) {
        super(player);
        this.itemId = itemId;
    }

    public Item getItem() {
        for (Item item : getPlayer().getInventory()) {
            if (item.getId().equals(this.itemId)) {
                return item;
            }
        }
        return null;
    }
}

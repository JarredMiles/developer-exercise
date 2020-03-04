package assignment.events;

import assignment.api.Item;
import assignment.implem.ItemImplem;
import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;

import java.util.List;

public class GiveItemEvent extends PlayerEvent {
    private Item item;
    private List<Item> playerInventory;

    public GiveItemEvent(Player player, String itemId, String name, int amount) {
        super(player);
        this.item =  ItemImplem.newItem(itemId, name, amount);
        this.playerInventory = player.getInventory();
    }

    public void giveItem() {
        if (playerInventory.contains(item)) {
            int itemIndex = playerInventory.indexOf(item);
            Item existingItem = playerInventory.get(itemIndex);

            int newAmount = existingItem.getAmount() + item.getAmount();
            existingItem.setAmount(newAmount);
        } else {
            playerInventory.add(item);
        }
    }
}
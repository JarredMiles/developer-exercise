package assignment.events;

import assignment.api.Item;
import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;

import java.util.List;

public class RemoveItemEvent extends PlayerEvent {
    private Item item;
    private int amount = -1;

    public RemoveItemEvent(Player player, Item item) {
        super(player);
        this.item = item;
    }

    public RemoveItemEvent(Player player, Item item, int amount) {
        super(player);
        this.item = item;
        this.amount = amount;
    }

    public void removeItem() {
        if (amount > -1) {
            List<Item> playerInventory = getPlayer().getInventory();

            if (playerInventory.contains(item)) {
                int itemIndex = playerInventory.indexOf(item);
                Item existingItem = playerInventory.get(itemIndex);

                int newAmount = existingItem.getAmount() - this.amount;
                if (newAmount < 1) {
                    playerInventory.remove(itemIndex);
                }
                else {
                    existingItem.setAmount(newAmount);
                }
            }
        } else {
            getPlayer().getInventory().remove(this.item);
        }
    }
}

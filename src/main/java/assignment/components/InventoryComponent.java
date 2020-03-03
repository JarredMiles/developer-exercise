package assignment.components;

import assignment.api.Item;
import assignment.events.GetItemEvent;
import assignment.events.GiveItemEvent;
import assignment.events.RemoveItemEvent;
import net.gameslabs.api.Component;

public class InventoryComponent extends Component {

    @Override
    public void onLoad() {
        registerEvent(GiveItemEvent.class, this::onGivePlayerItem);
        registerEvent(GetItemEvent.class, this::onGetPlayerItem);
        registerEvent(RemoveItemEvent.class, this::onRemovePlayerItem);
    }

    private void onGivePlayerItem(GiveItemEvent event) { event.giveItem(); }

    private void onGetPlayerItem(GetItemEvent event) {
        Item item = event.getItem();

        // null means they did not have item in their inventory
        if (item == null)
            return;

        if (item.getId().equals("dragon-axe")) {
            // do a check if player has level to hold item
            // if they don't remove item from action bar and into inventory
            // ^ is beyond the scope of this assignment
        }
    }

    private void onRemovePlayerItem(RemoveItemEvent event) { event.removeItem(); }

    @Override
    public void onUnload() {
        // Do nothing
    }
}

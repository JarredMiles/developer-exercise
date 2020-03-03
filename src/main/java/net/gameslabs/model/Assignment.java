package net.gameslabs.model;

import assignment.api.Item;
import assignment.events.GetItemEvent;
import assignment.events.GetPlayerInventory;
import assignment.events.GiveItemEvent;
import assignment.events.RemoveItemEvent;
import net.gameslabs.api.Component;
import net.gameslabs.api.ComponentRegistry;
import net.gameslabs.api.Player;
import net.gameslabs.components.ChartComponent;
import net.gameslabs.events.GetPlayerLevel;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.implem.PlayerImplem;

import java.util.Arrays;
import java.util.List;

public class Assignment {

    protected final ComponentRegistry registry;
    private final Player mainPlayer;

    public Assignment(Component ... myComponentsToAdd) {
        registry = new ComponentRegistry();
        Arrays.asList(myComponentsToAdd).forEach(registry::registerComponent);
        registry.registerComponent(new ChartComponent());
        registry.load();
        mainPlayer = PlayerImplem.newPlayer("MyPlayer");
    }

    public final void run() {
        runXpEvents();

        runInventoryEvents();

        registry.unload();
    }

    private void runXpEvents() {
        registry.sendEvent(new GiveXpEvent(mainPlayer, Skill.CONSTRUCTION, 25));
        registry.sendEvent(new GiveXpEvent(mainPlayer, Skill.EXPLORATION, 25));

        log("Player level", mainPlayer, getPlayerSkillLevel(mainPlayer, Skill.CONSTRUCTION));

        runXpChecks();
    }

    private void runXpChecks() {
        if (getPlayerSkillLevel(mainPlayer, Skill.EXPLORATION) != 1) throw new AssignmentFailed("Exploration XP should be set to level 1");
        if (getPlayerSkillLevel(mainPlayer, Skill.CONSTRUCTION) != 2) throw new AssignmentFailed("Construction XP should be set to level 2");
    }

    private int getPlayerSkillLevel(Player player, Skill skill) {
        GetPlayerLevel getPlayerLevel = new GetPlayerLevel(player, skill);
        registry.sendEvent(getPlayerLevel);
        return getPlayerLevel.getLevel();
    }

    private void runInventoryEvents() {
        registry.sendEvent(new GiveItemEvent(mainPlayer, "dragon-axe", "Dragon Axe", 2));

        logInventory();

        Item playerItem = getPlayerItem(mainPlayer, "dragon-axe");

        // check
        runPlayerHasItemInInventoryCheck();

        if (playerItem != null) {
            log("Remove 1 dragon-axe from player");
            registry.sendEvent(new RemoveItemEvent(mainPlayer, playerItem, 1));
        }

        // check
        runPlayerHasRemovedXItemsRemainingInInventoryCheck();
        logInventory();

        // remove any remaining items
        if (playerItem != null) {
            log("Remove all remaining dragon-axe from player");
            registry.sendEvent(new RemoveItemEvent(mainPlayer, playerItem));
        }

        // check
        runPlayerHasRemovedItemFromInventoryCheck();
        logInventory();
    }

    private void runPlayerHasItemInInventoryCheck() {
        Item playerItem = getPlayerItem(mainPlayer, "dragon-axe");
        if (playerItem == null) throw new AssignmentFailed("Player should have a dragon-axe in inventory");
    }

    private void runPlayerHasRemovedXItemsRemainingInInventoryCheck() {
        Item playerItem = getPlayerItem(mainPlayer, "dragon-axe");
        if (playerItem == null ) throw new AssignmentFailed("Player should should still have a dragon-axe in inventory");
        if (playerItem.getAmount() >= 2) throw new AssignmentFailed("Player should should only have 1 dragon-axe in inventory");
    }

    private void runPlayerHasRemovedItemFromInventoryCheck() {
        Item playerItem = getPlayerItem(mainPlayer, "dragon-axe");
        if (playerItem != null) throw new AssignmentFailed("Player should not have a dragon-axe in inventory");
    }

    private Item getPlayerItem(Player player, String itemId) {
        GetItemEvent getItemEvent = new GetItemEvent(player, itemId);
        registry.sendEvent(getItemEvent);
        return getItemEvent.getItem();
    }

    private List<Item> getPlayerInventory(Player player) {
        GetPlayerInventory getPlayerInventory = new GetPlayerInventory(mainPlayer);
        registry.sendEvent(getPlayerInventory);
        return getPlayerInventory.getInventory();
    }

    public void log(Object ... arguments) {
        System.out.println(Arrays.asList(arguments).toString());
    }

    public void logInventory() {
        log("Player Inventory:");
        for (Item item : getPlayerInventory(mainPlayer)) {
            log("Player item", item.toString());
        }
    }
}

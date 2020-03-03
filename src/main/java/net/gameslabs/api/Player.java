package net.gameslabs.api;

import assignment.api.Item;

import java.util.List;

public interface Player {
    String getId();
    String getName();
    List<Item> getInventory();
}

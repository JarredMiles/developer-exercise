package assignment.events;

import assignment.model.Ores;
import net.gameslabs.api.Player;
import net.gameslabs.api.PlayerEvent;

public class PlayerMiningEvent extends PlayerEvent {
    private Ores oreType;
    private int miningLevel;
    private int xpEarned;
    private boolean ableToMineOre;

    public PlayerMiningEvent(Player player, Ores oreType, int playerMiningLevel) {
        super(player);
        this.oreType = oreType;
        this.miningLevel = playerMiningLevel;
    }

    public Ores getOreType() { return this.oreType; }

    public int getMiningLevel() { return this.miningLevel; }

    public int getXpEarned() { return this.xpEarned; }
    public void setXpEarned(int xpEarned) { this.xpEarned = xpEarned; }

    public boolean isAbleToMineOre() { return ableToMineOre; }
    public void setAbleToMineOre(boolean ableToMineOre) { this.ableToMineOre = ableToMineOre; }
}

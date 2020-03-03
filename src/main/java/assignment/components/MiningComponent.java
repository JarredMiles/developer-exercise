package assignment.components;

import assignment.events.PlayerMiningEvent;
import assignment.model.Ores;
import net.gameslabs.api.Component;

public class MiningComponent extends Component {

    @Override
    public void onLoad() {
        registerEvent(PlayerMiningEvent.class, this::onPlayerMining);
    }

    private void onPlayerMining(PlayerMiningEvent event) {
        Ores oreType = event.getOreType();
        if (oreType == Ores.COPPER) {

            event.setAbleToMineOre(true);
            event.setXpEarned(50);

        } else if (oreType == Ores.COAL) {

            if (event.getMiningLevel() >= 5) {
                event.setAbleToMineOre(true);
                event.setXpEarned(150);
            } else {
                event.setAbleToMineOre(false);
                event.setCancelled(true);
            }

        } else {
            // if we reach here that means the ore type is not implemented yet
            event.setCancelled(true);
        }
    }

    @Override
    public void onUnload() {

    }
}

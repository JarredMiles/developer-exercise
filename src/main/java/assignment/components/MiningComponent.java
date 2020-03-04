package assignment.components;

import assignment.events.PlayerMiningEvent;
import assignment.model.Ores;
import net.gameslabs.api.Component;

import java.util.Random;

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

            roleForGem(event, 10);
        } else if (oreType == Ores.COAL) {

            if (event.getMiningLevel() >= 5) {
                event.setAbleToMineOre(true);
                event.setXpEarned(150);

                roleForGem(event, 25);
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

    private void roleForGem(PlayerMiningEvent event, int chance) {
        Random rng = new Random();

        int rngNumber = rng.nextInt(100) + 1;
        if (rngNumber <= chance) {
            event.setShouldGiveGem(true);
        } else {
            event.setShouldGiveGem(false);
        }
    }
}

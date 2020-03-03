package assignment.components;

import net.gameslabs.api.Component;
import net.gameslabs.events.GiveXpEvent;
import net.gameslabs.model.Skill;

public class MyXPBoosterComponent extends Component {

    @Override
    public void onLoad() {
        registerEvent(GiveXpEvent.class, this::onGiveXP);
    }

    private void onGiveXP(GiveXpEvent event) {
        Skill skill = event.getSkill();
        if (skill == Skill.CONSTRUCTION)
        {
            int doubleXpFromEvent = event.getXp() * 2;
            event.setXp(doubleXpFromEvent);
        }
    }

    @Override
    public void onUnload() {
        // Do nothing
    }
}

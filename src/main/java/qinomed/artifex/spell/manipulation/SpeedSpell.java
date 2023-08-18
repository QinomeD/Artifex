package qinomed.artifex.spell.manipulation;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import qinomed.artifex.spell.Spell;

public class SpeedSpell extends Spell {
    public SpeedSpell(Category category, int cost, int cooldown) {
        super(category, cost, cooldown);
    }

    @Override
    public boolean onCast(Player caster) {
        boolean success = super.onCast(caster);
        if (success) {
            caster.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 0, false, false));
        }
        return success;
    }
}

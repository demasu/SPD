package com.demasu.pixeldungeonskills.actors.skills;


import com.demasu.noosa.tweeners.AlphaTweener;
import com.demasu.pixeldungeonskills.Dungeon;
import com.demasu.pixeldungeonskills.actors.Actor;
import com.demasu.pixeldungeonskills.actors.hero.Hero;
import com.demasu.pixeldungeonskills.actors.hero.Legend;
import com.demasu.pixeldungeonskills.actors.mobs.npcs.SummonedPet;
import com.demasu.pixeldungeonskills.effects.Pushing;
import com.demasu.pixeldungeonskills.items.wands.WandOfMagicCasting;
import com.demasu.pixeldungeonskills.levels.Level;
import com.demasu.pixeldungeonskills.scenes.GameScene;
import com.demasu.pixeldungeonskills.scenes.MissionScene;
import com.demasu.pixeldungeonskills.ui.StatusPane;
import com.demasu.utils.Random;

import java.util.ArrayList;

/**
 * Created by Moussa on 20-Jan-17.
 */
public class DarkBolt extends ActiveSkill3{


    {
        name = "Dark Bolt";
        castText = "I must";
        tier = 3;
        image = 113;
        mana = 5;
    }


    @Override
    public float getAlpha()
    {
        return 1f;
    }

    @Override
    public ArrayList<String> actions( Hero hero ) {
        ArrayList<String> actions = new ArrayList<String>();
        if(level > 0 && hero.MP >= getManaCost())
            actions.add(AC_CAST);
        return actions;
    }

    @Override
    public void execute( Hero hero, String action ) {
        if(action == Skill.AC_CAST && hero.MP >= getManaCost())
        {
            //hero.MP -= getManaCost();
            //castTextYell();
            Legend.haxWand.castSpell(WandOfMagicCasting.CAST_TYPES.DARK_BOLT);
            Dungeon.hero.heroSkills.lastUsed = this;
        }
    }

    @Override
    public int getManaCost()
    {
        return (int)Math.ceil(mana * (1 + 0.25 * level));
    }

    @Override
    protected boolean upgrade()
    {
        return true;
    }


    @Override
    public String info()
    {
        return "Hurls concentrated dark energy into a target shredding their essence to pieces.\n What's left is a fate worse than death.\n"

                + costUpgradeInfo();
    }

}
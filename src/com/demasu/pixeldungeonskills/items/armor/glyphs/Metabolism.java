/*
 * Pixel Dungeon
 * Copyright (C) 2012-2015 Oleg Dolya
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>
 */
package com.demasu.pixeldungeonskills.items.armor.glyphs;

import com.demasu.pixeldungeonskills.actors.Char;
import com.demasu.pixeldungeonskills.actors.buffs.Hunger;
import com.demasu.pixeldungeonskills.effects.Speck;
import com.demasu.pixeldungeonskills.items.armor.Armor;
import com.demasu.pixeldungeonskills.items.armor.Armor.Glyph;
import com.demasu.pixeldungeonskills.sprites.CharSprite;
import com.demasu.pixeldungeonskills.sprites.ItemSprite;
import com.demasu.pixeldungeonskills.sprites.ItemSprite.Glowing;
import com.demasu.pixeldungeonskills.ui.BuffIndicator;
import com.demasu.utils.Random;

public class Metabolism extends Glyph {

	private static final String TXT_METABOLISM	= "%s of metabolism";
	
	private static ItemSprite.Glowing RED = new ItemSprite.Glowing( 0xCC0000 );
	
	@Override
	public int proc( Armor armor, Char attacker, Char defender, int damage) {

		int level = Math.max( 0, armor.effectiveLevel() );
		if (Random.Int( level / 2 + 5 ) >= 4) {
			
			int healing = Math.min( defender.HT - defender.HP, Random.Int( 1, defender.HT / 5 ) );
			if (healing > 0) {
				
				Hunger hunger = defender.buff( Hunger.class );
				
				if (hunger != null && !hunger.isStarving()) {
					
					hunger.satisfy( -Hunger.STARVING / 10 );
					BuffIndicator.refreshHero();
					
					defender.HP += healing;
					defender.sprite.emitter().burst( Speck.factory( Speck.HEALING ), 1 );
					defender.sprite.showStatus( CharSprite.POSITIVE, Integer.toString( healing ) );
				}
			}

		}
		
		return damage;
	}
	
	@Override
	public String name( String weaponName) {
		return String.format( TXT_METABOLISM, weaponName );
	}

	@Override
	public Glowing glowing() {
		return RED;
	}
}
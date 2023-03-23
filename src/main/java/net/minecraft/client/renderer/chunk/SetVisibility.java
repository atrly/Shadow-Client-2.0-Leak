package net.minecraft.client.renderer.chunk;

import java.util.BitSet;
import java.util.Iterator;
import java.util.Set;

import net.minecraft.util.EnumFacing;

/**+
 * This portion of EaglercraftX contains deobfuscated Minecraft 1.8 source code.
 * 
 * Minecraft 1.8.8 bytecode is (c) 2015 Mojang AB. "Do not distribute!"
 * Mod Coder Pack v9.18 deobfuscation configs are (c) Copyright by the MCP Team
 * 
 * EaglercraftX 1.8 patch files are (c) 2022-2023 LAX1DUDE. All Rights Reserved.
 * 
 * WITH THE EXCEPTION OF PATCH FILES, MINIFIED JAVASCRIPT, AND ALL FILES
 * NORMALLY FOUND IN AN UNMODIFIED MINECRAFT RESOURCE PACK, YOU ARE NOT ALLOWED
 * TO SHARE, DISTRIBUTE, OR REPURPOSE ANY FILE USED BY OR PRODUCED BY THE
 * SOFTWARE IN THIS REPOSITORY WITHOUT PRIOR PERMISSION FROM THE PROJECT AUTHOR.
 * 
 * NOT FOR COMMERCIAL OR MALICIOUS USE
 * 
 * (please read the 'LICENSE' file this repo's root directory for more info) 
 * 
 */
public class SetVisibility {

	private static final int COUNT_FACES = EnumFacing.values().length;
	private final BitSet bitSet;

	public SetVisibility() {

		this.bitSet = new BitSet(COUNT_FACES * COUNT_FACES);
	}

	public void setManyVisible(Set<EnumFacing> faces) {

		Iterator<EnumFacing> iterator = faces.iterator();

		while (iterator.hasNext()) {
			EnumFacing enumfacing = iterator.next();
			Iterator<EnumFacing> iterator1 = faces.iterator();

			while (iterator1.hasNext()) {
				EnumFacing enumfacing1 = iterator1.next();
				setVisible(enumfacing, enumfacing1, true);
			}
		}
	}

	public void setVisible(EnumFacing from, EnumFacing to, boolean visible) {

		bitSet.set(from.ordinal() + to.ordinal() * COUNT_FACES, visible);
		bitSet.set(to.ordinal() + from.ordinal() * COUNT_FACES, visible);
	}

	public void setAllVisible(boolean visible) {

		bitSet.set(0, bitSet.size(), visible);
	}

	public boolean isAllVisible(boolean visible) {

		int i = visible ? bitSet.nextClearBit(0) : bitSet.nextSetBit(0);
		return i < 0 || i >= (COUNT_FACES * COUNT_FACES);
	}

	public boolean isVisible(EnumFacing from, EnumFacing to) {

		return bitSet.get(from.ordinal() + to.ordinal() * COUNT_FACES);
	}

	@Override
	public boolean equals(Object o) {

		if (o instanceof SetVisibility) {
			return ((SetVisibility) o).bitSet.equals(bitSet);
		}
		return false;
	}

	@Override
	public int hashCode() {

		return bitSet.hashCode();
	}

	@Override
	public SetVisibility clone() {

		SetVisibility r = new SetVisibility();
		r.bitSet.or(bitSet);
		return r;
	}

	@Override
	public String toString() {

		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append(' ');
		EnumFacing[] aenumfacing = EnumFacing.values();
		int i = aenumfacing.length;
		int j;
		EnumFacing enumfacing;

		//for (j = 0; j < i; ++j) {
		for (j = i; --j >= 0;) {
			enumfacing = aenumfacing[j];
			stringbuilder.append(' ').append(enumfacing.toString().toUpperCase().charAt(0));
		}

		stringbuilder.append('\n');
		aenumfacing = EnumFacing.values();
		i = aenumfacing.length;

		//for (j = 0; j < i; ++j) {
		for (j = i; --j >= 0;) {
			enumfacing = aenumfacing[j];
			stringbuilder.append(enumfacing.toString().toUpperCase().charAt(0));
			EnumFacing[] aenumfacing1 = EnumFacing.values();
			int k = aenumfacing1.length;

			//for (int l = 0; l < k; ++l) {
			for (int l = k; --l >= 0;) {
				EnumFacing enumfacing1 = aenumfacing1[l];

				if (enumfacing == enumfacing1) {
					stringbuilder.append("  ");
				} else {
					boolean flag = this.isVisible(enumfacing, enumfacing1);
					stringbuilder.append(' ').append(flag ? 'Y' : 'n');
				}
			}

			stringbuilder.append('\n');
		}

		return stringbuilder.toString();
	}

}
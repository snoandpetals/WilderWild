/*
 * Copyright 2023-2024 FrozenBlock
 * This file is part of Wilder Wild.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, see <https://www.gnu.org/licenses/>.
 */

package net.frozenblock.wilderwild.block.entity;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.frozenblock.wilderwild.entity.ai.TermiteManager;
import net.frozenblock.wilderwild.misc.client.ClientMethodInteractionHandler;
import net.frozenblock.wilderwild.registry.RegisterBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

public class TermiteMoundBlockEntity extends BlockEntity {

	public final TermiteManager termiteManager;
	public final IntArrayList clientTermiteIDs = new IntArrayList();
	public final IntArrayList prevClientTermiteIDs = new IntArrayList();

	public TermiteMoundBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		super(RegisterBlockEntities.TERMITE_MOUND, pos, state);
		this.termiteManager = new TermiteManager();
	}

	public void tickServer(@NotNull Level level, @NotNull BlockPos pos, boolean natural, boolean awake, boolean canSpawn) {
		this.termiteManager.tick(level, pos, natural, awake, canSpawn);
		this.setChanged();
	}

	public void tickClient() {
		for (TermiteManager.Termite termite : this.termiteManager.termites()) {
			int termiteID = termite.getID();
			if (clientTermiteIDs.contains(termiteID) && !this.prevClientTermiteIDs.contains(termiteID)) {
				ClientMethodInteractionHandler.addTermiteSound(this, termiteID, termite.getEating());
			}
		}
		this.prevClientTermiteIDs.clear();
		this.prevClientTermiteIDs.addAll(this.clientTermiteIDs);
		this.clientTermiteIDs.clear();
		for (TermiteManager.Termite termite : this.termiteManager.termites()) {
			this.clientTermiteIDs.add(termite.getID());
		}
	}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}

	@Override
	@NotNull
	public CompoundTag getUpdateTag() {
		return this.saveWithoutMetadata();
	}

	@Override
	protected void saveAdditional(@NotNull CompoundTag tag) {
		super.saveAdditional(tag);
		this.termiteManager.saveAdditional(tag);
	}

	@Override
	public void load(@NotNull CompoundTag tag) {
		super.load(tag);
		this.termiteManager.load(tag);
	}
}

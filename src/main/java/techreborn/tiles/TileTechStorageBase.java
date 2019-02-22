/*
 * This file is part of TechReborn, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2018 TechReborn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package techreborn.tiles;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.items.CapabilityItemHandler;
import reborncore.api.IListInfoProvider;
import reborncore.api.IToolDrop;
import reborncore.api.tile.ItemHandlerProvider;
import reborncore.common.tile.TileMachineBase;
import reborncore.common.util.Inventory;
import reborncore.common.util.ItemUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class TileTechStorageBase extends TileMachineBase
		implements ItemHandlerProvider, IToolDrop, IListInfoProvider {

	public final int maxCapacity;
	public final Inventory<TileTechStorageBase> inventory;
	public ItemStack storedItem;

	public TileTechStorageBase(TileEntityType<?> tileEntityTypeIn, String name, int maxCapacity) {
		super(tileEntityTypeIn);
		this.maxCapacity = maxCapacity;
		storedItem = ItemStack.EMPTY;
		inventory = new Inventory<>(3, name, maxCapacity, this).withConfiguredAccess();
	}

	public void readWithoutCoords(NBTTagCompound tagCompound) {

		storedItem = ItemStack.EMPTY;

		if (tagCompound.contains("storedStack")) {
			storedItem = ItemStack.read(tagCompound.getCompound("storedStack"));
		}

		if (!storedItem.isEmpty()) {
			storedItem.setCount(Math.min(tagCompound.getInt("storedQuantity"), this.maxCapacity));
		}

		inventory.read(tagCompound);
	}

	public NBTTagCompound writeWithoutCoords(NBTTagCompound tagCompound) {
		if (!storedItem.isEmpty()) {
			ItemStack temp = storedItem.copy();
			if (storedItem.getCount() > storedItem.getMaxStackSize()) {
				temp.setCount(storedItem.getMaxStackSize());
			}
			tagCompound.put("storedStack", temp.write(new NBTTagCompound()));
			tagCompound.putInt("storedQuantity", Math.min(storedItem.getCount(), maxCapacity));
		} else {
			tagCompound.putInt("storedQuantity", 0);
		}
		inventory.write(tagCompound);
		return tagCompound;
	}

	public ItemStack getDropWithNBT() {
		NBTTagCompound tileEntity = new NBTTagCompound();
		ItemStack dropStack = new ItemStack(getBlockType(), 1);
		writeWithoutCoords(tileEntity);
		dropStack.setTag(new NBTTagCompound());
		dropStack.getTag().put("tileEntity", tileEntity);
		storedItem.setCount(0);
		inventory.setStackInSlot(1, ItemStack.EMPTY);
		syncWithAll();

		return dropStack;
	}

	public int getStoredCount() {
		return storedItem.getCount();
	}

	public List<ItemStack> getContentDrops() {
		ArrayList<ItemStack> stacks = new ArrayList<>();

		if (!getStoredItemType().isEmpty()) {
			if (!inventory.getStackInSlot(1).isEmpty()) {
				stacks.add(inventory.getStackInSlot(1));
			}
			int size = storedItem.getMaxStackSize();
			for (int i = 0; i < getStoredCount() / size; i++) {
				ItemStack droped = storedItem.copy();
				droped.setCount(size);
				stacks.add(droped);
			}
			if (getStoredCount() % size != 0) {
				ItemStack droped = storedItem.copy();
				droped.setCount(getStoredCount() % size);
				stacks.add(droped);
			}
		}

		return stacks;
	}

	// TileMachineBase
	@Override
	public void tick() {
		super.tick();
		if (!world.isRemote) {
			ItemStack outputStack = ItemStack.EMPTY;
			if (!inventory.getStackInSlot(1).isEmpty()) {
				outputStack = inventory.getStackInSlot(1);
			}
			if (!inventory.getStackInSlot(0).isEmpty()
					&& (storedItem.getCount() + outputStack.getCount()) < maxCapacity) {
				ItemStack inputStack = inventory.getStackInSlot(0);
				if (getStoredItemType().isEmpty()
						|| (storedItem.isEmpty() && ItemUtils.isItemEqual(inputStack, outputStack, true, true))) {

					storedItem = inputStack;
					inventory.setStackInSlot(0, ItemStack.EMPTY);
				} else if (ItemUtils.isItemEqual(getStoredItemType(), inputStack, true, true)) {
					int reminder = maxCapacity - storedItem.getCount() - outputStack.getCount();
					if (inputStack.getCount() <= reminder) {
						setStoredItemCount(inputStack.getCount());
						inventory.setStackInSlot(0, ItemStack.EMPTY);
					} else {
						setStoredItemCount(maxCapacity - outputStack.getCount());
						inventory.getStackInSlot(0).shrink(reminder);
					}
				}
				markDirty();
				syncWithAll();
			}

			if (!storedItem.isEmpty()) {
				if (outputStack.isEmpty()) {

					ItemStack delivered = storedItem.copy();
					delivered.setCount(Math.min(storedItem.getCount(), delivered.getMaxStackSize()));
					storedItem.shrink(delivered.getCount());

					if (storedItem.isEmpty()) {
						storedItem = ItemStack.EMPTY;
					}

					inventory.setStackInSlot(1, delivered);
					markDirty();
					syncWithAll();
				} else if (ItemUtils.isItemEqual(storedItem, outputStack, true, true)
						&& outputStack.getCount() < outputStack.getMaxStackSize()) {

					int wanted = Math.min(storedItem.getCount(),
							outputStack.getMaxStackSize() - outputStack.getCount());
					outputStack.setCount(outputStack.getCount() + wanted);
					storedItem.shrink(wanted);

					if (storedItem.isEmpty()) {
						storedItem = ItemStack.EMPTY;
					}
					markDirty();
					syncWithAll();
				}
			}
		}
	}

	@Override
	public boolean canBeUpgraded() {
		return false;
	}

	@Override
	public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
		world.markBlockRangeForRenderUpdate(pos.getX(), pos.getY(), pos.getZ(), pos.getX(), pos.getY(), pos.getZ());
		read(packet.getNbtCompound());
	}

	@Override
	public void read(NBTTagCompound tagCompound) {
		super.read(tagCompound);
		readWithoutCoords(tagCompound);
	}

	@Override
	public NBTTagCompound write(NBTTagCompound tagCompound) {
		super.write(tagCompound);
		writeWithoutCoords(tagCompound);
		return tagCompound;
	}

	@Nonnull
	@Override
	public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap) {
		if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {

			return LazyOptional.of(new NonNullSupplier<T>() {
				@Nonnull
				@Override
				public T get() {
					return (T) inventory;
				}
			});
		}
		return super.getCapability(cap);
	}

	// ItemHandlerProvider
	@Override
	public Inventory<TileTechStorageBase> getInventory() {
		return inventory;
	}

	// IToolDrop
	@Override
	public ItemStack getToolDrop(EntityPlayer entityPlayer) {
		return getDropWithNBT();
	}

	// IListInfoProvider
	@Override
	public void addInfo(List<ITextComponent> info, boolean isRealTile, boolean hasData) {
		if (isRealTile || hasData) {
			int size = 0;
			String name = "of nothing";
			if (!storedItem.isEmpty()) {
				name = storedItem.getDisplayName().getString();
				size += storedItem.getCount();
			}
			if (!inventory.getStackInSlot(1).isEmpty()) {
				name = inventory.getStackInSlot(1).getDisplayName().getString();
				size += inventory.getStackInSlot(1).getCount();
			}
			info.add(new TextComponentString(size + " " + name));
		}
	}

	public ItemStack getStoredItemType() {
		return storedItem.isEmpty() ? inventory.getStackInSlot(1) : storedItem;
	}


	public void setStoredItemCount(int amount) {
		storedItem.grow(amount);
		markDirty();
	}

	public void setStoredItemType(ItemStack type, int amount) {
		storedItem = type;
		storedItem.setCount(amount);
		markDirty();
	}

	public int getMaxStoredCount() {
		return maxCapacity;
	}
}

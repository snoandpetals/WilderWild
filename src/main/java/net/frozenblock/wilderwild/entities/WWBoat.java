package net.frozenblock.wilderwild.entities;

import net.frozenblock.wilderwild.init.WWBlocks;
import net.frozenblock.wilderwild.init.WWEntityTypes;
import net.frozenblock.wilderwild.init.WWItems;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class WWBoat extends Boat {
    private static final EntityDataAccessor<Integer> BOAT_TYPE = SynchedEntityData.defineId(WWBoat.class, EntityDataSerializers.INT);

    public WWBoat(EntityType<? extends WWBoat> entityType, Level world) {
        super(entityType, world);
    }

    public WWBoat(Level world, double x, double y, double z) {
        this(WWEntityTypes.BOAT.get(), world);
        this.setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BOAT_TYPE, BOAT_TYPE.getId());
    }

    @Override
    public Item getDropItem() {
        return this.getWWBoatType() == WWBoatType.BAOBAB ? WWItems.BAOBAB_BOAT_ITEM.get() : WWItems.CYPRESS_BOAT_ITEM.get();
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        if (nbt.contains("Type", 8)) {
            this.setWWBoatType(WWBoatType.byName(nbt.getString("Type")));
        }
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putString("Type", this.getWWBoatType().getName());
    }

    public WWBoatType getWWBoatType() {
        return WWBoatType.byId(this.entityData.get(BOAT_TYPE));
    }

    public void setWWBoatType(WWBoatType type) {
        this.entityData.set(BOAT_TYPE, type.ordinal());
    }

    @Override
    protected void checkFallDamage(double heightDifference, boolean onGround, BlockState state, BlockPos landedPosition) {
        this.lastYd = this.getDeltaMovement().y;
        if (!this.isPassenger()) {
            if (onGround) {
                if (this.fallDistance > 3.0F) {
                    if (this.status != Boat.Status.ON_LAND) {
                        this.resetFallDistance();
                        return;
                    }

                    this.causeFallDamage(this.fallDistance, 1.0F, DamageSource.FALL);
                    if (!this.level.isClientSide && !this.isRemoved()) {
                        this.kill();
                        if (this.level.getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                            int i;
                            for(i = 0; i < 3; ++i) {
                                this.spawnAtLocation(this.getWWBoatType().getPlanks());
                            }

                            for(i = 0; i < 2; ++i) {
                                this.spawnAtLocation(Items.STICK);
                            }
                        }
                    }
                }

                this.resetFallDistance();
            } else if (!this.canBoatInFluid(this.level.getFluidState(this.blockPosition().below())) && heightDifference < 0.0) {
                this.fallDistance -= (float)heightDifference;
            }

        }
    }

    public enum WWBoatType {
        BAOBAB(WWBlocks.BAOBAB_PLANKS.get(), "baobab"),
        CYPRESS(WWBlocks.CYPRESS_PLANKS.get(), "cypress");

        private final String name;
        private final Block planks;

        WWBoatType(Block block, String name) {
            this.name = name;
            this.planks = block;
        }

        public String getName() {
            return this.name;
        }

        public Block getPlanks() {
            return this.planks;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public static WWBoatType byId(int id) {
            WWBoatType[] aboat$type = values();
            if (id < 0 || id >= aboat$type.length) {
                id = 0;
            }

            return aboat$type[id];
        }

        public static WWBoatType byName(String name) {
            WWBoatType[] types = values();
            for (WWBoatType type : types) {
                if (type.getName().equals(name)) return type;
            }
            return types[0];
        }
    }
}

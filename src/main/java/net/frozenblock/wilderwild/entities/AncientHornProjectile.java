package net.frozenblock.wilderwild.entities;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.frozenblock.wilderwild.WilderWild;
import net.frozenblock.wilderwild.block.entity.HangingTendrilBlockEntity;
import net.frozenblock.wilderwild.config.WilderWildConfig;
import net.frozenblock.wilderwild.init.WWBlockStateProperties;
import net.frozenblock.wilderwild.init.WWBlockTags;
import net.frozenblock.wilderwild.init.WWBlocks;
import net.frozenblock.wilderwild.init.WWEntityTypes;
import net.frozenblock.wilderwild.init.WWEntityTypesTags;
import net.frozenblock.wilderwild.init.WWGameEvents;
import net.frozenblock.wilderwild.init.WWItems;
import net.frozenblock.wilderwild.init.WWSoundEvents;
import net.frozenblock.wilderwild.item.AncientHornItem;
import net.frozenblock.wilderwild.util.FrozenProjectileDamageSource;
import net.frozenblock.wilderwild.util.MathUtil;
import net.frozenblock.wilderwild.util.NetworkUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.SpawnUtil;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.monster.warden.AngerLevel;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.BellBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LevelEvent;
import net.minecraft.world.level.block.SculkSensorBlock;
import net.minecraft.world.level.block.SculkShriekerBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public class AncientHornProjectile  extends AbstractArrow {
    private static final TagKey<Block> NON_COLLIDE = WWBlockTags.ANCIENT_HORN_NON_COLLIDE;
    public static final int DEFAULT_LIFESPAN = 300;
    public static final float MIN_SIZE = 0.01F;
    public static final float MAX_SIZE = 30F;

    private static final EntityDataAccessor<Float> BOUNDING_BOX_MULTIPLIER = SynchedEntityData.defineId(AncientHornProjectile.class, EntityDataSerializers.FLOAT);

    private boolean shot;
    private boolean leftOwner;
    private int aliveTicks;
    private double vecX;
    private double vecY;
    private double vecZ;
    private boolean shotByPlayer;
    private int bubbles;
    private BlockState inBlockState;
    private IntArrayList hitEntities = new IntArrayList();

    public AncientHornProjectile(@NotNull EntityType<? extends AncientHornProjectile> entityType, Level level) {
        super(entityType, level);
        this.setSoundEvent(WWSoundEvents.ENTITY_ANCIENT_HORN_PROJECTILE_DISSIPATE.get());
    }

    public AncientHornProjectile(Level level, double x, double y, double z) {
        super(WWEntityTypes.ANCIENT_HORN_PROJECTILE_ENTITY.get(), x, y, z, level);
        this.setSoundEvent(WWSoundEvents.ENTITY_ANCIENT_HORN_PROJECTILE_DISSIPATE.get());
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(BOUNDING_BOX_MULTIPLIER, 0F);
    }

    public List<Entity> collidingEntities() {
        return this.level.getEntities(this, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), this::canHitEntity);
    }

    @Override
    public boolean shouldRender(double cameraX, double cameraY, double cameraZ) {
        return true;
    }

    public int getAliveTicks() {
        return this.aliveTicks;
    }

    public void setShotByPlayer(final boolean bl) {
        this.shotByPlayer = bl;
    }

    public void setBubbles(final int amount) {
        this.bubbles = amount;
    }

    @Override
    public void tick() {
        this.baseTick();
        if (this.bubbles > 0 && this.level instanceof ServerLevel server) {
            --this.bubbles;
            NetworkUtil.sendSculkBubblesParticle(server, this.position(), MathUtil.random().nextDouble() > 0.7 ? 1 : 0, 20 + MathUtil.random().nextInt(40), 0.05, server.random.nextIntBetweenInclusive(1, 3));
        }
        if (this.aliveTicks > WilderWildConfig.HORN_LIFE_SPAN.get()) {
            this.remove(RemovalReason.DISCARDED);
        }
        ++this.aliveTicks;
        if (!this.shot) {
            this.shot = true;
        }
        if (!this.leftOwner) {
            this.leftOwner = this.checkLeftOwner();
        }
        boolean noPhysics = this.isNoPhysics();
        Vec3 deltaMovement = this.getDeltaMovement();
        if (this.xRotO == 0.0F && this.yRotO == 0.0F) {
            double horizontalDistance = deltaMovement.horizontalDistance();
            this.setYRot((float) (Mth.atan2(deltaMovement.x, deltaMovement.z) * 57.2957763671875D));
            this.setXRot((float) (Mth.atan2(deltaMovement.y, horizontalDistance) * 57.2957763671875D));
            this.yRotO = this.getYRot();
            this.xRotO = this.getXRot();
        }
        BlockPos blockPos = this.blockPosition();
        BlockState blockState = this.level.getBlockState(blockPos);
        Vec3 deltaPosition;

        if (this.shakeTime > 0) {
            --this.shakeTime;
        }

        if (this.isInWater() && level instanceof ServerLevel server) {
            NetworkUtil.sendSculkBubblesParticle(server, new Vec3(this.xo, this.yo, this.zo), 0, 60, 0.05, 4);
        }
        if (this.isInWaterOrRain() || blockState.is(Blocks.POWDER_SNOW)) {
            this.clearFire();
        }
        Vec3 position = this.position();
        deltaPosition = position.add(deltaMovement);
        HitResult hitResult = this.level.clip(new ClipContext(position, deltaPosition, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (!this.isRemoved() && canInteract()) {
            List<Entity> entities = this.collidingEntities();
            Entity owner = this.getOwner();
            for (Entity entity : entities) {
                if (!this.isRemoved() && entity != null && entity != owner) {
                    boolean shouldDamage = true;
                    if (entity instanceof Player player) {
                        if (player.isCreative()) {
                            shouldDamage = false;
                        }
                        if (owner instanceof Player playerOwner && !playerOwner.canHarmPlayer(player)) {
                            shouldDamage = false;
                        }
                    }
                    if (entity.isInvulnerable()) {
                        shouldDamage = false;
                    }
                    int uuid = entity.getUUID().hashCode();
                    if (this.hitEntities.contains(uuid))
                        shouldDamage = false;
                    if (shouldDamage) {
                        this.hitEntities.add(uuid);
                        this.hitEntity(entity);
                    }
                }
            }
        }
        if (!this.isRemoved() && hitResult != null && !noPhysics) {
            this.onHit(hitResult);
            if (this.isRemoved()) {
                return;
            }
            this.hasImpulse = true;
        }
        deltaMovement = this.getDeltaMovement();
        double deltaX = deltaMovement.x;
        double deltaY = deltaMovement.y;
        double deltaZ = deltaMovement.z;
        if (this.isCritArrow()) {
            for (int i = 0; i < 4; ++i) {
                this.level.addParticle(ParticleTypes.CRIT, this.getX() + deltaX * (double) i / 4.0D, this.getY() + deltaY * (double) i / 4.0D, this.getZ() + deltaZ * (double) i / 4.0D, -deltaX, -deltaY + 0.2D, -deltaZ);
            }
        }
        float divider = this.getBoundingBoxMultiplier() + 1F;
        float moveDivider = this.getBoundingBoxMultiplier() * 0.5F + 1F;
        double x = this.getX() + (deltaX / moveDivider);
        double y = this.getY() + (deltaY / moveDivider);
        double z = this.getZ() + (deltaZ / moveDivider);
        double horizontalDistance = deltaMovement.horizontalDistance();
        if (noPhysics) {
            this.setYRot((float) (Mth.atan2(-deltaX, -deltaZ) * 57.2957763671875D));
        } else {
            this.setYRot((float) (Mth.atan2(deltaX, deltaZ) * 57.2957763671875D));
        }
        this.setXRot((float) (Mth.atan2(deltaY, horizontalDistance) * 57.2957763671875D));
        this.setXRot(lerpRotation(this.xRotO, this.getXRot()));
        this.setYRot(lerpRotation(this.yRotO, this.getYRot()));

        this.setPos(x, y, z);
        this.checkInsideBlocks();

        float size = (float) (this.getBoundingBoxMultiplier() + WilderWildConfig.HORN_SIZE_MULTIPLIER.get());
        if (size > MIN_SIZE && size < MAX_SIZE)
            this.setBoundingBoxMultiplier(size);
    }

    @Override
    @NotNull
    public AABB makeBoundingBox() {
        return super.makeBoundingBox().inflate(this.getBoundingBoxMultiplier() / 2F);
    }

    public void setCooldown(int cooldownTicks) {
        Entity owner = this.getOwner();
        if (owner != null) {
            if (owner instanceof Player user) {
                user.getCooldowns().addCooldown(WWItems.ANCIENT_HORN.get(), cooldownTicks);
            }
        }
    }

    public void addCooldown(int cooldownTicks) {
        Entity owner = this.getOwner();
        if (owner != null) {
            if (owner instanceof Player user) {
                if (!user.isCreative()) {
                    ItemCooldowns manager = user.getCooldowns();
                    ItemCooldowns.CooldownInstance entry = manager.cooldowns.get(WWItems.ANCIENT_HORN.get());
                    if (entry != null) {
                        int cooldown = (entry.endTime - entry.startTime) + cooldownTicks;
                        manager.removeCooldown(WWItems.ANCIENT_HORN.get());
                        manager.addCooldown(WWItems.ANCIENT_HORN.get(), Math.min(600, cooldown));
                    } else {
                        manager.addCooldown(WWItems.ANCIENT_HORN.get(), cooldownTicks);
                    }
                }
            }
        }
    }

    @Override
    public boolean canHitEntity(Entity entity) {
        if (!entity.isSpectator() && entity.isAlive() && entity.isPickable() && !(entity instanceof Projectile)) {
            Entity owner = this.getOwner();
            return owner != null && (this.leftOwner || !owner.isPassengerOfSameVehicle(entity));
        } else {
            return false;
        }
    }

    @Override
    public void playerTouch(@NotNull Player player) {
    }

    @Override
    protected void onHitBlock(BlockHitResult result) { //BLOCK INTERACTIONS
        this.inBlockState = this.level.getBlockState(result.getBlockPos());
        BlockState blockState = this.level.getBlockState(result.getBlockPos());
        Entity owner = this.getOwner();
        // Copper pipes is not on forge yet

//        if (WilderWild.isCopperPipe(blockState) && owner != null) {
//            if (result.getDirection() == blockState.getValue(BlockStateProperties.FACING).getOpposite() && this.level instanceof ServerLevel server) {
//                if (((SimpleCopperPipesIntegration) WilderModIntegrations.SIMPLECOPPERPIPES_INTEGRATION).addHornNbtToBlock(server, result.getBlockPos(), owner))
//                    this.discard();
//            }
//        }

        blockState.onProjectileHit(this.level, blockState, result, this);
        Vec3 hitVec = result.getLocation().subtract(this.getX(), this.getY(), this.getZ());
        this.setDeltaMovement(hitVec);
        Vec3 hitNormal = hitVec.normalize().scale(0.05000000074505806D);
        this.setPosRaw(this.getX() - hitNormal.x, this.getY() - hitNormal.y, this.getZ() - hitNormal.z);
        this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
        this.inGround = true;
        this.shakeTime = 7;
        this.setCritArrow(false);
        if (this.level instanceof ServerLevel server && canInteract()) {
            if (blockState.getBlock() == Blocks.SCULK_SHRIEKER) {
                if (WilderWildConfig.HORN_CAN_SUMMON_WARDEN.get()) {
                    BlockPos pos = result.getBlockPos();
                    WilderWild.log(Blocks.SCULK_SHRIEKER, pos, "Horn Projectile Touched", WilderWild.UNSTABLE_LOGGING);
                    if (blockState.getValue(WWBlockStateProperties.SOULS_TAKEN) < 2 && !blockState.getValue(SculkShriekerBlock.SHRIEKING)) {
                        if (!blockState.getValue(SculkShriekerBlock.CAN_SUMMON)) {
                            server.setBlockAndUpdate(pos, blockState.setValue(WWBlockStateProperties.SOULS_TAKEN, blockState.getValue(WWBlockStateProperties.SOULS_TAKEN) + 1));
                        } else {
                            server.setBlockAndUpdate(pos, blockState.setValue(SculkShriekerBlock.CAN_SUMMON, false));
                        }
                        server.sendParticles(ParticleTypes.SCULK_SOUL, (double) pos.getX() + 0.5D, (double) pos.getY() + 1.15D, (double) pos.getZ() + 0.5D, 1, 0.2D, 0.0D, 0.2D, 0.0D);
                        trySpawnWarden(server, pos);
                        Warden.applyDarknessAround(server, Vec3.atCenterOf(this.blockPosition()), null, 40);
                        server.levelEvent(LevelEvent.PARTICLES_SCULK_SHRIEK, pos, 0);
                        server.gameEvent(GameEvent.SHRIEK, pos, GameEvent.Context.of(owner));
                        setCooldown(AncientHornItem.getCooldown(this.getOwner(), AncientHornItem.SHRIEKER_COOLDOWN));
                        this.setSoundEvent(WWSoundEvents.ENTITY_ANCIENT_HORN_PROJECTILE_DISSIPATE.get());
                        this.setShotFromCrossbow(false);
                        this.remove(RemovalReason.DISCARDED);
                    }
                }
            } else if (blockState.getBlock() == Blocks.SCULK_SENSOR) {
                BlockPos pos = result.getBlockPos();
                WilderWild.log(Blocks.SCULK_SENSOR, pos, "Horn Projectile Touched", WilderWild.UNSTABLE_LOGGING);
                if (blockState.getValue(WWBlockStateProperties.HICCUPPING)) {
                    server.setBlockAndUpdate(pos, blockState.setValue(WWBlockStateProperties.HICCUPPING, false));
                } else {
                    server.setBlockAndUpdate(pos, blockState.setValue(WWBlockStateProperties.HICCUPPING, true));
                }
                if (SculkSensorBlock.canActivate(blockState)) {
                    SculkSensorBlock.activate(null, level, pos, this.level.getBlockState(pos), MathUtil.random().nextInt(15));
                    this.level.gameEvent(null, WWGameEvents.SCULK_SENSOR_ACTIVATE.get(), pos);
                    setCooldown(AncientHornItem.getCooldown(this.getOwner(), AncientHornItem.SENSOR_COOLDOWN));
                }
            }
        }
        this.setSoundEvent(WWSoundEvents.ENTITY_ANCIENT_HORN_PROJECTILE_DISSIPATE.get());
        this.setShotFromCrossbow(false);
        this.remove(RemovalReason.DISCARDED);
    }

    private static void trySpawnWarden(ServerLevel level, BlockPos pos) {
        if (level.getGameRules().getBoolean(GameRules.RULE_DO_WARDEN_SPAWNING)) {
            Optional<Warden> warden = SpawnUtil.trySpawnMob(EntityType.WARDEN, MobSpawnType.TRIGGERED, level, pos, 20, 5, 6, SpawnUtil.Strategy.ON_TOP_OF_COLLIDER);
            warden.ifPresent(wardenEntity -> wardenEntity.playSound(SoundEvents.WARDEN_AGITATED, 5.0F, 1.0F));
        }
    }

    @Override
    protected SoundEvent getDefaultHitGroundSoundEvent() {
        return WWSoundEvents.ENTITY_ANCIENT_HORN_PROJECTILE_DISSIPATE.get();
    }

    @Override
    public boolean isNoPhysics() {
        BlockState insideState = this.level.getBlockState(this.blockPosition());
        if (insideState.is(WWBlocks.HANGING_TENDRIL.get()) && this.level instanceof ServerLevel server && canInteract()) { //HANGING TENDRIL
            BlockPos pos = this.blockPosition();
            BlockEntity entity = this.level.getBlockEntity(pos);
            WilderWild.log(WWBlocks.HANGING_TENDRIL.get(), pos, "Horn Projectile Touched", false);
            if (entity instanceof HangingTendrilBlockEntity tendril) {
                WilderWild.log("Horn Projectile Found Hanging Tendril Entity", WilderWild.UNSTABLE_LOGGING);
                this.playSound(this.getHitGroundSoundEvent(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                int XP = tendril.storedXP;
                if (XP > 0) {
                    tendril.storedXP = 0;
                    this.level.explode(this, this.getX(), this.getY(), this.getZ(), 0, Explosion.BlockInteraction.NONE);
                    NetworkUtil.createLocalSound(this.level, pos, WWSoundEvents.ENTITY_ANCIENT_HORN_PROJECTILE_BLAST.get(), SoundSource.NEUTRAL, 1.5F, 1.0F, true);
                    this.level.destroyBlock(this.blockPosition(), false);
                    ExperienceOrb.award(server, Vec3.atCenterOf(pos).add(0, 0, 0), XP);
                    setCooldown(AncientHornItem.getCooldown(this.getOwner(), AncientHornItem.TENDRIL_COOLDOWN));
                    this.setShotFromCrossbow(false);
                    this.remove(RemovalReason.DISCARDED);
                }
            }
        } else if (insideState.is(NON_COLLIDE)) {
            if (this.level instanceof ServerLevel server) {
                if (insideState.getBlock() instanceof BellBlock bell) { //BELL INTERACTION
                    bell.onProjectileHit(server, insideState, this.level.clip(new ClipContext(this.position(), new Vec3(this.getBlockX(), this.getBlockY(), this.getBlockZ()), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this)), this);
                } else if (insideState.getBlock() instanceof AbstractGlassBlock || insideState.is(Tags.Blocks.GLASS) || insideState.is(Tags.Blocks.GLASS_PANES)) {
                    if (WilderWildConfig.HORNS_SHATTERS_GLASS.get()) { //GLASS INTERACTION
                        insideState.onProjectileHit(this.level, insideState, this.level.clip(new ClipContext(this.position(), new Vec3(this.getBlockX(), this.getBlockY(), this.getBlockZ()), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this)), this);
                        this.level.destroyBlock(this.blockPosition(), false, this);
                    }
                }
            }
            return true;
        }
        Vec3 pos = this.position();
        Vec3 deltaMovement = this.getDeltaMovement();
        Vec3 scaledDelta = pos.add(deltaMovement.scale(0.08));
        BlockHitResult hitResult = this.level.clip(new ClipContext(pos, scaledDelta, ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, this));
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockState state = this.level.getBlockState(hitResult.getBlockPos());
            return state.is(NON_COLLIDE);
        }
        return false;
    }

    private boolean checkLeftOwner() {
        Entity owner = this.getOwner();
        if (owner != null) {
            for (Entity entity2 : this.level.getEntities(this, this.getBoundingBox().expandTowards(this.getDeltaMovement()).inflate(1.0D), (entityx) -> !entityx.isSpectator() && entityx.isPickable())) {
                if (entity2.getRootVehicle() == owner.getRootVehicle()) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    public boolean canInteract() {
        return this.getOwner() != null;
    }

    @Override
    protected ItemStack getPickupItem() {
        return ItemStack.EMPTY;
    }

    public float getBoundingBoxMultiplier() {
        return this.entityData.get(BOUNDING_BOX_MULTIPLIER);
    }

    public void setBoundingBoxMultiplier(float value) {
        this.entityData.set(BOUNDING_BOX_MULTIPLIER, value);
    }

    @Override
    public void addAdditionalSaveData(@NotNull CompoundTag compound) {
        if (!this.isRemoved()) {
            if (this.inBlockState != null) {
                compound.put("inBlockState", NbtUtils.writeBlockState(this.inBlockState));
            }
            compound.putInt("aliveTicks", this.aliveTicks);
            if (this.leftOwner) {
                compound.putBoolean("LeftOwner", true);
            }
            compound.putBoolean("HasBeenShot", this.shot);
            compound.putDouble("originX", this.vecX);
            compound.putDouble("originY", this.vecY);
            compound.putDouble("originZ", this.vecZ);
            compound.putBoolean("shotByPlayer", this.shotByPlayer);
            compound.putInt("bubbles", this.bubbles);
            compound.putFloat("boundingBoxMultiplier", this.getBoundingBoxMultiplier());
            compound.putIntArray("hitEntities", this.hitEntities);
        }
    }

    @Override
    public void readAdditionalSaveData(@NotNull CompoundTag compound) {
        if (!this.isRemoved()) {
            if (compound.contains("inBlockState", 10)) {
                this.inBlockState = NbtUtils.readBlockState(compound.getCompound("inBlockState"));
            }
            this.aliveTicks = compound.getInt("aliveTicks");
            this.leftOwner = compound.getBoolean("LeftOwner");
            this.shot = compound.getBoolean("HasBeenShot");
            this.vecX = compound.getDouble("originX");
            this.vecY = compound.getDouble("originY");
            this.vecZ = compound.getDouble("originZ");
            this.shotByPlayer = compound.getBoolean("shotByPlayer");
            this.bubbles = compound.getInt("bubbles");
            this.setBoundingBoxMultiplier(compound.getFloat("boundingBoxMultiplier"));
            this.hitEntities = IntArrayList.wrap(compound.getIntArray("hitEntities"));
        }
    }

    @Override
    public void shootFromRotation(Entity shooter, float pitch, float yaw, float roll, float speed, float divergence) {
        float xRot = -Mth.sin(yaw * 0.017453292F) * Mth.cos(pitch * 0.017453292F);
        float yRot = -Mth.sin((pitch + roll) * 0.017453292F);
        float zRot = Mth.cos(yaw * 0.017453292F) * Mth.cos(pitch * 0.017453292F);
        this.shoot(xRot, yRot, zRot, speed, divergence);
        this.vecX = shooter.getX();
        this.vecY = shooter.getEyeY();
        this.vecZ = shooter.getZ();
        this.setOwner(shooter);
    }

    @Override
    protected void onHit(HitResult result) {
        HitResult.Type type = result.getType();
        if (type == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) result;
            if (!level.getBlockState(blockHitResult.getBlockPos()).is(NON_COLLIDE)) {
                this.onHitBlock(blockHitResult);
                this.remove(RemovalReason.DISCARDED);
            }
        }
    }

    public float getDamage(@Nullable Entity entity) {
        int base = entity instanceof Player ? WilderWildConfig.HORN_PLAYER_DAMAGE.get() : WilderWildConfig.HORN_MOB_DAMAGE.get();
        return base / (this.getBoundingBoxMultiplier() + 1F);
    }

    @Override
    protected float getWaterInertia() {
        return 1.0F;
    }

    @Override
    public boolean isNoGravity() {
        return true;
    }

    private void hitEntity(Entity entity) {
        float damage = this.getDamage(entity);
        Entity owner = this.getOwner();
        if (entity != owner) {
            DamageSource damageSource;
            if (owner == null) {
                damageSource = FrozenProjectileDamageSource.source("ancient_horn", this, this);
            } else {
                damageSource = FrozenProjectileDamageSource.source("ancient_horn", this, owner);
                if (owner instanceof LivingEntity) {
                    ((LivingEntity) owner).setLastHurtMob(entity);
                }
            }
            int fireTicks = entity.getRemainingFireTicks();
            if (this.isOnFire()) {
                entity.setSecondsOnFire(5);
            }
            if (entity instanceof Warden warden && owner != null && canInteract()) {
                WilderWild.log(warden, "Horn Projectile Touched", false);
                warden.increaseAngerAt(owner, AngerLevel.ANGRY.getMinimumAnger() + 20, true);
                warden.playSound(SoundEvents.WARDEN_TENDRIL_CLICKS, 5.0F, warden.getVoicePitch());
                this.discard();
            } else if (!entity.getType().is(WWEntityTypesTags.ANCIENT_HORN_IMMUNE)) {
                if (entity.hurt(damageSource, damage)) {
                    if (entity instanceof LivingEntity livingEntity) {
                        Level level = this.getLevel();
                        WilderWild.log(livingEntity, "Horn Projectile Touched", false);
                        if (!this.level.isClientSide && owner instanceof LivingEntity) {
                            EnchantmentHelper.doPostHurtEffects(livingEntity, owner);
                            EnchantmentHelper.doPostDamageEffects((LivingEntity) owner, livingEntity);
                        }
                        this.doPostHurtEffects(livingEntity);
                        if (livingEntity.isDeadOrDying() && level instanceof ServerLevel server) {
                            server.sendParticles(ParticleTypes.SCULK_SOUL, livingEntity.getX(), livingEntity.getEyeY(), livingEntity.getZ(), 1, 0.2D, 0.0D, 0.2D, 0.0D);
                            if (this.getOwner() != null) {
                                if (this.getOwner() instanceof ServerPlayer serverPlayer) {
                                    addCooldown(livingEntity.getExperienceReward() * 10);
                                    //EasyPacket.EasyCompetitionPacket.sendAncientHornKillInfo(level, serverPlayer, livingEntity);
                                }
                            }
                        }
                    }
                    this.playSound(WWSoundEvents.ENTITY_ANCIENT_HORN_PROJECTILE_DISSIPATE.get(), 1.0F, 1.2F / (this.random.nextFloat() * 0.2F + 0.9F));
                } else {
                    entity.setRemainingFireTicks(fireTicks);
                    if (!this.level.isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7D) {
                        this.discard();
                    }
                }
            } else {
                entity.setRemainingFireTicks(fireTicks);
                if (!this.level.isClientSide && this.getDeltaMovement().lengthSqr() < 1.0E-7D) {
                    this.discard();
                }
            }
        }
    }

    @Override
    public void remove(RemovalReason reason) {
        this.hitEntities.clear();
        super.remove(reason);
    }

    @Override
    public boolean dampensVibrations() {
        return true;
    }

    @Override
    public void gameEvent(@NotNull GameEvent event) {
    }

    @Override
    public void gameEvent(@NotNull GameEvent event, @Nullable Entity entity) {
    }

}

package net.xiaoyu233.mitemod.miteite.events.listener;

import moddedmite.rustedironcore.api.event.listener.ITickListener;
import net.minecraft.World;
import net.minecraft.server.MinecraftServer;
import net.xiaoyu233.mitemod.miteite.api.ITEWorldInfoShared;
import net.xiaoyu233.mitemod.miteite.util.Configs;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;
public class TickListener implements ITickListener {
    @Override
    public void onServerTick(MinecraftServer server) {
        if (!Configs.GameMechanics.FIRST_DAY_LONGER_DAY_TIME.get()) return;
        World world = server.getEntityWorld();
        ITEWorldInfoShared info = (ITEWorldInfoShared) world.getWorldInfo().shared;
        if (info.ite$isTimeBack()) return;
        if (world.getTotalWorldTime() == 12000L) {
            info.ite$setTimeBack(true);
            world.setTotalWorldTime(0L);
            // ==== 振金装备效果 START ====
            if (server.getConfigurationManager() != null) {
                for (Object obj : server.getConfigurationManager().playerEntityList) {
                    if (obj instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer) obj;
                        if (player.worldObj.isRemote) continue;

                        ItemStack boots = player.getCurrentArmor(0);
                        ItemStack leggings = player.getCurrentArmor(1);
                        ItemStack chestplate = player.getCurrentArmor(2);
                        ItemStack helmet = player.getCurrentArmor(3);

                        // 振金靴子：永久速度 I
                        if (boots != null && boots.itemID == MITEITEItemRegistryInit.VIBRANIUM_BOOTS.itemID) {
                            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 2, 0));
                        }
                        // 振金护腿：永久跳跃 I
                        if (leggings != null && leggings.itemID == MITEITEItemRegistryInit.VIBRANIUM_LEGGINGS.itemID) {
                            player.addPotionEffect(new PotionEffect(Potion.jump.id, 2, 0));
                        }
                        // 振金胸甲：永久抗性 I
                        if (chestplate != null && chestplate.itemID == MITEITEItemRegistryInit.VIBRANIUM_CHESTPLATE.itemID) {
                            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 2, 0));
                        }
                        // 振金头盔：永久抗火 I
                        if (helmet != null && helmet.itemID == MITEITEItemRegistryInit.VIBRANIUM_HELMET.itemID) {
                            player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 2, 0));
                        }
                    }
                }
            }
            // ==== 振金装备效果 END ====
        }
    }
}

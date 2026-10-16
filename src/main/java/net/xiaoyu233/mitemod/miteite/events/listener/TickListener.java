package net.xiaoyu233.mitemod.miteite.events.listener;

import moddedmite.rustedironcore.api.event.listener.ITickListener;
import net.minecraft.*;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;

public class TickListener implements ITickListener {
    @Override
    public void onServerTick(MinecraftServer server) {
        for (World world : server.worldServers) {
            if (world != null && world.playerEntities != null) {
                for (Object obj : world.playerEntities) {
                    if (obj instanceof EntityPlayer) {
                        EntityPlayer player = (EntityPlayer) obj;
                        if (player.worldObj.isRemote) continue;

                        ItemStack boots = player.getCurrentArmor(0);
                        ItemStack leggings = player.getCurrentArmor(1);
                        ItemStack chestplate = player.getCurrentArmor(2);
                        ItemStack helmet = player.getCurrentArmor(3);

                        // 最后参数 true 开启粒子与图标，999999 tick 约 138 小时，脱下前无限刷新
                        if (boots != null && boots.getItem() == MITEITEItemRegistryInit.VIBRANIUM_BOOTS) {
                            player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 999999, 0, true));
                        }
                        if (leggings != null && leggings.getItem() == MITEITEItemRegistryInit.VIBRANIUM_LEGGINGS) {
                            player.addPotionEffect(new PotionEffect(Potion.jump.id, 999999, 0, true));
                        }
                        if (chestplate != null && chestplate.getItem() == MITEITEItemRegistryInit.VIBRANIUM_CHESTPLATE) {
                            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 999999, 0, true));
                        }
                        if (helmet != null && helmet.getItem() == MITEITEItemRegistryInit.VIBRANIUM_HELMET) {
                            player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 999999, 0, true));
                        }
                    }
                }
            }
        }
    }
}

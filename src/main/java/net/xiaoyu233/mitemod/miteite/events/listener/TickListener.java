package net.xiaoyu233.mitemod.miteite.events.listener;

import moddedmite.rustedironcore.api.event.listener.ITickListener;
import net.minecraft.EntityPlayer;
import net.minecraft.ItemStack;
import net.minecraft.Potion;
import net.minecraft.PotionEffect;
import net.minecraft.World;
import net.minecraft.server.MinecraftServer;
import net.xiaoyu233.mitemod.miteite.item.MITEITEItemRegistryInit;

public class TickListener implements ITickListener {
    @Override
    public void onServerTick(MinecraftServer server) {
        if (server == null || server.worldServers == null) return;
        
        for (World world : server.worldServers) {
            if (world == null || world.playerEntities == null) continue;
            
            for (Object obj : world.playerEntities) {
                if (obj instanceof EntityPlayer) {
                    EntityPlayer player = (EntityPlayer) obj;
                    if (player.worldObj.isRemote) continue;

                    ItemStack boots = player.getCurrentArmor(0);
                    ItemStack leggings = player.getCurrentArmor(1);
                    ItemStack chestplate = player.getCurrentArmor(2);
                    ItemStack helmet = player.getCurrentArmor(3);

                    // 999999 tick = 约138小时，无限刷新，最后参数 true 确保显示图标和粒子
                    if (boots != null && boots.itemID == MITEITEItemRegistryInit.VIBRANIUM_BOOTS.itemID) {
                        player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id, 999999, 0, true));
                    }
                    if (leggings != null && leggings.itemID == MITEITEItemRegistryInit.VIBRANIUM_LEGGINGS.itemID) {
                        player.addPotionEffect(new PotionEffect(Potion.jump.id, 999999, 0, true));
                    }
                    if (chestplate != null && chestplate.itemID == MITEITEItemRegistryInit.VIBRANIUM_CHESTPLATE.itemID) {
                        player.addPotionEffect(new PotionEffect(Potion.resistance.id, 999999, 0, true));
                    }
                    if (helmet != null && helmet.itemID == MITEITEItemRegistryInit.VIBRANIUM_HELMET.itemID) {
                        player.addPotionEffect(new PotionEffect(Potion.fireResistance.id, 999999, 0, true));
                    }
                }
            }
        }
    }
}

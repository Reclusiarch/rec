package com.bixel.rec.capabilties;

import com.bixel.rec.RecMod;

import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.Explosion;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class ChargeEventHandler 
{
	/**
	 * When an entity is created - like a mob - we can attach a "charge" to the entity
	 * @param event
	 */
	public static void onAttachCapabilitiesEvent(AttachCapabilitiesEvent<Entity> event) 
	{
        if (event.getObject() instanceof CreatureEntity) 
        {
            ChargeProvider provider = new ChargeProvider();
            event.addCapability(new ResourceLocation(RecMod.MOD_ID, "charge"), provider);
            event.addListener(provider::invalidate);//if entity dies - call invalidate and cleans up the lazyoptional
        }
    }

    public static void onDeathEvent(LivingDeathEvent event) 
    {
        Entity entity = event.getEntity();
        entity.getCapability(Capabilities.CAPABILITY_CHARGE).ifPresent(h -> {
            int charge = h.getCharge();
            if (charge > 0) {
                entity.getEntityWorld().createExplosion(entity, entity.getPosX(), entity.getPosY(), entity.getPosZ(), charge * .3f + 1.0f, Explosion.Mode.DESTROY);
            }
        });
    }

    public static void onAttackEvent(AttackEntityEvent event) 
    {
        Entity attacker = event.getEntity();
        if (attacker instanceof PlayerEntity) 
        {
            PlayerEntity player = (PlayerEntity) attacker;
            ItemStack stack = player.getHeldItemMainhand();
            if (stack.getItem() == Items.GUNPOWDER) 
            {
                Entity target = event.getTarget();
                target.getCapability(Capabilities.CAPABILITY_CHARGE).ifPresent(h -> {
                    int charge = h.getCharge() + 1;
                    h.setCharge(charge);
                    player.sendStatusMessage(new TranslationTextComponent("message.increase_charge", Integer.toString(charge)), true);
                    stack.shrink(1); //.removes 1 item from stack
                    player.setHeldItem(Hand.MAIN_HAND, stack);//sets the stack to the player
                    event.setCanceled(true); //cancels attack damage...
                    target.getEntityWorld().addParticle(ParticleTypes.FIREWORK, target.getPosX(), target.getPosY()+1, target.getPosZ(), 0.0, 0.0, 0.0);
                });
            }
        }
    }
}

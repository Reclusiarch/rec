package com.bixel.rec.objects.tiles.interfaces;


/**
* Implement this if your machine/generator has some form of active state.
*
* @author aidancbrady
*/
public interface IActiveState {

   /**
    * Gets the active state as a boolean.
    *
    * @return active state
    */
   boolean getActive();

   /**
    * Sets the active state to a new value.
    *
    * @param active - new active state
    */
   void setActive(boolean active);

   /**
    * Whether or not this block has a visual effect when it is on it's active state. Used for rendering.
    *
    * @return if the block has a visual effect in it's active state
    */
   boolean renderUpdate();

   //TODO: Name this better
   boolean lightUpdate();

   default int getActiveLightValue() 
   {
       return 1;//MekanismConfig.client.ambientLightingLevel.get();
   }
}
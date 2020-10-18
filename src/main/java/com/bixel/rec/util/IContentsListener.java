package com.bixel.rec.util;

/**
 * Helper class to reduce generic duplicate code between various handler types
 */
@FunctionalInterface
public interface IContentsListener 
{
	/**
     * Called when the contents this listener is monitoring gets changed.
     */
    void onContentsChanged();
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minor.poker.table;

import java.util.Map;

/**
 *
 * @author David
 */
public interface Pot {
    /**
     * Get the players' token contribution to *this* pot (does not include
     * side pots).
     * @return
     */
    Map<Player,Long> getPotContributions();

    /**
     * Players' token contribution to all side pots.
     * @return
     */
    Map<Player,Long> getSideContributions();

    /**
     * Players' token contribution to both main and side pots.
     *
     * @return
     */
    Map<Player,Long> getContributions();

    /**
     * The amount of tokens in this pot (does not include side pots).
     * @return
     */
    long getPotTotal();

    /**
     * The amount of tokens in all side pots.
     *
     * @return
     */
    long getSideTotal();

    /**
     * The combined amount of tokens in the main pot and all side pots.
     * @return
     */
    long getTotal();

    /**
     * Get the side pot, if any (returns null if none).
     *
     * @return
     */
    Pot getSidePot();
}

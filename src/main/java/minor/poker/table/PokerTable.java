/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minor.poker.table;

import java.util.List;

/**
 *
 * @author David
 */
public interface PokerTable {

    /**
     * Returns the current seat that the dealer button is at.
     *
     * @return
     */
    int getDealer();

    Hand getCurrentHand();

    int getNumberOfSeats();

    PlayerTableInfo getPlayerInfo(int seat);

    PlayerTableInfo getPlayerInfo(Player player);
    
    List<PlayerTableInfo> getPlayerInfoList();

    /**
     * Return the seat of a particular player. Returns -1
     * if not seated.
     */
    int getSeat(Player player);

    boolean isOccupied(int seat);

    boolean isSeated(Player player);

}

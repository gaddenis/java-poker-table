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

    int getDealer();

    Hand getHand();

    int getNumberOfSeats();

    PlayerInfo getPlayer(int seat);

    List<PlayerInfo> getPlayers();

    /**
     * Return the seat of a particular player. Returns -1
     * if not seated.
     */
    int getSeat(PlayerInfo player);

    boolean isOccupied(int seat);

    boolean isSeated(PlayerInfo player);

}

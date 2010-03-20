/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minor.poker.table;

/**
 *
 * @author David
 */
public interface PlayerTableInfo {
    Player getPlayer();
    long getChips();
    long getPendingChips();
    TableStatus getTableStatus();
}

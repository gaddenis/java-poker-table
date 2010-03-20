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
    Map<Player,Long> getContributions();
    long getTotal();
    Pot getNextPot();
}

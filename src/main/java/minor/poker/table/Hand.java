/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package minor.poker.table;

import java.util.List;
import java.util.Map;

/**
 *
 * @author David
 */
public interface Hand {
    List<Event> getEvents();
    Pot getPot();
    Map<Player,PlayerHandInfo> getPlayerInfoMap();
    PlayerHandInfo getPlayerInfo(Player player);
}

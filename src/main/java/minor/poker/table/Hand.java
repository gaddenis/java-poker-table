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
public interface Hand {
    List<Action> getActions();
    Pot getPot();
}

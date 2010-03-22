/*
 *  Copyright 2010 David Minor.
 * 
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *  under the License.
 */

package minor.poker.table.event;

import minor.poker.table.Player;

/**
 * Abstract parent of PlayerCallEvent and PlayerRaiseEvent. Check
 * and bet are just special cases of those two (use call with 0
 * for a check).
 * 
 * @author dave
 */
public abstract class PlayerBetEvent extends PlayerEvent {

  private long bet;
  
  public PlayerBetEvent(long time, Player player, long bet) {
    super(time, player);
    this.bet = bet;
  }

  /**
   * @return the bet
   */
  public long getBet() {
    return bet;
  }

}
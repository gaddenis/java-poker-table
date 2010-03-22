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

import minor.poker.table.DealtCard;
import minor.poker.table.Player;

/**
 *
 * @author dave
 */
public abstract class PlayerCardEvent extends PlayerEvent {
  private DealtCard dealtCard;

  public PlayerCardEvent(long time, Player player, DealtCard card) {
    super(time, player);
    this.dealtCard = card;
  }

  /**
   * @return the dealtCard
   */
  public DealtCard getDealtCard() {
    return dealtCard;
  }

  
}

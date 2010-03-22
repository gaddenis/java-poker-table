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

package minor.poker.table;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dave
 */
public class MutablePlayerHandInfo extends ObservablePlayerHandInfo {
  private HandStatus handStatus;
  private List<DealtCard> playerCards;

  public MutablePlayerHandInfo() {
    handStatus = HandStatus.PLAYING;
    playerCards = new ArrayList<DealtCard>();
  }

  /**
   * @return the handStatus
   */
  @Override
  public HandStatus getHandStatus() {
    return handStatus;
  }

  /**
   * @param handStatus the handStatus to set
   */
  public void setHandStatus(HandStatus handStatus) {
    this.handStatus = handStatus;
    doNotify();
  }

  /**
   * @return the playerCards
   */
  @Override
  public List<DealtCard> getPlayerCards() {
    return playerCards;
  }

  /**
   * @param playerCards the playerCards to set
   */
  public void setPlayerCards(List<DealtCard> playerCards) {
    this.playerCards = playerCards;
    doNotify();
  }
}

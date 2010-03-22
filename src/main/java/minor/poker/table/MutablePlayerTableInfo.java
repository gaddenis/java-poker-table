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

/**
 *
 * @author dave
 */
public class MutablePlayerTableInfo extends ObservablePlayerTableInfo {
  private Player player;
  private long chips;
  private long pendingChips;
  private TableStatus tableStatus;

  public MutablePlayerTableInfo(Player player, long chips) {
    this.player = player;
    this.chips = chips;
    this.pendingChips = 0;
    this.tableStatus = TableStatus.JOINED;
  }


  /**
   * @param chips the chips to set
   */
  public void setChips(long chips) {
    this.chips = chips;
    doNotify();
  }

  /**
   * @param pendingChips the pendingChips to set
   */
  public void setPendingChips(long pendingChips) {
    this.pendingChips = pendingChips;
    doNotify();
  }

  /**
   * @param tableStatus the tableStatus to set
   */
  public void setTableStatus(TableStatus tableStatus) {
    this.tableStatus = tableStatus;
    doNotify();
  }

  /**
   * @return the player
   */
  @Override
  public Player getPlayer() {
    return player;
  }

  /**
   * @return the chips
   */
  @Override
  public long getChips() {
    return chips;
  }

  /**
   * @return the pendingChips
   */
  @Override
  public long getPendingChips() {
    return pendingChips;
  }

  /**
   * @return the tableStatus
   */
  @Override
  public TableStatus getTableStatus() {
    return tableStatus;
  }

}

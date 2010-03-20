/*
Copyright 2010 David Minor

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

File: PokerTable.java - basic poker table (note: not synchronized)
 */
package minor.poker.table;

import java.util.ArrayList;
import java.util.List;

public class MutablePokerTable extends ObservablePokerTable {

  private int dealer;
  private ArrayList<PlayerTableInfo> players;
  private Hand currentHand;

  public MutablePokerTable(int numberOfSeats, int dealer) {
    this.dealer = dealer;
    players = new ArrayList<PlayerTableInfo>(numberOfSeats);
    for (int index = 0; index < numberOfSeats; index++) {
      players.add(null);
    }
  }

  @Override
  public int getDealer() {
    return dealer;
  }

  public void setDealer(int dealer) {
    if (this.dealer != dealer) {
      this.dealer = dealer;
      doNotify();
    }
  }

  @Override
  public int getNumberOfSeats() {
    return players.size();
  }

  /**
   * Set the number of seats (will truncate player list -
   * move players to empty seats beforehand).
   */
  public void setNumberOfSeats(int numberOfSeats) {
    if (numberOfSeats < players.size()) {
      for (int index = players.size() - 1; index >= numberOfSeats; index--) {
        players.remove(index);
      }
      doNotify();
    } else if (numberOfSeats > players.size()) {
      for (int index = numberOfSeats - players.size(); index > 0; index--) {
        players.add(null);
      }
      doNotify();
    }
  }

  /**
   * Add a player to the table at the specified seat
   * (numbered from 0). Will return false if seat is
   * unavailable.
   */
  public boolean addPlayerInfo(int seat, PlayerTableInfo player) {
    if (isOccupied(seat)) {
      return false;
    }
    players.set(seat, player);
    doNotify();
    return true;
  }

  /**
   * Add a player to the first available seat. Returns
   * true if the player was added.
   */
  public boolean addPlayerInfo(PlayerTableInfo player) {
    for (int index = 0; index < players.size(); index++) {
      if (!isOccupied(index)) {
        players.set(index, player);
        doNotify();
        return true;
      }
    }
    return false;
  }

  /**
   * Remove a player from the specified seat (starting at 0).
   */
  public boolean removePlayer(int seat) {
    if (isOccupied(seat)) {
      players.set(seat, null);
      doNotify();
      return true;
    }
    return false;
  }

  /**
   * Remove a player from the table. Returns
   * true if removed.
   */
  public boolean removePlayer(Player player) {
    int seat = getSeat(player);
    if (seat >= 0) {
      return removePlayer(seat);
    }
    return false;
  }

  @Override
  public List<PlayerTableInfo> getPlayerInfoList() {
    return players;
  }

  @Override
  public PlayerTableInfo getPlayerInfo(int seat) {
    return players.get(seat);
  }

  @Override
  public PlayerTableInfo getPlayerInfo(Player player) {
    int seat = getSeat(player);
    if (seat >= 0) {
      return getPlayerInfo(seat);
    }
    return null;
  }

  @Override
  public int getSeat(Player player) {
    PlayerTableInfo seated;
    for (int index = 0; index < players.size(); index++) {
      seated = players.get(index);
      if (seated != null && seated.getPlayer().equals(player)) {
        return index;
      }
    }
    return -1;
  }

  @Override
  public boolean isOccupied(int seat) {
    return players.get(seat) == null;
  }

  @Override
  public boolean isSeated(Player player) {
    return getSeat(player) >= 0;
  }

  public void setCurrentHand(Hand hand) {
    this.currentHand = hand;
    doNotify();
  }

  @Override
  public Hand getCurrentHand() {
    return currentHand;
  }
}

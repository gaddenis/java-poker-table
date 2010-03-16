/**
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

public class BasicPokerTable extends AbstractObservablePokerTable implements PokerTable {
  private int dealer;
  private ArrayList<PlayerInfo> players;
  private Hand currentHand;
  
  public BasicPokerTable(int numberOfSeats, int dealer) {
    this.dealer = dealer;
    players = new ArrayList<PlayerInfo>(numberOfSeats);
    for(int index = 0; index < numberOfSeats; index++)
      players.add(null);
  }

  private void doNotify() {
    setChanged();
    notifyObservers();
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
      for(int index = players.size() - 1; index >= numberOfSeats; index-- )
        players.remove(index);
      doNotify();
    } else if (numberOfSeats > players.size()) {
      for(int index = numberOfSeats - players.size(); index > 0; index--)
        players.add(null);
      doNotify();
    }
  }

  /**
   * Add a player to the table at the specified seat
   * (numbered from 0). Will return false if seat is
   * unavailable.
   */
  public boolean addPlayer(int seat, PlayerInfo player) {
    if (isOccupied(seat))
      return false;
    players.set(seat, player);
    doNotify();
    return true;
  }

  /**
   * Add a player to the first available seat. Returns
   * true if the player was added.
   */
  public boolean addPlayer(PlayerInfo player) {
    for(int index = 0; index < players.size(); index++) {
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
  public void removePlayer(int seat) {
    if (isOccupied(seat)) {
      players.set(seat, null);
      doNotify();
    }
  }

  /**
   * Remove a player from the table. Returns
   * true if removed.
   */
  public boolean removePlayer(PlayerInfo player) {
    PlayerInfo seated;
    for(int index = 0; index < players.size(); index++) {
      seated = players.get(index);
      if (seated != null && seated.equals(player)) {
        players.set(index, null);
        doNotify();
        return true;
      }
    }
    return false;
  }

    @Override
  public List<PlayerInfo> getPlayers() {
    return players;
  }

    @Override
  public PlayerInfo getPlayer(int seat) {
    return players.get(seat);
  }

  /**
   * Return the seat of a particular player. Returns -1
   * if not seated.
   */
    @Override
  public int getSeat(PlayerInfo player) {
    PlayerInfo seated;
    for(int index = 0; index < players.size(); index++) {
      seated = players.get(index);
      if (seated != null && seated.equals(player))
        return index;
    }
    return -1;
  }

    @Override
  public boolean isOccupied(int seat) {
    return players.get(seat) == null;
  }

    @Override
  public boolean isSeated(PlayerInfo player) {
    return getSeat(player) != -1;
  }

  public void setCurrentHand(Hand hand) {
    this.currentHand = hand;
    doNotify();
  }

    @Override
  public Hand getHand() {
    return currentHand;
  }
}

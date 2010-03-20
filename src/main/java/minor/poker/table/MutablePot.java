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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dave
 */
public class MutablePot extends ObservablePot {
  private long total;
  private Map<Player, Long> potContributions;
  private Pot sidePot;

  public MutablePot() {
    total = 0;
    potContributions = new HashMap<Player,Long>();
  }

  public MutablePot(Pot sidePot) {
    this.sidePot = sidePot;
  }

  public void addContribution(Player player, long contribution) {
    if (contribution == 0)
      return;
    long current = potContributions.get(player);
    potContributions.put(player, contribution + current);
    total += contribution;
    doNotify();
  }

  public void removeContribution(Player player, long contribution) {
    if (contribution == 0)
      return;
    addContribution(player, -contribution);
    if (potContributions.get(player) == 0) {
      potContributions.remove(player);
    }
    doNotify();
  }

  /**
   * Creates side pots based on contributions to this pot. Players
   * with more contributions than others will remain in the main pot
   * (which is returned). Returns true if any side pots were created.
   *
   * @return true if side pot(s) created
   */
  public boolean createSidePots() {
    if (potContributions.size() == 0)
      return false;

    long min = Long.MAX_VALUE;
    long count = 0;
    for(long contrib : potContributions.values()) {
      if (contrib < min) {
        min = contrib;
        count = 1;
      } else if (min == contrib) {
        count++;
      }
    }

    //everyone has made the same contribution -- no side pots
    if (count == potContributions.size())
      return false;

    //create a new side pot with our old side pot chained off of it
    MutablePot newSidePot = new MutablePot(getSidePot());
    this.sidePot = newSidePot;

    //add everyone's contribution to the new side pot
    for(Player player : new ArrayList<Player>(potContributions.keySet())) {
      //add the lowest contribution to the side pot and remove from this pot
      newSidePot.addContribution(player, min);
      removeContribution(player, min);
    }

    //recursively create additional side pots
    createSidePots();

    return true;
  }

  @Override
  public Map<Player, Long> getPotContributions() {
    return potContributions;
  }

  @Override
  public Map<Player, Long> getSideContributions() {
    if (sidePot != null) {
      return sidePot.getContributions();
    }
    return Collections.EMPTY_MAP;
  }

  @Override
  public Map<Player, Long> getContributions() {
    //could probably cache this and just check
    //whether side pots changed
    Map<Player, Long> sideContrib = getSideContributions();
    Map<Player, Long> totalContrib = new HashMap<Player, Long>(getPotContributions());
    for(Player player : sideContrib.keySet()) {
      if (totalContrib.containsKey(player)) {
        totalContrib.put(player, sideContrib.get(player) + totalContrib.get(player));
      } else {
        totalContrib.put(player, sideContrib.get(player));
      }
    }
    return totalContrib;
  }

  @Override
  public long getPotTotal() {
    return total;
  }

  @Override
  public long getSideTotal() {
    return sidePot.getTotal();
  }

  @Override
  public long getTotal() {
    return total + sidePot.getTotal();
  }

  @Override
  public Pot getSidePot() {
    return sidePot;
  }

}

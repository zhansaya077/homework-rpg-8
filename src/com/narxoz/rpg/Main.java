package com.narxoz.rpg;

import com.narxoz.rpg.combatant.*;
import com.narxoz.rpg.floor.*;
import com.narxoz.rpg.state.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Hero h1 = new Hero("Knight", 100, 20, 5);
        Hero h2 = new Hero("Mage", 80, 25, 3);

        h1.setState(new NormalState());
        h2.setState(new BerserkState());

        List<Hero> party = List.of(h1, h2);

        List<TowerFloor> floors = List.of(
                new BattleFloor(),
                new TrapFloor(),
                new RestFloor(),
                new BattleFloor()
        );

        for (TowerFloor f : floors) {
            f.explore(party);
        }

        System.out.println("\n=== FINAL STATUS ===");
        for (Hero h : party) {
            System.out.println(
                    h.getName()
                    + " | HP: " + h.getHp()
                    + " | State: " + h.getState().getName()
            );
        }

        System.out.println("\nTower finished");
    }
}

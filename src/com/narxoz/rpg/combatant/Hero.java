package com.narxoz.rpg.combatant;

import com.narxoz.rpg.state.HeroState;
import com.narxoz.rpg.state.NormalState;

public class Hero {

    private final String name;
    private int hp;
    private final int maxHp;
    private final int attackPower;
    private final int defense;

    private HeroState state;

    public Hero(String name, int hp, int attackPower, int defense) {
        this.name = name;
        this.hp = hp;
        this.maxHp = hp;
        this.attackPower = attackPower;
        this.defense = defense;
        this.state = new NormalState();
    }

    public String getName()        { return name; }
    public int getHp()             { return hp; }
    public int getMaxHp()          { return maxHp; }
    public int getAttackPower()    { return attackPower; }
    public int getDefense()        { return defense; }
    public boolean isAlive()       { return hp > 0; }

    public HeroState getState() {
        return state;
    }

    public void setState(HeroState state) {
        System.out.println(name + " state -> " + state.getName());
        this.state = state;
    }

    public void takeTurn(Monster monster) {
        state.onTurnStart(this);

        if (!isAlive()) return;

        if (!state.canAct()) {
            System.out.println(name + " cannot act (" + state.getName() + ")");
            state.onTurnEnd(this);
            return;
        }

        int damage = state.modifyOutgoingDamage(attackPower);

        System.out.println(name + " attacks for " + damage);
        monster.takeDamage(damage);

        state.onTurnEnd(this);
    }

    public void takeDamage(int amount) {
        int modified = state.modifyIncomingDamage(amount);
        hp = Math.max(0, hp - modified);

        System.out.println(name + " takes " + modified + " damage (HP: " + hp + ")");
    }

    public void heal(int amount) {
        hp = Math.min(maxHp, hp + amount);
        System.out.println(name + " heals " + amount + " (HP: " + hp + ")");
    }
}

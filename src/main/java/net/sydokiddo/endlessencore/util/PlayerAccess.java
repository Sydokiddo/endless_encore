package net.sydokiddo.endlessencore.util;

public interface PlayerAccess {

    void resetLastOffhandAttackTicks();

    void setOffhandAttack();

    boolean isOffhandAttack();

    float getAttackCooldownProgressOffhand(float baseTime);
}
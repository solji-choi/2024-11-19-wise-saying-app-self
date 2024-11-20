package com.ll;

public class KnifeExam {
    public static void main(String[] args) {
        Weapon weapon = new Knife();
        weapon.attack();
    }
}

class Weapon {
    void attack() {}
}

class Knife extends Weapon {
    void attack() {
        System.out.println("칼로 공격!");
    }
}
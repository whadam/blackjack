package com.cnu.blackjack;

import java.util.Scanner;

public class Application {
   public static void main(String[] args) {
       Scanner sc = new Scanner(System.in);
        System.out.print("몇 개의 덱으로 게임을 진행 하시겠습니까? ");

       Game game = new Game(new Deck(sc.nextInt()));
       game.start();

   }
}

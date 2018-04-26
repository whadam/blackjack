package com.cnu.blackjack;

import com.cnu.blackjack.exceptions.DuplicatePlayerException;
import com.cnu.blackjack.exceptions.NotEveyonePlacedBetException;
import com.cnu.blackjack.exceptions.PlayerDoesNotExistException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Game {

    private Map<String, Player> playerList = new HashMap<>();
    private Deck deck;


    public Game(Deck deck) {
        this.deck = deck;
    }

    public void addPlayer(String playerName, int seedMoney) {
        Player player = new Player(seedMoney, new Hand(deck));
        if (playerList.get(playerName) != null) {
            throw new DuplicatePlayerException();
        }
        playerList.put(playerName, player);
    }

    public Map<String, Player> getPlayerList() {
        return playerList;
    }


    public void start() {
        Scanner sc = new Scanner(System.in);    //플레이어 수 입력받기 위한 스캐너
        System.out.print("플레이어는 몇 명입니까? ");
        int numberOfPlayer = sc.nextInt();      //플레이어 수를 런타임에 입력받는다.

        for(int i = 0 ; i< numberOfPlayer; i++) {
            String playerName = "Player " + String.valueOf(i + 1);
            addPlayer(playerName, 10000);    //플레이어 추가
        }
        while(true) {
            for (int i = 0; i < numberOfPlayer; i++) {
                String playerName = "Player " + String.valueOf(i + 1);

                System.out.print(playerName + "님 배팅 하십시오 -> ");
                placeBet(playerName, sc.nextInt());          //각 플레이어가 돈을 베팅한다.
            }

            notBettingPlayerCheck();     //한 플레이어라도 0원을 배팅(배팅을 안한)한 경우 Exception 발생

            Evaluator evaluator = new Evaluator(playerList);    //이때 각 플레이어에게 카드 두장씩 분배됨
            evaluator.start();
            System.out.println("게임을 계속 하시겠습니까? (계속 진행 : 1    종료 : 0)  ");

            int go = sc.nextInt();
            if(go==0){  //0을 입력하면 게임 종료
                break;
            }

        }


    }

    public void placeBet(String name, int bet) {
        Player player = playerList.get(name);
        if (player == null) {
            throw new PlayerDoesNotExistException();
        }
        player.placeBet(bet);
    }

    public void notBettingPlayerCheck(){
        playerList.forEach((name, player) -> {
            if (player.getCurrentBet() == 0) {      //한 플레이어라도 0원을 배팅(배팅을 안한)한 경우 Exception 발생
                throw new NotEveyonePlacedBetException();
            }
        });
    }

}


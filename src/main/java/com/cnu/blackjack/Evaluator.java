package com.cnu.blackjack;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Evaluator {

    private Map<String, Player> playerMap;
    private Dealer dealer;


    public Evaluator(Map<String, Player> playerMap) {
        this.playerMap = playerMap;
        dealer = new Dealer();
        dealCardToPlayers();      //처음에 카드 두장씩 분배!
    }

    public void start() {
         hitAndStay();  //각 플레이어가 본인의 score에 따라 히트와 스테이를 한다.


        int dealerScore = dealer.getDealerScore();

        for(int i = 1; i<= playerMap.size() ;i++){
            Player player  = playerMap.get("Player "+String.valueOf(i));

            if(!playerWin(player,dealerScore)){
                System.out.println("\n\nPlayer "+String.valueOf(i)+" 이 패배하였습니다.");
                System.out.println("Player "+String.valueOf(i)+" -> "+player.scoreOfPlayer());
                System.out.println("Dealer -> "+ dealerScore);
                System.out.println(player.getCurrentBet()+"원을 잃어서 " + player.getBalance() +"원이 남았습니다.");
            }
            else{
                System.out.println("\n\nPlayer "+String.valueOf(i)+" 이 승리하였습니다.");
                System.out.println("Player "+ String.valueOf(i)+" -> "+player.scoreOfPlayer());
                System.out.println("Dealer -> "+ dealerScore);
                player.setBalance(player.getBalance()+player.getCurrentBet()*2);
                System.out.println(2*player.getCurrentBet()+"원을 보상받아 " + player.getBalance() +"원이 남았습니다.");
            }
        }


    }

    private void dealCardToPlayers() {
        playerMap.forEach((name, player) -> {
            player.hitCard();
            player.hitCard();
        });
    }


    private  void hitAndStay() {  //각 플레이어가 본인의 score에 따라 히트와 스테이를 한다.

        for (int i = 1; i <= playerMap.size(); i++) {
            Player player  = playerMap.get("Player "+String.valueOf(i));

            System.out.println("\n\n지금은 Player " + String.valueOf(i) + "의 턴입니다!\n");
            while (true) {
                    if (player.scoreOfPlayer() <= 16) { //16이하일때 히트
                        System.out.println("현재 점수가 16점 이하이므로 카드를 한장 더 뽑습니다.");
                        player.hitCard();
                    } else {
                        System.out.println("현재 점수가 17점 초과이므로 카드를 그만 뽑습니다.");
                        break;
                    }
            }


        }
    }

    public boolean playerWin(Player player,int delearScore){
        //21점 초과하면 플레이어 패배
        if(player.scoreOfPlayer() > 21){
            return false;
        }

        //플레이어가 21점이면 무조건 승리
        if(player.scoreOfPlayer() == 21){
            return true;
        }

        //플레이어가 21점 미만힐때, dealer보다 점수가 작으면 패배
        if(player.scoreOfPlayer() < delearScore){
            return false;
        }

        //플레이어가 21점 미만일때, dealer보다 점수가 크면 승리
        if(player.scoreOfPlayer() > delearScore){
            return true;
        }


        return false;
    }


}

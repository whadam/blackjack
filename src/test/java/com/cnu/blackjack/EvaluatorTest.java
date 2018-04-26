package com.cnu.blackjack;

import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class EvaluatorTest {

    @Test
    public void 게임초기화시_모든플레이어는_2장의카드를_받는다() {

    }

    @Test
    public void 각_플레이어는_16이하면_히트한다() {

    }

    @Test
    public void 블랙잭이나오면_2배로_보상받고_해당_플레이어의_턴은_끝난다() {

        Game game = new Game(new Deck(2));
        game.addPlayer("conch", 5000);  //플레이어 conch추가
        game.placeBet("conch", 3000);               //베팅


        Map<String, Player> playerList = game.getPlayerList();

        Evaluator evaluator = new Evaluator(playerList);
        Dealer dealer = new Dealer();

        Player player  = playerList.get("conch");

        while (true) {
            if (player.scoreOfPlayer() <= 16) { //16이하일때 히트
                player.hitCard();
            } else { //16 초과일때 스테이
                break;
            }
        }

        if(evaluator.playerWin(player,dealer.getDealerScore())){    //player가 승리할 경우(블랙잭)

            player.setBalance(player.getBalance()+player.getCurrentBet()*2);    //2배로 보상받는다.
            assertThat(player.getBalance(), is(8000));
        }


    }

    @Test
    public void 각_플레이어는_17이상이면_스테이한다() {

    }
}

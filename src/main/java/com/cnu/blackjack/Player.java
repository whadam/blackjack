package com.cnu.blackjack;

import com.cnu.blackjack.exceptions.NotEnoughBalanceException;
import lombok.Data;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Data
public class Player {

    private int balance;
    private int currentBet;
    private Hand hand;

    public Player(int seedMoney, Hand hand) {
        this.balance = seedMoney;
        this.hand = hand;
    }

    public void placeBet(int bet) {
        if(balance < bet) {
            throw new NotEnoughBalanceException();
        }
    balance -= bet;
    currentBet = bet;
}


    public Card hitCard() {
        return hand.drawCard();
    }

    public int scoreOfPlayer(){ //플레이어의 score를 리턴하는 메소드
        List<Card> card = hand.getCardList();
        int totalScore = 0;

        for(int i = 0; i< card.size() ; i++){
            int score = card.get(i).getRank();
            if(score>10){
                totalScore  += 10;
            }
            else{
                totalScore += score;
            }
        }

        return totalScore;
    }

}

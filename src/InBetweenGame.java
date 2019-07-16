/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inbetweengame;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 *
 * @author RomanM
 */
public class InBetweenGame extends Application {
    //objects for driver
    Scene players, game, endgame;
    Label firstPlayer,secondPlayer,potLabel,firstCard,secondCard,thirdCard,
            thirdPlayer,fourthPlayer,currentPlayer,gmLabel,
            first, second, third, fourth, potLabel2;
    //objects for game
    Person player1,player2,player3,player4,playing;
    static Deck deck = new Deck();
    double pot = 0;
    Card card1,card2,card3;
    boolean submitBet = true;
    
    
    @Override
    public void start(Stage primaryStage) {
        //first scene substance
        Label nameLabel = new Label("Enter Player Names or keep the default");
        
        TextField nameP1 = new TextField("Player 1");
        
        TextField nameP2 = new TextField("Player 2");
        
        TextField nameP3 = new TextField("Player 3");
        
        TextField nameP4 = new TextField("Player 4");
        
        Button threePlayer = new Button("3 Player Game");
        threePlayer.setOnAction(e -> {
            primaryStage.setScene(game);
            player1 = new Person(nameP1.getText(),80);
            player1.setActivePlayer(true);
            player2 = new Person(nameP2.getText(),80);
            player2.setActivePlayer(true);
            player3 = new Person(nameP3.getText(),80);
            player3.setActivePlayer(true);
            player4 = new Person(nameP4.getText(),0);
            player4.setActivePlayer(false);
            pot = 60;
            
            //refresh labels
            nameLabelRefresh(firstPlayer, player1);
            nameLabelRefresh(secondPlayer, player2);
            nameLabelRefresh(thirdPlayer, player3);
            nameLabelRefresh(fourthPlayer, player4);
            currentPlayerRefresh(player4);
            cardRefresh();
            gmLabelRefresh(0);
                });
        
        Button fourPlayer = new Button("4 Player Game");
        fourPlayer.setOnAction(e -> {
            primaryStage.setScene(game);
            player1 = new Person(nameP1.getText(),80);
            player1.setActivePlayer(true);
            player2 = new Person(nameP2.getText(),80);
            player2.setActivePlayer(true);
            player3 = new Person(nameP3.getText(),80);
            player3.setActivePlayer(true);
            player4 = new Person(nameP4.getText(),80);
            player4.setActivePlayer(true);
            pot = 80;
            
            //refresh labels
            nameLabelRefresh(firstPlayer, player1);
            nameLabelRefresh(secondPlayer, player2);
            nameLabelRefresh(thirdPlayer, player3);
            nameLabelRefresh(fourthPlayer, player4);
            currentPlayerRefresh(player4);
            cardRefresh();
            gmLabelRefresh(0);
                });
        
        //first scene layout
        VBox layout1 = new VBox(10);
        layout1.getChildren().addAll(nameLabel,nameP1,nameP2,nameP3,nameP4,threePlayer,fourPlayer);
        players = new Scene(layout1,250,300);
        //second scene substance
        firstPlayer = new Label(" ");
        GridPane.setConstraints(firstPlayer, 0, 0);
        
        secondPlayer = new Label(" ");
        GridPane.setConstraints(secondPlayer, 0, 1);
        
        thirdPlayer  = new Label(" ");
        GridPane.setConstraints(thirdPlayer, 0, 2);
        
        fourthPlayer = new Label(" ");
        GridPane.setConstraints(fourthPlayer, 0, 3);
        
        currentPlayer = new Label(" ");
        GridPane.setConstraints(currentPlayer, 2, 0);
        
        potLabel = new Label("Pot : " + pot);
        GridPane.setConstraints(potLabel, 2, 1);
        
        firstCard = new Label(" ");
        GridPane.setConstraints(firstCard,1, 2);
        
        secondCard = new Label(" ");
        GridPane.setConstraints(secondCard,3,2);
        
        thirdCard = new Label(" ");
        GridPane.setConstraints(thirdCard,2,2);
        
        gmLabel = new Label(" ");
        GridPane.setConstraints(gmLabel, 1,4);
        
        TextField betHold = new TextField("0");
        GridPane.setConstraints(betHold, 2,4);
        
        Button submit = new Button("Submit");
        GridPane.setConstraints(submit, 3,4);
        submit.setOnAction(e -> {
            if(submitBet){
                double bet = Double.parseDouble(betHold.getText());
                if(checkBet(bet)){
                    cardRefresh2();
                    gameResults(bet);
                    submitBet = false;
                }
            }else{
                nameLabelRefresh(firstPlayer, player1);
                nameLabelRefresh(secondPlayer, player2);
                nameLabelRefresh(thirdPlayer, player3);
                nameLabelRefresh(fourthPlayer, player4);
                currentPlayerRefresh(playing);
                thirdCard.setText(" ");
                cardRefresh();
                gmLabelRefresh(0);
                if(checkEndgame()){
                    primaryStage.setScene(endgame);
                    nameLabelRefresh(first,player1);
                    nameLabelRefresh(second,player2);
                    nameLabelRefresh(third,player3);
                    nameLabelRefresh(fourth,player4);
                    potLabel2.setText("Pot total: $" + pot);
                }
                submitBet = true;
            }
                
        });
        //second scene layout
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(5);
        grid.setHgap(5);
        grid.getChildren().addAll(firstPlayer,secondPlayer,potLabel,
                thirdPlayer,fourthPlayer,currentPlayer,firstCard,
                secondCard,thirdCard,gmLabel,betHold,submit);
        game = new Scene(grid, 750, 300);
        
        //third scene functionality
        Label congrats = new Label("Game Over");
        first = new Label(" ");
        second = new Label(" ");
        third = new Label(" ");
        fourth = new Label(" ");
        potLabel2 = new Label(" ");
        //third scene layout
        VBox pane = new VBox();
        pane.getChildren().addAll(congrats,first,second,third,fourth,potLabel2);
        endgame = new Scene(pane,500,500);
        //window
        primaryStage.setTitle("In-Between");
        primaryStage.setScene(players);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        deck.shuffle();
        launch(args);
    }
    /**
     * Desc: checks if game should end (pot is empty or no more players)
     * @return boolean true if game is over false if not
     */
    private boolean checkEndgame(){
        boolean isOver = false;
        if(pot <= 0)
            isOver = true;
        if(player1.getBankroll() <= 0 && player2.getBankroll() <= 0 
                && player3.getBankroll() <= 0 && player4.getBankroll() <= 0)
            isOver = true;
        return isOver;
        
    }
    /**
     * Desc: Checks to see if the bet is legal for the player
     * @param amount : amount to be checked
     * @return boolean that returns true if it is legal
     */
    private boolean checkBet(double amount){
        try{
           playing.setBet(amount);
           if(amount <= pot)
               return true;
           else{
               gmLabelRefresh(4);
               return false;
           }
        }catch(IllegalBetException e){
            gmLabelRefresh(4);
            return false;
        }
    }
    /**
     * Desc: determines if the round was a win lose or super loss
     * @param amount : the bet amount that is accepted
     */
    private void gameResults(double amount){
        if(card3.compareTo(card1) == 0 || card3.compareTo(card2) == 0)
        {
            pot += amount * 2;
            playing.setBankroll(amount * -2);
            if(playing.getBankroll() > 0)
                gmLabelRefresh(2);
            else
                gmLabelRefresh(3);
        }
        else if((card3.compareTo(card2) < 0 && card3.compareTo(card1) < 0)
                || (card3.compareTo(card2) > 0 && card3.compareTo(card1) > 0))
        {
            pot += amount;
            playing.setBankroll(-1 * amount);
            if(playing.getBankroll() > 0)
                gmLabelRefresh(2);
            else
                gmLabelRefresh(3);
        }
        else
        {
            pot -= amount;
            playing.setBankroll(amount);
            gmLabelRefresh(1);
        }
    }
    
    /**
     * Desc: sets game manager message
     * @param version 
     */
    private void gmLabelRefresh(int version){
        switch (version) {
            case 0:
                gmLabel.setText("Place bet and click submit");
                break;
            case 1:
                gmLabel.setText("Congrats on the win");
                break;
            case 2:
                gmLabel.setText("You lost. Too bad!");
                break;
            case 3:
                gmLabel.setText("Bankrupt!");
                break;
            case 4:
                gmLabel.setText("Illegal Bet, Try again");
                break;
            default:
                break;
        }
            
    }
    /**
     * Desc: refreshes the name label to keep it up to date in the game 
     * @param label : label to refresh
     * @param person : person object it refrences
     */
    private void nameLabelRefresh(Label label,Person person){
        String hold = person.getName() + " : $" + person.getBankroll();
        if(!person.isActivePlayer())
            hold += "(Bankrupt)";
        label.setText(hold);
    }
    
    /**
     * Desc: refreshes the current player label and pot
     * @param current : the person to be switched out
     */
    private void currentPlayerRefresh(Person current){
        boolean isActive = false;
        int count = 0;
        Person hold = current;
        do{
            if(hold.equals(player1))
                hold = player2;
            else if(hold.equals(player2))
                hold = player3;
            else if(hold.equals(player3))
                hold = player4;
            else
                hold = player1;
            
            if(hold.isActivePlayer())
                isActive = true;
            
            ++count;
            if(count == 4)
                break;
        }while(isActive == false);
        
        playing = hold;
        currentPlayer.setText("Current Player: " + hold.getName());
        potLabel.setText("Pot : " + pot);
    }
    
    /**
     * Desc: refreshes the labels for the first and second card while also drawing them from 
     * the deck
     */
    private void cardRefresh(){
        card1 = dealCard();
        card2 = dealCard();
        
        firstCard.setText(card1.toString());
        secondCard.setText(card2.toString());
    }
    /**
     * Desc: refreshes the label for the third card and draws it from the deck
     */
    private void cardRefresh2(){
        card3 = dealCard();
        
        thirdCard.setText(card3.toString());
    }
    /**
     * deals the next card in the deck / shuffles the deck if none remain
     * @return the next card available
     */
    private Card dealCard()
    {
        if(!deck.hasNext())
            deck.shuffle();
        return deck.next();
    }
    
    
}

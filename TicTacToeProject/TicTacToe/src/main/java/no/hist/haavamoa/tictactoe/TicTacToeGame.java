package no.hist.haavamoa.tictactoe;

import java.util.Random;

/**
 * Created by haavamoa on 11/5/13.
 */
public class TicTacToeGame {
   private char mBoard[]; //Hovedbrettet
    private final static int BOARD_SIZE = 9; //Brett størrelse

    //Alle brikkesymboler
    public static final char PLAYER_ONE = 'X';
    public static final char PLAYER_TWO = 'O';
    public static final char EMPTY_SPACE = ' ';

    private Random mRand;

    public int getBoard_SIZE(){
        return BOARD_SIZE;
    }

    /**
     * Konstruktør
     */
    public TicTacToeGame(){ //Konstruktør

        mBoard = new char[BOARD_SIZE];

        //Lager et tomt brett første gangen
        for(int i=0; i< BOARD_SIZE;i++){
            mBoard[i] = EMPTY_SPACE;
        }

        mRand = new Random();
    }

    /**
     * Resetter hele spillet
     */
    public void clearBoard(){
        for(int i=0;i<BOARD_SIZE;i++){
            mBoard[i] =EMPTY_SPACE;
        }
    }

    /**
     * Plasserer en spiller på brettet
     * @param player spiller
     * @param location lokasjon på brettet
     */
    public void setMove(char player,int location){
        mBoard[location] = player;
    }


    /**
     * * * * * * * * * *
     *  0  |  1  |  2  *
     *-----|-----|-----*
     *  3  |  4  |  5  *
     *-----|-----|-----*
     *  6  |  7  |  8  *
     * * * * * * * * * *
     * horisontalt : [i] & [i+1] & [i+2]
     * vertikalt : [i] & [i+3] & [i+6]
     * skrått :
     *
     * @return number hvis player_one er vinner(2), player_two er vinner(3) eller uavgjort (1);
     */
    public int checkForWinner(){
        for(int i=0;i<6;i+=3){

            //Horisontal sjekk
            if(mBoard[i] == PLAYER_ONE &&
               mBoard[i+1] == PLAYER_ONE &&
               mBoard[i+2] == PLAYER_ONE){
                return 2;
            }
            if(mBoard[i] == PLAYER_TWO &&
               mBoard[i+1] == PLAYER_TWO &&
               mBoard[i+2] == PLAYER_TWO){
                return 3;
            }
        }

        //Vertikal sjekk
        for(int i=0;i<=2;i++){
            if(mBoard[i] == PLAYER_ONE &&
               mBoard[i+3]==PLAYER_ONE &&
               mBoard[i+6] == PLAYER_ONE){
                return 2;
            }
            if(mBoard[i] == PLAYER_TWO &&
               mBoard[i+3]==PLAYER_TWO &&
               mBoard[i+6] == PLAYER_TWO){
                return 3;
            }
        }
         //Skrått human player.
        if((mBoard[0] ==PLAYER_ONE &&
           mBoard[4] == PLAYER_ONE &&
           mBoard[8] == PLAYER_ONE) ||
           (mBoard[2] == PLAYER_ONE &&
           mBoard[4] == PLAYER_ONE &&
           mBoard[6] == PLAYER_ONE)) {
            return 2;
        }
            //Skrått android player.
            if((mBoard[0] ==PLAYER_TWO &&
                    mBoard[4] == PLAYER_TWO &&
                    mBoard[8] == PLAYER_TWO) ||
                    (mBoard[2] == PLAYER_TWO &&
                            mBoard[4] == PLAYER_TWO &&
                            mBoard[6] == PLAYER_TWO)) {
                return 3;
            }

        for(int i=0;i<getBoard_SIZE();i++){
            //Hvis en posisjon er ledig
            if(mBoard[i] != PLAYER_ONE && mBoard[i] != PLAYER_TWO){
                return 0;
            }
        }
        //Uavgjort hvis ingen av if else går gjennom
        return 1;
    }
}

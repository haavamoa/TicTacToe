package no.hist.haavamoa.tictactoe;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by haavamoa on 11/5/13.
 */
public class TicTacToe extends Activity {

    private TicTacToeGame mGame;

    private Button mBoardButtons[];

    private TextView mInfoTextView;
    private TextView mPlayerOneCount;
    private TextView mTieCount;
    private TextView mPlayerTwoCount;
    private TextView mPlayerOneName;
    private TextView mPlayerTwoName;
    private TextView mPlayerOneTurn;
    private TextView mPlayerTwoTurn;

    private int mPlayerOneCounter = 0;
    private int mTieCounter = 0;
    private int mPlayerTwoCounter = 0;
    private int mGameCounter=0;
    private int mPlayerOneNumTurns =0;
    private int mPlayerTwoNumTurns = 0;

    private boolean playerOneTurn = true;
    private boolean mPlayerOneFirst = true;
    private boolean mGameOver = false;

    //Variabler som kommer fra forrige aktivitet.
    private String playerOne;
    private String playerTwo;
    private int numRounds;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tictactoe_main);

        //Get values from
        playerOne = getIntent().getStringExtra("playerOne");
        playerTwo = getIntent().getStringExtra("playerTwo");
        numRounds = getIntent().getIntExtra("numRounds", 0); //Default er 0, men det skjer aldri pga. mainactivity sjekk på numrounds >0

        mGame = new TicTacToeGame();

        mBoardButtons = new Button[mGame.getBoard_SIZE()];
        mBoardButtons[0] = (Button) findViewById(R.id.one);
        mBoardButtons[1] = (Button) findViewById(R.id.two);
        mBoardButtons[2] = (Button) findViewById(R.id.three);
        mBoardButtons[3] = (Button) findViewById(R.id.four);
        mBoardButtons[4] = (Button) findViewById(R.id.five);
        mBoardButtons[5] = (Button) findViewById(R.id.six);
        mBoardButtons[6] = (Button) findViewById(R.id.seven);
        mBoardButtons[7] = (Button) findViewById(R.id.eight);
        mBoardButtons[8] = (Button) findViewById(R.id.nine);
        mInfoTextView = (TextView) findViewById(R.id.information);
        mPlayerOneCount = (TextView) findViewById(R.id.playerOneCount);
        mTieCount = (TextView) findViewById(R.id.tiesCount);
        mPlayerTwoCount = (TextView) findViewById(R.id.playerTwoCount);
        mPlayerOneTurn = (TextView)findViewById(R.id.playerOneTurn);
        mPlayerTwoTurn = (TextView)findViewById(R.id.playerTwoTurn);
        mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
        mTieCount.setText(Integer.toString(mTieCounter));
        mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
                        mInfoTextView.setText(R.string.result_player_one_wins);

        mPlayerOneName = (TextView) findViewById(R.id.playerOne);
        mPlayerTwoName = (TextView) findViewById(R.id.playerTwo);
        mPlayerOneName.setText(playerOne+": ");
        mPlayerTwoName.setText(playerTwo+": ");

        startNewGame();

    }

    private void startNewGame()
    {
        if(mGameCounter<numRounds){
        mGame.clearBoard(); //Klarer brettet

        for (int i = 0; i < mBoardButtons.length; i++)
        {
            mBoardButtons[i].setText("");
            mBoardButtons[i].setEnabled(true);
            mBoardButtons[i].setOnClickListener(new ButtonClickListener(i));
        }
          //mInfoTextView.setText(R.string.result_player_one_wins);
        if (mPlayerOneFirst)
        {
            mInfoTextView.setText(R.string.turn_player_one);
            mInfoTextView.setText(playerOne+" "+mInfoTextView.getText().toString());
            mPlayerOneFirst = false;
        }
        else
        {
            mInfoTextView.setText(R.string.turn_player_two);
            mInfoTextView.setText(playerTwo+" "+mInfoTextView.getText().toString());
            mPlayerOneFirst = true;
        }
        }else{
            mGameOver=true;
            if(mPlayerOneCounter > mPlayerTwoCounter){
                mInfoTextView.setText(R.string.result_player_one_wins);
                mInfoTextView.setText(playerOne+" "+mInfoTextView.getText());
            }else if (mPlayerOneCounter<mPlayerTwoCounter){
                mInfoTextView.setText(R.string.result_player_two_wins);
                mInfoTextView.setText(playerTwo+" "+mInfoTextView.getText().toString());
            }else if(mPlayerOneCounter == mPlayerTwoCounter){
                mInfoTextView.setText(R.string.result_tie);
            }
        }
    }

    private class ButtonClickListener implements View.OnClickListener
    {
        int location;

        public ButtonClickListener(int location)
        {
            this.location = location;
        }

        public void onClick(View view)
        {
            if (!mGameOver)
            {
                if (mBoardButtons[location].isEnabled())
                {
                    int winner = mGame.checkForWinner();
                    if(playerOneTurn){ //Spiller en setter brikke
                    setMove(mGame.PLAYER_ONE, location);
                    mPlayerOneNumTurns++;
                    mPlayerOneTurn.setText(mPlayerOneNumTurns+"");
                    winner = mGame.checkForWinner();
                    playerOneTurn = false;
                    }else{
                    if (winner == 0) //Hvis det fortsatt er ledig på brettet
                    { //Spiller to setter brikke
                        mInfoTextView.setText(R.string.turn_player_one);
                        mInfoTextView.setText(playerOne+" +"+mInfoTextView.getText().toString());
                        setMove(mGame.PLAYER_TWO, location);
                        mPlayerTwoNumTurns++;
                        mPlayerTwoTurn.setText(mPlayerTwoNumTurns+"");
                        winner = mGame.checkForWinner();
                        playerOneTurn = true;
                    }
                    }

                    if (winner == 0) //Hvis det fortsatt er ledig på brettet
                    if(playerOneTurn){
                        mInfoTextView.setText(R.string.turn_player_one);
                        mInfoTextView.setText(playerOne+" "+mInfoTextView.getText().toString());
                    }else{
                        mInfoTextView.setText(R.string.turn_player_two);
                        mInfoTextView.setText(playerTwo+" "+mInfoTextView.getText().toString());
                    }
                    else if (winner == 1) //Hvis det er uavgjort
                    {
                        mTieCounter++;
                        mTieCount.setText(Integer.toString(mTieCounter));
                        mGameOver = true;
                    }
                    else if (winner == 2) //Hvis spiller 1 har vunnet
                    {
                        mPlayerOneCounter++;
                        mPlayerOneCount.setText(Integer.toString(mPlayerOneCounter));
                        mGameCounter++;
                        playerOneTurn=false;
                        startNewGame();
                    }
                    else //Hvis spiller 2 har vunnet
                    {
                        mPlayerTwoCounter++;
                        mPlayerTwoCount.setText(Integer.toString(mPlayerTwoCounter));
                        mGameCounter++;
                        playerOneTurn = true;
                        startNewGame();
                    }
                }
            }
        }
    }

    private void setMove(char player, int location)
    {
        mGame.setMove(player, location);
        mBoardButtons[location].setEnabled(false);
        mBoardButtons[location].setText(String.valueOf(player));
        if (player == mGame.PLAYER_ONE)
            mBoardButtons[location].setTextColor(Color.GREEN);
        else
            mBoardButtons[location].setTextColor(Color.RED);
    }

}
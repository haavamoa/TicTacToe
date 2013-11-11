package no.hist.haavamoa.tictactoe;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Locale;

/**
 * Created by haavamoa on 11/5/13.
 */
public class MainActivity extends Activity {
   private Button btnNorwegian;
   private Button btnEnglish;
   private Button btnStartGame;
   private EditText playerOne;
   private EditText playerTwo;
   private EditText numRounds;
   private String language;
   private Context main = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNorwegian = (Button) findViewById(R.id.btnNor);
        btnEnglish = (Button) findViewById(R.id.btnEnglish);
        btnStartGame = (Button) findViewById(R.id.btnStartGame);
        playerOne = (EditText) findViewById(R.id.playerOne);
        playerTwo = (EditText) findViewById(R.id.playerTwo);
        numRounds = (EditText) findViewById(R.id.numRounds);
        setOnClicks();


    }

    private void changeLanguage(){
        Resources res = getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(language.toLowerCase());
        res.updateConfiguration(conf, dm);
    }

    /**
     * Method to set onclick listeners
     * En liten hack for å skifte språk her, jeg starter en ny activitet, men husker å avslutte den forrige.
     */
    private void setOnClicks(){
        btnNorwegian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language = "nb";//Norsk bokmål
                changeLanguage();
                Intent i = new Intent(main,MainActivity.class);
                finish();
                startActivity(i);
            }
        });

        btnEnglish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                language = "en";
                changeLanguage();
                setContentView(R.layout.activity_main);
                Intent i = new Intent(main,MainActivity.class);
                finish();
                startActivity(i);
            }
        });

        btnStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(main,TicTacToe.class);
                String playerOneName = playerOne.getText().toString();
                String playerTwoName = playerTwo.getText().toString();
                String numRoundsValue = numRounds.getText().toString();
                try{
                int numRoundsInt = Integer.parseInt(numRoundsValue);
                if(!playerOneName.equals("") &&
                        !playerTwoName.equals("") &&
                        numRoundsInt>0){
                i.putExtra("playerOne",playerOneName);
                i.putExtra("playerTwo",playerTwoName);
                i.putExtra("numRounds",numRoundsInt);
                finish();
                startActivity(i);
                }else{
                    Toast.makeText(getBaseContext(),R.string.inputError,Toast.LENGTH_LONG).show();
                }
                }catch(Exception e){
                    Toast.makeText(getBaseContext(),R.string.inputError,Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}

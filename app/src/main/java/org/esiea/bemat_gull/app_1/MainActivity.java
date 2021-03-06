package org.esiea.bemat_gull.app_1;

import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button actionButton;
    private TextView myChrono;
    private TextView myScore;
    private CountDownTimer cdt;
    private int flag;
    private int score;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionButton = (Button) findViewById(R.id.btn_start);
        myChrono = (TextView) findViewById(R.id.ready);
        myScore = (TextView) findViewById(R.id.score);
        flag = 0;
        score = 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_cheat:
                // Comportement du bouton "A Propos"
                Intent second= new Intent(this, SecondeActivity.class);
                startActivity(second);
                return true;
            case R.id.action_notification:
                // Comportement du bouton "Notification"
                NotificationCompat.Builder wat =
                        new NotificationCompat.Builder(this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("CLICK THAT")
                                .setContentText(getString(R.string.notification_example));
                NotificationManager manager = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
                manager.notify(1,wat.build());
                return true;
            case R.id.action_toast:
                // Comportement du bouton "Toast"
                Toast.makeText(getApplicationContext(), getString(R.string.toast_example), Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





    public void btnRules(View v) {

        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setTitle(R.string.rules);
        adb
                .setMessage(R.string.game_rule)
                .setCancelable(false)
                .setNeutralButton(R.string.dialog_box, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = adb.create();
        alertDialog.show();
    }

    public void btnPlay(View v) {
        score++;

    }

    public void btnStart(View v) {
        if(flag == 1) {
            cdt.cancel();
            score = 0;
        }
        score=0;
        flag = 1;
        actionButton.setText(R.string.restart);

        cdt = new CountDownTimer(31000, 100) {
            public void onTick(long millisUntilFinished) {
                myChrono.setText(""+millisUntilFinished / 1000);
                myScore.setText("Score : "+score);

            }
            public void onFinish() {
                if(score > 50) {
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle(R.string.win);
                    adb
                            .setMessage(R.string.win_game)
                            .setCancelable(false)
                            .setNeutralButton(R.string.dialog_box, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    myChrono.setText(R.string.end_game);
                                    Intent second = new Intent(getApplicationContext(), SecondeActivity.class);
                                    startActivity(second);
                                }
                            });
                    AlertDialog alertDialog = adb.create();
                    alertDialog.show();
                }else{
                    AlertDialog.Builder adb = new AlertDialog.Builder(MainActivity.this);
                    adb.setTitle(R.string.lost);
                    adb
                            .setMessage(R.string.lost_game)
                            .setCancelable(false)
                            .setNeutralButton(R.string.dialog_box, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                    myChrono.setText(R.string.end_game);
                                }
                            });
                    AlertDialog alertDialog = adb.create();
                    alertDialog.show();
                }
            }
        }.start();
    }

}

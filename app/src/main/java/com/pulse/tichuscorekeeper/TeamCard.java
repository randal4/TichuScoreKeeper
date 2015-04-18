package com.pulse.tichuscorekeeper;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.activeandroid.ActiveAndroid;
import com.pulse.tichuscorekeeper.model.TichuHand;
import com.pulse.tichuscorekeeper.service.TichuHandIntentService;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by puLse on 3/21/15.
 */
public class TeamCard extends RelativeLayout {
    private static final int ONE_TWO_POINTS = 200;

    private TextView totalScore;
    private CheckBox oneTwoBonus;
    private SeekBar pointsSeekbar;
    private TextView handPointsLabel;

    private TeamCard opposingTeam;

    private PlayerCard player1;
    private PlayerCard player2;

    public TeamCard(Context context){
        super(context);
        init(context);
    }

    public TeamCard(Context context, AttributeSet attr){
        super(context, attr);
        init(context);
    }

    public void init(Context context){
        View v = View.inflate(this.getContext(), R.layout.team_card, this);

        totalScore = (TextView) v.findViewById(R.id.total_score);
        totalScore.setText("0");

        handPointsLabel = (TextView) v.findViewById(R.id.round_points_text);

        player1 = (PlayerCard) v.findViewById(R.id.player1_card);
        player2 = (PlayerCard) v.findViewById(R.id.player2_card);

        pointsSeekbar = (SeekBar) v.findViewById(R.id.points_seekbar);
        pointsSeekbar.setMax(30);

        oneTwoBonus = (CheckBox) v.findViewById(R.id.one_two);
        oneTwoBonus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    handPointsLabel.setText("200");
                    disablePointsSeekbar();

                    if(opposingTeam != null) {
                        opposingTeam.setHandPointsLabel("0");
                        opposingTeam.disablePointsSeekbar();
                        opposingTeam.disableOneTwoButton();
                    }
                }else{
                    handPointsLabel.setText(String.valueOf((pointsSeekbar.getProgress() * 5) - 25));
                    enablePointsSeekbar();
                    pointsSeekbar.setEnabled(true);

                    if(opposingTeam != null) {
                        opposingTeam.setHandPointsLabel(String.valueOf(((30 - pointsSeekbar.getProgress()) * 5) - 25));
                        opposingTeam.enablePointsSeekbar();
                        opposingTeam.enableOneTwoButton();
                    }
                }
            }
        });

        pointsSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pointsVal;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pointsVal = (progress * 5) - 25;
                handPointsLabel.setText(pointsVal + "");

                if(opposingTeam != null) {
                    opposingTeam.setPointsSeekbarProgress(((100 - pointsVal) + 25) / 5);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        pointsSeekbar.setProgress(15);
    }

    public void setPointsSeekbarProgress(int i) {
        this.pointsSeekbar.setProgress(i);
    }

    public void disableOneTwoButton() {
        this.oneTwoBonus.setEnabled(false);
    }

    public void enableOneTwoButton() {
        this.oneTwoBonus.setEnabled(true);
    }

    public void disablePointsSeekbar() {
        this.pointsSeekbar.setEnabled(false);
    }

    public void enablePointsSeekbar(){
        this.pointsSeekbar.setEnabled(true);
    }

    public void clearSelections(){
        oneTwoBonus.setChecked(false);
        pointsSeekbar.setProgress(15);

        player1.reset();
        player2.reset();
    }

    public void reset(){
        totalScore.setText("0");

        clearSelections();
    }

    public TeamCard getOpposingTeam() {
        return opposingTeam;
    }

    public void setOpposingTeam(TeamCard opposingTeam) {
        this.opposingTeam = opposingTeam;
    }

    public void setHandPointsLabel(String points){
        this.handPointsLabel.setText(points);
    }

    public int calculateHandPoints() {
        int score = 0;

        if(oneTwoBonus.isChecked()) {
           score += ONE_TWO_POINTS;
        }else if(opposingTeam.isOneTwoChecked()) {
            //no points for you
        }else {
            score += Integer.parseInt((String) handPointsLabel.getText());
        }

        score += player1.getTichuPoints();
        score += player2.getTichuPoints();

        Integer currentScoreTeam1 = Integer.parseInt(totalScore.getText().toString());

        totalScore.setText(String.valueOf(currentScoreTeam1 + score));

        return score;
    }

    private boolean isOneTwoChecked() {
        return oneTwoBonus.isChecked();
    }

    public String getTotalScore(){
        return totalScore.getText().toString();
    }

    public void setTotalScore(String totalScore){
        this.totalScore.setText(totalScore);
    }

    public void setPlayer1Name(String s) {
        this.player1.setPlayerName(s);
    }

    public void setPlayer2Name(String s) {
        this.player2.setPlayerName(s);
    }

    public void computeStats(int handScore, int hand, int game) {
        TichuHand th1 = new TichuHand();
        th1.player = player1.getPlayerName();
        th1.partner = player2.getPlayerName();
        th1.tichu = player1.isTichuCall();
        th1.grandTichu = player1.isGrandTichuCall();
        th1.imperialTichu = player1.isImperialTichuCall();
        th1.oneTwo = oneTwoBonus.isChecked();
        th1.oneTwoAgainst = opposingTeam.isOneTwoChecked();
        th1.score = handScore;

        TichuHandIntentService.startActionSaveHand(getContext(), th1);

        TichuHand th2 = new TichuHand();
        th2.player = player2.getPlayerName();
        th2.partner = player1.getPlayerName();
        th2.tichu = player2.isTichuCall();
        th2.grandTichu = player2.isGrandTichuCall();
        th2.imperialTichu = player2.isImperialTichuCall();
        th2.oneTwo = oneTwoBonus.isChecked();
        th2.oneTwoAgainst = opposingTeam.isOneTwoChecked();
        th2.score = handScore;

        TichuHandIntentService.startActionSaveHand(getContext(), th2);
    }
}

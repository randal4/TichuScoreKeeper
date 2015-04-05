package com.pulse.tichuscorekeeper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.pulse.tichuscorekeeper.model.TichuHand;
import com.pulse.tichuscorekeeper.service.TichuHandIntentService;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScoreAdder.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScoreAdder#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScoreAdder extends Fragment {

    static final String STATE_TEAM_1_SCORE = "team1Score";
    static final String STATE_TEAM_2_SCORE = "team2Score";

    private static final int ONE_TWO_POINTS = 200;

    private OnFragmentInteractionListener mListener;

    private PlayerCard player1Card;
    private PlayerCard player2Card;
    private PlayerCard player3Card;
    private PlayerCard player4Card;

    private TextView totalScoreTeam1;
    private CheckBox oneTwoBonusTeam1;
    private SeekBar pointsSeekbarTeam1;
    private TextView handPointsLabelTeam1;

    private TextView totalScoreTeam2;
    private CheckBox oneTwoBonusTeam2;
    private SeekBar pointsSeekbarTeam2;
    private TextView handPointsLabelTeam2;

    private Button endHandButton;
    private Button resetButton;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ScoreAdder.
     */
    public static ScoreAdder newInstance() {
        ScoreAdder fragment = new ScoreAdder();

        return fragment;
    }

    public ScoreAdder() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View adder = inflater.inflate(R.layout.fragment_score_adder, container, false);

        player1Card = (PlayerCard) adder.findViewById(R.id.player1_card);
        player2Card = (PlayerCard) adder.findViewById(R.id.player2_card);
        player3Card = (PlayerCard) adder.findViewById(R.id.player3_card);
        player4Card = (PlayerCard) adder.findViewById(R.id.player4_card);

        player1Card.setPlayerName("Player 1");
        player2Card.setPlayerName("Player 2");
        player3Card.setPlayerName("Player 3");
        player4Card.setPlayerName("Player 4");

        totalScoreTeam1 = (TextView) adder.findViewById(R.id.total_score_team_1);
        totalScoreTeam1.setText("0");

        totalScoreTeam2 = (TextView) adder.findViewById(R.id.total_score_team_2);
        totalScoreTeam2.setText("0");

        handPointsLabelTeam1 = (TextView) adder.findViewById(R.id.round_points_text_team_1);
        handPointsLabelTeam2 = (TextView) adder.findViewById(R.id.round_points_text_team_2);

        oneTwoBonusTeam1 = (CheckBox) adder.findViewById(R.id.one_two_team_1);
        oneTwoBonusTeam1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    handPointsLabelTeam1.setText("200");
                    handPointsLabelTeam2.setText("0");
                    pointsSeekbarTeam1.setEnabled(false);
                    pointsSeekbarTeam2.setEnabled(false);
                    oneTwoBonusTeam2.setEnabled(false);
                }else{
                    handPointsLabelTeam1.setText(String.valueOf((pointsSeekbarTeam1.getProgress() * 5) - 25));
                    handPointsLabelTeam2.setText(String.valueOf((pointsSeekbarTeam2.getProgress() * 5) - 25));
                    pointsSeekbarTeam1.setEnabled(true);
                    pointsSeekbarTeam2.setEnabled(true);
                    oneTwoBonusTeam2.setEnabled(true);
                }
            }
        });
        oneTwoBonusTeam2 = (CheckBox) adder.findViewById(R.id.one_two_team_2);
        oneTwoBonusTeam2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    handPointsLabelTeam1.setText("0");
                    handPointsLabelTeam2.setText("200");
                    pointsSeekbarTeam1.setEnabled(false);
                    pointsSeekbarTeam2.setEnabled(false);
                    oneTwoBonusTeam1.setEnabled(false);
                }else{
                    handPointsLabelTeam1.setText(String.valueOf((pointsSeekbarTeam1.getProgress() * 5) - 25));
                    handPointsLabelTeam2.setText(String.valueOf((pointsSeekbarTeam2.getProgress() * 5) - 25));
                    pointsSeekbarTeam1.setEnabled(true);
                    pointsSeekbarTeam2.setEnabled(true);
                    oneTwoBonusTeam1.setEnabled(true);
                }
            }
        });

        pointsSeekbarTeam1 = (SeekBar) adder.findViewById(R.id.points_seekbar_team_1);
        pointsSeekbarTeam2 = (SeekBar) adder.findViewById(R.id.points_seekbar_team_2);

        pointsSeekbarTeam1.setMax(30);
        pointsSeekbarTeam2.setMax(30);


        pointsSeekbarTeam1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pointsVal;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pointsVal = (progress * 5) - 25;
                handPointsLabelTeam1.setText(pointsVal + "");
                pointsSeekbarTeam2.setProgress(((100 - pointsVal) + 25 ) / 5);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        pointsSeekbarTeam2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pointsVal;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pointsVal = (progress * 5) - 25;
                handPointsLabelTeam2.setText(pointsVal + "");
                pointsSeekbarTeam1.setProgress(((100 - pointsVal) + 25 ) / 5);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        pointsSeekbarTeam1.setProgress(15);
        pointsSeekbarTeam2.setProgress(15);

        endHandButton = (Button) adder.findViewById(R.id.end_hand_button);
        endHandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHandEndButtonPressed();
            }
        });

        resetButton = (Button) adder.findViewById(R.id.reset_button);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onResetButtonPressed();
            }
        });

        return adder;
    }

    public void onHandEndButtonPressed(){
        int team1Score = 0;
        int team2Score = 0;

        if(oneTwoBonusTeam1.isChecked()) {
            team1Score += ONE_TWO_POINTS;
        }else if(oneTwoBonusTeam2.isChecked()) {
            //no points for you
        }else {
            team1Score += Integer.parseInt((String) handPointsLabelTeam1.getText());
        }

        if(oneTwoBonusTeam2.isChecked()) {
            team2Score += ONE_TWO_POINTS;
        }else if(oneTwoBonusTeam1.isChecked()) {
            //no points for you
        }else {
            team2Score += Integer.parseInt((String) handPointsLabelTeam2.getText());
        }

        team1Score += player1Card.getTichuPoints();
        team1Score += player2Card.getTichuPoints();
        team2Score += player3Card.getTichuPoints();
        team2Score += player4Card.getTichuPoints();

        Integer currentScoreTeam1 = Integer.parseInt(totalScoreTeam1.getText().toString());
        Integer currentScoreTeam2 = Integer.parseInt(totalScoreTeam2.getText().toString());

        totalScoreTeam1.setText(String.valueOf(currentScoreTeam1 + team1Score));
        totalScoreTeam2.setText(String.valueOf(currentScoreTeam2 + team2Score));

        saveStats(team1Score, team2Score);
        clearSelections();
    }

    private void saveStats(int team1Score, int team2Score) {

        TichuHand th1 = new TichuHand();
        th1.player = player1Card.getPlayerName();
        th1.partner = player2Card.getPlayerName();
        th1.tichu = player1Card.isTichuCall();
        th1.grandTichu = player1Card.isGrandTichuCall();
        th1.imperialTichu = player1Card.isImperialTichuCall();
        th1.oneTwo = oneTwoBonusTeam1.isChecked();
        th1.oneTwoAgainst = oneTwoBonusTeam2.isChecked();
        th1.score = team1Score;

        TichuHandIntentService.startActionSaveHand(getActivity(), th1);

        TichuHand th2 = new TichuHand();
        th2.player = player2Card.getPlayerName();
        th2.partner = player1Card.getPlayerName();
        th2.tichu = player2Card.isTichuCall();
        th2.grandTichu = player2Card.isGrandTichuCall();
        th2.imperialTichu = player2Card.isImperialTichuCall();
        th2.oneTwo = oneTwoBonusTeam1.isChecked();
        th2.oneTwoAgainst = oneTwoBonusTeam2.isChecked();
        th2.score = team1Score;

        TichuHandIntentService.startActionSaveHand(getActivity(), th2);

        TichuHand th3 = new TichuHand();
        th3.player = player3Card.getPlayerName();
        th3.partner = player4Card.getPlayerName();
        th3.tichu = player3Card.isTichuCall();
        th3.grandTichu = player3Card.isGrandTichuCall();
        th3.imperialTichu = player3Card.isImperialTichuCall();
        th3.oneTwo = oneTwoBonusTeam2.isChecked();
        th3.oneTwoAgainst = oneTwoBonusTeam1.isChecked();
        th3.score = team2Score;

        TichuHandIntentService.startActionSaveHand(getActivity(), th3);

        TichuHand th4 = new TichuHand();
        th4.player = player4Card.getPlayerName();
        th4.partner = player3Card.getPlayerName();
        th4.tichu = player4Card.isTichuCall();
        th4.grandTichu = player4Card.isGrandTichuCall();
        th4.imperialTichu = player4Card.isImperialTichuCall();
        th4.oneTwo = oneTwoBonusTeam2.isChecked();
        th4.oneTwoAgainst = oneTwoBonusTeam1.isChecked();
        th4.score = team2Score;

        TichuHandIntentService.startActionSaveHand(getActivity(), th4);
    }


    public void onResetButtonPressed(){
        getActivity().setTitle(getActivity().getTitle());
        totalScoreTeam1.setText("0");
        totalScoreTeam2.setText("0");

        clearSelections();
    }

    private void clearSelections(){
        oneTwoBonusTeam1.setChecked(false);
        oneTwoBonusTeam2.setChecked(false);

        pointsSeekbarTeam1.setProgress(15);
        pointsSeekbarTeam2.setProgress(15);

        player1Card.reset();
        player2Card.reset();
        player3Card.reset();
        player4Card.reset();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_TEAM_1_SCORE, totalScoreTeam1.getText().toString());
        savedInstanceState.putString(STATE_TEAM_2_SCORE, totalScoreTeam2.getText().toString());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null) {
            totalScoreTeam1.setText(savedInstanceState.getString(STATE_TEAM_1_SCORE));
            totalScoreTeam2.setText(savedInstanceState.getString(STATE_TEAM_2_SCORE));
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }

}

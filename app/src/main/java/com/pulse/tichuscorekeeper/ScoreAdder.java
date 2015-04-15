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

    private OnFragmentInteractionListener mListener;

    private TeamCard team1Card;
    private TeamCard team2Card;

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

        team1Card = (TeamCard) adder.findViewById(R.id.team1_card);
        team2Card = (TeamCard) adder.findViewById(R.id.team2_card);

        team1Card.setOpposingTeam(team2Card);
        team2Card.setOpposingTeam(team1Card);

        team1Card.setPlayer1Name("Player 1");
        team1Card.setPlayer2Name("Player 2");

        team2Card.setPlayer1Name("Player 3");
        team2Card.setPlayer2Name("Player 4");

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
        int team1HandPoints = team1Card.calculateHandPoints();
        int team2HandPoints = team2Card.calculateHandPoints();

        team1Card.computeStats(team1HandPoints, 0, 0);
        team2Card.computeStats(team2HandPoints, 0, 0);
        clearSelections();
    }

    private void saveStats(int team1Score, int team2Score) {
/*
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
        */
    }


    public void onResetButtonPressed(){
        getActivity().setTitle(getActivity().getTitle());
        team1Card.reset();
        team2Card.reset();
    }

    private void clearSelections(){
        team1Card.clearSelections();
        team2Card.clearSelections();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(STATE_TEAM_1_SCORE, team1Card.getTotalScore());
        savedInstanceState.putString(STATE_TEAM_2_SCORE, team2Card.getTotalScore());

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null) {
            team1Card.setTotalScore(savedInstanceState.getString(STATE_TEAM_1_SCORE));
            team2Card.setTotalScore(savedInstanceState.getString(STATE_TEAM_2_SCORE));
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

package com.pulse.tichuscorekeeper;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.pulse.tichuscorekeeper.manager.GameManager;

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

    private TextView handNumberLabel;

    private Button endHandButton;
    private Button resetHandButton;
    private Button newGameButton;

    private int hand = 1;
    private int game;

    private GameManager gameManager = new GameManager();


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

        handNumberLabel = (TextView) adder.findViewById(R.id.hand_number);
        handNumberLabel.setText(Integer.toString(hand));

        endHandButton = (Button) adder.findViewById(R.id.end_hand_button);
        endHandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onHandEndButtonPressed();
            }
        });

        resetHandButton = (Button) adder.findViewById(R.id.reset_button);
        resetHandButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelections();
            }
        });

        newGameButton = (Button) adder.findViewById(R.id.new_game_button);
        newGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearSelections();
                resetTeamScores();

                game = gameManager.getMaxGame() + 1;
                hand = 1;
                handNumberLabel.setText(Integer.toString(hand));
            }
        });

        game = gameManager.getMaxGame() + 1;

        return adder;
    }

    public void onHandEndButtonPressed(){
        final Animation shrinkHandLabel = AnimationUtils.loadAnimation(getActivity(), R.anim.shrink);
        int team1HandPoints = team1Card.calculateHandPoints();
        int team2HandPoints = team2Card.calculateHandPoints();

        team1Card.computeStats(team1HandPoints, hand, game);
        team2Card.computeStats(team2HandPoints, hand, game);

        hand++;
        handNumberLabel.setText(Integer.toString(hand));
        handNumberLabel.startAnimation(shrinkHandLabel);

        clearSelections();
    }

    public void resetTeamScores(){
        team1Card.resetScore();
        team2Card.resetScore();
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

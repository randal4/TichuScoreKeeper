package com.pulse.tichuscorekeeper;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;

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
    static final String HAND_NUMBER = "handNumber";

    private static final int ONE_TWO_POINTS = 200;

    private OnFragmentInteractionListener mListener;

    private PlayerCard player1Card;
    private PlayerCard player2Card;
    private PlayerCard player3Card;
    private PlayerCard player4Card;

    private TextView totalScoreTeam1;
    private CheckBox oneTwoBonusTeam1;
    private Spinner pointsSpinnerTeam1;

    private TextView totalScoreTeam2;
    private CheckBox oneTwoBonusTeam2;
    private Spinner pointsSpinnerTeam2;

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

        oneTwoBonusTeam1 = (CheckBox) adder.findViewById(R.id.one_two_team_1);
        oneTwoBonusTeam1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pointsSpinnerTeam1.setEnabled(false);
                    pointsSpinnerTeam2.setEnabled(false);
                    oneTwoBonusTeam2.setEnabled(false);
                }else{
                    pointsSpinnerTeam1.setEnabled(true);
                    pointsSpinnerTeam2.setEnabled(true);
                    oneTwoBonusTeam2.setEnabled(true);
                }
            }
        });
        oneTwoBonusTeam2 = (CheckBox) adder.findViewById(R.id.one_two_team_2);
        oneTwoBonusTeam2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    pointsSpinnerTeam1.setEnabled(false);
                    pointsSpinnerTeam2.setEnabled(false);
                    oneTwoBonusTeam1.setEnabled(false);
                }else{
                    pointsSpinnerTeam1.setEnabled(true);
                    pointsSpinnerTeam2.setEnabled(true);
                    oneTwoBonusTeam1.setEnabled(true);
                }
            }
        });

        pointsSpinnerTeam1 = (Spinner) adder.findViewById(R.id.points_spinner_team_1);

        pointsSpinnerTeam2 = (Spinner) adder.findViewById(R.id.points_spinner_team_2);
        final ArrayAdapter<CharSequence> pointsAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.points_array, android.R.layout.simple_spinner_item);
        pointsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        pointsSpinnerTeam1.setAdapter(pointsAdapter);
        pointsSpinnerTeam2.setAdapter(pointsAdapter);

        pointsSpinnerTeam1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer team1CurrentPoints = Integer.parseInt(pointsSpinnerTeam1.getSelectedItem().toString());
                Integer team2CurrentPoints = 100 - team1CurrentPoints;

                Integer team2position = pointsAdapter.getPosition(team2CurrentPoints.toString());

                pointsSpinnerTeam2.setSelection(team2position);
                Log.i(this.getClass().getName(), "Changed team2 selection");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Do nothing
            }
        });

        pointsSpinnerTeam2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Integer team2CurrentPoints = Integer.parseInt(pointsSpinnerTeam2.getSelectedItem().toString());
                Integer team1CurrentPoints = 100 - team2CurrentPoints;

                pointsSpinnerTeam1.setSelection(pointsAdapter.getPosition(team1CurrentPoints.toString()));
                Log.i(this.getClass().getName(), "Changed team1 selection");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Do Nothing
            }
        });

        //Start with spinner at 50 each
        pointsSpinnerTeam1.setSelection(15);

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
        Integer team1Score = Integer.parseInt(totalScoreTeam1.getText().toString());
        Integer team2Score = Integer.parseInt(totalScoreTeam2.getText().toString());

        if(oneTwoBonusTeam1.isChecked()) {
            team1Score += ONE_TWO_POINTS;
        }else if(oneTwoBonusTeam2.isChecked()) {
            //no points for you
        }else {
            team1Score += Integer.parseInt((String) pointsSpinnerTeam1.getSelectedItem());
        }

        if(oneTwoBonusTeam2.isChecked()) {
            team2Score += ONE_TWO_POINTS;
        }else if(oneTwoBonusTeam1.isChecked()) {
            //no points for you
        }else {
            team2Score += Integer.parseInt((String) pointsSpinnerTeam2.getSelectedItem());
        }

        team1Score += player1Card.getTichuPoints();
        team1Score += player2Card.getTichuPoints();
        team2Score += player3Card.getTichuPoints();
        team2Score += player4Card.getTichuPoints();

        totalScoreTeam1.setText(team1Score.toString());
        totalScoreTeam2.setText(team2Score.toString());

        clearSelections();
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

        pointsSpinnerTeam1.setSelection(15);

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

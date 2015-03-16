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

    private static final int TICHU_POINTS = 100;
    private static final int GRAND_TICHU_POINTS = 200;
    private static final int IMPERIAL_TICHU_POINTS = 400;

    private static final int ONE_TWO_POINTS = 200;

    private OnFragmentInteractionListener mListener;

    private TextView roundTitle;

    private TextView totalScoreTeam1;
    private CheckBox oneTwoBonusTeam1;
    private Spinner pointsSpinnerTeam1;
    private Spinner tichuSpinnerTeam1;

    private TextView totalScoreTeam2;
    private CheckBox oneTwoBonusTeam2;
    private Spinner pointsSpinnerTeam2;
    private Spinner tichuSpinnerTeam2;

    private Button endRoundButton;
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

        roundTitle = (TextView) adder.findViewById(R.id.round_title);
        roundTitle.setText("1");

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

        tichuSpinnerTeam1 = (Spinner) adder.findViewById(R.id.tichu_spinner_team_1);
        tichuSpinnerTeam2 = (Spinner) adder.findViewById(R.id.tichu_spinner_team_2);
        ArrayAdapter<CharSequence> tichuAdapter = ArrayAdapter.createFromResource(this.getActivity(), R.array.tichu_array, android.R.layout.simple_spinner_item);
        tichuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tichuSpinnerTeam1.setAdapter(tichuAdapter);
        tichuSpinnerTeam2.setAdapter(tichuAdapter);

        endRoundButton = (Button) adder.findViewById(R.id.end_round_button);
        endRoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRoundEndButtonPressed();
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

    public void onRoundEndButtonPressed(){
        String roundTitleText = roundTitle.getText().toString();
        Integer roundNumber = Integer.parseInt(roundTitleText);
        roundNumber = roundNumber + 1;
        roundTitle.setText(roundNumber.toString());

        Integer team1Score = Integer.parseInt(totalScoreTeam1.getText().toString());
        Integer team2Score = Integer.parseInt(totalScoreTeam2.getText().toString());

        if(oneTwoBonusTeam1.isChecked()) {
            team1Score += 200;
        }else if(oneTwoBonusTeam2.isChecked()) {
            //no points for you
        }else {

            team1Score += Integer.parseInt((String) pointsSpinnerTeam1.getSelectedItem());
        }

        if(oneTwoBonusTeam2.isChecked()) {
            team2Score += 200;
        }else if(oneTwoBonusTeam1.isChecked()) {
            //no points for you
        }else {
            team2Score += Integer.parseInt((String) pointsSpinnerTeam2.getSelectedItem());
        }

        team1Score += getTichuPoints(tichuSpinnerTeam1);
        team2Score += getTichuPoints(tichuSpinnerTeam2);

        totalScoreTeam1.setText(team1Score.toString());
        totalScoreTeam2.setText(team2Score.toString());

        clearSelections();
    }

    private Integer getTichuPoints(Spinner tichuSpinner) {
        Integer points = null;
        String tichu = (String) tichuSpinner.getSelectedItem();

        if(tichu.equals("(Choose Tichu)")) return 0;

        if(tichu.equals("Tichu Success")) {
           points = TICHU_POINTS;
        } else if(tichu.equals("Tichu Fail")){
            points = TICHU_POINTS * -1;
        } else if(tichu.equals("Grand Tichu Success")){
            points = GRAND_TICHU_POINTS;
        } else if(tichu.equals("Grand Tichu Fail")){
            points = GRAND_TICHU_POINTS * -1;
        } else if(tichu.equals("Imperial Tichu Success")){
            points = IMPERIAL_TICHU_POINTS;
        } else if(tichu.equals("Imperial Tichu Fail")) {
            points = IMPERIAL_TICHU_POINTS * -1;
        }

        return points;
    }

    public void onResetButtonPressed(){
        roundTitle.setText("1");
        totalScoreTeam1.setText("0");
        totalScoreTeam2.setText("0");

        oneTwoBonusTeam1.setChecked(false);
        oneTwoBonusTeam2.setChecked(false);

        pointsSpinnerTeam1.setSelection(0);
        //pointsSpinnerTeam2.setSelection(0);

        tichuSpinnerTeam1.setSelection(0);
        tichuSpinnerTeam2.setSelection(0);
    }

    private void clearSelections(){
        oneTwoBonusTeam1.setChecked(false);
        oneTwoBonusTeam2.setChecked(false);

        tichuSpinnerTeam1.setSelection(0);
        tichuSpinnerTeam2.setSelection(0);
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

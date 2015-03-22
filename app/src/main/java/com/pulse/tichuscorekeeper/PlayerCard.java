package com.pulse.tichuscorekeeper;

import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.content.Context;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by puLse on 3/21/15.
 */
public class PlayerCard extends RelativeLayout {
    private static final int TICHU_POINTS = 100;
    private static final int GRAND_TICHU_POINTS = 200;
    private static final int IMPERIAL_TICHU_POINTS = 400;

    private TextView playerName;
    private Button playerSelectButton;
    private Spinner tichuSpinner;
    private SeekBar tichuSeekbar;


    public PlayerCard(Context context){
        super(context);
        init(context);
    }

    public PlayerCard(Context context, AttributeSet attr){
        super(context, attr);
        init(context);
    }


    public void init(Context context){
        View v = View.inflate(this.getContext(), R.layout.player_card, this);

        playerName = (TextView) v.findViewById(R.id.player_name);
        playerSelectButton = (Button) v.findViewById(R.id.player_settings_button);

        tichuSpinner = (Spinner) v.findViewById(R.id.tichus_spinner);
        if(!isInEditMode()) {
            ArrayAdapter<CharSequence> tichuAdapter = ArrayAdapter.createFromResource(context, R.array.tichu_array, android.R.layout.simple_spinner_item);
            tichuAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            tichuSpinner.setAdapter(tichuAdapter);
        }

        tichuSeekbar = (SeekBar) v.findViewById(R.id.tichu_seekbar);

        reset();
    }

    public void reset(){
        tichuSeekbar.setProgress(1);
        tichuSpinner.setSelection(0);
    }

    public void setPlayerName(String name){
        playerName.setText(name);
    }

    public Integer getTichuPoints() {
        Integer points = null;
        String tichu = (String) tichuSpinner.getSelectedItem();
        Integer seekbarPosition = tichuSeekbar.getProgress();

        if(tichu.equals("(Choose Tichu)")) return 0;

        if(tichu.equals("Tichu")) {
            points = TICHU_POINTS;
        } else if(tichu.equals("Grand Tichu")){
            points = GRAND_TICHU_POINTS;
        } else if(tichu.equals("Imperial Tichu")){
            points = IMPERIAL_TICHU_POINTS;
        }

        if(seekbarPosition == 0){
            points = points * -1;
        }else if(seekbarPosition == 1){
            points = 0;
        }else if(seekbarPosition == 2){
            points = points;
        }

        return points;
    }
}

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
}

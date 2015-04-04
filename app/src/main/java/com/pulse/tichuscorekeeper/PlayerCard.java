package com.pulse.tichuscorekeeper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.content.Context;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.pulse.tichuscorekeeper.model.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by puLse on 3/21/15.
 */
public class PlayerCard extends RelativeLayout {
    private static final int TICHU_POINTS = 100;
    private static final int GRAND_TICHU_POINTS = 200;
    private static final int IMPERIAL_TICHU_POINTS = 400;

    private Player player;
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

        playerSelectButton = (Button) v.findViewById(R.id.player_settings_button);
        playerSelectButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("PlayerSettingsButton", String.format("Player Setting Clicked"));
                final AlertDialog.Builder adBuilder = new AlertDialog.Builder(getContext());

                adBuilder.setTitle(R.string.pick_player_header);

                final List<Player> playerList = new Select().from(Player.class).execute();

                List<String> playerNamesList = new ArrayList<String>(playerList.size());

                for(Player p : playerList){
                    playerNamesList.add(p.name);
                }

                //Collections.sort(playerNamesList);

                playerNamesList.add(0, "(Create New Player)");

                adBuilder.setCancelable(true);
                adBuilder.setItems(playerNamesList.toArray(new CharSequence[playerNamesList.size()]),new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("DialogOnClick", "Clicked");
                        ListView lw = ((AlertDialog) dialog).getListView();
                        String selected = (String) lw.getAdapter().getItem(which);
                        Log.d("DialogOnClick", "Selected : " + selected);

                        if(which == 0){
                            showCreateNewPlayerDialog();
                        }else{
                           setPlayer(playerList.get(which-1));
                           playerName.setText(selected);
                        }
                    }
                });
                adBuilder.show();
            }
        });
        tichuSeekbar = (SeekBar) v.findViewById(R.id.tichu_seekbar);

        reset();
    }

    private void showCreateNewPlayerDialog(){
        LayoutInflater li = LayoutInflater.from(getContext());
        View playerSelectView = li.inflate(R.layout.fragment_player_select_dialog, null);

        AlertDialog.Builder psBuilder = new AlertDialog.Builder(getContext());

        psBuilder.setView(playerSelectView);

        final EditText newPlayerName = (EditText) playerSelectView.findViewById(R.id.edit_text_player_name);

        psBuilder.setCancelable(false);

        psBuilder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Player newPlayer = new Player(newPlayerName.getText().toString());

                        newPlayer.save();
                        setPlayer(newPlayer);
                        playerName.setText(newPlayerName.getText());
                    }
                })
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

        AlertDialog ad = psBuilder.create();
        ad.show();

    }

    public void reset(){
        tichuSeekbar.setProgress(1);
        tichuSpinner.setSelection(0);
    }

    public void setPlayerName(String name){
        playerName.setText(name);
    }

    public String getPlayerName(){
        return playerName.getText().toString();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getTichuPoints() {
        int points = 0;
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

    public Boolean isTichuCall(){
        String tichu = (String) tichuSpinner.getSelectedItem();
        Integer seekbarPosition = tichuSeekbar.getProgress();

        if(tichu.equals("Tichu") && seekbarPosition == 0){
            return false;
        }else if(tichu.equals("Tichu") && seekbarPosition == 2){
            return true;
        }else{
            return null;
        }
    }

    public Boolean isGrandTichuCall(){
        String tichu = (String) tichuSpinner.getSelectedItem();
        Integer seekbarPosition = tichuSeekbar.getProgress();

        if(tichu.equals("Grand Tichu") && seekbarPosition == 0){
            return false;
        }else if(tichu.equals("Grand Tichu") && seekbarPosition == 2){
            return true;
        }else{
            return null;
        }
    }

    public Boolean isImperialTichuCall(){
        String tichu = (String) tichuSpinner.getSelectedItem();
        Integer seekbarPosition = tichuSeekbar.getProgress();

        if(tichu.equals("Imperial Tichu") && seekbarPosition == 0){
            return false;
        }else if(tichu.equals("Imperial Tichu") && seekbarPosition == 2){
            return true;
        }else{
            return null;
        }
    }
}

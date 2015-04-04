package com.pulse.tichuscorekeeper.service;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import com.pulse.tichuscorekeeper.manager.HandManager;
import com.pulse.tichuscorekeeper.model.TichuHand;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class TichuHandIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_SAVE_HAND = "com.pulse.tichuscorekeeper.action.SAVE_HAND";

    // TODO: Rename parameters
    private static final String TICHU_HAND = "com.pulse.tichuscorekeeper.extra.TICHU_HAND";

    private HandManager handManager = new HandManager();
    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionSaveHand(Context context, TichuHand tichuHand) {
        Intent intent = new Intent(context, TichuHandIntentService.class);
        intent.setAction(ACTION_SAVE_HAND);
        intent.putExtra(TICHU_HAND, tichuHand);
        context.startService(intent);
    }

    public TichuHandIntentService() {
        super("TichuHandIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SAVE_HAND.equals(action)) {
                final TichuHand hand = intent.getParcelableExtra(TICHU_HAND);
                handleActionSaveHand(hand);
            }
        }
    }

    private void handleActionSaveHand(TichuHand hand) {
        handManager.saveHand(hand);
    }
}

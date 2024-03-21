package com.github.kalsmic.android.pomodorotimer;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.preference.PreferenceManager;

public class Sound {
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    MediaPlayer mediaPlayer;
    private final Context context;
    private int soundId = 0;

    public Sound(Context context) {
        this.context = context;
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        editor = sharedPreferences.edit();
    }

    /**
     * @param soundId the identify of the sound
     * @return sound with specified id
     */
    public static int getSound(int soundId) {
        switch (soundId) {
            default:
            case 0:
                return R.raw.beep;
            case 1:
                return R.raw.magic_bubble_shimmer;
            case 2:
                return R.raw.small_bell_ring;
            case 3:
                return R.raw.single_shot;
            case 4:
                return R.raw.pop_goes_the_weasel;
            case 5:
                return R.raw.synth;

        }
    }

    /**
     * @return the id of the default sound
     */
    public int getDefaultSoundId() {
        if (sharedPreferences.contains("soundId")) {
            soundId = sharedPreferences.getInt("soundId", 0);
        }
        return soundId;
    }

    /**
     * @return the default sound Uri
     */
    public int getDefaultSound() {
        return getSound(soundId);
    }

    /**
     * This method changes the default sound to the one with specified id.
     *
     * @param soundId the id of the new default sound
     */
    public void setDefaultSound(int soundId) {

        // store value in shared prefs file.
        editor.putInt("soundId", soundId);
        // persist the changes
        editor.commit();
    }

    /**
     * This method creates a media sound object.
     *
     * @param soundId is the reference to the sound
     * @return MediaPlayer object for sound with id soundId
     */
    public MediaPlayer getMediaPlayer(int soundId) {
        mediaPlayer = MediaPlayer.create(context, Sound.getSound(soundId));
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });
        return mediaPlayer;
    }

    /**
     * This method plays a sound with specified id
     *
     * @param soundId the id of the sound
     */
    public void play(int soundId) {
        // Always release mediaPlay Object if it exists
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        // force release object reference
        mediaPlayer = null;
        // create new MediaPlayer object for selected sound
        getMediaPlayer(soundId);
        mediaPlayer.start();
    }

    /**
     * This method plays the default sound
     */
    public void playDefaultSound() {
        play(getDefaultSoundId());
    }


}

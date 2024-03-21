package com.github.kalsmic.android.pomodorotimer

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaPlayer.OnCompletionListener
import androidx.core.content.edit


private const val NOTIFICATION_PREFERENCES_NAME = "sound_prefences"

class Sound(private val context: Context) {

    private val sharedPreferences = context.applicationContext.getSharedPreferences(
        NOTIFICATION_PREFERENCES_NAME, Context.MODE_PRIVATE
    )


    private var mediaPlayer: MediaPlayer? = null
    private var soundId = 0

    val defaultSoundId: Int
        /**
         * @return the id of the default sound
         */
        get() {
            soundId = sharedPreferences.getInt(NOTIFICATION_PREFERENCES_NAME, 0)
            return soundId
        }

    var defaultSound: Int
        /**
         * @return the default sound Uri
         */
        get() = getSound(soundId)
        /**
         * This method changes the default sound to the one with specified id.
         *
         * @param soundId the id of the new default sound
         */
        set(soundId) {
            sharedPreferences.edit {
                putInt(NOTIFICATION_PREFERENCES_NAME, soundId)
            }
        }

    /**
     * This method creates a media sound object.
     *
     * @param soundId is the reference to the sound
     * @return MediaPlayer object for sound with id soundId
     */
    private fun getMediaPlayer(soundId: Int): MediaPlayer? {
        mediaPlayer = MediaPlayer.create(context, getSound(soundId))
        mediaPlayer?.setOnCompletionListener(OnCompletionListener { mediaPlayer!!.release() })
        return mediaPlayer
    }

    /**
     * This method plays a sound with specified id
     *
     * @param soundId the id of the sound
     */
    fun play(soundId: Int) {
        // Always release mediaPlay Object if it exists
        if (mediaPlayer != null) mediaPlayer?.release()
        // force release object reference
        mediaPlayer = null
        // create new MediaPlayer object for selected sound and play the sound
        getMediaPlayer(soundId)?.start()
    }

    /**
     * This method plays the default sound
     */
    fun playDefaultSound() {
        play(defaultSoundId)
    }


    companion object {
        /**
         * @param soundId the identify of the sound
         * @return sound with specified id
         */
        fun getSound(soundId: Int): Int {
            return when (soundId) {
                0 -> R.raw.beep
                1 -> R.raw.magic_bubble_shimmer
                2 -> R.raw.small_bell_ring
                3 -> R.raw.single_shot
                4 -> R.raw.pop_goes_the_weasel
                5 -> R.raw.synth

                else -> R.raw.beep
            }
        }
    }
}

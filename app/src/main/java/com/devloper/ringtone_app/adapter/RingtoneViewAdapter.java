package com.devloper.ringtone_app.adapter;


import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devloper.ringtone_app.MainActivity;
import com.devloper.ringtone_app.NavActivity;
import com.devloper.ringtone_app.R;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.List;

public class RingtoneViewAdapter extends RecyclerView.Adapter<RingtoneViewAdapter.RingtoneViewHolder> {

    private Context context;
    private List<RingtoneObject> allSongs;
    private boolean isAudioPlaying = false;
    MediaPlayer mediaPlayer;
    private boolean isAudioInitialize = false;


    public RingtoneViewAdapter(Context context, List<RingtoneObject> allSongs) {
        this.context = context;
        this.allSongs = allSongs;
        mediaPlayer = new MediaPlayer();

    }

    public RingtoneViewAdapter(ValueEventListener valueEventListener, List<RingtoneObject> mRingtoneObject) {

    }


    @NonNull
    @Override
    public RingtoneViewAdapter.RingtoneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.song_list_layout, viewGroup, false);
        return new RingtoneViewAdapter.RingtoneViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull final RingtoneViewAdapter.RingtoneViewHolder ringtoneViewHolder, int position) {
        final RingtoneObject uploadCur = allSongs.get(position);
        ringtoneViewHolder.audio_description.setText(uploadCur.imgName);
        ringtoneViewHolder.play_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playRingtone(uploadCur.imgUrl);
            }
        });

    }

    public void playRingtone(String imgUrl) {

        if (!isAudioPlaying) {
            isAudioPlaying = true;


            try{
                //you can change the path, here path is external directory(e.g. sdcard) /Music/maine.mp3
                if(!isAudioInitialize) {
                    isAudioInitialize = true;
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mediaPlayer.setDataSource(imgUrl);

                    mediaPlayer.prepare();


                }
                mediaPlayer.start();
            }catch(Exception e){e.printStackTrace();}

        } else {
            mediaPlayer.pause();
            isAudioPlaying = false;
            mediaPlayer.reset();




        }

    }



    @Override
    public int getItemCount() {
        return allSongs.size();
    }




    public class RingtoneViewHolder extends RecyclerView.ViewHolder {
        public TextView audio_description;
        public Button play_audio;

        public RingtoneViewHolder(@NonNull View itemView) {
            super(itemView);
            audio_description = itemView.findViewById(R.id.audio_description);
            play_audio = itemView.findViewById(R.id.playPause_button);

        }
    }
}
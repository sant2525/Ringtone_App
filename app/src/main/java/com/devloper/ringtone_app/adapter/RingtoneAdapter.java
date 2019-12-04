package com.devloper.ringtone_app.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.devloper.ringtone_app.R;
import com.devloper.ringtone_app.SecondActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static com.firebase.ui.auth.AuthUI.TAG;


public class RingtoneAdapter extends RecyclerView.Adapter<RingtoneAdapter.RingtoneViewHolder> {
    private static final String TAG = "RecyclerAdapter";

    private Context context;
    private List<RingtoneObject> allSongs;
    private boolean isAudioPlaying = false;
    private boolean playPauseBtn = true;
    private boolean progressbar = false;
    MediaPlayer mediaPlayer1;
    private Button playingView;
    private DatabaseReference mDatabaseRef;
    private Map<ImageView, Boolean> isFavHashMap = new HashMap<>();





    public RingtoneAdapter(Context context, List<RingtoneObject> allSongs) {
        this.context = context;
        mediaPlayer1 = new MediaPlayer();
        this.allSongs = allSongs;
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("FavouriteRingtone"); //Dont pass any path if you want root of the tree


    }


    @NonNull
    @Override
    public RingtoneAdapter.RingtoneViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Log.d(TAG, "onCreateViewHolder: Element view of recycler list");
        View v = LayoutInflater.from(context).inflate(R.layout.ringtone_items,viewGroup,false);
        return new RingtoneAdapter.RingtoneViewHolder(v);

    }


    @Override
    public void onBindViewHolder(@NonNull final RingtoneAdapter.RingtoneViewHolder ringtoneViewHolder, final int i) {
        final RingtoneObject uploadCur = allSongs.get(i);
        boolean isFavRingtone = false;

        ringtoneViewHolder.audio_description.setText(uploadCur.imgName);
        Random rnd = new Random();
        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
        ringtoneViewHolder.cardb.setBackgroundColor(color);
        ringtoneViewHolder.play_audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (playPauseBtn) {
                    playingView = ringtoneViewHolder.play_audio;
                    if (mediaPlayer1.isPlaying()) {
                        mediaPlayer1.stop();
                        mediaPlayer1.prepareAsync();
                        ringtoneViewHolder.play_audio.setBackgroundResource(R.drawable.pay_image);


                    } else {
                        mediaPlayer1.start();
                        ringtoneViewHolder.seekBar.setVisibility(View.VISIBLE);
                        ringtoneViewHolder.play_audio.setBackgroundResource(R.drawable.stop_button);
                    }
                    playPauseBtn = false;
                } else {
                    if (mediaPlayer1.isPlaying()) {
                        mediaPlayer1.stop();
                        mediaPlayer1.prepareAsync();
                        ringtoneViewHolder.play_audio.setBackgroundResource(R.drawable.pay_image);

                    } else {
                        mediaPlayer1.start();
                        ringtoneViewHolder.seekBar.setVisibility(View.VISIBLE);
                        ringtoneViewHolder.play_audio.setBackgroundResource(R.drawable.stop_button);
                    }
                    playingView.setBackgroundResource(R.drawable.pay_image);
                    playPauseBtn = true;
                }
                playRingtone(uploadCur.imgUrl, ringtoneViewHolder);

            }
        });
        ringtoneViewHolder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ringtoneViewHolder.favourite.setBackgroundResource(R.drawable.ic_favorite_active);
            }


        });
        ringtoneViewHolder.cardb.setOnClickListener(new View.OnClickListener() {
            private int adapterPosition;

            public void setAdapterPosition(int adapterPosition) {
                this.adapterPosition = adapterPosition;
            }

            public int getAdapterPosition() {
                return adapterPosition;
            }

            @Override
            public void onClick(View v) {
                int p = getAdapterPosition();
                RingtoneObject c = allSongs.get(p);

                Intent intent = new Intent(context, SecondActivity.class);

                context.startActivity(intent);

            }
        });
    }



    public void playRingtone(String imgUrl, final RingtoneViewHolder ringtoneViewHolder) {

        if (!isAudioPlaying) {
            isAudioPlaying = true;
            try {
                mediaPlayer1.stop();
                mediaPlayer1.setDataSource(imgUrl);
                mediaPlayer1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.start();
                        ringtoneViewHolder.seekBar.setVisibility(View.VISIBLE);
                    }
                });
                mediaPlayer1.prepareAsync();

            } catch (Exception e) {
                e.getMessage();
            }

        } else {
            mediaPlayer1.pause();
            ringtoneViewHolder.seekBar.setVisibility(View.INVISIBLE);
            mediaPlayer1.reset();
            isAudioPlaying = false;
        }
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ringtoneViewHolder.seekBar.setProgress(mediaPlayer1.getCurrentPosition());
                ringtoneViewHolder.seekBar.setMax(mediaPlayer1.getDuration());
            }
        },0,100);
    }

    @Override
    public int getItemCount() {
        return allSongs.size();
    }

    public interface OnItemClickListener {
    }


    public class RingtoneViewHolder extends RecyclerView.ViewHolder {

        public TextView audio_description;
        public CardView card_view;
        public Button play_audio;
        public ImageView favourite;
        public RelativeLayout cardb;
        public ProgressBar progress;
        public SeekBar seekBar;
        ImageView checkBoxFav;

        public RingtoneViewHolder(@NonNull View itemView) {
            super(itemView);
            audio_description = itemView.findViewById(R.id.audio_description);
            card_view = itemView.findViewById(R.id.card_View);
            play_audio = itemView.findViewById(R.id.playPause_button);
            checkBoxFav = itemView.findViewById(R.id.isFav);
            cardb = itemView.findViewById(R.id.cardback);
            seekBar = itemView.findViewById(R.id.seekBar);
            progress = itemView.findViewById(R.id.progress_play);
            favourite = itemView.findViewById(R.id.isFav);


        }


    }
}




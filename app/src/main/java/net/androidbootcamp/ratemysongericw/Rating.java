package net.androidbootcamp.ratemysongericw;


import android.content.Intent;
        import android.media.MediaPlayer;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.RatingBar;

public class Rating extends AppCompatActivity {
    Integer HoldSpot = 0;

    String[] xmusic = {"Perfect", "What About Us", "Another Brick in the Wall"};
    String[] iAlbumCover = {"@drawable/edsheeran","@drawable/pink","@drawable/pinkfloyd"};
    String[] iMP3 = {"@raw/another_brick_in_the_wall","@raw/perfect","@raw/what_about_us"};
    Button PlayButton;
    MediaPlayer mp3;
    RatingBar RatingBar1;
    int holdRating = 0;
    int playing;

    Integer nHold = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            HoldSpot = (Integer) bundle.get("HoldSpot");
            holdRating = (Integer) bundle.get("HoldRating");
            nHold = HoldSpot;

        }

        ImageView imgChange = (ImageView) findViewById(R.id.imageView);
        int resourceID = getResources().getIdentifier(iAlbumCover[nHold], "id", this.getPackageName());
        imgChange.setImageResource(resourceID);

        PlayButton = (Button) findViewById(R.id.btnMusic);
        PlayButton.setOnClickListener(bPlaying);
        mp3 = new MediaPlayer();
        resourceID = getResources().getIdentifier(iMP3[nHold],"raw", this.getPackageName());
        mp3 = MediaPlayer.create(this, resourceID);



        RatingBar1 = (RatingBar) findViewById(R.id.rb1);
        RatingBar1.setRating(holdRating);

    }

    Button.OnClickListener bPlaying = new Button.OnClickListener(){
        @Override
        public void onClick(View v){
            if (playing == 0) {
                playing = 1;
                mp3.start();
                PlayButton.setText(getText(R.string.btnStop));

            } else {
                playing = 0;
                mp3.pause();
                PlayButton.setText(getText(R.string.btnPlay));
            }
        }
    };

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        int x = 0;

        RatingBar1 = (RatingBar) findViewById(R.id.rb1);
        x = Math.round(RatingBar1.getRating());
        i.putExtra("HoldRating",x);

        setResult(RESULT_OK, i);
        finish();
    }

}

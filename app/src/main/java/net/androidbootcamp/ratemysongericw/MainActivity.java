package net.androidbootcamp.ratemysongericw;

import android.app.ListActivity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends ListActivity {
    String[] xMusic = {"Another Brick in the Wall", "Perfect", "What About Us"};
    Integer[] nRating = {1,2,2};
    Integer HoldSpot = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setListAdapter(new ArrayAdapter<String>(this, R.layout.activity_main, R.id.txtMusic, xMusic));
    }

    protected void onListItemClick(ListView l, View v, int position, long id) {
        Intent intent = new Intent(MainActivity.this, Rating.class);
        intent.putExtra("HoldSpot", position);
        HoldSpot = position;
        intent.putExtra("HoldRating",nRating[position]);
        startActivityForResult(intent, 1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                Integer HoldRating = data.getIntExtra("HoldRating",0);

                nRating[HoldSpot] = HoldRating;
            }
        }
    }
}

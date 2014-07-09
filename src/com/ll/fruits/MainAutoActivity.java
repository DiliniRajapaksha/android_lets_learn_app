/**
 * 
 */
package com.ll.fruits;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author user
 * 
 */
public class MainAutoActivity extends Activity implements OnInitListener {

    private static final int DATA_CHECK_CODE = 1121212;
    private static final long DELAY = 5000;
    private FruitDAO dao;
    private TextToSpeech tts;
    private Random random;
    private ImageView img;
    private TextView txtName;
    private Button btnReplay;

    private List<Fruit> fruits;
    private List<Fruit> fruitsClone;

    private Handler handler;
    private Runnable runnable;

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onCreate(android.os.Bundle)
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("Dilini", "on create");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fruits_auto);
       
        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        dao = new FruitDAO(dbHelper);
        fruits = dao.findAllFruits();
        fruitsClone = new ArrayList<Fruit>();
        fruitsClone.addAll(fruits);

        random = new Random();
        handler = new Handler();
        img = (ImageView) findViewById(R.id.imgAuto);
        txtName = (TextView) findViewById(R.id.txtNameAuto);
        btnReplay = (Button) findViewById(R.id.btnReplayAuto);
        btnReplay.setEnabled(false);
        btnReplay.setVisibility(View.GONE);
        btnReplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                fruitsClone.addAll(fruits);
                handler.postDelayed(runnable, DELAY);
            }
        });

        Intent checkIntent = new Intent();
        checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
        startActivityForResult(checkIntent, DATA_CHECK_CODE);

        runnable = new Runnable() {
            @Override
            public void run() {
                play();
            }
        };
        handler.postDelayed(runnable, DELAY);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == DATA_CHECK_CODE) {
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
                // success, create the TTS instance
                tts = new TextToSpeech(this, this);
            } else {
                // missing data, install it
                Intent installIntent = new Intent();
                installIntent
                        .setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installIntent);
            }
        }
    }

    protected void play() {
        handler.postDelayed(runnable, DELAY);
        if (fruitsClone.isEmpty()) {
            btnReplay.setEnabled(true);
            btnReplay.setVisibility(View.VISIBLE);
            handler.removeCallbacks(runnable);
        } else {
            btnReplay.setVisibility(View.GONE);
            btnReplay.setEnabled(false);
            int num = random.nextInt(fruitsClone.size());
            Fruit fruit = fruitsClone.get(num);
            img.setImageResource(fruit.getImgResId());
            txtName.setText(fruit.getName());
            tts.speak(fruit.getName(), TextToSpeech.QUEUE_FLUSH, null);
            fruitsClone.remove(num);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onDestroy()
     */
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        tts.shutdown();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onPause()
     */
    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onRestart()
     */
    @Override
    protected void onRestart() {
        // TODO Auto-generated method stub
        super.onRestart();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onResume()
     */
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    /*
     * (non-Javadoc)
     * 
     * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO Auto-generated method stub
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onInit(int status) {

        tts.setLanguage(Locale.US);

    }
}

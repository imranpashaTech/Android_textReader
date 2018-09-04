package tech.imranpasha.textreader.textreader;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;


public class newscard extends AppCompatActivity {

    private TextView label_tv,news_tv,reporter_tv;
    private String label_txt,news_txt,reporter_txt,full_txt;
    private TextToSpeech speaker;
    private boolean speaking_status;
    private ImageView play_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newscard);

        init();
        load_data();

        speaker=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    speaker.setLanguage(Locale.UK);
                }
            }
        });
    }

    private void load_data() {
        label_tv.setText(label_txt);
        news_tv.setText(news_txt);
        reporter_tv.setText(reporter_txt);
    }

    private void init() {
        label_txt="Funding Circle to raise £300m with listing";
        news_txt="The firm offers loans to small businesses in the US, Germany and the Netherlands as well as the UK and the funds will be used to expand in new markets.Funding Circle said the listing would help engender trust with investors, borrowers and regulators.The venture was founded in London in August 2010.";
        reporter_txt="Reported by: imran pasha";

        label_tv=(TextView)findViewById(R.id.card_label_tv);
        news_tv=(TextView)findViewById(R.id.card_news_tv);
        reporter_tv=(TextView)findViewById(R.id.card_reporter_tv);
        play_iv=(ImageView)findViewById(R.id.play_IV);

        speaking_status=true;

        full_txt=label_txt+news_txt+reporter_txt;
    }

    public void play_news(View view) {
        if(speaking_status){
//            play mode
            speaker.speak(full_txt, TextToSpeech.QUEUE_FLUSH, null);
            speaking_status=false;
            play_iv.setImageResource(R.mipmap.ic_stop);
        }else{
//            pause mode
            if(speaker !=null){
                speaker.stop();
//                speaker.shutdown();
            }
            super.onPause();
            play_iv.setImageResource(R.mipmap.ic_play);
            speaking_status=true;
        }
    }
}

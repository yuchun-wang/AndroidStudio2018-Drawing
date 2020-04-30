package yuc1996229.iim.ncku.edu.tw.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class QuestionActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question);

        int i =  (int)(Math.random()*5 + 1);
        setQuestion(i);

        Button button = (Button)findViewById(R.id.start);
        button.setOnClickListener(new Button.OnClickListener(){
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(QuestionActivity.this, DrawPictureActivity.class);
                    startActivity(intent);
                    QuestionActivity.this.finish();
                }
            });
    }

    public void setQuestion(int QA) {

        TextView qn = (TextView) findViewById(R.id.qname);
        ImageView qp = (ImageView)findViewById(R.id.qphoto);

        if(QA == 1){
            qn.setText("IRONMAN");
            qp.setImageResource(R.drawable.ironman);
        }
        if(QA == 2){
            qn.setText("Dragonite");
            qp.setImageResource(R.drawable.dragonite);
        }
        if(QA == 3){
            qn.setText("BB-8");
            qp.setImageResource(R.drawable.bb8);
        }
        if(QA == 4){
            qn.setText("R2D2");
            qp.setImageResource(R.drawable.r2d2);
        }
        if(QA == 5){
            qn.setText("BATMAN");
            qp.setImageResource(R.drawable.batman);
        }
    }
}

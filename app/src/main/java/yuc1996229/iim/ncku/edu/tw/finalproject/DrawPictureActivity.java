package yuc1996229.iim.ncku.edu.tw.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class DrawPictureActivity extends Activity{

    private DrawingView drawingView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.draw);

        drawingView = (DrawingView) findViewById(R.id.drawingView);

        Button button = (Button) findViewById(R.id.again);
        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(DrawPictureActivity.this, QuestionActivity.class);
                startActivity(intent);
                DrawPictureActivity.this.finish();
            }
        });
    }

        public void OnSaveClicked(View view) {
                try {
                    File file = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".jpg");
                    OutputStream stream = new FileOutputStream(file);
                    drawingView.saveBitmap(stream);
                    stream.close();
                    // send broadcast to Media to update data
                    /*Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
                    intent.setData(Uri.fromFile(Environment
                            .getExternalStorageDirectory()));
                    sendBroadcast(intent);*/

                    Toast.makeText(this, "save success", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(this, "save failed", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.setClass(DrawPictureActivity.this, FinishActivity.class);
                startActivity(intent);
                DrawPictureActivity.this.finish();
            }
}

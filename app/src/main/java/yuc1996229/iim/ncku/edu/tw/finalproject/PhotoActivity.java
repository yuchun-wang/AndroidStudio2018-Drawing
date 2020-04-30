package yuc1996229.iim.ncku.edu.tw.finalproject;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class PhotoActivity extends Activity {

    private ImageView mImg;
    private DisplayMetrics mPhone;
    private static Bitmap photo;
    private final static int CAMERA = 66 ;
    private final static int PHOTO = 99 ;
    Uri uri;
    boolean go = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo);

        mPhone = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(mPhone);

        mImg = (ImageView) findViewById(R.id.img);
        Button mCamera = (Button) findViewById(R.id.take);
        Button mPhoto = (Button) findViewById(R.id.select);
        Button mOK = (Button)findViewById(R.id.ok2);

        mOK.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();

                if(go == false){
                    Toast toast = Toast.makeText(PhotoActivity.this, "Setting the photo!!", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    String Takename = getIntent().getStringExtra("Name");
                    intent.putExtra("Name", Takename);
                    //intent.putExtra("Photo", photo);

                    intent.setClass(PhotoActivity.this, StartActivity.class);
                    startActivity(intent);
                    PhotoActivity.this.finish();
                }
            }
        });

        mCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues value = new ContentValues();
                value.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                Uri uri= getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, value);
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri.getPath());
                startActivityForResult(intent, CAMERA);
            }
        });

        mPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        if ((requestCode == CAMERA || requestCode == PHOTO ) && data != null) {
            uri = data.getData();
            ContentResolver cr = this.getContentResolver();

            try {
                Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));

                if(bitmap.getWidth() > bitmap.getHeight())
                    ScalePic(bitmap, mPhone.heightPixels);
                else ScalePic(bitmap,mPhone.widthPixels);
            }
            catch (FileNotFoundException e) {
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void ScalePic(Bitmap bitmap,int phone) {
        float mScale = 1 ;

        if(bitmap.getWidth() > phone ) {
            mScale = (float)phone/(float)bitmap.getWidth();

            Matrix mMat = new Matrix() ;
            mMat.setScale(mScale, mScale);

            Bitmap mScaleBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), mMat, false);
            mImg.setImageBitmap(mScaleBitmap);
            photo = mScaleBitmap;
            go = true;
        }
        else {
            mImg.setImageBitmap(bitmap);
            photo = bitmap;
            go = true;
        }
    }
}

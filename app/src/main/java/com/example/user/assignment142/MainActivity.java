package com.example.user.assignment142;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

//Creating class by extending AppCompatActivity and implementing OnClickListener.
public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    //Creating references of Classes whose elements are used in the layout.
    ImageView dataTaker;
    Button saveBtn,showBtn;
    Bitmap bitmap;
    File fileToSaveImage;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);   //Setting contentView.

        //Setting references with their IDs.
        dataTaker = (ImageView) findViewById(R.id.image_data);
        saveBtn = (Button) findViewById(R.id.save_btn);
        showBtn = (Button) findViewById(R.id.check_btn);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.bird);

        //Setting onclick listener to the Buttons.
        saveBtn.setOnClickListener(this);
        showBtn.setOnClickListener(this);
    }

    //Private method to save the image in the external storage of android.
    private void storeImage(Bitmap imageToSave)
    {
        //Getting path of External storage and converting it to String.
        String pathToSave = Environment.getExternalStorageState().toString();

        //Creating file on above path and setting File name.
        fileToSaveImage = new File(pathToSave,"test"+".jpg");

        try
        {
            //Crreating OutputStream for file.
            OutputStream outputStream = null;
            outputStream = new FileOutputStream(fileToSaveImage);

            //Compressing bitmap image to outputStream.
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);

            outputStream.flush();  //flushing outputStream.
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    @Override
    //onClick method to handle event when button is clicked.
    public void onClick(View v)
    {
        //Checking which Button is clicked by switch statement.
        switch (v.getId())
        {
            //When SAVE is clicked.
            case R.id.save_btn : Toast.makeText(getApplicationContext(),"Writing to File",Toast.LENGTH_SHORT).show();
                storeImage(bitmap);  //storing image.
                Toast.makeText(getApplicationContext(),"Sucess!",Toast.LENGTH_LONG).show();
                break;
            case R.id.check_btn : Toast.makeText(getApplicationContext(),"Reading from File",Toast.LENGTH_SHORT).show();
                dataTaker.setImageBitmap(bitmap);  //displaying image.
                Toast.makeText(getApplicationContext(),"Sucess!",Toast.LENGTH_LONG).show();
        }
    }
}

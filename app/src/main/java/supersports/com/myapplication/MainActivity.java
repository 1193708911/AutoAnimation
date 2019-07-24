package supersports.com.myapplication;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.lang.reflect.Constructor;

public class MainActivity extends AppCompatActivity {


    private ImageView mImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        mImg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SecondActivity.class),
                        ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, v, "sharedView").toBundle());
            }
        });


    }


    private void initView() {
        mImg = (ImageView) findViewById(R.id.img);
    }
}

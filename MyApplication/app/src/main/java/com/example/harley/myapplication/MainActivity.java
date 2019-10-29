package com.example.harley.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.Image;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private IntentFilter intentFilter;
    private NetworkChangeReceiver networkChangeReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView=(TextView)findViewById(R.id.tv);
        String text=textView.getText().toString();
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
        //SystemClock.sleep(1000);
        //
        intentFilter =new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        networkChangeReceiver=new NetworkChangeReceiver();
        registerReceiver(networkChangeReceiver,intentFilter);
        Button button=(Button)findViewById(R.id.button);
        final ImageView imageView=(ImageView)findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent=new Intent("com.example.harley.myapplication");
                sendBroadcast(intent);
                textView.setText("开始广播！");
                imageView.setImageResource(R.drawable.i_2);
            }
        });
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        unregisterReceiver(networkChangeReceiver);
    }
    class NetworkChangeReceiver extends BroadcastReceiver{
        public void onReceive(Context context, Intent intent){
            ConnectivityManager connectivityManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo=connectivityManager.getActiveNetworkInfo();
            if(networkInfo!=null&&networkInfo.isAvailable()){
                Toast.makeText(context,"宋恒宇已连接网络",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(context,"宋恒宇未连接网络",Toast.LENGTH_SHORT).show();
            }

        }
    }

}

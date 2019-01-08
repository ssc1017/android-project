package com.shusichao.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 去除actionbar换成title.xml
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // 如果activity被destroy了，为了重开时恢复数据采用这种
        if (savedInstanceState != null) {
            String tempData = savedInstanceState.getString("data_key");
        }


        Button button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Toast.makeText(MainActivity.this, "click button1", Toast.LENGTH_SHORT).show();
                // Intent intent = new Intent(MainActivity.this, SecondActivity.class); // 显示intent
                Intent intent = new Intent("com.example.myapplication.ACTION_START"); // 隐式intent，添加action
                intent.addCategory("com.example.myapplication.MY_CATEGORY");
                startActivity(intent); //测试下内部指针
                */

                /*
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://wwww.baidu.com"));
                startActivity(intent);
                */

                /*
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
                */

                /*
                String data = "Hello SecondActivity";
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("extra_data", data);
                startActivityForResult(intent, 1);
                */
                SecondActivity.actionStart(MainActivity.this, "Hello SecondActivity");
            }
        });

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState);
        String tempData = "Something you just typed";
        outState.putString("data_key", tempData);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    Log.d(TAG, returnedData);
                }
                break;
                default:
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "add", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "remove", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}

package com.example.cipher1729.httptest;

import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.textView);
        tv.setMovementMethod(new ScrollingMovementMethod() {
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==R.id.do_task)
        {
            myBackgroundTask task = new myBackgroundTask();
            task.execute("Param1","Param2","Param3");
        }


        return super.onOptionsItemSelected(item);
    }

    void updateDisplay(String text)
    {
        tv.append(text+"\n");
    }

    private class myBackgroundTask extends AsyncTask<String,String, String>
    {
        @Override
        protected void onPreExecute()
        {
            updateDisplay("Starting");
        }

        @Override
        protected String doInBackground(String... strings) {
            return "Task complete!";
        }


        @Override
        protected void onPostExecute(String result)
        {
            updateDisplay(result);
        }
    }
}

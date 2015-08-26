package com.example.cipher1729.httptest;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;


public class MainActivity extends ActionBarActivity {

    TextView tv;
    ProgressBar pb;
    List<myBackgroundTask> lists;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.textView);
        pb= (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);
        tv.setMovementMethod(new ScrollingMovementMethod() {
        });
        lists = new ArrayList<>();

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
        {if(isOnline())
            requestTask("http://www.w3schools.com/xml/note.xml");
            else
            Toast.makeText(this,"Not online sorry :(",Toast.LENGTH_LONG).show();
        }


        return super.onOptionsItemSelected(item);
    }

    private void requestTask(String uri) {
        myBackgroundTask task = new myBackgroundTask();
        task.execute(uri);
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
            if(lists.size()==0)
            pb.setVisibility(View.VISIBLE);
            lists.add(this);
        }

        @Override
        protected String doInBackground(String... strings) {
            String content = HttpManager.getData(strings[0]);
            return content;
        }


        @Override
        protected void onPostExecute(String result)
        {   lists.remove(this);
            updateDisplay(result);
            if(lists.size()==0)
            pb.setVisibility(View.INVISIBLE);
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            updateDisplay(values[0]);
        }
    }

    private boolean isOnline()
    {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo!= null && networkInfo.isConnectedOrConnecting())
            return true;
        else
            return false;
    }
}

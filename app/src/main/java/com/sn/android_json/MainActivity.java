package com.sn.android_json;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sn.android_json.domain.Person;
import com.sn.android_json.util.HttpUtils;
import com.sn.android_json.util.JsonTools;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    /** Called when the activity is first created. */
    private Button person, persons, liststring, listmap;
    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        person = (Button) this.findViewById(R.id.person);
        persons = (Button) this.findViewById(R.id.persons);
        liststring = (Button) this.findViewById(R.id.liststring);
        listmap = (Button) this.findViewById(R.id.listmap);
        person.setOnClickListener(this);
        persons.setOnClickListener(this);
        liststring.setOnClickListener(this);
        listmap.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.person:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String path = "http://192.168.43.7:8080/jsonProject/servlet/JsonAction?action_flag=person";
                        String jsonString = HttpUtils.getJsonContent(path);
                        Person person = JsonTools.getPerson("person", jsonString);
                        Log.i(TAG, person.toString());
                    }
                }).start();
                //closeStrictMode();
                //String path = "http://192.168.43.7:8080/jsonProject/servlet/JsonAction?action_flag=person";
               // String jsonString = HttpUtils.getJsonContent(path);
                //Person person = JsonTools.getPerson("person", jsonString);
               // Log.i(TAG, person.toString());
                break;
            case R.id.persons:
                String path2 = "http://192.168.43.7:8080/jsonProject/servlet/JsonAction?action_flag=persons";
                String jsonString2 = HttpUtils.getJsonContent(path2);
                List<Person> list2 = JsonTools.getPersons("persons", jsonString2);
                Log.i(TAG, list2.toString());
                break;
            case R.id.liststring:
                String path3 = "http://192.168.43.7:8080/jsonProject/servlet/JsonAction?action_flag=liststring";
                String jsonString3 = HttpUtils.getJsonContent(path3);
                List<String> list3 = JsonTools.getList("liststring", jsonString3);
                Log.i(TAG, list3.toString());
                break;
            case R.id.listmap:
                String path4 = "http://192.168.43.7:8080/jsonProject/servlet/JsonAction?action_flag=listmap";
                String jsonString4 = HttpUtils.getJsonContent(path4);
                List<Map<String, Object>> list4 = JsonTools.listKeyMaps("listmap",
                        jsonString4);
                Log.i(TAG, list4.toString());
                break;
        }
    }
    public static void closeStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll().penaltyLog().build());
    }
}

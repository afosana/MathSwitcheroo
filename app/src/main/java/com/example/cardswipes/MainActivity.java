package com.example.cardswipes;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

import static java.sql.DriverManager.println;

public class MainActivity extends AppCompatActivity {

    ArrayList<Question> questionSet;
    ArrayList<Question> q;
    ArrayList<String> questions, answers;
    ArrayAdapter adapter;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        parseXML();

        questionSet = new ArrayList<Question>();
        questionSet = obtainFifteen();

        questions = new ArrayList<String>();
        questions = getQuestStrings(questionSet);

        answers = new ArrayList<String>();
        answers = getAnswerStrings(questionSet);

        SwipeFlingAdapterView swav = (SwipeFlingAdapterView) findViewById(R.id.cards);

        adapter = new ArrayAdapter<String> (this, R.layout.card, R.id.adaptInput, questions);
        swav.setAdapter(adapter);
        swav.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                questions.remove(0);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object o) {
                if (answers.get(0).equals("false")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_dark);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_dark);
                    toast.show();
                }
                answers.remove(0);


            }

            @Override
            public void onRightCardExit(Object o) {
                if (answers.get(0).equals("true")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_dark);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "Wrong!", Toast.LENGTH_SHORT);
                    View toastView = toast.getView();
                    toastView.setBackgroundResource(R.drawable.toast_dark);
                    toast.show();
                }
                answers.remove(0);

            }

            @Override
            public void onAdapterAboutToEmpty(int i) {

            }

            @Override
            public void onScroll(float v) {

            }
        });

    }

    public void parseXML() {
        XmlPullParserFactory parserFactory;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();
            InputStream inp = getAssets().open("questions.xml");
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inp, null);

            processParse(parser);
        }
        catch (XmlPullParserException e) {

        }
        catch (IOException e) {

        }
    }

    public void processParse(XmlPullParser parser) throws IOException, XmlPullParserException {
        ArrayList<Question> question = new ArrayList<>();
        int eventType = parser.getEventType();
        Question currQ = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String preVal = null;

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    preVal = parser.getName();

                    if ("question".equals(preVal)){
                        currQ = new Question();
                        question.add(currQ);
                    } else if (currQ != null) {
                        if ("desc".equals(preVal)) {
                            currQ.quest = parser.nextText();
                        } else if ("answer".equals(preVal)) {
                            currQ.answer = parser.nextText();
                        }
                    }
                    break;
            }
            eventType = parser.next();
        }
        this.q = question;
    }



    public ArrayList<String> getQuestStrings(ArrayList<Question> arr){
        ArrayList<String> pass = new ArrayList<String>();
        for (Question qStr : arr) {
            pass.add(qStr.getQuest());
        }
        return pass;
    }

    public ArrayList<String> getAnswerStrings(ArrayList<Question> arr){
        ArrayList<String> pass = new ArrayList<String>();
        for (Question qStr : arr) {
            pass.add(qStr.getAnswer());
        }
        return pass;
    }

    public ArrayList<Question> randomizeList(ArrayList<Question> arr) {
        ArrayList<Question> pass = new ArrayList<Question>();
        Collections.shuffle(arr);
        pass = arr;
        return pass;
    }

    public ArrayList<Question> obtainFifteen() {
        ArrayList<Question> pass = new ArrayList<Question>();
        ArrayList<Question> neut = new ArrayList<Question>();
        pass = randomizeList(this.q);

        Integer limit = 15;
        for(int count = 0; count < limit; count++) {
            neut.add(pass.get(count));
        }

        return neut;
    }
}
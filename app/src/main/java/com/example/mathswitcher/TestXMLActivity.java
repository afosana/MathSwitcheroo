package com.example.mathswitcher;

import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;

public class TestXMLActivity extends AppCompatActivity {
    private ArrayList<Question> que;
    private ArrayList<String> quest, answr;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_x_m_l);

        text = (TextView) findViewById(R.id.text);
        parseXML();


        this.que = randomizeList(this.que);
        testParse(this.que);


        //quest = getQuestStrings(que);
        //testParseQuest(quest);

        //answr = getAnswerStrings(que);
        //testParseQuest(answr);
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
        ArrayList<Question> questions = new ArrayList<>();
        int eventType = parser.getEventType();
        Question currQ = null;

        while (eventType != XmlPullParser.END_DOCUMENT) {
            String preVal = null;

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    preVal = parser.getName();

                    if ("question".equals(preVal)){
                        currQ = new Question();
                        questions.add(currQ);
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
        this.que = questions;
    }

    public void testParse(ArrayList<Question> q) {
        StringBuilder builder = new StringBuilder();

        for (Question ques: q) {
            builder.append(ques.quest).append("   ").
                    append(ques.answer).append("\n").append("\n");
        }

        text.setText(builder.toString());
    }

    public void testParseQuest(ArrayList<String> q) {
        StringBuilder builder = new StringBuilder();

        for (String questt: q) {
            builder.append(questt).append("\n");
        }
        text.setText(builder.toString());
    }

    public void testParseAnswer(ArrayList<String> q) {
        StringBuilder builder = new StringBuilder();

        for (String questt: q) {
            builder.append(questt).append("\n");
        }
        text.setText(builder.toString());
    }

    public ArrayList<String> getQuestStrings(ArrayList<Question> arr) {
        ArrayList<String> pass = new ArrayList<String>();
            for (Question qStr : arr) {
                pass.add(qStr.getQuest());
            }
        return pass;
    }

    public ArrayList<String> getAnswerStrings(ArrayList<Question> arr) {
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


}
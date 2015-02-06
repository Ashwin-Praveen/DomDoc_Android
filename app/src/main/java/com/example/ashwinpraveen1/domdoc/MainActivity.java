package com.example.ashwinpraveen1.domdoc;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MainActivity extends ActionBarActivity {

    TextView printText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            Document xmlDoc = getDocument();
            TextView printText = (TextView) findViewById(R.id.printText);
            printText.setText(xmlDoc.getDocumentElement().getNodeName());
            NodeList listOfShows = xmlDoc.getElementsByTagName("list");
            //printText.setText("Number of shows: "+listOfShows.getLength());




            }

            private  Document getDocument() {
                try {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    factory.setIgnoringComments(true);
                    factory.setIgnoringElementContentWhitespace(true);
                    //factory.setValidating(true);

                    DocumentBuilder builder = factory.newDocumentBuilder();
                    AssetManager assetManager = this.getAssets();
                    InputStream is = assetManager.open("tvshows.xml");

                    InputSource inStream = new InputSource(is);
                    return builder.parse(inStream);

                }
                catch(Exception e) {
                    TextView printText = (TextView) findViewById(R.id.printText);
                    printText.setText(e.getMessage());
                    return null;
                }
            }



    }

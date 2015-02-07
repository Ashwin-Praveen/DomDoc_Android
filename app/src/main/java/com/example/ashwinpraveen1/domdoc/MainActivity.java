package com.example.ashwinpraveen1.domdoc;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


public class MainActivity extends ActionBarActivity {

    TextView printText;
    EditText emailEdit, passwordEdit;
    Button saveButton;
    String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText emailEdit = (EditText) findViewById(R.id.emailEdit);
        final EditText passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        Button saveButton = (Button) findViewById(R.id.saveButton);

        Document xmlDoc = getDocument();
        final TextView printText = (TextView) findViewById(R.id.printText);
        printText.setText(xmlDoc.getDocumentElement().getNodeName());
        NodeList listOfShows = xmlDoc.getElementsByTagName("list");
        //printText.setText("Number of shows: "+listOfShows.getLength());

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEdit.getText().toString();
                password = passwordEdit.getText().toString();
                if (email.isEmpty() || password.isEmpty()) {
                    //code when the user presses the "save" button for no reason
                }
            }
        });




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

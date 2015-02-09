package com.example.ashwinpraveen1.domdoc;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;


public class MainActivity extends ActionBarActivity {

    TextView printText;
    EditText emailEdit, passwordEdit;
    Button saveButton;
    String email,password;
    static final String xmlFileName = "users.xml";
    String string = "Hello world!";
    FileOutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText emailEdit = (EditText) findViewById(R.id.emailEdit);
        final EditText passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        Button saveButton = (Button) findViewById(R.id.saveButton);
        try {
            string = getFromAssets();
        }catch (IOException e) {
            e.printStackTrace();
        }
        makeDefaultFile();

        Document xmlDoc = getDocument();
        final TextView printText = (TextView) findViewById(R.id.printText);
        //printText.setText(string);
        printText.setText(xmlDoc.getDocumentElement().getNodeName());
        //NodeList listOfShows = xmlDoc.getElementsByTagName("list");
        //printText.setText("Number of shows: "+listOfShows.getLength());

        /*
        String filename = "myfile";
String string = "Hello world!";
FileOutputStream outputStream;

try {
  outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
  outputStream.write(string.getBytes());
  outputStream.close();
} catch (Exception e) {
  e.printStackTrace();
}
         */

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

    private String getFromAssets() throws IOException {

//        try {
//            AssetManager assetManager = this.getAssets();
//            InputStream is = assetManager.open("tvshows.xml");
//            InputSource inStream = new InputSource(is);
//            String stringg=is.toString();
//            return stringg;
//
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//            return null;
//     }

//        String mLine=null;
//        BufferedReader reader = null;
//    try {
//            reader = new BufferedReader(
//            new InputStreamReader(getAssets().open("tvshows.xml"),"UTF-8"));
//
//            // do reading, usually loop until end of file reading
//            mLine = reader.readLine();
//            while (mLine != null) {
//                //process line
//                mLine = reader.readLine();
//            }
//    } catch (IOException e) {
//            //log the exception
//      } finally {
//        if (reader != null) {
//             try {
//                 reader.close();
//             } catch (IOException e) {
//                //log the exception
//             }
//        }
//    }
//        return mLine;
        String line=null;
      BufferedReader br = new BufferedReader(new InputStreamReader(getAssets().open("tvshows.xml")));

            try {
                StringBuilder sb = new StringBuilder();
                line = br.readLine();

                //while ((line = br.readLine()) != null) {

                while(line != null) {
                    sb.append(line);
                    sb.append("\n");
                    line = br.readLine();
                }
                line=sb.toString();
                return line;
            } finally {
                try {
                    br.close();
                }
                catch(IOException e) {
                    System.out.println(e);
                }
            }



    }

    private void makeDefaultFile() {
        try {
            outputStream = openFileOutput(xmlFileName, Context.MODE_PRIVATE);
            outputStream.write(string.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private  Document getDocument() {

                Document doc=null;
                try {
                    FileInputStream fileInputStream = openFileInput(xmlFileName);

                    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                    dbFactory.setIgnoringComments(true);
                    dbFactory.setIgnoringElementContentWhitespace(true);

                    DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                    doc=dBuilder.parse(fileInputStream);

                    return doc;

                }
                catch(ParserConfigurationException | IOException | SAXException e) {
                    TextView printText = (TextView) findViewById(R.id.printText);
                    printText.setText(e.getMessage());
                    return doc;
                }
            }



    }

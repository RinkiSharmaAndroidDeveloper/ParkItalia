package com.parkitalia.android.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.parkitalia.android.R;

import org.w3c.dom.Text;

/**
 * Created by Indosurplus on 5/24/2017.
 */

public class Emergency extends Activity {
    String data1="Caribinere  (National Police)\n" +
            "Via Dardano, Cortona\n" +
            "Dial 112\n" +
            "\n" +
            "\n" +
            "Municipal Police \n" +
            "Piazza dellaRepubblica, 13\n" +
            "0575 637225\n" +
            "\n" +
            "Ambulance Dial 118\n" +
            "\n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergencycotacts);
        TextView textView= (TextView) findViewById(R.id.text_emergency);
        textView.setText(data1);
    }
}

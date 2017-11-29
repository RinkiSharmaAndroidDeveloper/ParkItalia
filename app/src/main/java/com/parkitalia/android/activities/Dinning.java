package com.parkitalia.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.parkitalia.android.R;

/**
 * Created by Indosurplus on 5/24/2017.
 */

public class Dinning extends AppCompatActivity{
    /*String data1="La Grotta\n" +
            "Piazza Baldelli, 3  Cortona\n" +
            "WEBSITE ( www.trattorialagrotta.it )\n" +
            "0575 630271\n" +
            "Type: Trattoria\n" +
            "GPS: 43.274674, 11.985278\n";43.274674, 11.985278
    String data2="FiaschetteriaFett'unta\n" +
            "Via Giuseppe Maffei, 5, Cortona\n" +
            "Website:  (www.winebarcortona.com )\n" +
            "0575 630582\n" +
            "Type:  Deli, Winebar\n" +
            "GPS: 43.275944, 11.985616\n";43.275944, 11.985616
    String data3="Pizza e Focacce\n" +
            "Via Benedetti, 5, 52044 Cortona AR\n" +
            "0575 631062\n" +
            "Type:  Takeaway Pizza\n" +
            "GPS:  43.275195, 11.985534\n";
    String data4="Gelateria Dolce Vita\n" +
            "Via Nazionale, 71, 52044 Cortona AR\n" +
            "Website;  (www.gelateriadolcevita.it )\n" +
            "0575 630102\n" +
            "Type:  Ice Cream Shop\n" +
            "GPS:  43.274222, 11.987504\n" +43.275195, 11.985534
            "\n";
    String data5="Il Gozzoviglio\n" +
            "Via Guelfa, 9, 52044 Cortona AR\n" +
            "Website:  (www.ilgozzoviglio.it )\n" +
            "0575 601778\n" +
            "Type:  Trattoria \n" +
            "GPS: 43.274783, 11.985090\n";
    String data6="Bar AlCaffé\n" +
            "Via Guelfa at Via Ghibellina\n" +
            "Type:  Coffee Bar\n" +
            "GPS:  43.274783, 11.985090\n";
    String data7="Caffè Vittoria\n" +
            "Piazza Luca Signorelli\n" +
            "Type:  Bar, Coffee, Lunch\n" +
            "GPS:  43.275240, 11.985264\n";
    String data8="Il CacioBrillo\n" +
            "Piazza Luca Signorelli, 6 Cortona\n" +
            "Website:  (www.ilcaciobrillo.com. )\n" +
            "0575 62555\n" +
            "Type:  Wine Bar\n" +
            "GPS: 43.275207, 11.985160\n" +
            "\n";


    String data_name1="La Grotta";String data_address1="Piazza Baldelli, 3  Cortona";String data_webLink1="WEBSITE ( www.trattorialagrotta.it )";String data_phonneNumber1= "0575 630271";String data_dinningType1="Type: Trattoria";
    String data_name2="FiaschetteriaFett'unta";String data_address2="Via Giuseppe Maffei, 5, Cortona";String data_webLink2= "Website:  (www.winebarcortona.com )";String phonneNumber2="0575 630582";String data_dinningType2="Type:  Deli, Winebar";
    String data_name3="Pizza e Focacce";String data_address3="Via Benedetti, 5, 52044 Cortona AR";String data_phonneNumber3="0575 631062";String data_dinningType3="Type:  Takeaway Pizza";
    String data_name4="Gelateria Dolce Vita";String data_address4="Via Nazionale, 71, 52044 Cortona AR";String data_webLink4="Website;  (www.gelateriadolcevita.it )";String data_phonneNumber4="0575 630102";String data_dinningType4="Type:  Ice Cream Shop";
    String data_name5="Il Gozzoviglio";String data_address5="Via Guelfa, 9, 52044 Cortona AR";String data_webLink5="Website:  (www.ilgozzoviglio.it )";String data_phonneNumber5="0575 601778";String data_dinningType5="Type:  Trattoria ";
    String data_name6="Bar AlCaffé";String data_address6="Via Guelfa at Via Ghibellina";String data_dinningType6="Type:  Coffee Bar";
    String data_name7="Caffè Vittoria";String data_address7="Piazza Luca Signorelli";String data_dinningType7="Type:  Bar, Coffee, Lunch";
    String data_name8="Il CacioBrillo";String data_address8= "Piazza Luca Signorelli, 6 Cortona";String data_webLink8="Website:  (www.ilcaciobrillo.com. )";String data_phonneNumber8="0575 62555";String data_dinningType8="Type:  Wine Bar";
*/

    double lat1= 43.274674; double lng1= 11.985278; double lat2= 43.275944; double lng2= 11.985616; double lat3= 43.275107; double lng3= 11.984936;
    double lat4= 43.275195; double lng4= 11.985534; double lat5= 43.274222; double lng5= 11.987504; double lat6= 43.274783; double lng6= 11.985090;
    double lat7= 43.274783; double lng7= 11.985090; double lat8= 43.275240; double lng8= 11.985264;  double lat9= 43.275207; double lng9= 11.985160;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrolldining);
        /*TextView name1= (TextView) findViewById(R.id.text1);
        TextView textView2= (TextView) findViewById(R.id.text2);
        TextView textView3= (TextView) findViewById(R.id.text3);
        TextView textView4= (TextView) findViewById(R.id.text4);
        TextView textView5= (TextView) findViewById(R.id.text5);
        TextView textView6= (TextView) findViewById(R.id.text6);
        TextView textView7= (TextView) findViewById(R.id.text7);
        TextView textView8= (TextView) findViewById(R.id.text8);*/

        Button gps1 = (Button) findViewById(R.id.button1);
        Button gps2 = (Button) findViewById(R.id.button2);
        Button gps3 = (Button) findViewById(R.id.button3);
        Button gps4 = (Button) findViewById(R.id.button4);
        Button gps5 = (Button) findViewById(R.id.button5);
        Button gps6 = (Button) findViewById(R.id.button6);
        Button gps7 = (Button) findViewById(R.id.button7);
        Button gps8 = (Button) findViewById(R.id.button8);
        Button gps9 = (Button) findViewById(R.id.button9);

        gps1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Dinning.this, DinningMapActivity.class);
                intent.putExtra("lat",lat1);
                intent.putExtra("lng",lng1);
                startActivity(intent);
            }
        });

        gps2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Dinning.this, DinningMapActivity.class);
                intent.putExtra("lat",lat2);
                intent.putExtra("lng",lng2);
                startActivity(intent);
            }
        });

        gps3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Dinning.this, DinningMapActivity.class);
                intent.putExtra("lat",lat3);
                intent.putExtra("lng",lng3);
                startActivity(intent);
            }
        });

        gps4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Dinning.this, DinningMapActivity.class);
                intent.putExtra("lat",lat4);
                intent.putExtra("lng",lng4);
                startActivity(intent);
            }
        });

        gps5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Dinning.this, DinningMapActivity.class);
                intent.putExtra("lat",lat5);
                intent.putExtra("lng",lng5);
                startActivity(intent);
            }
        });

        gps6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //      String strUri = "http://maps.google.com/maps?q=loc:" + accomodationModel.getAccomd_gps_lat() + "," + accomodationModel.getAccomd_gps_lng()+ " (" + "Label which you want" + ")";
                Intent intent = new Intent(Dinning.this, DinningMapActivity.class);
                intent.putExtra("lat",lat6);
                intent.putExtra("lng",lng6);
                startActivity(intent);
            }
        });

        gps7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Dinning.this, DinningMapActivity.class);
                intent.putExtra("lat",lat7);
                intent.putExtra("lng",lng7);
                startActivity(intent);
            }
        });

        gps8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Dinning.this, DinningMapActivity.class);
                intent.putExtra("lat",lat8);
                intent.putExtra("lng",lng8);
                startActivity(intent);
            }
        });

        gps9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Dinning.this, DinningMapActivity.class);
                intent.putExtra("lat",lat9);
                intent.putExtra("lng",lng9);
                startActivity(intent);
            }
        });


       /* textView1.setText(data1);
        textView2.setText(data2);
        textView3.setText(data3);
        textView4.setText(data4);
        textView5.setText(data5);
        textView6.setText(data6);
        textView7.setText(data7);
        textView8.setText(data8);*/
    }


}

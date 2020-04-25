package com.lmas.covid19;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
 TextView confirm,death,recover,date,confirm1,death1,recover1;
 RequestQueue requestQueue;
 ImageView menu;
    BarChart barChart;
    LineChart lineChart;
    int a, b, c;
    private static final int TAG = 1;
    public static final int[] JOYFUL_COLORS = {
            Color.rgb(255, 102, 0), Color.rgb(255, 0, 0), Color.rgb(1, 103, 52)
    };

    String Url1 = "https://api.covid19api.com/summary";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        confirm = findViewById(R.id.confirm);
        death = findViewById(R.id.death);
        recover = findViewById(R.id.recover);

        confirm1 = findViewById(R.id.confirm1);
        death1 = findViewById(R.id.death2);
        recover1 = findViewById(R.id.recover3);
        date = findViewById(R.id.date);
        barChart = (BarChart) findViewById(R.id.chart);
        //lineChart=(LineChart)findViewById(R.id.line);
        TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        final String countryCode = tm.getSimCountryIso();

         Spinner spinner = findViewById(R.id.countries);
        String[] countries = new String[]{"ALA Aland Islands", "Afghanistan", "Albania", "Algeria","American Samoa", "Andorra", "Angola", "Anguilla", "Antarctica","Antigua and Barbuda", "Argentina", "Armenia", "Aruba", "Australia", "Austria", "Azerbaijan", "Bahamas", "Bahrain", "Bangladesh", "Barbados", "Belarus", "Belgium", "Belize", "Benin", "Bermuda", "Bhutan", "Bolivia", "Bosnia and Herzegovina", "Botswana", "Bouvet Island", "Brazil", "British Indian Ocean Territory", "British Virgin Islands", "Brunei Darussalam", "Bulgaria", "Burkina Faso", "Burundi", "Cambodia", "Cameroon", "Canada", "Cape Verde", "Cayman Islands", "Central African Republic", "Chad", "Chile", "China", "Christmas Island", "Cocos (Keeling) Islands", "Colombia", "Comoros", "Congo (Brazzaville)", "Congo (Kinshasa)", "Cook Islands", "Costa Rica", "Croatia", "Cuba", "Cyprus", "Czech Republic", "Côte d'Ivoire", "Denmark", "Djibouti", "Dominica", "Dominican Republic", "Ecuador", "Egypt", "El Salvador", "Equatorial Guinea", "Eritrea", "Estonia", "Ethiopia", "Falkland Islands (Malvinas)", "Faroe Islands", "Fiji", "Finland", "France", "French Guiana", "French Polynesia", "French Southern Territories", "Gabon", "Gambia", "Georgia", "Germany", "Ghana", "Gibraltar", "Greece", "Greenland", "Grenada", "Guadeloupe", "Guam", "Guatemala", "Guernsey", "Guinea", "Guinea-Bissau", "Guyana", "Haiti", "Heard and Mcdonald Islands", "Holy See (Vatican City State)", "Honduras", "Hong Kong, SAR China", "Hungary", "Iceland", "India", "Indonesia", "Iran, Islamic Republic of", "Iraq", "Ireland", "Isle of Man", "Israel", "Italy", "Jamaica", "Japan", "Jersey", "Jordan", "Kazakhstan", "Kenya", "Kiribati", "Korea (North)", "Korea (South)", "Kuwait", "Kyrgyzstan", "Lao PDR", "Latvia", "Lebanon", "Lesotho", "Liberia", "Libya", "Liechtenstein", "Lithuania", "Luxembourg", "Macao, SAR China", "Macedonia, Republic of", "Madagascar", "Malawi", "Malaysia", "Maldives", "Mali", "Malta", "Marshall Islands", "Martinique", "Mauritania", "Mauritius", "Mayotte", "Mexico", "Micronesia, Federated States of", "Moldova", "Monaco", "Mongolia", "Montenegro", "Montserrat", "Morocco", "Mozambique", "Myanmar", "Namibia", "Nauru", "Nepal", "Netherlands", "Netherlands Antilles", "New Caledonia", "New Zealand", "Nicaragua", "Niger", "Nigeria", "Niue", "Norfolk Island", "Northern Mariana Islands", "Norway", "Oman", "Pakistan", "Palau", "Palestinian Territory", "Panama", "Papua New Guinea", "Paraguay", "Peru", "Philippines", "Pitcairn", "Poland", "Portugal", "Puerto Rico", "Qatar", "Republic of Kosovo", "Romania", "Russian Federation", "Rwanda", "Réunion", "Saint Helena", "Saint Kitts and Nevis", "Saint Lucia", "Saint Pierre and Miquelon", "Saint Vincent and Grenadines", "Saint-Barthélemy", "Saint-Martin (French part)", "Samoa", "San Marino", "Sao Tome and Principe", "Saudi Arabia", "Senegal", "Serbia", "Seychelles", "Sierra Leone", "Singapore", "Slovakia", "Slovenia", "Solomon Islands", "Somalia", "South Africa", "South Georgia and the South Sandwich Islands", "South Sudan", "Spain", "Sri Lanka", "Sudan", "Suriname", "Svalbard and Jan Mayen Islands", "Swaziland", "Sweden", "Switzerland", "Syrian Arab Republic (Syria)", "Taiwan, Republic of China", "Tajikistan", "Tanzania, United Republic of", "Thailand", "Timor-Leste", "Togo", "Tokelau", "Tonga", "Trinidad and Tobago", "Tunisia", "Turkey", "Turkmenistan", "Turks and Caicos Islands", "Tuvalu", "US Minor Outlying Islands", "Uganda", "Ukraine", "United Arab Emirates", "United Kingdom", "United States of America", "Uruguay", "Uzbekistan", "Vanuatu", "Venezuela (Bolivarian Republic)", "Viet Nam", "Virgin Islands, US", "Wallis and Futuna Islands", "Western Sahara", "Yemen", "Zambia", "Zimbabwe"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,R.layout.support_simple_spinner_dropdown_item,countries);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String name = (String) adapterView.getItemAtPosition(i);
                Log.d("msg" , (String) adapterView.getItemAtPosition(i));
                JsonParse(name);
                confirm.setText("");
                death.setText("");
                recover.setText("");
                date.setText("");
                RewardApi(name);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        requestQueue = Volley.newRequestQueue(this);

    }

    private void RewardApi(final String Name) {

        JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, Url1, null
                , new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Countries");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject con = jsonArray.getJSONObject(i);

                        if(Name.equalsIgnoreCase(String.valueOf(con.getString("Country")))){
                            a= con.getInt("TotalConfirmed");
                            b= con.getInt("TotalDeaths");
                            c= con.getInt("TotalRecovered");
                            ArrayList<BarEntry> entries = new ArrayList<>();
                            entries.add(new BarEntry(Float.parseFloat(String.valueOf(a)), 0));
                            entries.add(new BarEntry(Float.parseFloat(String.valueOf(b)), 1));
                            entries.add(new BarEntry(Float.parseFloat(String.valueOf(c)), 2));

                            BarDataSet dataset = new BarDataSet(entries, "Covid-19");

                            ArrayList<String> labels = new ArrayList<String>();
                            labels.add("Confirmed");
                            labels.add("Deaths");
                            labels.add("Recovered");

                            BarData bardata = new BarData(labels, dataset);
                            dataset.setColors(JOYFUL_COLORS);
                            barChart.setData(bardata);
                            barChart.animateY(3000);
                            barChart.animateX(3000);
                           /* ArrayList<Entry> entries1 = new ArrayList<>();
                            entries1.add(new Entry(Float.parseFloat(String.valueOf(a)), 0));
                            entries1.add(new Entry(Float.parseFloat(String.valueOf(b)), 1));
                            entries1.add(new Entry(Float.parseFloat(String.valueOf(c)), 2));

                            LineDataSet dataset1 = new LineDataSet(entries1, "");

                            ArrayList<String> labels1 = new ArrayList<String>();
                            labels1.add("Confirmed");
                            labels1.add("Deaths");
                            labels1.add("Recovered");
                            LineData data1 = new LineData(labels1, dataset1);
                            dataset1.setColors(ColorTemplate.COLORFUL_COLORS);
                            lineChart.setData(data1);
                            lineChart.animateY(5000);
                            lineChart.animateX(3000);*/

                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "Internet Connection Lost,Please Try Again", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("UserId", "2");
                return map;
            }
        };
        RequestQueue requestQueue12 = Volley.newRequestQueue(this);
        requestQueue12.add(request1);
    }



    private void JsonParse(final String Name) {



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Url1, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("Countries");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject con = jsonArray.getJSONObject(i);

                        if(Name.equalsIgnoreCase(String.valueOf(con.getString("Country")))){
                            confirm.append(String.valueOf(con.getInt("TotalConfirmed")));
                            death.append(String.valueOf(con.getInt("TotalDeaths")));
                            recover.append(String.valueOf(con.getInt("TotalRecovered")));
                            String dateval = String.valueOf(con.getString("Date"));
                            dateval = dateval.replace('T',' ');
                            dateval = dateval.replace('Z',' ');
                            dateval = dateval.replace('-','/');

                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                            Date date1 = new Date();
                            String today = formatter.format(date1);
                            String upda = dateval;
                            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

                            Date d1 = null;
                            Date d2 = null;

                            try {
                                d1 = format.parse(upda);
                                d2 = format.parse(today);

                                //in milliseconds
                                long diff = d2.getTime() - d1.getTime();

                                long diffSeconds = diff / 1000 % 60;
                                long diffMinutes = diff / (60 * 1000) % 60;
                                long diffHours = diff / (60 * 60 * 1000) % 24;
                                long diffDays = diff / (24 * 60 * 60 * 1000);

                                System.out.print(diffDays + " days, ");
                                System.out.print(diffHours + " hours, ");
                                System.out.print(diffMinutes + " minutes, ");
                                System.out.print(diffSeconds + " seconds.");

                                if(diffHours!=0) {
                                    date.append(String.valueOf(diffHours) + "hrs ago");
                                }
                                else{

                                    date.append(String.valueOf(diffMinutes) + "mins ago");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();

                            }

                        }

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);
        StringRequest request1 = new StringRequest(Url1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("Code", response);
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                Details details = gson.fromJson(response,Details.class);

                confirm1.setText(String.valueOf(details.getGlobal().getTotalConfirmed()));
                death1.setText(String.valueOf(details.getGlobal().getTotalDeaths()));
                recover1.setText(String.valueOf(details.getGlobal().getTotalRecovered()));
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request1);

        }

    public void symps(View view) {
        startActivity(new Intent(MainActivity.this,Symptomps.class));
        finish();
    }
}

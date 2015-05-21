package org.urbancortex.timeserver;

import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.StrictMode;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.commons.net.ntp.TimeInfo;
import org.apache.commons.net.time.TimeTCPClient;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

import static android.os.SystemClock.elapsedRealtime;


public class MainActivity extends ActionBarActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // must give permission in manifest to access internet


//        For use in ad-hoc, use static IP address.
//        InetAddress.getByName("192.168.1.232");


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        System.out.println(elapsedRealtime());

//        InetAddress ip;
//        try {
//
//            ip = InetAddress.getLocalHost();
//            System.out.println("Current IP address : " + ip.getHostAddress());
//
//        } catch (UnknownHostException e) {
//
//            e.printStackTrace();
//
//        }
//
//
//
//        WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
//        String ip2 = wm.getConnectionInfo().toString();
//        System.out.println("Current IP address : " +ip2);



    }



//    public static void main() throws InterruptedException {
//        SntpClient client = new SntpClient();
//        long now = 0;
//        boolean time = client.requestTime("time-a.nist.gov",5000);
//
//        Thread.sleep(5000);
//
//        System.out.println("requestTime"+time);
//
//        if (client.requestTime("0.uk.pool.ntp.org",5000)) {
//            Thread.sleep(1000);
//
//            now = client.getNtpTime() + SystemClock.elapsedRealtime() - client.getNtpTimeReference();
//            System.out.println(client.getNtpTime());
//        }
//
//
//
//        for (int i = 0; i <= 10; i++)
//        {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println(now);
//
//        }
//        System.out.println("You can count to ten.");
//    }

    public void getTimeOffset (View view){

        EditText input = (EditText) findViewById(R.id.edit_message);
        String newIp = input.getText().toString();

        TextView offsetText = (TextView) findViewById(R.id.offset);
        TextView offsetAverageText = (TextView) findViewById(R.id.offsetAverage);

        int[] a = new int[10];
        int sum = 0;
        TimeInfo info = null;


        for (int i =0; i < a.length; i++) {

            if (newIp != null) {
                info = NTPClient.main(new String[]{newIp}); //"time-a.nist.gov"
            }

            if (info != null) {
                info.computeDetails(); // compute offset/delay if not already done
                Long offsetValue = info.getOffset();
                Long delayValue = info.getDelay();
                String delay = (delayValue == null) ? "N/A" : delayValue.toString();
                String offset = (offsetValue == null) ? "N/A" : offsetValue.toString();

                offsetText.setText(offset);
                a[i]  =   Integer.parseInt( offset );

            }

            System.out.println(i);
        }

        for (int i =0; i < a.length; i++) {
            sum += a[i];
        }
        int average = sum / a.length;
        System.out.println("average is "+average);
        offsetAverageText.setText("Offset average is "+average);



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

        return super.onOptionsItemSelected(item);
    }
}

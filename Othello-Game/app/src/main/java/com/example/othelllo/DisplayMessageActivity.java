package com.example.othelllo;

import static android.os.SystemClock.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {
    private static final String TAG = "MyActivity";
    //private static final String TAG = "DisplayMessageActivity";
    public void newgame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        int id = getResources().getIdentifier("i33", "id", getPackageName());
        //Log.i(TAG, "print ID:" + id);
        ImageView img = (ImageView) findViewById(id);
        img.setImageResource(R.drawable.ic_white);
        img.setContentDescription("white");

        id = getResources().getIdentifier("i34", "id", getPackageName());
        img = (ImageView) findViewById(id);
        img.setImageResource(R.drawable.ic_black);
        img.setContentDescription("black");

        id = getResources().getIdentifier("i43", "id", getPackageName());
        img = (ImageView) findViewById(id);
        img.setImageResource(R.drawable.ic_black);
        img.setContentDescription("black");

        id = getResources().getIdentifier("i44", "id", getPackageName());
        img = (ImageView) findViewById(id);
        img.setImageResource(R.drawable.ic_white);
        img.setContentDescription("white");

        blackWhite();
        possiblePositionsCalculator("black","white");
    }

    private void blackWhite() {
        int white =0, black=0;
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                String id = "i"+ (char)(i + '0') + (char)(j + '0');
                int resID = getResources().getIdentifier(id, "id", getPackageName());
                ImageView img = findViewById(resID);
                if(img != null && img.getDrawable() != null) {
                    String imageName = (String) img.getContentDescription();
                    if(imageName == "white") {
                        white++;
                    }
                    else if(imageName == "black") {
                        black++;
                    }
                }

            }
        }
    }

    private void winner(View view) {
        int winnerFound =1, black =0, white =0;
        for(int i=0; i<8; i++) {
            for(int j=0; j<8; j++) {
                String id = "i"+ (char)(i + '0') + (char)(j + '0');
                int resID = getResources().getIdentifier(id, "id", getPackageName());
                ImageView img = findViewById(resID);
                if(img != null && img.getDrawable() != null) {
                    String imageName = (String) img.getContentDescription();
                    if(imageName == "transparent") {
                        winnerFound = 0;
                        break;
                    }
                    else if(imageName == "white") {
                        white++;
                    }
                    else if(imageName == "black") {
                        black++;
                    }
                }

            }
        }
        if(winnerFound == 1) {
            Log.i(TAG,"print winner indeed found ");
            if(white >= black) {
                //white won
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.winner_popup, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
                //TextView textView = findViewById(R.id.textView2);
                //textView.setText("The WINNER is WHITE!");

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(this.findViewById(android.R.id.content), Gravity.CENTER, 0, 0);
                TextView textView = popupView.findViewById(R.id.textView2);
                textView.setText("The WINNER is WHITE!");
            }
            else {
                //black won
                // inflate the layout of the popup window
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.winner_popup, null);

                // create the popup window
                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true; // lets taps outside the popup also dismiss it
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);
                //TextView textView = findViewById(R.id.textView2);
                //textView.setText("The WINNER is BLACK!");

                // show the popup window
                // which view you pass in doesn't matter, it is only used for the window tolken
                popupWindow.showAtLocation(this.findViewById(android.R.id.content), Gravity.CENTER, 0, 0);
                TextView textView = popupView.findViewById(R.id.textView2);
                textView.setText("The WINNER is BLACK!");
            }
        }


    }

    private void possiblePositionsCalculator(String ownColor, String otherColor) {
        for(int i=0; i<8; i++ ) {
            for(int j=0; j<8; j++) {
                String id = "i"+ (char)(i + '0') + (char)(j + '0');
                int resID = getResources().getIdentifier(id, "id", getPackageName());
                ImageView img = findViewById(resID);
                if(img.getDrawable() == null) {
                    String id1 = "i"+ (char)(i+1+'0')+ (char)(j+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img1 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i+1+'0')+ (char)(j+1+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img2 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i+'0')+ (char)(j+1+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img3 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i-1+'0')+ (char)(j+1+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img4 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i-1+'0')+ (char)(j+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img5 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i-1+'0')+ (char)(j-1+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img6 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i+'0')+ (char)(j-1+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img7 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i+1+'0')+ (char)(j-1+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img8 = (ImageView) findViewById(resID);

                    if(img1 != null && img1.getDrawable() != null) {
                        String imageName = (String) img1.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i+k+'0')+ (char)(j+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        img.setContentDescription("transparent");
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                            //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                           // img.setImageResource(R.drawable.ic_baseline_lens_24);
                        }
                    }

                    if(img2 != null && img2.getDrawable() != null) {
                        String imageName = (String) img2.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i+k+'0')+ (char)(j+k+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        img.setContentDescription("transparent");
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }

                    if(img3 != null && img3.getDrawable() != null) {
                        String imageName = (String) img3.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i+'0')+ (char)(j+k+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        img.setContentDescription("transparent");
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }

                    if(img4 != null && img4.getDrawable() != null) {
                        String imageName = (String) img4.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i-k+'0')+ (char)(j+k+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        img.setContentDescription("transparent");
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }

                    if(img5 != null && img5.getDrawable() != null) {
                        String imageName = (String) img5.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i-k+'0')+ (char)(j+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        img.setContentDescription("transparent");
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }

                    if(img6 != null && img6.getDrawable() != null) {
                        String imageName = (String) img6.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i-k+'0')+ (char)(j-k+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        img.setContentDescription("transparent");
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }

                    if(img7 != null && img7.getDrawable() != null) {
                        String imageName = (String) img7.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i+'0')+ (char)(j-k+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        img.setContentDescription("transparent");
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }

                    if(img8 != null && img8.getDrawable() != null) {
                        String imageName = (String) img8.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i+k+'0')+ (char)(j-k+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        img.setContentDescription("transparent");
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }


        /*Check if the button is empty or not. (I'm using buttons as tiles)
        Check if there're any neighbors of the opposite color.
        If there is, continue checking every direction there's an opposite color until
        If we reach a boundary - return false.
        If we reach our color - turn all the pieces to my color.*/

    }

    public void onClick(View view) throws InterruptedException {
        ImageView image = findViewById(view.getId());
        String imageName1 = (String) image.getContentDescription();
        if(imageName1 == "transparent") {
            image.setImageDrawable(null);
            image.setImageResource(R.drawable.ic_black);
            image.setContentDescription("black");


            //remove
            remove();
            String str = String.valueOf(view.getTag());
            int i = str.charAt(0) - '0';
            int j = str.charAt(1) - '0';
            Log.i(TAG, "ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt " + str + " " + i + " " + j);
            //sleep(5000);
            change(i, j, "black", "white");
            //Thread.sleep(1000);
            blackWhite();

            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {
                    try {
                        moveAi(view);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }, 1000);
        }
    }

    private void remove() {
        for(int i=0; i<8; i++ ) {
            for (int j = 0; j < 8; j++) {
                String id = "i" + (char) (i + '0') + (char) (j + '0');
                int resID = getResources().getIdentifier(id, "id", getPackageName());
                ImageView img = findViewById(resID);
                if (img.getDrawable() != null) {
                    String imageName2 = (String) img.getContentDescription();
                    if(imageName2 == "transparent") {
                        //image.setImageResource(R.drawable.ic_black);
                        img.setImageDrawable(null);
                        img.setContentDescription("");
                    }
                }
            }
        }
    }

    private void moveAi(View view) throws InterruptedException {
        possiblePositionsCalculator("white","black");
        winner(view);
        //Thread.sleep(5000);
        int breakC = 0, i = 0, j = 0;
        for( i=0; i<8; i++ ) {
            for ( j = 0; j < 8; j++) {
                String id = "i" + (char) (i + '0') + (char) (j + '0');
                int resID = getResources().getIdentifier(id, "id", getPackageName());
                ImageView img = findViewById(resID);
                if (img.getDrawable() != null) {
                    String imageName2 = (String) img.getContentDescription();
                    if (imageName2 == "transparent") {
                        img.setImageDrawable(null);
                        img.setImageResource(R.drawable.ic_white);
                        img.setContentDescription("white");
                        breakC = 1;
                        break;
                    }
                }
            }
            if(breakC == 1) {
                break;
            }
        }
        remove();
        if(breakC == 1) {
            Handler handler = new Handler();
            int finalJ = j;
            int finalI = i;
            handler.postDelayed(new Runnable() {

                @Override
                public void run() {

                        change(finalI, finalJ, "white", "black");

                }

            }, 1000);
            blackWhite();
            //Thread.sleep(1000);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                possiblePositionsCalculator("black","white");
                winner(view);
            }

        }, 1000);
    }

    private void change(int i, int j, String ownColor, String otherColor) {

                String id = "i"+ (char)(i + '0') + (char)(j + '0');
                int resID = getResources().getIdentifier(id, "id", getPackageName());
                ImageView img = findViewById(resID);

                    String id1 = "i"+ (char)(i+1+'0')+ (char)(j+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img1 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i+1+'0')+ (char)(j+1+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img2 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i+'0')+ (char)(j+1+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img3 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i-1+'0')+ (char)(j+1+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img4 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i-1+'0')+ (char)(j+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img5 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i-1+'0')+ (char)(j-1+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img6 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i+'0')+ (char)(j-1+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img7 = (ImageView) findViewById(resID);

                    id1= "i"+ (char)(i+1+'0')+ (char)(j-1+'0');
                    resID = getResources().getIdentifier(id1, "id", getPackageName());
                    ImageView img8 = (ImageView) findViewById(resID);

                    if(img1 != null && img1.getDrawable() != null) {
                        String imageName = (String) img1.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i+k+'0')+ (char)(j+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        //img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        //img.setContentDescription("transparent");
                                        if(ownColor == "white") {
                                            img1.setImageResource(R.drawable.ic_white);
                                            img1.setContentDescription("white");
                                            for(k = 2; k < 8; k++) {
                                                Log.i(TAG,"tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttxxxx "+ k);
                                                id1= "i"+ (char)(i+k+'0')+ (char)(j+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                 img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                     imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_white);
                                                        img9.setContentDescription("white");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            img1.setImageResource(R.drawable.ic_black);
                                            img1.setContentDescription("black");
                                            for(k = 2; k < 8; k++) {
                                                Log.i(TAG,"tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttxxxx "+ k);
                                                id1= "i"+ (char)(i+k+'0')+ (char)(j+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_black);
                                                        img9.setContentDescription("black");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                            //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                            // img.setImageResource(R.drawable.ic_baseline_lens_24);
                        }
                    }

                    if(img2 != null && img2.getDrawable() != null) {
                        String imageName = (String) img2.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i+k+'0')+ (char)(j+k+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        //img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        //img.setContentDescription("transparent");
                                        if(ownColor == "white") {
                                            img2.setImageResource(R.drawable.ic_white);
                                            img2.setContentDescription("white");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i+k+'0')+ (char)(j+k+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_white);
                                                        img9.setContentDescription("white");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            img2.setImageResource(R.drawable.ic_black);
                                            img2.setContentDescription("black");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i+k+'0')+ (char)(j+k+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_black);
                                                        img9.setContentDescription("black");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }




                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }

                    if(img3 != null && img3.getDrawable() != null) {
                        String imageName = (String) img3.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i+'0')+ (char)(j+k+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        //img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        //img.setContentDescription("transparent");
                                        if(ownColor == "white") {
                                            img3.setImageResource(R.drawable.ic_white);
                                            img3.setContentDescription("white");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i+'0')+ (char)(j+k+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_white);
                                                        img9.setContentDescription("white");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            img3.setImageResource(R.drawable.ic_black);
                                            img3.setContentDescription("black");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i+'0')+ (char)(j+k+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_black);
                                                        img9.setContentDescription("black");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }



                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }

                    if(img4 != null && img4.getDrawable() != null) {
                        String imageName = (String) img4.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i-k+'0')+ (char)(j+k+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        //img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        //img.setContentDescription("transparent");
                                        if(ownColor == "white") {
                                            img4.setImageResource(R.drawable.ic_white);
                                            img4.setContentDescription("white");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i-k+'0')+ (char)(j+k+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_white);
                                                        img9.setContentDescription("white");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            img4.setImageResource(R.drawable.ic_black);
                                            img4.setContentDescription("black");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i-k+'0')+ (char)(j+k+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_black);
                                                        img9.setContentDescription("black");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }



                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }

                    if(img5 != null && img5.getDrawable() != null) {
                        String imageName = (String) img5.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i-k+'0')+ (char)(j+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        //img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        //img.setContentDescription("transparent");
                                        if(ownColor == "white") {
                                            img5.setImageResource(R.drawable.ic_white);
                                            img5.setContentDescription("white");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i-k+'0')+ (char)(j+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_white);
                                                        img9.setContentDescription("white");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            img5.setImageResource(R.drawable.ic_black);
                                            img5.setContentDescription("black");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i-k+'0')+ (char)(j+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_black);
                                                        img9.setContentDescription("black");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }

                    if(img6 != null && img6.getDrawable() != null) {
                        String imageName = (String) img6.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i-k+'0')+ (char)(j-k+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        //img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        //img.setContentDescription("transparent");
                                        if(ownColor == "white") {
                                            img6.setImageResource(R.drawable.ic_white);
                                            img6.setContentDescription("white");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i-k+'0')+ (char)(j-k+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_white);
                                                        img9.setContentDescription("white");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            img6.setImageResource(R.drawable.ic_black);
                                            img6.setContentDescription("black");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i-k+'0')+ (char)(j-k+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_black);
                                                        img9.setContentDescription("black");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }


                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }

                    if(img7 != null && img7.getDrawable() != null) {
                        String imageName = (String) img7.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i+'0')+ (char)(j-k+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        //img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        //img.setContentDescription("transparent");
                                        if(ownColor == "white") {
                                            img7.setImageResource(R.drawable.ic_white);
                                            img7.setContentDescription("white");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i+'0')+ (char)(j-k+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_white);
                                                        img9.setContentDescription("white");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            img7.setImageResource(R.drawable.ic_black);
                                            img7.setContentDescription("black");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i+'0')+ (char)(j-k+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_black);
                                                        img9.setContentDescription("black");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }

                    if(img8 != null && img8.getDrawable() != null) {
                        String imageName = (String) img8.getContentDescription();
                        if(imageName == otherColor) {
                            for(int k = 1; k < 8; k++) {
                                id1= "i"+ (char)(i+k+'0')+ (char)(j-k+'0');
                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                ImageView img9 = (ImageView) findViewById(resID);
                                if(img9 != null && img9.getDrawable() != null) {
                                    String imageName1 = (String) img9.getContentDescription();
                                    if(imageName1 == ownColor) {
                                        //Log.i(TAG,"ttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt "+ id1 +" "+ resID+ imageName);
                                        //img.setImageResource(R.drawable.ic_baseline_lens_24);
                                        //img.setContentDescription("transparent");
                                        if(ownColor == "white") {
                                            img8.setImageResource(R.drawable.ic_white);
                                            img8.setContentDescription("white");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i+k+'0')+ (char)(j-k+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_white);
                                                        img9.setContentDescription("white");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                        else {
                                            img8.setImageResource(R.drawable.ic_black);
                                            img8.setContentDescription("black");
                                            for(k = 2; k < 8; k++) {
                                                id1= "i"+ (char)(i+k+'0')+ (char)(j-k+'0');
                                                resID = getResources().getIdentifier(id1, "id", getPackageName());
                                                img9 = (ImageView) findViewById(resID);
                                                if(img9 != null && img9.getDrawable() != null) {
                                                    imageName1 = (String) img9.getContentDescription();
                                                    if(imageName1 == otherColor) {
                                                        img9.setImageResource(R.drawable.ic_black);
                                                        img9.setContentDescription("black");
                                                    }
                                                    else {
                                                        break;
                                                    }
                                                }
                                                else {
                                                    break;
                                                }
                                            }
                                        }
                                        break;
                                    }
                                }
                                else {
                                    break;
                                }
                            }
                        }
                    }

    }
}
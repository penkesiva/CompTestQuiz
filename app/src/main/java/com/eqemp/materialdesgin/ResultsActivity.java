package com.eqemp.materialdesgin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

public class ResultsActivity extends ActionBarActivity {

    ListView listView;
    ResultTransitionListAdapter mAdapter;
    ArrayList<ResultsListItem> listData = new ArrayList<ResultsListItem>();
    int userscore = 0;
    RelativeLayout sharebar;
    ImageView launchericon;
    public static ArrayList<String> question = new ArrayList<String>();
    public static ArrayList<String> answers = new ArrayList<String>();
    public static ArrayList<String> useranswers = new ArrayList<String>();
    public static ArrayList<String> reasons = new ArrayList<String>();
    public static ArrayList<String> optionas = new ArrayList<String>();
    public static ArrayList<String> optionbs = new ArrayList<String>();
    public static ArrayList<String> optioncs = new ArrayList<String>();
    public static ArrayList<String> optionds = new ArrayList<String>();

    private InterstitialAd mInterstitialAd;

    ImageView title_bar_share_menu;
    TextView titleBarText, scorecard;

    Toolbar toolbar;
    TextView toolbar_title;
    ImageView im, syncimage;

    //AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_activitys);
        listView = findViewById(R.id.listView);
        title_bar_share_menu = findViewById(R.id.title_bar_share_menu);
        sharebar = findViewById(R.id.sharebar);
        titleBarText = findViewById(R.id.titleBar);
        scorecard = findViewById(R.id.scorecard);


        if (TextUtils.isEmpty(getString(R.string.interstitial_full_screen))) {
            Toast.makeText(getApplicationContext(), "Please mention your Interstitial Ad ID in strings.xml", Toast.LENGTH_LONG).show();
            return;
        }

        mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get your test device ID
                .addTestDevice("ca-app-pub-3579992647935349~6935520519")
                .build();

        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }

            @Override
            public void onAdClosed() {
                Toast.makeText(getApplicationContext(), "Ad is closed!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getApplicationContext(), "Ad failed to load! error code: " + errorCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdLeftApplication() {
                Toast.makeText(getApplicationContext(), "Ad left application!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdOpened() {
                Toast.makeText(getApplicationContext(), "Ad is opened!", Toast.LENGTH_SHORT).show();
            }
        });
//		mAdView = (AdView) findViewById(R.id.adMob);
//		sharebar.setVisibility(View.VISIBLE);
//		mAdView.setVisibility(View.VISIBLE);
//		launcherbar.setVisibility(View.INVISIBLE);
//
//		AdRequest adRequest = new AdRequest.Builder()

        // Add a test device to show Test Ads
        //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
        //.addTestDevice("AD795F0DA0BB01DEE26A8E6D309DBF76") //Random Text
//		.build();
//
//		// Load ads into Banner Ads
//		mAdView.loadAd(adRequest);
        //overridePendingTransition(R.anim.enter_from_right,R.anim.exit_to_left);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            im = (ImageView) toolbar.findViewById(R.id.toolbar_image);
            syncimage = (ImageView) toolbar.findViewById(R.id.scaner);
            toolbar_title = (TextView) toolbar.findViewById(R.id.toolbar_title);
            //im.setVisibility(View.INVISIBLE);
            syncimage.setImageResource(R.drawable.ic_next);

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        SingleQuestionDetailActivity sqd = new SingleQuestionDetailActivity();

        if (useranswers.size() > 0) {
            useranswers.clear();
        }
        if (question.size() > 0) {
            question.clear();
        }
        if (optionas.size() > 0) {
            optionas.clear();
        }
        if (optionbs.size() > 0) {
            optionbs.clear();
        }
        if (optioncs.size() > 0) {
            optioncs.clear();
        }
        if (optionds.size() > 0) {
            optionds.clear();
        }
        if (answers.size() > 0) {
            answers.clear();
        }
        if (useranswers.size() > 0) {
            useranswers.clear();
        }
        if (reasons.size() > 0) {
            reasons.clear();
        }
        if (listData.size() > 0) {
            listData.clear();
        }


        if (sqd.questions.size() > 0) {
            question.addAll(sqd.questions);
        }
        if (sqd.optionas.size() > 0) {
            optionas.addAll(sqd.optionas);
        }

        if (sqd.optionbs.size() > 0) {
            optionbs.addAll(sqd.optionbs);
        }

        if (sqd.optioncs.size() > 0) {
            optioncs.addAll(sqd.optioncs);
        }

        if (sqd.optionds.size() > 0) {
            optionds.addAll(sqd.optionds);
        }
        if (sqd.answers.size() > 0) {
            answers.addAll(sqd.answers);
        }
        if (sqd.reasons.size() > 0) {
            reasons.addAll(sqd.reasons);
        }
        if (sqd.useranswers.size() > 0) {
            useranswers.addAll(sqd.useranswers);
        }
        //		question.addAll(sqd.questions);
        //		answers.addAll(sqd.answers);
        //		useranswers.addAll(sqd.useranswers);
        //		reasons.addAll(sqd.reasons);
        //		optionas.addAll(sqd.optionas);
        //		optionbs.addAll(sqd.optionbs);
        //		optioncs.addAll(sqd.optioncs);
        //		optionds.addAll(sqd.optionds);

        for (int i = 0; i < question.size(); i++) {
            String answe = null;
            String userAns = null;

            if (answers.get(i).equalsIgnoreCase("1")) {
                answe = optionas.get(i);
            } else if (answers.get(i).equalsIgnoreCase("2")) {
                answe = optionbs.get(i);

            } else if (answers.get(i).equalsIgnoreCase("3")) {
                answe = optioncs.get(i);

            } else if (answers.get(i).equalsIgnoreCase("4")) {
                answe = optionds.get(i);

            }


            if (useranswers.get(i).equalsIgnoreCase("1")) {
                userAns = optionas.get(i);

            } else if (useranswers.get(i).equalsIgnoreCase("2")) {
                userAns = optionbs.get(i);

            } else if (useranswers.get(i).equalsIgnoreCase("3")) {
                userAns = optioncs.get(i);

            } else if (useranswers.get(i).equalsIgnoreCase("4")) {
                userAns = optionds.get(i);

            } else if (useranswers.get(i).equalsIgnoreCase("not")) {
                userAns = "Not answered";

            }
            String reasondes = "";
            if (i < reasons.size())
                reasondes = reasons.get(i);

            ResultsListItem rli = new ResultsListItem(question.get(i), answe, userAns, reasondes);
            listData.add(rli);
        }
        mAdapter = new ResultTransitionListAdapter(ResultsActivity.this, listData);
        listView.setAdapter(mAdapter);

        final int fi = ShowSubTransitionListFragment.clickedposition + 1;
        int si = ShowSubTransitionListFragment.totalsubquizes;

        for (int i = 0; i < answers.size(); i++) {
            Log.d("user answer", useranswers.get(i) + "/" + answers.get(i));
            if (useranswers.get(i).equalsIgnoreCase(answers.get(i)))
                userscore++;

        }

        titleBarText.setText("Your Score");
        scorecard.setText(userscore + "/" + question.size());
        //Log.d("clciked post", ""+fi+si);
        if (fi == si) {
            syncimage.setEnabled(false);
            syncimage.setVisibility(View.INVISIBLE);
            ShowSubTransitionListFragment.setTotalsubquizes(0);
        }
        syncimage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSubTransitionListFragment.setClickedposition(fi);
                Intent intent = new Intent(ResultsActivity.this, SingleQuestionDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("from", "ResultsActivity");
                bundle.putInt("clickedpo", fi);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        Button btnback = (Button) findViewById(R.id.title_bar_left_menu);
        btnback.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                userbackpressed();
            }

            private void userbackpressed() {

                Intent i = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(i);

                //getSupportFragmentManager().popBackStack();
            }

        });
        title_bar_share_menu.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                sharebar.setVisibility(View.GONE);
                //mAdView.setVisibility(View.INVISIBLE);
                //launcherbar.setVisibility(View.VISIBLE);
                shareTextUrl();

                //				ContextThemeWrapper ctw = new ContextThemeWrapper(ResultsActivity.this, R.style.CustomDialogTheme);
                //				AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(ctw);
                //				// ...Irrelevant code for customizing the buttons and title
                //				LayoutInflater inflater = getLayoutInflater();
                //				View dialogView = inflater.inflate(R.layout.custom_dialog, null);
                //				dialogBuilder.setView(dialogView);
                //
                ////				EditText editText = (EditText) dialogView.findViewById(R.id.label_field);
                ////				editText.setText("test label");
                //				AlertDialog alertDialog = dialogBuilder.create();
                //				alertDialog.show();

                //				final Dialog dialog = new Dialog(ResultsActivity.this);
                //                dialog.setContentView(R.layout.custom_dialog);
                //                dialog.setTitle("Custom Dialog");
                //
                //                TextView text = (TextView) dialog.findViewById(R.id.textDialog);
                //                text.setText("Your Score For");
                //
                //                ImageButton shareButton = (ImageButton) dialog.findViewById(R.id.shareButton);
                //                shareButton.setOnClickListener(new OnClickListener() {
                //                    @Override
                //                    public void onClick(View v) {
                //                        // Close dialog
                //                        dialog.dismiss();
                //        				shareTextUrl();
                //
                //                    }
                //                });
                //
                //                ImageButton allQuizes = (ImageButton) dialog.findViewById(R.id.allQuizes);
                //                // if decline button is clicked, close the custom dialog
                //                allQuizes.setOnClickListener(new OnClickListener() {
                //                    @Override
                //                    public void onClick(View v) {
                //                        // Close dialog
                //                        dialog.dismiss();
                //                        Intent i=new Intent(ResultsActivity.this,MainActivity.class);
                //        				startActivity(i);
                //                    }
                //                });
                //
                //                dialog.show();

            }
        });


    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (question != null && question.size() > 0)
            question.clear();
        if (optionas != null && optionas.size() > 0)
            optionas.clear();
        if (optionbs != null && optionbs.size() > 0)
            optionbs.clear();
        if (optioncs != null && optioncs.size() > 0)
            optioncs.clear();
        if (optionds != null && optionds.size() > 0)
            optionds.clear();
        if (answers != null && answers.size() > 0)
            answers.clear();
        if (useranswers != null && useranswers.size() > 0)
            useranswers.clear();
        if (reasons != null && reasons.size() > 0)
            reasons.clear();


    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ResultsActivity.this, MainActivity.class);
        startActivity(i);
        super.onBackPressed();

    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

    private void shareTextUrl() {


        File file = new File(getExternalFilesDir(null), "screen.jpg");
        //String file1=Environment.getExternalStorageDirectory().toString()+"/"+ "screen.jpg";
        View v = getWindow().getDecorView().getRootView();
        v.setDrawingCacheEnabled(true);
        Bitmap bmp = Bitmap.createBitmap(v.getDrawingCache());
        v.setDrawingCacheEnabled(false);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(CompressFormat.PNG, 100, fos);
            Uri imageUri = Uri.fromFile(file);
            Intent share = new Intent(android.content.Intent.ACTION_SEND);
            share.setType("text/plain");
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
            share.putExtra(Intent.EXTRA_SUBJECT, "Your Projected Score in Quiz");
            share.putExtra(Intent.EXTRA_TEXT, "Your Score is" + userscore + "/" + question.size());
            share.putExtra(Intent.EXTRA_STREAM, imageUri);
            share.setType("image/*");
            //share.setType("multipart/mixed");
            share.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivity(Intent.createChooser(share, "Share link!"));

            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            sharebar.setVisibility(View.VISIBLE);
            //mAdView.setVisibility(View.VISIBLE);
            //launcherbar.setVisibility(View.GONE);

        }

    }

    private File savebitmap(Bitmap bmp) {
        String temp = "SplashItShare";
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
        OutputStream outStream = null;
        String path = Environment.getExternalStorageDirectory()
                .toString();
        new File(path + "/SplashItTemp").mkdirs();
        File file = new File(path + "/SplashItTemp", temp + ".png");
        if (file.exists()) {
            file.delete();
            file = new File(path + "/SplashItTemp", temp + ".png");
        }

        try {
            outStream = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return file;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intent i2 = new Intent(ResultsActivity.this, MainActivity.class);
        startActivity(i2);
        //overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
        return super.onOptionsItemSelected(menuItem);
    }

}

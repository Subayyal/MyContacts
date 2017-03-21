package com.example.subi.mycontacts;

import android.content.ContentValues;
import android.net.Uri;
import android.os.StrictMode;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        byte[] image = getImage("http://formsdotstar-c3l.s3.amazonaws.com/user-icon.png");
        setTitle("My Contacts");
        insertNote("subayyal", "mustafvi", "1234234523", "s.m@hotmail.com", "123 street dallas texas", "TRUE", image);
        insertNote("ABC", "EFG", "1234234523", "s.m@hotmail.com", "123 street dallas texas", "FALSE", image);
        insertNote("Jimmy", "Bollard", "1234234523", "s.m@hotmail.com", "123 street dallas texas", "FALSE", image);
        insertNote("cristiano", "Ronaldo", "1234234523", "s.m@hotmail.com", "123 street dallas texas", "TRUE", image);
        insertNote("Leo", "Messi", "1234234523", "s.m@hotmail.com", "123 street dallas texas", "TRUE", image);

    }


    private byte[] getImage(String url){
        try{
            URL imageUrl = new URL(url);
            URLConnection ucon = imageUrl.openConnection();

            InputStream is = ucon.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            //We create an array of bytes
            byte[] data = new byte[50];
            int current = 0;

            while((current = bis.read(data,0,data.length)) != -1){
                buffer.write(data,0,current);
            }

            FileOutputStream fos = new FileOutputStream(url);
            fos.write(buffer.toByteArray());
            fos.close();
            return  buffer.toByteArray();
        }catch (Exception e){
            Log.d("ImageManager", "Error: " + e.toString());
        }
        return null;
    }

    private void insertNote(String fname, String lname, String phnum, String email, String address, String bool, byte[] image){
        ContentValues values = new ContentValues();
        values.put(DBHelper.CONTACT_FIRST_NAME,fname);
        values.put(DBHelper.CONTACT_LAST_NAME, lname);
        values.put(DBHelper.CONTACT_PHONE_NUMBER, phnum);
        values.put(DBHelper.CONTACT_EMAIL, email);
        values.put(DBHelper.CONTACT_ADDRESS, address);
        values.put(DBHelper.CONTACT_FAVORITE, bool);
        values.put(DBHelper.CONTACT_IMAGE, image);
        Uri noteUri = getContentResolver().insert(ContactsProvider.CONTENT_URI,values);
        Log.d("MainActivity", "Inserted note " + noteUri.getLastPathSegment());
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


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    ContactsTab contactsTab = new ContactsTab();
                    return contactsTab;
                case 1:
                    FavoritesTab favoritesTab = new FavoritesTab();
                    return favoritesTab;
                default:
                    return null;
            }
        }


        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Contacts";
                case 1:
                    return "Favorites";
            }
            return null;
        }
    }
}

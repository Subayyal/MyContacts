package com.example.subi.mycontacts;


import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by subi on 3/19/2017.
 */

public class ContactsTab extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private CursorAdapter cursorAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.contacts_tab_main, container, false);

        String[] from = {DBHelper.CONTACT_FIRST_NAME};
        int[] to = {R.id.contactTextView};
        cursorAdapter = new SimpleCursorAdapter(getContext(), R.layout.custom_contact_list, null, from, to, 0);

        ListView list = (ListView) getView().findViewById(android.R.id.list);
        list.setAdapter(cursorAdapter);

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getContext(), ContactsProvider.CONTENT_URI, null,null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

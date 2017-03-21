package com.example.subi.mycontacts;


import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by subi on 3/19/2017.
 */

public class ContactsTab extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private CursorAdapter cursorAdapter;
    private static final int LOADER_ID = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.contacts_tab_main, container, false);
        String[] from = {DBHelper.CONTACT_FIRST_NAME};
        int[] to = {android.R.id.text1};
        cursorAdapter = new SimpleCursorAdapter(getActivity(), android.R.layout.simple_list_item_1, null, from, to, 0);

        ListView list = (ListView) rootView.findViewById(R.id.contactList);
        list.setAdapter(cursorAdapter);

        getActivity().getSupportLoaderManager().initLoader(0, null, this);

        return rootView;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(getActivity(), ContactsProvider.CONTENT_URI, null,null, null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        cursorAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
       // cursorAdapter.swapCursor(null);
    }
}

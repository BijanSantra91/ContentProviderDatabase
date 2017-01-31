package com.example.bijan.contentproviderex1;

import android.net.Uri;

/**
 * Created by Bijan on 1/29/2017.
 */

//declare column names and prepare URIs(Uniform Resource Identifier) for your table
public class UriProvider {
    public static final String _ID = "_id";
    public static final String NAME = "sname";
    public static final String SUM = "ssub";

    //prepare URI for student information table
    public static final Uri STIDENT_URI =
            Uri.parse("content://com.techpalle.com/studenttable");
}

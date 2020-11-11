package com.csc241;

import java.io.*;

public class HshTbl
{
    private LnkLst[] lnkLsts;

    private static final int HASHSIZE = 701;   // A prime number not too near a power of 2.

    private int hash( final Data searchData )
    {
        int cnt, hash;

        String key = searchData.getSsn();

        for( cnt = hash = 0 ; cnt < Data.SSNLN ; cnt++ )
        {
            //hash = ( hash * Data.NUMCHARS + ( int ) key.charAt( cnt ) - Data.SP ) % HASHSIZE;
        }

        return( hash );
    }

    public HshTbl()
    {
        lnkLsts = new LnkLst[ HASHSIZE ];

        for( int i = 0 ; i < HASHSIZE ; i++ )
        {
            lnkLsts[ i ] = new LnkLst();
        }
    }

    public int createFromFile( String fileName )   // TODO
    {
        return 1;
    }


    public int delRec( final Data searchData )   // TODO
    {
        return 1;
    }

    public Data getCurrentEntry( final Data searchData )
    {
        return lnkLsts[ hash( searchData ) ].getCurrentEntry();
    }

    public int ins( final Data data )   // TODO
    {
        return 1;
    }

    public boolean isEmpty()
    {
        return length() == 0;
    }

    public int length()   // TODO
    {
        return 1;
    }

    public int search( final Data searchData )   // TODO
    {
        return 1;
    }
}
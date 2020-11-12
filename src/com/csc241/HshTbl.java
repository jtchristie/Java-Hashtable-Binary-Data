package com.csc241;

import java.io.*;

public class HshTbl extends SrtdLnkLst
{
    private LnkLst[] lnkLsts;

    private static final int HASHSIZE = 701;   // A prime number not too near a power of 2.

    private int hash( final Data searchData )
    {
        int cnt, hash;

        String key = searchData.getSsn();

        for( cnt = hash = 0 ; cnt < Data.SSNLN ; cnt++ )
        {
           // hash = ( hash * Data.NUMCHARS + ( int ) key.charAt( cnt ) - Data.SP ) % HASHSIZE;
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
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        FileInputStream fis = null;
        boolean var5 = true;

        int nData;
        try {
            File file = new File(fileName);
            dis = new DataInputStream(bis = new BufferedInputStream(fis = new FileInputStream(file)));
            nData = (int)file.length() / 131;
            byte[] dataBytes = new byte[131];
            int i = 0;

            for(byte offset = 0; i < nData; ++i) {
                Data data = new Data();
                dis.read(dataBytes, offset, 131);
                data.setSsn(new String(dataBytes, offset, 9));
                 offset += 9;
                data.setFName(new String(dataBytes, offset, 20));
                offset += 20;
                data.setMName(new String(dataBytes, offset, 1));
                ++offset;
                data.setLName(new String(dataBytes, offset, 30));
                offset += 30;
                data.setAddr(new String(dataBytes, offset, 40));
                offset += 40;
                data.setCity(new String(dataBytes, offset, 20));
                offset += 20;
                data.setState(new String(dataBytes, offset, 2));
                offset += 2;
                data.setZip(new String(dataBytes, offset, 9));
                offset = 0;
              //  this.insTail(data);
            }
        } catch (Exception var19) {
            var19.printStackTrace();
            nData = -1;
        } finally {
            try {
                fis.close();
                bis.close();
                dis.close();
            } catch (Exception var18) {
            }

        }

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
        return nNodes;
    }

    public int search( final Data searchData )   // TODO
    {
        return 1;
    }
}
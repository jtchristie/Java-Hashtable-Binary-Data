package com.csc241;

import javax.security.auth.login.FailedLoginException;
import java.io.*;

public class HshTbl
{
    private LnkLst[] lnkLsts;
    static int FAILURE = -1;
    private static final int HASHSIZE = 701;   // A prime number not too near a power of 2.

    //This method gets the key from the data and hashes it, and returns the hash. This hash is used as the key to search the data
    private int hash( final Data searchData )
    {
        int cnt, hash;

        String key = searchData.getSsn();

        for( cnt = hash = 0 ; cnt < Data.SSNLN ; cnt++ )
        {
            hash = ( hash * Data.DATALN + ( int ) key.charAt( cnt ) - Data.SSNLN ) % HASHSIZE;
        }

        return( hash );
    }

    //this method creates an array of linked lists of size HASHSIZE. For each element in the array a new linked list is created
    public HshTbl()
    {
        lnkLsts = new LnkLst[ HASHSIZE ];

        for( int i = 0 ; i < HASHSIZE ; i++ )
        {
            lnkLsts[ i ] = new LnkLst();
        }
    }

    //this method creates the hashtable of linked lists from a file
    public int createFromFile( String fileName )
    {
        BufferedInputStream bis = null;
        DataInputStream dis = null;
        FileInputStream fis = null;
        boolean var5 = true;

        int nData;
        try {
            //initializes input stream
            File file = new File(fileName);
            dis = new DataInputStream(bis = new BufferedInputStream(fis = new FileInputStream(file)));
            //the value is divided by 131 because that is how many characters there are in one unit of data
            nData = (int)file.length() / 131;
            byte[] dataBytes = new byte[131];
            int i = 0;

            for(byte offset = 0; i < nData; ++i) {
                //Data is an object oriented class that has categories, SSN, Fname, Mname, LName, Addr, City, State, Zip
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

                this.ins(data);
            }
            return nData;
        } catch (Exception var19) {
            var19.printStackTrace();
        } finally {
            try {
                fis.close();
                bis.close();
                dis.close();
            } catch (Exception var18) {
                return FAILURE;
            }

        }
        return FAILURE;
    }


    public int delRec( final Data searchData )
    {
        //this hashes the data that is being prepared to be deleted. The hashed value will match the data that the user is trying to delete
        int hash = hash(searchData);
        //this if statements checks to make sure that the value that the user is trying to delete exists.
        if (lnkLsts[hash].length() > 0){
            //reseting lnkLsts at the hash index, removes it.
            lnkLsts[hash].reset();
            return lnkLsts[hash].length();
        } else {
            return FAILURE;
        }
    }

    public Data getCurrentEntry( final Data searchData )
    {
        return lnkLsts[ hash( searchData ) ].getCurrentEntry();
    }


    public int ins( final Data data )
    {
        int hash = hash(data);
        //checks to make sure that the data being inserted has a value, then inserts the data.
        if (lnkLsts[hash].insHead(data) > 0){
            lnkLsts[hash].insHead(data);
            return lnkLsts[hash].length();
        } else {
            return FAILURE;
        }
    }

    public boolean isEmpty()
    {
        return length() == 0;
    }

    public int length()
    {
        try {
            return length();
        } catch (Exception e){
            return FAILURE;
        }
    }

    public int search( final Data searchData )
    {
        int hash = hash(searchData);
        StringBuilder msg = new StringBuilder("");
        long searchOutput = 0;
        //gets the Ssn of the inputted data.
        String data = searchData.getSsn();
        boolean found = false;
        //searchStartTime and searchEndTime are used to calculate how long it took to search the data.
        long  searchStartTime = System.currentTimeMillis();
        //checks if the hashed searchData equals to the input data.
        if (lnkLsts[hash].getCurrentEntry().getSsn().equals(data)){
            msg.append("\nThe key was found in record # ").append(hash);
            System.out.print(msg);
            msg.delete(0, msg.length());
            found = true;
        }
        long searchEndTime = System.currentTimeMillis();

        searchOutput = searchEndTime - searchStartTime;

        if (found == true){
            msg.append("\nThe search required ").append(searchOutput).append(" ms to complete\n");
            msg.append("\n").append(lnkLsts[hash].getCurrentEntry()).append("\n");
            System.out.print(msg);
            msg.delete(0, msg.length());
        }  else {
            msg.append("SSN ").append(Data.fmtSsn(data)).append(" was not found");
            System.out.print(msg);
            msg.delete(0, msg.length());
            return FAILURE;
        }
        return hash;
    }
}
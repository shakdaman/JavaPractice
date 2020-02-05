package com.foruswithus.pdf;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class FunWithPDFs {
    public static void main(String[] args) {
        File[] files = new File("C:\\Users\\shakd\\Desktop\\testing").listFiles();

        Set<String> set = new HashSet<String>();

        for( int i=0; i< files.length; i++ ) {
            System.out.println(files[i].getName());
            set.add( files[i].getName().substring(0, files[i].getName().indexOf("_")) );
        }

        int[] nbrOfFiles = new int[set.size()];
        Iterator<String> itr = set.iterator();

        for ( int i=0; i<files.length; i++ ) {
            while ( itr.hasNext()) {
                if ( files[i].getName().substring(0, files[i].getName().indexOf("_")).equalsIgnoreCase(itr.next())) {
                    System.out.println("true");
                } else {
                    System.out.println("false");
                }
            }
        }
        System.out.println(set);


        System.out.println("Going through set......");




    }

}

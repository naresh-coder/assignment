package com.test;

import java.util.ArrayList;
import java.util.List;

public class IntegerParseMain {

    public static void main(String[] args) {



       String s = "90";

        List<String> stringList = new ArrayList<>();
        String ss = "";

        for(int i = 0; i < 10;i++){
            if(i%2==0){
                ss = ss+i+"f";
            }
            else{
                ss =""+i;
            }

            stringList.add(ss);
        }
        long start = System.currentTimeMillis();
        for(String s1: stringList){
            IsVAlidExcption(s1);
        }
        long end = System.currentTimeMillis();

        System.out.println("===Excpetion =="+(end-start));


         start = System.currentTimeMillis();
        for(String s1: stringList){

            IsValidNumber(s);
        }
         end = System.currentTimeMillis();

        System.out.println("===no =="+(end-start));







    }

    public static void IsVAlidExcption(String s) {
        try{
            Integer.parseInt(s);
        }catch (Exception e){
           // System.out.println(s);
        }
    }

    public static void IsValidNumber(String s) {
        if(s.length() >=0 && s.length() <=2){

            int f = s.charAt(0) -'0';
            int t = 0;


            int e  =0;
            if(s.length()>1){
                 e = s.charAt(1) -'0';
                 t  = f*10+e;
            }
            else{
                t = f;
            }

            if(t > 0  && t > 80 ){
               // System.out.println(s);
            }



        }
        else{
           // System.out.println(s);
        }
    }


}

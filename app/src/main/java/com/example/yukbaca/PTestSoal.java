package com.example.yukbaca;

import java.util.Random;

public class PTestSoal {
    private String[] mSoal = {

            "Manakah Huruf...",//1
            "Manakah Huruf...",//2
            "Manakah Huruf...",//3
            "Manakah Huruf...",//4
            "Manakah Huruf...",//5

            "Manakah Huruf...",//1
            "Manakah Huruf...",//2
            "Manakah Huruf...",//3
            "Manakah Huruf...",//4
            "Manakah Huruf...",//5

            "Manakah Kata...",//6
            "Manakah Kata...",//7
            "Manakah Kata...",//8
            "Manakah Kata...",//9
            "Manakah Kata...",//10

            "buku",//11
            "buah",//12
            "topi",//13
            "makan",//14
            "kapal",//15

    };



    private String[][] mChoices = {
            {"d","b"}, //1
            {"z","s"}, //2
            {"n","h"}, //3
            {"p","q"}, //4
            {"O","Q"}, //5
            {"d","b"}, //6
            {"r","s"}, //7
            {"t","c"}, //8
            {"f","p"}, //9
            {"p","b"}, //10

            {"buku","duku"}, //6
            {"dadu","babu"}, //7
            {"zuzu","susu"}, //8
            {"motor","motro"}, //9
            {"dadi","babi"}, //10

            {"topi","kopi"}, //11
            {"kapal","palak"}, //12
            {"tanduk","handuk"}, //13
            {"cacing","cacang"}, //14
            {"foto","fata"}, //15

    };


    private String[] mCorrectAnswers = {
            "b", //1
            "z", //2
            "n", //3
            "p", //4
            "O", //5
            "d", //6
            "r", //7
            "c", //8
            "p", //9
            "b", //10
            "buku", //6
            "dadu", //7
            "susu", //8
            "motor", //9
            "babi", //10
            "topi", //11
            "kapal", //12
            "handuk", //13
            "cacing", //14
            "foto", //15
    };

    private int[] sQuestion={
            R.raw.b, //1
            R.raw.z, //2
            R.raw.n, //3
            R.raw.p, //4
            R.raw.o, //5

            R.raw.d, //6
            R.raw.r,//7
            R.raw.c,//8
            R.raw.p,//9
            R.raw.b,//10

            R.raw.buku, //11
            R.raw.dadu, //12
            R.raw.susu, //13
            R.raw.motor, //14
            R.raw.babi, //15

            R.raw.topi, //16
            R.raw.kapal, //17
            R.raw.handuk, //18
            R.raw.cacing, //19
            R.raw.foto //20
    };

    private int[] pQuestion={
            0, //1
            0, //2
            0, //3
            0, //4
            0, //5
            0, //1
            0, //2
            0, //3
            0, //4
            0, //5

            R.drawable.bukup, //11
            R.drawable.dadup, //12
            R.drawable.susup, //13
            R.drawable.motor, //14
            R.drawable.babi, //15

            0, //1
            0, //2
            0, //3
            0, //4
            0, //5

    };
    public int getpQuestion(int a) {
        return pQuestion[a];
    }
    public String getQuestion(int a) {
        Random random = new Random();
        int il = random.nextInt();
        return mSoal[a];
    }

    public int getsQuestion(int a) {
        return sQuestion[a];
    }

    public String getChoice1(int a) {
        return mChoices[a][0];
    }


    public String getChoice2(int a) {
        return mChoices[a][1];
    }



    public String getCorrectAnswer(int a) {
        return mCorrectAnswers[a];
    }

    public int getQuestion() {
        return mSoal.length;
    }
}

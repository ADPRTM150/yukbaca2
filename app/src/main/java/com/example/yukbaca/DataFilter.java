package com.example.yukbaca;

public class DataFilter {
    private String Nama;
    private String ID;
    private String Nilai;
    private String Nilai1;

    DataFilter(String ID, String Nama, String Nilai, String Nilai1) {
        this.Nama = Nama;
        this.ID = ID;
        this.Nilai = Nilai;
        this.Nilai1 = Nilai1;
    }

    String getNama() {
        return Nama;
    }

    public String getID() {
        return ID;
    }

    public String getNilai() {
        return Nilai;
    }

    public String getNilai1() {
        return Nilai1;
    }
}

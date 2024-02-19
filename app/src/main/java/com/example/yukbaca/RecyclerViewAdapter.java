package com.example.yukbaca;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<DataFilter> dataList;
    private Context context;




    //Membuat Konstruktor pada Class RecyclerViewAdapter
    RecyclerViewAdapter(ArrayList<DataFilter> dataList){
        this.dataList = dataList;
    }

    //ViewHolder Digunakan Untuk Menyimpan Referensi Dari View-View
    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView Nama, Nilai, ID;
        private ImageButton btnDelete;
        private LinearLayout rvcc;

        ViewHolder(View itemView) {
            super(itemView);

            //Mendapatkan Context dari itemView yang terhubung dengan Activity ViewData
            context = itemView.getContext();

            //Menginisialisasi View-View untuk kita gunakan pada RecyclerView
            Nama = itemView.findViewById(R.id.name);
//            ID = itemView.findViewById(R.id.ID);
            Nilai = itemView.findViewById(R.id.nilai);
            btnDelete = itemView.findViewById(R.id.delete);
            rvcc = itemView.findViewById(R.id.rvclick);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Membuat View untuk Menyiapkan dan Memasang Layout yang Akan digunakan pada RecyclerView
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_design, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        //Memanggil Nilai/Value Pada View-View Yang Telah Dibuat pada Posisi Tertentu
        final String Nama = dataList.get(position).getNama();//Mengambil data (Nama) sesuai dengan posisi yang telah ditentukan
        final String ID = dataList.get(position).getID();//Mengambil data (Jurusan) sesuai dengan posisi yang telah ditentukan
        final String Nilai = dataList.get(position).getNilai();//Mengambil data (NIM) sesuai dengan posisi yang telah ditentukan
        final String Nilai1 = dataList.get(position).getNilai1();//Mengambil data (NIM) sesuai dengan posisi yang telah ditentukan
        holder.Nama.setText(Nama);
        holder.Nilai.setText(Nilai);
//        holder.ID.setText(ID);

        //Mengimplementasikan Menu Popup pada Overflow (ImageButton)
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

                        //Menghapus Data Dari Database
                        DatabaseHelper getDatabase = new DatabaseHelper(view.getContext());
                        SQLiteDatabase DeleteData = getDatabase.getWritableDatabase();
                        //Menentukan di mana bagian kueri yang akan dipilih
                        String selection = DatabaseHelper.MyColumns.ID + " LIKE ?";
                        //Menentukan Nama Dari Data Yang Ingin Dihapus
                        String[] selectionArgs = {ID};
                        DeleteData.delete(DatabaseHelper.MyColumns.TABLE_NAME, selection, selectionArgs);

                        //Menghapus Data pada List dari Posisi Tertentu
                        String position2 = String.valueOf(ID.indexOf(ID));
                        dataList.remove(position);
                        notifyItemRemoved(position);
                        if (position2 == null) {
                            notifyItemRangeChanged(Integer.parseInt(position2), dataList.size());
                        }


            }
        });
        holder.rvcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Item is clicked"+Nama, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), DetailNilai.class);
                i.putExtra("nama", Nama);
                i.putExtra("score", Nilai);
                i.putExtra("scorehuruf", Nilai1);
                i.putExtra("scorebunyi", "90");
                i.putExtra("scorekata", "90");
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        //Menghitung Ukuran/Jumlah Data Yang Akan Ditampilkan Pada RecyclerView
        return dataList.size();
    }

    void setFilter(ArrayList<DataFilter> filterList){
        dataList = new ArrayList<>();
        dataList.addAll(filterList);
        notifyDataSetChanged();
    }

}

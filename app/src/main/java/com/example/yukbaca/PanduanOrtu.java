package com.example.yukbaca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.yukbaca.fragment.Karakteristik;
import com.example.yukbaca.fragment.PengenalanFragment;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.tabs.TabLayout;

public class PanduanOrtu extends AppCompatActivity {
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panduan_ortu);
        tabLayout=findViewById(R.id.tablayputid);
        appBarLayout=findViewById(R.id.appbarid);
        viewPager=findViewById(R.id.viewpagerid);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.AddFragment(new PengenalanFragment(),"Pengenalan Yuk Membaca");
        adapter.AddFragment(new Karakteristik(),"Karakteristik");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
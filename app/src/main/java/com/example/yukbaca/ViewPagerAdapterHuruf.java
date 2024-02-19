package com.example.yukbaca;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapterHuruf extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList = new ArrayList<>();
    private  final List<String> FragmentListJudul = new ArrayList<>();

    public ViewPagerAdapterHuruf(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public ViewPagerAdapterHuruf(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return FragmentListJudul.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentListJudul.get(position);
    }

    public void AddFragment(Fragment fragment, String Judul){
        fragmentList.add(fragment);
        FragmentListJudul.add(Judul);
    }
}
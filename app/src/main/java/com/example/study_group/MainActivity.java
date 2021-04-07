package com.example.study_group;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.kakao.util.helper.Utility;
//import com.kakao.util.maps.helper.Utility;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;

    ArrayList<Integer> imgIds = new ArrayList<Integer>();
    MyAdapter adapter;
    ViewPager Pager;

    FragmentManager fragmentManager;
    Fragment[] fragments = new Fragment[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        drawerLayout = findViewById(R.id.layout_drawer);
        navigationView = findViewById(R.id.nav);
        //item icon색조를 적용하지 않음. 설정하지 않으면 기본 회색계열 틴트
        navigationView.setItemIconTintList(null);

        String keyHash = Utility.getKeyHash(this);


        imgIds.add(R.drawable.studygroup01);
        imgIds.add(R.drawable.studygroup02);
        imgIds.add(R.drawable.studygroup03);
        imgIds.add(R.drawable.studygroup04);
        imgIds.add(R.drawable.studygroup05);


        Pager = findViewById(R.id.pager);
        adapter = new MyAdapter(this, imgIds);
        Pager.setAdapter(adapter);

        fragments[0] = new Tab1Fragment();
        fragments[1] = new Tab2Fragment();
        fragments[2] = new Tab3Fragment();
        fragments[3] = new Tab3Fragment();

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction tran = fragmentManager.beginTransaction();
        tran.add(R.id.container, fragments[0]);
        tran.add(R.id.container, fragments[1]);
        tran.add(R.id.container, fragments[2]);
        tran.add(R.id.container, fragments[3]);
        tran.show(fragments[0]);
        tran.hide(fragments[1]);
        tran.hide(fragments[2]);
        tran.hide(fragments[3]);
        tran.commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.menu_faveorite:
                        startActivity(new Intent(MainActivity.this, com.example.study_group.nav_faveorite.class));
                        break;
                    case R.id.menu_gallery:
                        startActivity(new Intent(MainActivity.this, com.example.study_group.nav_gallery.class));
                        break;
                    case R.id.menu_event:
                        startActivity(new Intent(MainActivity.this, com.example.study_group.nav_event.class));
                        break;
                    case R.id.menu_calculate:
                        startActivity(new Intent(MainActivity.this, com.example.study_group.nav_calculate.class));
                        break;
                    case R.id.menu_setting:
                        startActivity(new Intent(MainActivity.this, com.example.study_group.nav_setting.class));
                        break;
                    case R.id.menu_mail:
                        startActivity(new Intent(MainActivity.this, com.example.study_group.nav_mail.class));
                        break;
                }
                //Drawer 닫기
                drawerLayout.closeDrawer(navigationView);

                return false;
            }
        });
        //드로우어 조절용 토글버튼 생성
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.app_name,R.string.app_name);

        //액션바의 홈or업 버튼의 위치에 아이콘이 보이게끔
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        //삼선아이콘 모양으로 보이도록 토글버튼의 동기 맞추기
        drawerToggle.syncState();
        //삼선아이콘과 화살표아이콘이 자동으로 변환되도록
        drawerLayout.addDrawerListener(drawerToggle);


    }


    public void clickPrev(View view) {
        int index = Pager.getCurrentItem() - 1;
        Pager.setCurrentItem(index, true);
    }

    public void clickNext(View view) {
        int index = Pager.getCurrentItem() + 1;
        Pager.setCurrentItem(index, true);
    }



}

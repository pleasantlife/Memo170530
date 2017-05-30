package android.kimjinhwan.com.memo3;

import android.content.Intent;
import android.kimjinhwan.com.memo3.domain.Loader;
import android.kimjinhwan.com.memo3.domain.Memo;
import android.kimjinhwan.com.util.FileUtil;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    RecyclerAdapter adapter;
    boolean firstFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        //1. 데이터
        ArrayList<Memo> datas = Loader.getData(this);
        //2. 아답터
        adapter = new RecyclerAdapter(datas);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        //3. 레이아웃 매니저
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.btnNew);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
                startActivity(intent);
            }
        });
    }
    //디테일 액티비티를 열었다가 종료하면 호출될 때
    //데이터를 갱신하고, 목록을 다시 그려준다.
    @Override
    protected void onResume() {
        super.onResume();
        //데이터를 갱신
        if (!firstFlag) {
            Loader.getData(this);
            //아답터를 갱신
            adapter.notifyDataSetChanged(); //아답터에 변경사항이 생기면 변경사항을 화면(액티비티)에 반영해줌.
        } else {
            firstFlag = false;              //preference를 사용해도 됨!
        }
    }
}

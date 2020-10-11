package com.example.todolistandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

public class List extends AppCompatActivity implements AsyncListenerInterface {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        setTitle("All Todos Available");
        Service.getInstance().getData(this);
    }

    public void showData (){
        final Service service = Service.getInstance();
        if(service.data.size() > 0){
            MyAdapter adapter = new MyAdapter(service.data);
            recyclerView = findViewById(R.id.recycler_view);
            layoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
            adapter.setOnClickListener(new MyAdapter.onClickListener() {
                @Override
                public void onItemClick(int position) {
                    Toast.makeText(getBaseContext() , service.data.get(position).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @Override
    public void onTaskFinished() {
        showData();
    }
}

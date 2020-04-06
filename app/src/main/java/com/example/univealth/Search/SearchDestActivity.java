package com.example.univealth.Search;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.univealth.Pois.PoisItem;
import com.example.univealth.Pois.SearchAsyncTask;
import com.example.univealth.R;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class SearchDestActivity extends AppCompatActivity {
    private EditText editSearch;
    private ListView listView;
    private SearchAdapter adapter;
    ArrayList<PoisItem> list;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchdest);

        editSearch = findViewById(R.id.editSearch);
        listView = findViewById(R.id.listView);
        list = new ArrayList<PoisItem>();
        Intent intent = getIntent();
        String startActivity = intent.getStringExtra("activity");
        if(startActivity.equals("InsertActivity")){
            adapter = new SearchAdapter(list,this,"InsertActivity");
        }else{
            adapter = new SearchAdapter(list,this, "FragmentMap");
        }
        listView.setAdapter(adapter);
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                SearchAsyncTask searchAsyncTask = new SearchAsyncTask(SearchDestActivity.this,editSearch.getText().toString());
                try {
                    search(searchAsyncTask.execute().get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void search(ArrayList<PoisItem> list) {
        this.list.clear();
        for(int i=0 ; i<list.size() ; i++){
            this.list.add(list.get(i));
        }
        adapter.notifyDataSetChanged();
    }
}

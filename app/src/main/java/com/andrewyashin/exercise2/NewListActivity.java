package com.andrewyashin.exercise2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

public class NewListActivity extends Activity {

    private final NewsRecyclerAdapter.OnItemClickListener clickListener = new NewsRecyclerAdapter.OnItemClickListener() {
        @Override
        public void OnItemClick(int position) {
            NewsDetailsActivity.start(NewListActivity.this, position);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_list);

        int columnsCount = getResources().getConfiguration().orientation;

        RecyclerView recyclerView = findViewById(R.id.recycler);
        recyclerView.setAdapter(new NewsRecyclerAdapter(this, DataUtils.generateNews(), clickListener));
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, columnsCount);

        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(4, this));

        getActionBar().setDisplayHomeAsUpEnabled(false);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(this, AboutActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

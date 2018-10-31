package com.andrewyashin.exercise2;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    private final int spaceDIP;

    public SpaceItemDecoration(int space, @NonNull Context context) {
        this.spaceDIP = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, space, context.getResources().getDisplayMetrics());
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = spaceDIP;
        outRect.left = spaceDIP;
        outRect.right = spaceDIP;

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.top = spaceDIP;
        } else {
            outRect.top = 0;
        }

        GridLayoutManager gr =  (GridLayoutManager) parent.getLayoutManager();
        int spanCount = gr.getSpanCount();

        if(spanCount == 1) {
            if (parent.getChildLayoutPosition(view) == 0) {
                outRect.top = spaceDIP;
            } else {
                outRect.top = 0;
            }
            outRect.bottom = spaceDIP;
            outRect.left = spaceDIP;
            outRect.right = spaceDIP;
        } else {
            if ((parent.getChildLayoutPosition(view) == 0)||(parent.getChildLayoutPosition(view) == 1)) {
                outRect.top = spaceDIP;
            } else {
                outRect.top = 0;
            }
            if (parent.getChildLayoutPosition(view) % 2 == 0) {
                outRect.left = spaceDIP;
                outRect.right = spaceDIP;
            } else {
                outRect.left = 0;
                outRect.right = spaceDIP;
            }
        }
    }

}

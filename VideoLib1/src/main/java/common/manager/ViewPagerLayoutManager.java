package common.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.util.Log;
import android.view.View;

import android.support.v7.widget.RecyclerView;


public class ViewPagerLayoutManager extends LinearLayoutManager {
    private static final String TAG = "ViewPagerLayoutManager";
    private PagerSnapHelper mPagerSnapHelper;
    private OnViewPagerListener mOnViewPagerListener;
    private int mDrift;//位移，用来判断移动方向
    private boolean isScoll = true; //是否可以垂直滑动
    private int currentPageIndex;
    public int firstDy = 0;
    public boolean isToDown = true;

    public ViewPagerLayoutManager(Context context) {
        super(context);
        init();
    }

    public ViewPagerLayoutManager(Context context, int orientation) {
        super(context, orientation, false);
        init();
    }

    public ViewPagerLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
        init();
    }

    private void init() {
        mPagerSnapHelper = new PagerSnapHelper();

    }

    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }


    @Override
    public void onAttachedToWindow(RecyclerView view) {
        super.onAttachedToWindow(view);
        try {
            view.setOnFlingListener(null);
            mPagerSnapHelper.attachToRecyclerView(view);
            view.addOnChildAttachStateChangeListener(mChildAttachStateChangeListener);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 滑动状态的改变
     * 缓慢拖拽-> SCROLL_STATE_DRAGGING
     * 快速滚动-> SCROLL_STATE_SETTLING
     * 空闲状态-> SCROLL_STATE_IDLE
     *
     * @param state
     */
    @Override
    public void onScrollStateChanged(int state) {
        switch (state) {
            case RecyclerView.SCROLL_STATE_IDLE:
                View viewIdle = mPagerSnapHelper.findSnapView(this);
                if (null == viewIdle) {
                    return;
                }
                int positionIdle = getPosition(viewIdle);
                if (currentPageIndex == positionIdle) {
                    return;
                } else {
                    currentPageIndex = positionIdle;
                }
                if (getItemCount() > 0) {
                    if (mOnViewPagerListener != null && getChildCount() == 1) {
                        mOnViewPagerListener.onPageSelected(positionIdle, positionIdle == getItemCount() - 1);
                    }
                }
                break;
            case RecyclerView.SCROLL_STATE_DRAGGING:
                View viewDrag = mPagerSnapHelper.findSnapView(this);
                if (null == viewDrag) {
                    return;
                }
                int positionDrag = getPosition(viewDrag);
                break;
            case RecyclerView.SCROLL_STATE_SETTLING:
                View viewSettling = mPagerSnapHelper.findSnapView(this);
                if (null == viewSettling) {
                    return;
                }
                int positionSettling = getPosition(viewSettling);
                break;
        }
    }


    /**
     * 监听竖直方向的相对偏移量
     *
     * @param dy
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dy;
        if (firstDy == 0) {
            if (dy >= 0) {
                isToDown = true;
                firstDy++;
            } else {
                isToDown = false;
                firstDy++;
            }
        }
        return super.scrollVerticallyBy(dy, recycler, state);
    }


    /**
     * 监听水平方向的相对偏移量
     *
     * @param dx
     * @param recycler
     * @param state
     * @return
     */
    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        this.mDrift = dx;
        return super.scrollHorizontallyBy(dx, recycler, state);
    }

    /**
     * 设置监听
     *
     * @param listener
     */
    public void setOnViewPagerListener(OnViewPagerListener listener) {
        this.mOnViewPagerListener = listener;
    }

    private RecyclerView.OnChildAttachStateChangeListener mChildAttachStateChangeListener = new RecyclerView.OnChildAttachStateChangeListener() {
        @Override
        public void onChildViewAttachedToWindow(View view) {
            if (mOnViewPagerListener != null && getChildCount() == 1) {
                mOnViewPagerListener.onInitComplete();
            }
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {
            if (mDrift >= 0) {
                if (mOnViewPagerListener != null)
                    mOnViewPagerListener.onPageRelease(true, getPosition(view));
            } else {
                if (mOnViewPagerListener != null)
                    mOnViewPagerListener.onPageRelease(false, getPosition(view));
            }

        }
    };

    /**
     * 设置是否可以竖直滑动
     *
     * @param isScoll
     */
    public void setCanScoll(boolean isScoll) {
        this.isScoll = isScoll;
    }

    @Override
    public boolean canScrollVertically() {
        if (isScoll) {
            return super.canScrollVertically();
        } else {
            return false;
        }

    }
}
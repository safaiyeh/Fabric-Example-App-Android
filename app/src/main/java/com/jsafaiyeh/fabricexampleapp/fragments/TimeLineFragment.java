package com.jsafaiyeh.fabricexampleapp.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.digits.sdk.android.ContactsCallback;
import com.jsafaiyeh.fabricexampleapp.R;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.Tweet;
import com.twitter.sdk.android.tweetui.CollectionTimeline;
import com.twitter.sdk.android.tweetui.SearchTimeline;
import com.twitter.sdk.android.tweetui.TimelineResult;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.TwitterListTimeline;
import com.twitter.sdk.android.tweetui.UserTimeline;

public class TimeLineFragment extends ListFragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;
    private TweetTimelineListAdapter adapter;
    private Toolbar mToolbar;
    private SwipeRefreshLayout mSwipeRefreshLayout;



    public static TimeLineFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        TimeLineFragment fragment = new TimeLineFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);

        mToolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timeline, container, false);

        //Build Timelines
        switch (mPage) {
            case 0:
                buildUserTimeline();
                break;
            case 1:
                buildSearchTimeline();
                break;
            case 2:
                buildCollectionTimeline();
                break;
            case 3:
                buildListTimeline();
                break;
        }

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_layout);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getListView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int topRowVerticalPosition =
                        (getListView() == null || getListView().getChildCount() == 0) ?
                                0 : getListView().getChildAt(0).getTop();
                mSwipeRefreshLayout.setEnabled(firstVisibleItem == 0 && topRowVerticalPosition >= 0);
                ((TweetTimelineListAdapter) getListAdapter()).refresh(new ContactsCallback<TimelineResult<Tweet>>() {
                    @Override
                    public void success(Result<TimelineResult<Tweet>> result) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void failure(TwitterException e) {
                    }
                });
            }
        });
    }

    private void buildUserTimeline() {
        UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName("Jsafaiyeh")
                .build();
        adapter = new TweetTimelineListAdapter(getActivity(), userTimeline);
        setListAdapter(adapter);
    }

    private void buildSearchTimeline() {
        SearchTimeline searchTimeline = new SearchTimeline.Builder()
                .query("#skateboarding")
                .build();
        adapter = new TweetTimelineListAdapter(getActivity(), searchTimeline);
        setListAdapter(adapter);
    }

    private void buildCollectionTimeline() {
        CollectionTimeline collectionTimeline = new CollectionTimeline.Builder()
                .id(539487832448843776L)
                .build();
        adapter = new TweetTimelineListAdapter(getActivity(), collectionTimeline);
        setListAdapter(adapter);
    }

    private void buildListTimeline() {
        TwitterListTimeline twitterListTimeline = new TwitterListTimeline.Builder()
                .slugWithOwnerScreenName("coachella-2015", "twittermusic")
                .build();
        adapter = new TweetTimelineListAdapter(getActivity(), twitterListTimeline);
        setListAdapter(adapter);
    }
}

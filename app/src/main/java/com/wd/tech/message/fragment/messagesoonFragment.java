package com.wd.tech.message.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.adapter.MessageSoonAdapter;
import com.wd.tech.utils.presenter.IPresentermpl;
import com.wd.tech.utils.view.IView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class messagesoonFragment extends Fragment implements IView {

    @BindView(R.id.msg_xrecycle)
    XRecyclerView msgXrecycle;
    Unbinder unbinder;
    private MessageSoonAdapter soonAdapter;
    private IPresentermpl presentermpl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_messagesoon, container,false);
        unbinder = ButterKnife.bind(this, view);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        msgXrecycle.setLayoutManager(manager);
        soonAdapter = new MessageSoonAdapter(getActivity());
        msgXrecycle.setAdapter(soonAdapter);
        presentermpl = new IPresentermpl(this);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void success(Object data) {

    }

    @Override
    public void failure(String error) {

    }
}

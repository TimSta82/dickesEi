package com.example.tstaats.myvermeer.Dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tstaats.myvermeer.Common.Aktien;
import com.example.tstaats.myvermeer.Fragments.MeantimeFragment;
import com.example.tstaats.myvermeer.MainActivity;
import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.R;

public class StayDialogFragment extends DialogFragment {

    private static final String TAG = "StayDialogFragment";

    public interface OnInputSelected{
        void sendInput(int timeSpan);
    }
    public OnInputSelected mOnInputSelected;

    private MainActivity mainActivity;
    private PlayerStats mPlayer;
    private Aktien mAktien;

    // widgets
    private TextView infoTv;
    private EditText stayEt;
    private Button sevenDaysBtn;
    private Button fourteenDaysBtn;
    private Button thirtyDaysBtn;
    private TextView confirmTv;
    private TextView cancelTv;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stay_dialog, container, false);
        
        initDialog(view);

        confirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: confirm btn clicken");

                String input = stayEt.getText().toString();
                if(!input.equals("")){
                    Log.d(TAG, "onClick: input: " + input);
                    int inputInt = Integer.parseInt(input);
                    mOnInputSelected.sendInput(inputInt);


                    mainActivity.fragmentSwitcher(new MeantimeFragment(), "MeantimeFragment");
                } else {
                    Log.d(TAG, "onClick: " + R.string.toast_empty_edittext);
                }


                getDialog().dismiss();
            }
        });

        sevenDaysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnInputSelected.sendInput(7);
                mainActivity.fragmentSwitcher(new MeantimeFragment(), "MeantimeFragment");
                getDialog().dismiss();
            }
        });

        fourteenDaysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnInputSelected.sendInput(14);
                mainActivity.fragmentSwitcher(new MeantimeFragment(), "MeantimeFragment");
                getDialog().dismiss();
            }
        });

        thirtyDaysBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnInputSelected.sendInput(30);
                mainActivity.fragmentSwitcher(new MeantimeFragment(), "MeantimeFragment");
                getDialog().dismiss();
            }
        });

        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: cancel btn clicken");
                getDialog().dismiss();
            }
        });


        return view;
    }

    private void initDialog(View view) {
        infoTv = view.findViewById(R.id.text_stay_info);
        stayEt = view.findViewById(R.id.edittext_stay);
        sevenDaysBtn = view.findViewById(R.id.button_sevendays);
        fourteenDaysBtn = view.findViewById(R.id.button_fourteendays);
        thirtyDaysBtn = view.findViewById(R.id.button_thirtydays);
        confirmTv = view.findViewById(R.id.text_confirm);
        cancelTv = view.findViewById(R.id.text_cancel);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "onAttach: called");

        try {
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        } catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainActivity = (MainActivity)getActivity();
        mPlayer = PlayerStats.getInstance();
        mAktien = Aktien.getInstance();
    }
}

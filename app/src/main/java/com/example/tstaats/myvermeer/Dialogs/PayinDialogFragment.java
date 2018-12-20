package com.example.tstaats.myvermeer.Dialogs;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tstaats.myvermeer.Common.Aktien;
import com.example.tstaats.myvermeer.Common.L;
import com.example.tstaats.myvermeer.Common.PlayerStats;
import com.example.tstaats.myvermeer.R;

public class PayinDialogFragment extends DialogFragment {

    private static final String TAG = "PayinDialogFragment";

    public interface OnInputSelected{
        void sendInput(double wealthNew, double creditNew);
    }
    public OnInputSelected mOnInputSelected;


    private PlayerStats mPlayer;
    private Aktien mAktien;

    // widgets
    private TextView mPayinTv;
    private TextView mOwnerTv;
    private TextView mConfirmTv;
    private TextView mCancelTv;
    private EditText mPayInEt;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_payin_payout_dialog, container, false);

        initDialog(view);

        mConfirmTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input");

                String input = mPayInEt.getText().toString();
                if(!input.equals("")){

                    double wealthNew;
                    double creditNew;

                    // best practice
                    if (mPlayer.getPlayerMoney() >= Double.parseDouble(mPayInEt.getText().toString())){
                        double wealth = mPlayer.getPlayerMoney();
                        double creditCur = mPlayer.getPlayerCredit();
                        double creditPayIn = Double.parseDouble(mPayInEt.getText().toString());
                        mPlayer.setPlayerMoney(wealth - creditPayIn);
                        mPlayer.setPlayerCredit(creditCur + creditPayIn);

                        wealthNew = mPlayer.getPlayerMoney();
                        creditNew = mPlayer.getPlayerCredit();
                        mOnInputSelected.sendInput(wealthNew, creditNew);
                    } else {
                        L.t(getActivity(), "Nicht genug Bargeld");
                    }
                }
                getDialog().dismiss();
            }
        });

        mCancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");

                getDialog().dismiss();
            }
        });


        return view;
    }

    private void initDialog(View view) {

        mPayinTv = view.findViewById(R.id.text_payin_payout);
        mOwnerTv = view.findViewById(R.id.text_dialog_konto_owner);
        mConfirmTv = view.findViewById(R.id.text_confirm);
        mCancelTv = view.findViewById(R.id.text_cancel);
        mPayInEt = view.findViewById(R.id.edittext_dialog_payin);

        mPayinTv.setText("Einzahlung");
        mOwnerTv.setText("Inhaber: " + mPlayer.getPlayerName());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        } catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException: " + e.getMessage());
        }
    }

    //    @Override
//    public Dialog onCreateDialog(Bundle savedInstanceState) {
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//
//        LayoutInflater inflater = getActivity().getLayoutInflater();
//
//        View view = inflater.inflate(R.layout.fragment_payin_payout_dialog, null);
//
//        builder.setView(view)
//                .setTitle("Konto Einzahlung")
//                .setPositiveButton(R.string.button_dialog_confirm, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        L.l(TAG, "Dialog: confirm Button clicked");
//                        if(payInEt.toString().isEmpty()){
//                            if (mPlayer.getPlayerMoney() >= Double.parseDouble(payInEt.getText().toString())){
//                                double wealth = mPlayer.getPlayerMoney();
//                                double creditCur = mPlayer.getPlayerCredit();
//                                double creditPayIn = Double.parseDouble(payInEt.getText().toString());
//                                mPlayer.setPlayerMoney(wealth - creditPayIn);
//                                mPlayer.setPlayerCredit(creditCur + creditPayIn);
//                                listener.applyInput(mPlayer.getPlayerMoney(), mPlayer.getPlayerCredit());
//                            } else {
//                                L.t(getActivity(), "Nicht genug Bargeld");
//                            }
//                        }
//                    }
//                })
//                .setNegativeButton(R.string.button_dialog_cancel, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        L.l(TAG, "Dialog: cancel Button clicked");
//                    }
//                });
//
//        payInEt = view.findViewById(R.id.edittext_dialog_payin);
//        ownerTv = view.findViewById(R.id.text_dialog_konto_owner);
//        ownerTv.setText(mPlayer.getPlayerName());
//
//        return builder.create();

//      }
//    public interface PayinDialogListener{
//        void applyInput(double wealth, double credit);
//    }
//
//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//
//        try {
//            listener = (PayinDialogListener)context;
//        } catch (ClassCastException e) {
//            throw new ClassCastException(context.toString() + " must implement PayinDialogListener");
//        }
//
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPlayer = PlayerStats.getInstance();
        mAktien = Aktien.getInstance();
    }
}

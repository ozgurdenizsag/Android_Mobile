package com.example.listeat;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDialogFragment;

public class ExampleDialog extends AppCompatDialogFragment {
    private TextView msgDelete;
    private ExampleDialogListener listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_dialog, null);
        final String nomList = getArguments().getString("nomList");
        builder.setView(view)
                .setTitle("Delete This List")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.deleteList(nomList);
                    }
                });
        msgDelete = view.findViewById(R.id.msgDelete);
        msgDelete.setText(msgDelete.getText().toString()+nomList + " ?");
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (ExampleDialogListener) context;
        }catch (Exception e){
            throw new ClassCastException(context.toString()+"must implement ExampleDialogListener");
        }

    }

    public interface ExampleDialogListener{
        void deleteList(String nomList);
    }
}

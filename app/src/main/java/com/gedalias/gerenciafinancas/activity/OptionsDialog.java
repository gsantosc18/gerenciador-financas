package com.gedalias.gerenciafinancas.activity;

import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.gedalias.gerenciafinancas.R;
import com.gedalias.gerenciafinancas.domain.Expense;
import com.gedalias.gerenciafinancas.service.ExpenseService;

@RequiresApi(api = Build.VERSION_CODES.O)
public class OptionsDialog extends DialogFragment {
    private ExpenseService expenseService;
    private Expense expense;
    private HomeActivity.RefreshOnAction action;
    public OptionsDialog(ExpenseService expenseService, Expense expense,
                         HomeActivity.RefreshOnAction refreshOnAction) {
        this.expenseService = expenseService;
        this.expense = expense;
        action = refreshOnAction;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.dialog_options, null);
        Button deleteExpenseBtn = view.findViewById(R.id.deleteExpenseBtn);
        TextView descriptionSelectedLb = view.findViewById(R.id.descriptionSelectedLb);

        descriptionSelectedLb.setText(expense.getDescription());

        AlertDialog alert = new AlertDialog.Builder(getContext())
                .setView(view)
                .create();

        deleteExpenseBtn.setOnClickListener(v -> {
            Log.i("OptionsDialog", "M=onCreateDialog, message=Click in delete," +
                    " expense="+expense);
            action.delete(expense);
            OptionsDialog.this.dismiss();
        });
        return alert;
    }
}

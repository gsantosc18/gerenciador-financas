package com.davi.sintomasenfermagem.adapter;

import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.davi.sintomasenfermagem.HomeActivity;
import com.davi.sintomasenfermagem.OptionsDialog;
import com.davi.sintomasenfermagem.R;
import com.davi.sintomasenfermagem.domain.Expense;
import com.davi.sintomasenfermagem.domain.enums.TypeEnum;
import com.davi.sintomasenfermagem.service.ExpenseService;

import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ExpenseListAdapter extends RecyclerView.Adapter<ExpenseListAdapter.ExpenseListViewHolder> {
    private List<Expense> expenses;
    private ExpenseService expenseService;
    private FragmentManager fragmentManager;
    private HomeActivity.RefreshOnAction action;

    public ExpenseListAdapter(List<Expense> expenses, FragmentManager fragmentManager,
                              ExpenseService expenseService,
                              HomeActivity.RefreshOnAction refreshOnAction) {
        this.expenses = expenses;
        this.fragmentManager = fragmentManager;
        this.expenseService = expenseService;
        action = refreshOnAction;
    }

    @NonNull
    @Override
    public ExpenseListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_expenses, parent, false);
        return new ExpenseListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseListViewHolder holder, int position) {
        final Expense expense = expenses.get(position);
        holder.expenseLb.setText(expense.getDescription());
        holder.valueLb.setText(String.valueOf(expense.getValue()));
        Log.i("ExpenseListAdapter", "M=onBindViewHolder, expense="+expense);
        if(expense.getType().getValue().equals(TypeEnum.FIXED.getValue())) {
            holder.typeLb.setBackgroundColor(holder.itemView.getResources().getColor(R.color.fixed_type));
        }
        holder.optionCardBtn.setOnClickListener(view -> {
            new OptionsDialog(expenseService, expense, action)
                    .show(fragmentManager, "Opções");
        });
    }


    @Override
    public int getItemCount() {
        return expenses.size();
    }

    public class ExpenseListViewHolder extends RecyclerView.ViewHolder {
        public TextView expenseLb;
        public TextView valueLb;
        public TextView typeLb;
        public ImageButton optionCardBtn;

        public ExpenseListViewHolder(@NonNull View itemView) {
            super(itemView);
            expenseLb = itemView.findViewById(R.id.expenseLb);
            valueLb = itemView.findViewById(R.id.valueLb);
            typeLb = itemView.findViewById(R.id.typeLb);
            optionCardBtn = itemView.findViewById(R.id.optionCardBtn);
        }
    }
}

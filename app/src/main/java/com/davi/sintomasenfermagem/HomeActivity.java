package com.davi.sintomasenfermagem;

import static java.util.Objects.requireNonNull;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.davi.sintomasenfermagem.adapter.ExpenseListAdapter;
import com.davi.sintomasenfermagem.domain.Expense;
import com.davi.sintomasenfermagem.service.ExpenseService;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;


@RequiresApi(api = Build.VERSION_CODES.O)
public class HomeActivity extends AppCompatActivity {
    private FloatingActionButton fixedExpensesBtnFloat;
    private FloatingActionButton optionsBtnFloat;
    private TextView fixedExpensesLb;
    private TextView totalValueLb;
    private boolean isVisibleButtonsFloating = false;
    private RecyclerView expenseList;
    private ExpenseService expenseService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        expenseService = new ExpenseService(this);

        optionsBtnFloat = (FloatingActionButton) findViewById(R.id.optionsBtnFloat);
        fixedExpensesBtnFloat = (FloatingActionButton) findViewById(R.id.fixedExpensesBtnFloat);
        fixedExpensesLb = (TextView) findViewById(R.id.fixedExpensesLb);

        totalValueLb = (TextView) findViewById(R.id.totalValueLb);

        expenseList = (RecyclerView) findViewById(R.id.expenseList);

        List<Expense> allExpenses = expenseService.findAll();
        ExpenseListAdapter expenseListAdapter = new ExpenseListAdapter(allExpenses,
            getSupportFragmentManager(), expenseService,
            expense -> {
                int position = allExpenses.indexOf(expense);
                expenseService.delete(expense);
                allExpenses.remove(position);
                totalValueLb.setText(String.valueOf(expenseService.sumExpenses()));
                requireNonNull(expenseList.getAdapter())
                        .notifyItemRemoved(position);
            });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        expenseList.setLayoutManager(linearLayoutManager);
        expenseList.setAdapter(expenseListAdapter);
        totalValueLb.setText(String.valueOf(expenseService.sumExpenses()));

        optionsBtnFloat.setOnClickListener(view -> {
            if(isVisibleButtonsFloating) {
                hideButtonsFloating();
            } else {
                showButtonsFloating();
            }
            isVisibleButtonsFloating = !isVisibleButtonsFloating;
        });

        fixedExpensesBtnFloat.setOnClickListener(view -> {
            Intent intent = new Intent(HomeActivity.this, FixedExpenseView.class);
            startActivity(intent);
        });
    }

    private void hideButtonsFloating() {
        fixedExpensesBtnFloat.setVisibility(View.GONE);
        fixedExpensesLb.setVisibility(View.GONE);
    }

    private void showButtonsFloating() {
        fixedExpensesBtnFloat.setVisibility(View.VISIBLE);
        fixedExpensesLb.setVisibility(View.VISIBLE);
    }

    public interface RefreshOnAction {
        void delete(Expense expense);
    }
}
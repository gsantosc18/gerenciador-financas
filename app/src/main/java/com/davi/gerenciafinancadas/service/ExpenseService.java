package com.davi.gerenciafinancadas.service;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.davi.gerenciafinancadas.dao.ExpenseDAO;
import com.davi.gerenciafinancadas.domain.Expense;
import com.davi.gerenciafinancadas.domain.enums.TypeEnum;

import java.time.LocalDateTime;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.O)
public class ExpenseService {
    private final ExpenseDAO expenseDAO;
    public ExpenseService(Context context) {
        this.expenseDAO = new ExpenseDAO(context);
    }

    public void save(Expense expense) {
        expenseDAO.save(expense);
    }

    public List<Expense> findAll() {
        return expenseDAO.findAll();
    }

    public void createNew(String description, double value, TypeEnum type) {
        Log.i("ExpenseService", "M=createNew, message=Init create a new expense data " +
                "record, description="+description+" type="+type.getValue());
        final Expense expense = new Expense(null, value, description, type, LocalDateTime.now(), null);
        save(expense);
    }

    public void delete(Expense expense) {
        expenseDAO.delete(expense);
    }

    public double sumExpenses() {
        return findAll()
                .stream()
                .mapToDouble(Expense::getValue)
                .sum();
    }
}

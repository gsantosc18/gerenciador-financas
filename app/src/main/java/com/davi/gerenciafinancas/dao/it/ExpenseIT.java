package com.davi.gerenciafinancas.dao.it;

import com.davi.gerenciafinancas.domain.Expense;

import java.util.List;

public interface ExpenseIT {
    void save(Expense expense);
    void delete(Expense expense);
    List<Expense> findAll();
}

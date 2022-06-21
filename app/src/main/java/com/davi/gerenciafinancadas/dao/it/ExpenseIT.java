package com.davi.gerenciafinancadas.dao.it;

import com.davi.gerenciafinancadas.domain.Expense;

import java.util.List;

public interface ExpenseIT {
    void save(Expense expense);
    void delete(Expense expense);
    List<Expense> findAll();
}

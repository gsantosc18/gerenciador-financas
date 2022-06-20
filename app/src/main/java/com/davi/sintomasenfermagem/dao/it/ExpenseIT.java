package com.davi.sintomasenfermagem.dao.it;

import com.davi.sintomasenfermagem.domain.Expense;

import java.util.List;

public interface ExpenseIT {
    void save(Expense expense);
    void delete(Expense expense);
    List<Expense> findAll();
}

package com.gedalias.gerenciafinancas.dao;

import static com.gedalias.gerenciafinancas.database.ExpenseTable.TABLE;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.gedalias.gerenciafinancas.database.ExpenseTable;
import com.gedalias.gerenciafinancas.dao.it.ExpenseIT;
import com.gedalias.gerenciafinancas.domain.Expense;
import com.gedalias.gerenciafinancas.domain.enums.TypeEnum;

import java.util.ArrayList;
import java.util.List;

@RequiresApi(api = Build.VERSION_CODES.N)
public class ExpenseDAO implements ExpenseIT {
    private final ExpenseTable expenseTable;
    private final String[] fields = new String[]{"id","description","value", "type"};
    public ExpenseDAO(Context context) {
        this.expenseTable = new ExpenseTable(context);
    }
    @Override
    public void save(Expense expense) {

        ContentValues values = new ContentValues();

        values.put("value", expense.getValue());
        values.put("description", expense.getDescription());
        values.put("createdAt", expense.getCreatedAt().toString());
        values.put("type", expense.getType().getValue());

        final SQLiteDatabase db = expenseTable.getWritableDatabase();

        db.insert(TABLE, null, values);
    }

    @Override
    public void delete(Expense expense) {
        SQLiteDatabase db = expenseTable.getReadableDatabase();
        db.delete(TABLE, String.format("id = %s", expense.getId()), null);
        db.close();
    }

    @Override
    public List<Expense> findAll() {

        List<Expense> expenses = new ArrayList<>();

        final SQLiteDatabase db = expenseTable.getReadableDatabase();

        Cursor cursor = db.query(TABLE, fields, null, null, null, null, "type desc, createdAt desc", null);

        if(cursor.moveToFirst()) {
            expenses.add(cursorToExpense(cursor));
            while (cursor.moveToNext()) {
                expenses.add(cursorToExpense(cursor));
            }
        }
        db.close();

        return expenses;
    }

    @NonNull
    private Expense cursorToExpense(Cursor cursor) {
        final long id = Long.parseLong(getColumn(fields[0], cursor));
        final String description = getColumn(fields[1], cursor);
        final double value = Double.parseDouble(getColumn(fields[2], cursor));
        final TypeEnum type = TypeEnum.of(getColumn(fields[3], cursor));
        return new Expense(id, value, description, type, null, null);
    }

    @SuppressLint("Range")
    private String getColumn(String name, Cursor cursor) {
        return cursor.getString(cursor.getColumnIndex(name));
    }
}

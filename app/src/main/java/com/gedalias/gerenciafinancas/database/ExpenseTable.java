package com.gedalias.gerenciafinancas.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ExpenseTable extends SQLiteOpenHelper {
    public static final String TABLE = "expense";

    public ExpenseTable(Context context) {
        super(context, TABLE, null, StatementTable.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(StatementTable.EXPENSE_UP);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(StatementTable.EXPENSE_DOWN);
        onCreate(sqLiteDatabase);
    }
}

package com.davi.sintomasenfermagem.database;

public class StatementTable {
    public static final int VERSION = 1;
    public static final String EXPENSE_UP = "CREATE TABLE expense(\n" +
            "    id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "    value REAL default 0,\n" +
            "    description TEXT,\n" +
            "    type TEXT not null,\n" +
            "    createdAt datetime not null,\n" +
            "    updateAt datetime\n" +
            ");";
    public static final String EXPENSE_DOWN = "DROP TABLE IF EXISTS expense;";
}

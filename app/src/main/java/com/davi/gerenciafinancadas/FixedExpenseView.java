package com.davi.gerenciafinancadas;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.davi.gerenciafinancadas.domain.enums.TypeEnum;
import com.davi.gerenciafinancadas.service.ExpenseService;

import java.util.Arrays;

@RequiresApi(api = Build.VERSION_CODES.O)
public class FixedExpenseView extends AppCompatActivity {
    private EditText descriptionTF;
    private EditText valueTF;
    private Button saveBtn;
    private ExpenseService expenseService;
    private Spinner typeSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixed_expense_view);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Gastos Fixos");

        expenseService = new ExpenseService(this);

        descriptionTF = (EditText) findViewById(R.id.descriptionTF);
        valueTF = (EditText) findViewById(R.id.valueTF);
        typeSelect = (Spinner) findViewById(R.id.typeSelect);
        saveBtn = (Button) findViewById(R.id.saveBtn);

        Object[] values = Arrays.stream(TypeEnum.values()).map(TypeEnum::getValue).toArray();
        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, values);

        typeSelect.setAdapter(adapter);

        saveBtn.setOnClickListener(view -> {
            final String description = descriptionTF.getText().toString();
            final double value = Double.parseDouble(valueTF.getText().toString());
            final TypeEnum type = TypeEnum.of(typeSelect.getSelectedItem().toString());
            Log.i("FixedExpenseView", "M=saveBtn.setOnClickListener, message=Init " +
                    "saved expense, description="+description+" type="+type);
            expenseService.createNew(description, value, type);
            backToHome();
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                backToHome();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void backToHome() {
        Intent intent = new Intent(FixedExpenseView.this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
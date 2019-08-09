package com.github.machadowma.conceitosbasicos;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    EditText editTextNome,editTextNasc,editTextPeso,editTextAltura;
    RadioGroup radioGroupSexo;
    Spinner spinnerInstr;
    SeekBar seekBarStress;
    RatingBar ratingBarNota;
    Button button;
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNome = (EditText) findViewById(R.id.editTextNome);
        editTextNasc = (EditText) findViewById(R.id.editTextNasc);
        editTextPeso = (EditText) findViewById(R.id.editTextPeso);
        editTextAltura = (EditText) findViewById(R.id.editTextAltura);
        radioGroupSexo = (RadioGroup) findViewById(R.id.radioGroupSexo);
        spinnerInstr = (Spinner) findViewById(R.id.spinnerInstr);
        seekBarStress = (SeekBar) findViewById(R.id.seekBarStress);
        ratingBarNota = (RatingBar) findViewById(R.id.ratingBarNota);
        button = (Button) findViewById(R.id.button);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.grau_instrucao, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerInstr.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exibirMsg();
            }
        });
    }

    public void exibirMsg(){
        String msg = "";
        Float imc, peso, altura;
        msg=msg+"\nNome: "+ editTextNome.getText().toString();
        
        msg=msg+"\nNascimento: "+ editTextNasc.getText().toString();
        msg=msg+"\nPeso: "+ editTextPeso.getText().toString();
        msg=msg+"\nAltura: "+ editTextAltura.getText().toString();
        if(!TextUtils.isEmpty(editTextPeso.getText().toString())&&!TextUtils.isEmpty(editTextAltura.getText().toString())) {
            peso = Float.parseFloat(editTextPeso.getText().toString());
            altura = Float.parseFloat(editTextAltura.getText().toString());
            imc = peso / (altura * altura);
            msg = msg + "\nIMC: " + imc.toString();
        }
        if(radioGroupSexo.getCheckedRadioButtonId()==R.id.radioButtonMasc){
            msg=msg+"\nSexo: Masculino";
        } else if(radioGroupSexo.getCheckedRadioButtonId()==R.id.radioButtonFem){
            msg=msg+"\nSexo: Feminino";
        }
        msg=msg+"\nInstrução: " + spinnerInstr.getSelectedItem().toString();
        msg=msg+"\nEstresse: " + seekBarStress.getProgress();
        msg=msg+"\nNota: " + ratingBarNota.getRating();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Titulo");
        builder.setMessage(msg);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface arg0, int arg1) {
            }
        });
        alerta = builder.create();
        alerta.show();
    }
}

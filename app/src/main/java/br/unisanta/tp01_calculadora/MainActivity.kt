package br.unisanta.tp01_calculadora

import android.os.Bundle
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(R.layout.activity_main) {


    private lateinit var edtValor1: EditText
    private lateinit var edtValor2: EditText
    private lateinit var txtResultado: TextView

    private lateinit var btnSomar: Button
    private lateinit var btnSubtrair: Button
    private lateinit var btnMultiplicar: Button
    private lateinit var btnDividir: Button

    private val calculadora = Calculadora()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        edtValor1 = findViewById(R.id.edt_valor1)
        edtValor2 = findViewById(R.id.edt_valor2)
        txtResultado = findViewById(R.id.txt_resultado)

        btnSomar = findViewById(R.id.btn_somar)
        btnSubtrair = findViewById(R.id.btn_subtrair)
        btnMultiplicar = findViewById(R.id.btn_multiplicar)
        btnDividir = findViewById(R.id.btn_dividir)


        btnSomar.setOnClickListener { calcular("somar") }
        btnSubtrair.setOnClickListener { calcular("subtrair") }
        btnMultiplicar.setOnClickListener { calcular("multiplicar") }
        btnDividir.setOnClickListener { calcular("dividir") }
    }

    private fun calcular(operacao: String) {
        val valor1Str = edtValor1.text.toString()
        val valor2Str = edtValor2.text.toString()

        if (valor1Str.isEmpty() || valor2Str.isEmpty()) {
            Toast.makeText(this, "Digite os dois valores", Toast.LENGTH_SHORT).show()
            return
        }

        val valor1 = valor1Str.toDouble()
        val valor2 = valor2Str.toDouble()

        try {
            val resultado = when (operacao) {
                "somar" -> calculadora.somar(valor1, valor2)
                "subtrair" -> calculadora.subtrair(valor1, valor2)
                "multiplicar" -> calculadora.multiplicar(valor1, valor2)
                "dividir" -> calculadora.dividir(valor1, valor2)
                else -> 0.0
            }
            txtResultado.text = "Resultado: $resultado"
        } catch (e: ArithmeticException) {
            Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
        }
    }
}

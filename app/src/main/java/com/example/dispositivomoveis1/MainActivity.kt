package com.example.dispositivomoveis1

// Não sabia se era apenas para inserir o código + quantidade e só exibir o valor, para garantir
// Fiz com que ele armazenasse em um carrinho e depois mostrasse o valor final

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // Variável para armazenar o total acumulado no carrinho
    var totalCarrinho: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val codigoProdutoEditText = findViewById<EditText>(R.id.codigoProduto)
        val quantidadeProdutoEditText = findViewById<EditText>(R.id.quantidadeProduto)
        val adicionarCarrinhoButton = findViewById<Button>(R.id.adicionarCarrinhoButton)
        val finalizarCompraButton = findViewById<Button>(R.id.finalizarCompraButton)
        val resultadoTextView = findViewById<TextView>(R.id.resultadoTextView)
        val totalCarrinhoTextView = findViewById<TextView>(R.id.totalCarrinhoTextView)

        // Tabela de produtos e preços
        val tabelaProdutos = mapOf(
            "ABCD" to 5.30,
            "XYPK" to 6.00,
            "KLMP" to 3.20,
            "QRST" to 2.50
        )

        // Lógica para adicionar itens ao carrinho
        adicionarCarrinhoButton.setOnClickListener {
            val codigoProduto = codigoProdutoEditText.text.toString().uppercase()
            val quantidadeProduto = quantidadeProdutoEditText.text.toString().toIntOrNull()

            if (quantidadeProduto == null || quantidadeProduto <= 0) {
                resultadoTextView.text = "Por favor, insira uma quantidade válida."
                return@setOnClickListener
            }

            val precoUnitario = tabelaProdutos[codigoProduto]

            if (precoUnitario != null) {
                val precoTotal = precoUnitario * quantidadeProduto
                totalCarrinho += precoTotal // Adiciona o valor ao carrinho
                resultadoTextView.text = "Produto adicionado ao carrinho. Preço: R$ %.2f".format(precoTotal)
                totalCarrinhoTextView.text = "Total no Carrinho: R$ %.2f".format(totalCarrinho)
            } else {
                resultadoTextView.text = "Código de produto inválido."
            }
        }

        // Lógica para finalizar a compra
        finalizarCompraButton.setOnClickListener {
            if (totalCarrinho > 0) {
                resultadoTextView.text = "Compra finalizada! Total a pagar: R$ %.2f".format(totalCarrinho)
                totalCarrinho = 0.0 // Reseta o carrinho após finalizar a compra
                totalCarrinhoTextView.text = "Total no Carrinho: R$ 0,00"
            } else {
                resultadoTextView.text = "Nenhum item no carrinho."
            }
        }
    }
}
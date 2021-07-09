package br.com.zup.academy

import java.io.FileInputStream
import java.io.FileOutputStream

fun main() {

    val request = FuncionarioRequest.newBuilder()
        .setNome("Emerson Bezerra")
        .setCpf("000.000.000-00")
        .setSalario(2000.0)
        .setAtivo(true)
        .setCargo(Cargo.DEV)
        .addEnderecos(
            FuncionarioRequest.Endereco.newBuilder()
                .setLogradouro("Rua das Tabajaras")
                .setCep("00000-000")
                .setComplemento("Casa 20")
                .build()
        )
        .build()

    // Escrevemos o objeto
    println(request) // Não é impresso o cargo DEV porque o primeiro item do ENUM é considerado Default
    request.writeTo(FileOutputStream("funcionario-request.bin"))

    // Lemos o objeto
    val request2 = FuncionarioRequest.newBuilder()
        .mergeFrom(FileInputStream("funcionario-request.bin"))

    request2.setCargo(Cargo.GERENTE).build()
    println(request2)
}
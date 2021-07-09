package br.com.zup.academy

import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

fun main() {

    val channel = ManagedChannelBuilder
        .forAddress("localhost", 50051)
        .usePlaintext()
        .build()

    val client = FuncionarioServiceGrpc.newBlockingStub(channel)

    val request = FuncionarioRequest.newBuilder()
        .setNome("Emerson Bezerra")
        .setCpf("000.000.000-00")
        .setIdade(32)
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

    val response = client.cadastrar(request)

    println(response)
}
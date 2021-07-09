package br.com.zup.academy

import com.google.protobuf.Timestamp
import io.grpc.ServerBuilder
import io.grpc.stub.StreamObserver
import java.time.LocalDateTime
import java.time.ZoneOffset

fun main() {

    val server = ServerBuilder
        .forPort(50051)
        .addService(FuncionarioEndpoint())
        .build()
    server.start()
    server.awaitTermination()

}

class FuncionarioEndpoint : FuncionarioServiceGrpc.FuncionarioServiceImplBase() {

    override fun cadastrar(request: FuncionarioRequest?, responseObserver: StreamObserver<FuncionarioResponse>?) {

        println(request!!)

        var nome: String? = request.nome
        if (!request.hasField(FuncionarioRequest.getDescriptor().findFieldByName("nome"))) {
            nome = "[???]"
        }

        val instant = LocalDateTime.now().toInstant(ZoneOffset.UTC)
        val criadoEm = Timestamp
            .newBuilder()
            .setSeconds(instant.epochSecond)
            .setNanos(instant.nano)
            .build()

        val response = FuncionarioResponse.newBuilder()
            .setNome(nome)
            .setCriadoEm(criadoEm)
            .build()

        responseObserver?.onNext(response)
        responseObserver?.onCompleted()
    }
}
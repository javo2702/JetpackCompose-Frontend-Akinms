package com.example.akinms.data.source.remote

import com.example.akinms.data.source.remote.dto.bodega.BodegaDto
import com.example.akinms.data.source.remote.dto.bodega.BodegasDto
import com.example.akinms.data.source.remote.dto.categoria.CategoriasDto
import com.example.akinms.data.source.remote.dto.pedido.*
import com.example.akinms.data.source.remote.dto.producto.ProductDto
import com.example.akinms.data.source.remote.dto.cliente.Cliente
import com.example.akinms.data.source.remote.dto.cliente.ClienteDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRest {
    @GET("productos/listarproductos/bodega/{id}")
    //suspend fun getAllProducts(): List<Products>
    suspend fun getAllProducts(
        @Path("id") id: Long
    ): ProductDto

    @GET("bodegas/listarbodegas/")
    suspend fun getBodegas(): BodegasDto

    @GET("bodegas/listarbodegas/premium")
    suspend fun getBodegasPremium(): BodegasDto

    @GET("bodegas/consultar/{id}")
    suspend fun getBodega(
        @Path("id") id:Long
    ): BodegaDto

    @GET("categorias/listar/bodega/{id}")
    suspend fun getAllCategoriesBodega(
        @Path("id") id: Long
    ): CategoriasDto

    @GET("productos/consultar/bodega/{id1}/categoria/{id2}")
    suspend fun getProductsCategoria(
        @Path("id1") id1: Long,
        @Path("id2") id2: Long
    ): ProductDto

    @GET("productos/consultar/bodega/{id}/buscar?")
    suspend fun getProductsByName(
        @Path("id") id: Long,
        @Query("nombre") nombre: String
    ): ProductDto

    @GET("pedidos/consultar/cliente/{id}")
    suspend fun getPedidoByClient(
        @Path("id") id: Long
    ): PedidoDto2

    @POST("pedidos/registrar")
    suspend fun setPedido(
        @Body pedido: Pedido
    ): PedidoDto3

    @GET("pedidos/consultar/cliente/{id_cliente}/pedido/{id_pedido}")
    suspend fun getDetallePedidoCliente(
        @Path("id_cliente") id_cliente: Long,
        @Path("id_pedido") id_pedido: Long
    ): PedidoDto4

    @POST("clientes/buscarcliente?")
    suspend fun buscarCliente(
        @Query("correo") correo: String,
        @Query("pass") pass: String,
        //@Body cliente: Cliente
    ): ClienteDto

    @GET("clientes/consultar/{id}")
    suspend fun getCliente(
        @Path("id") id: Long
    ): ClienteDto
}
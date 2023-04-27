package com.example.akinms.di

import com.example.akinms.data.repositories.*
import com.example.akinms.domain.repositories.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RespositoriesModule {

    @Binds
    abstract fun bindProductsRepository(impl: ProductRepositoryImpl) : ProductRepository
    @Binds
    abstract fun bindBodegaRepository(impl: BodegaRepositoryImpl) : BodegaRepository
    @Binds
    abstract fun bindCategoriaRepository(impl: CategoriaRepositoryImpl) : CategoriaRepository
    @Binds
    abstract fun bindPedidoRepository(impl: PedidoRepositoryImpl) : PedidoRepository
    @Binds
    abstract fun bindClienteRepository(impl: ClienteRepositoryImpl) : ClienteRepository
}
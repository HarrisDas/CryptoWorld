package com.harris.cryptoworld.data.repository

import com.harris.cryptoworld.data.datasource.ProductDataSource
import com.harris.cryptoworld.data.mapper.ProductNetworkMapper
import com.harris.cryptoworld.domain.repository.IProductRepository
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val dataSource: ProductDataSource,
    private val mapper: ProductNetworkMapper
) :
    IProductRepository {

}
package com.harris.mygroceryshop.data.repository

import com.harris.mygroceryshop.data.datasource.ProductDataSource
import com.harris.mygroceryshop.data.mapper.ProductNetworkMapper
import com.harris.mygroceryshop.domain.repository.IProductRepository
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val dataSource: ProductDataSource,
    private val mapper: ProductNetworkMapper
) :
    IProductRepository {

}
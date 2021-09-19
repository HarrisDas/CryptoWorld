package com.harris.cryptoworld.data.mapper

import com.harris.cryptoworld.data.remote.model.RemoteProduct
import com.harris.cryptoworld.domain.model.Product
import com.harris.cryptoworld.utils.PojoMapper
import javax.inject.Inject


class ProductNetworkMapper
@Inject
constructor() : PojoMapper<RemoteProduct, Product> {

    override fun mapFromEntity(entity: RemoteProduct): Product {
        return Product(
            id = entity.id
        )
    }

    override fun mapFromEntityList(entities: List<RemoteProduct>): List<Product> {
        return entities.map { mapFromEntity(it) }
    }
}







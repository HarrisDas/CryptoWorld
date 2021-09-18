package com.harris.mygroceryshop.domain.mapper

import com.harris.mygroceryshop.data.remote.model.RemoteProduct
import com.harris.mygroceryshop.domain.model.Product
import com.harris.mygroceryshop.utils.PojoMapper
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







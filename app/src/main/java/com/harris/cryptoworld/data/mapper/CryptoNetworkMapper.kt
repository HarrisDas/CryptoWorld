package com.harris.cryptoworld.data.mapper

import com.harris.cryptoworld.data.remote.model.RemoteCrypto
import com.harris.cryptoworld.domain.model.Crypto
import com.harris.cryptoworld.utils.PojoMapper
import javax.inject.Inject


class CryptoNetworkMapper
@Inject
constructor() : PojoMapper<RemoteCrypto, Crypto> {

    override fun mapFromEntity(entity: RemoteCrypto): Crypto {
        return Crypto(
            id = entity.id
        )
    }

    override fun mapFromEntityList(entities: List<RemoteCrypto>?): List<Crypto> {
        return entities?.map { mapFromEntity(it) } ?: emptyList()
    }
}







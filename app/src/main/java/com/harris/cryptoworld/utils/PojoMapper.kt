package com.harris.cryptoworld.utils

interface PojoMapper<Entity, DomainModel> {

    fun mapFromEntity(entity: Entity): DomainModel
    fun mapFromEntityList(entities: List<Entity>): List<DomainModel>
}
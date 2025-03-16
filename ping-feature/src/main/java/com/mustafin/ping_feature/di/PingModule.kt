package com.mustafin.ping_feature.di

import com.mustafin.ping_feature.data.repositories.pingRepository.PingRepository
import com.mustafin.ping_feature.data.repositories.pingRepository.PingRepositoryImpl
import com.mustafin.ping_feature.data.source.UnifiedApiService
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val pingModule = module {
    // General
    single { HttpClient(CIO) }

    // Api services
    singleOf(::UnifiedApiService)

    // Repositories
    singleOf(::PingRepositoryImpl) bind PingRepository::class
}
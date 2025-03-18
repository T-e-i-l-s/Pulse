package com.mustafin.background_checks_feature.di

import com.mustafin.background_checks_feature.data.repositories.backgroundChecksRepository.BackgroundChecksRepository
import com.mustafin.background_checks_feature.data.repositories.backgroundChecksRepository.BackgroundChecksRepositoryImpl
import com.mustafin.background_checks_feature.data.workManager.workers.PingWorker
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val backgroundChecksModule = module {
    singleOf(::BackgroundChecksRepositoryImpl) bind BackgroundChecksRepository::class
    singleOf(::PingWorker)
}
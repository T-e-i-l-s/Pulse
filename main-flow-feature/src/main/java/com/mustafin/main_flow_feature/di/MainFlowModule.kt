package com.mustafin.main_flow_feature.di

import com.mustafin.main_flow_feature.data.repositories.requestsRepository.RequestsRepository
import com.mustafin.main_flow_feature.data.repositories.requestsRepository.RequestsRepositoryImpl
import com.mustafin.main_flow_feature.presentation.screens.addRequestScreen.AddRequestScreenViewModel
import com.mustafin.main_flow_feature.presentation.screens.homeScreen.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val mainFlowModule = module {
    singleOf(::RequestsRepositoryImpl) bind RequestsRepository::class
    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::AddRequestScreenViewModel)
}
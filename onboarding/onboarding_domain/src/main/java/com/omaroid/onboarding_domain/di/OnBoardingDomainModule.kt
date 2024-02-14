package com.omaroid.onboarding_domain.di

import com.omaroid.onboarding_domain.use_case.ValidateNutrient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object OnBoardingDomainModule {
    @Provides
    @ViewModelScoped
    fun provideValidateNutrientsUseCase(): ValidateNutrient {
        return ValidateNutrient()
    }
}
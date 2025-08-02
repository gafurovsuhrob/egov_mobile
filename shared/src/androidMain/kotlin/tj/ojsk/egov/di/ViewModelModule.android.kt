package tj.ojsk.egov.di

import org.koin.core.module.dsl.viewModelOf
import tj.ojsk.egov.feature.home.HomeViewModel
import tj.ojsk.egov.feature.news.NewsViewModel
import tj.ojsk.egov.feature.auth.view_model.AuthViewModel
import tj.ojsk.egov.feature.onboarding.OnBoardingViewModel
import tj.ojsk.egov.feature.others.OthersViewModel
import tj.ojsk.egov.feature.profile.ProfileViewModel
import tj.ojsk.egov.feature.services.ServicesViewModel
import tj.ojsk.egov.feature.settings.SettingsViewModel
import tj.ojsk.egov.main.MainViewModel
import org.koin.dsl.module
import tj.ojsk.egov.feature.splash.SplashViewModel

actual val viewModelModule = module {
    viewModelOf(::SplashViewModel)
    viewModelOf(::AuthViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::NewsViewModel)
    viewModelOf(::OnBoardingViewModel)
    viewModelOf(::OthersViewModel)
    viewModelOf(::ProfileViewModel)
    viewModelOf(::ServicesViewModel)
    viewModelOf(::SettingsViewModel)
    viewModelOf(::MainViewModel)
}

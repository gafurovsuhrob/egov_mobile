package tj.ojsk.egov.di

import org.koin.core.module.dsl.viewModelOf
import tj.ojsk.egov.feature.home.HomeViewModel
import tj.ojsk.egov.feature.news.NewsViewModel
import tj.ojsk.egov.feature.auth.AuthViewModel
import tj.ojsk.egov.feature.onboarding.OnBoardingViewModel
import tj.ojsk.egov.feature.others.OthersViewModel
import tj.ojsk.egov.feature.profile.ProfileViewModel
import tj.ojsk.egov.feature.services.ServicesViewModel
import tj.ojsk.egov.feature.settings.SettingsViewModel
import tj.ojsk.egov.main.MainViewModel
import org.koin.dsl.module

actual val viewModelModule = module {
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

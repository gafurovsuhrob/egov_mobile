package tj.ojsk.egov.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import tj.ojsk.egov.feature.auth.AuthViewModel
import tj.ojsk.egov.feature.home.HomeViewModel
import tj.ojsk.egov.feature.news.NewsViewModel
import tj.ojsk.egov.feature.onboarding.OnBoardingViewModel
import tj.ojsk.egov.feature.others.OthersViewModel
import tj.ojsk.egov.feature.profile.ProfileViewModel
import tj.ojsk.egov.feature.services.ServicesViewModel
import tj.ojsk.egov.feature.settings.SettingsViewModel
import tj.ojsk.egov.main.MainViewModel

actual val viewModelModule = module {
    singleOf(::AuthViewModel)
    singleOf(::HomeViewModel)
    singleOf(::NewsViewModel)
    singleOf(::OnBoardingViewModel)
    singleOf(::OthersViewModel)
    singleOf(::ProfileViewModel)
    singleOf(::ServicesViewModel)
    singleOf(::SettingsViewModel)
    singleOf(::MainViewModel)
}

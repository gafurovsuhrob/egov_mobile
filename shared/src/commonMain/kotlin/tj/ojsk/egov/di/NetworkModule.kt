package tj.ojsk.egov.di

import org.koin.core.module.Module
import org.koin.dsl.module
import tj.ojsk.egov.core.data.remote.HttpClientFactory
import tj.ojsk.egov.core.data.remote.NetworkClient

val networkModule = module {
    single {
        HttpClientFactory().create()
    }
    single {
        NetworkClient(
            httpClient = get(),
            tokenManager = get()
        )
    }
}
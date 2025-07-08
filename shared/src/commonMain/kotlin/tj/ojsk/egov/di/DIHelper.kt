package tj.ojsk.egov.di

import org.koin.core.component.KoinComponent

class DIHelper : KoinComponent {
    inline fun <reified T : Any> get(): T = getKoin().get()
}
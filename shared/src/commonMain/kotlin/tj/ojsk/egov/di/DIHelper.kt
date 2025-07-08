package tj.ojsk.egov.di

import org.koin.core.component.KoinComponent
import kotlin.reflect.KClass

class DIHelper : KoinComponent {
    fun <T : Any> get(clazz: KClass<T>): T {
        return getKoin().get(clazz, null, null)
    }
}
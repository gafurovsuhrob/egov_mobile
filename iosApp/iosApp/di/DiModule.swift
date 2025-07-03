import Foundation
import shared

import Foundation
import shared

final class DiModule {
    static let koin = {
        KoinInit().doInit { _ in
            // appDeclaration если нужно
        }
    }()
}

final class Inject {
    private static let helper = di.DIHelper()

    static func get<T: AnyObject>(_ type: T.Type = T.self) -> T {
        let resolved: Any? = helper.get()

        if let result = resolved as? T {
            return result
        } else {
            fatalError("""
                [Inject] Ошибка разрешения зависимости: \(T.self)
                Проверьте, что этот тип зарегистрирован в Koin
                или корректно экспортирован в iOS
            """)
        }
    }
}
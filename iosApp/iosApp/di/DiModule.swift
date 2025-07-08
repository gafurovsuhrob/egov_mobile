import Foundation
import shared

class DiModule {
    static var koin = {
        KoinInit().doInit(
                appDeclaration: { _ in
                    // Do nothing
                }
        )
    }()
}

//
//final class Inject {
//    private static let helper = DIHelper()
//
//    static func get<T: AnyObject>(_ type: T.Type = T.self) -> T {
//        let resolved: Any? = helper.get(clazz: T.self)
//
//        if let result = resolved as? T {
//            return result
//        } else {
//            fatalError("""
//                [Inject] Ошибка разрешения зависимости: \(T.self)
//                Проверьте, что этот тип зарегистрирован в Koin
//                или корректно экспортирован в iOS
//            """)
//        }
//    }
//}

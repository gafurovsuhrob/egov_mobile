//
//  KoinApplication+Extension.swift
//

import Foundation
import shared

typealias KoinApplication = Koin_coreKoinApplication
typealias Koin = Koin_coreKoin

extension KoinApplication {

    static let shared: KoinApplication = companion.start()

    @discardableResult
    static func start() -> KoinApplication {
        shared
    }
}

extension KoinApplication {
    private static let keyPaths: [PartialKeyPath<Koin>] = [
        \.authViewModel,
        \.homeViewModel,
        \.newsViewModel,
        \.onBoardingViewModel,
        \.othersViewModel,
        \.profileViewModel,
        \.servicesViewModel,
        \.settingsViewModel,
        \.mainViewModel
    ]

    static func inject<T>() -> T {
        shared.inject()
    }

    func inject<T>() -> T {
        for partialKeyPath in Self.keyPaths {
            guard let keyPath = partialKeyPath as? KeyPath<Koin, T> else {
                continue
            }
            return koin[keyPath: keyPath]
        }
        fatalError("""
                        [Inject] Ошибка разрешения зависимости: \(T.self)
                        Проверьте, что этот тип зарегистрирован в Koin
                        или корректно экспортирован в iOS
                    """)
    }
}

@propertyWrapper
struct LazyKoin<T> {
    lazy var wrappedValue: T = {
        KoinApplication.shared.inject()
    }()

    init() {}
}
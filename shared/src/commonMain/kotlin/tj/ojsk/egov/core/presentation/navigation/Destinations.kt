package tj.ojsk.egov.core.presentation.navigation

import kotlinx.serialization.Serializable

sealed class Destinations {

    @Serializable
    object Onboarding

    @Serializable
    object Home

    @Serializable
    object Services

    @Serializable
    object Profile

    @Serializable
    object Others

    @Serializable
    object Settings

    @Serializable
    object About

    @Serializable
    object News

}

package tj.ojsk.egov.feature.auth.view_model

object LoginValidator {
    fun validateUsernamePassword(username: String, password: String): String? {
        if (username.isBlank() || password.isBlank()) {
            return "Заполните все поля"
        }
        if (username.length < 3) return "Имя пользователя слишком короткое"
        return null
    }
}
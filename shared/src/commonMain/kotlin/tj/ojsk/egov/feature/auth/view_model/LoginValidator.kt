package tj.ojsk.egov.feature.auth.view_model

object LoginValidator {
    fun validateUsernamePassword(username: String, password: String): String? {
        if (username.isBlank() || password.isBlank()) {
            return "Заполните все поля"
        }
        if (username.length < 3) return "Имя пользователя слишком короткое"
        return null
    }

    fun validatePhoneAndCode(phone: String, code: String): String? {
        if (phone.isBlank() || code.isBlank()) {
            return "Введите номер и код"
        }
        if (!phone.matches(Regex("^\\+992\\d{9}\$"))) return "Некорректный номер телефона"
        if (code.length != 6) return "Код должен содержать 6 цифр"
        return null
    }
}
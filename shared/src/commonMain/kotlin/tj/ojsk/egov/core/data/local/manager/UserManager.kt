package tj.ojsk.egov.core.data.local.manager

import tj.ojsk.egov.core.data.local.dao.UserDao
import tj.ojsk.egov.core.data.remote.model.auth.response.UserDTO
import tj.ojsk.egov.core.domain.model.auth.User

class UserManager(
    private val userDao: UserDao
) {

    suspend fun saveUser(user: UserDTO) {
        userDao.saveUser(user)
    }

    suspend fun getUser(): UserDTO? {
        return userDao.getUser()
    }

    suspend fun deleteUser() {
        userDao.deleteUser()
    }
}
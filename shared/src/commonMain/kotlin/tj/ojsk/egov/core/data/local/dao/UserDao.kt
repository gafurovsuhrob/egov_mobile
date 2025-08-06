package tj.ojsk.egov.core.data.local.dao

import tj.ojsk.egov.core.data.remote.model.auth.response.UserDTO

interface UserDao {
    suspend fun saveUser(user: UserDTO)
    suspend fun getUser(): UserDTO?
    suspend fun deleteUser()
}
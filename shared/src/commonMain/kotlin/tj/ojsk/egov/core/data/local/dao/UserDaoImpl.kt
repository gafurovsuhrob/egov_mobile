package tj.ojsk.egov.core.data.local.dao

import tj.ojsk.egov.core.data.remote.mapper.toDTO
import tj.ojsk.egov.core.data.remote.model.auth.response.UserDTO
import tj.ojsk.egov.database.EGOVDatabase

class UserDaoImpl(private val database: EGOVDatabase) : UserDao {

    private val queries = database.userQueries

    override suspend fun saveUser(user: UserDTO) {
        queries.insertUser(
            tin = user.tin.toString(),
            pinfl = user.pinfl,
            username = user.username,
            firstname = user.firstname,
            lastname = user.lastname,
            middlename = user.middlename,
            registered_at = user.registered_at,
            birth_date = user.birth_date,
            gender = user.gender,
            passport_serial_number = user.passport_serial_number,
            pport_issue_place = user.pport_issue_place,
            mobile_phone = user.mobile_phone,
            email = user.email,
        )
    }

    override suspend fun getUser(): UserDTO? {
        return queries.selectUser().executeAsOneOrNull()?.toDTO()
    }

    override suspend fun deleteUser() {
        queries.deleteAll()
    }
}
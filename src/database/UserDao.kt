package com.example.database

import com.example.models.Gender
import com.example.models.User
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import com.example.database.Tables.Users as Users

class UserDao(private val db: Database) : DAOInterface {

    override fun init() = transaction(db) {
        SchemaUtils.create(Users)
        /*repeat(5) {
            createUser(
                User(
                    "x$it",
                    "y$it",
                    "x$it@y.com",
                    "$it",
                    "123$it",
                    Gender.MALE,
                    "#$it"
                ),
            )
        }*/
    }

    override fun createUser(user: User) = transaction(db) {
        Users.insert {
            it[firstName] = user.firstName.orEmpty()
            it[lastName] = user.lastName.orEmpty()
            it[email] = user.email.orEmpty()
            it[password] = user.password.orEmpty()
            it[birthDay] = user.birthDay.orEmpty()
            it[phone] = user.phone.orEmpty()
            it[gender] = user.gender ?: Gender.OTHER
        }
        Unit
    }

    override fun updateUser(user: User) = transaction(db) {
        Users.update({ Users.id eq user.id!! }) {
            it[firstName] = user.firstName.orEmpty()
            it[lastName] = user.lastName.orEmpty()
            it[email] = user.email.orEmpty()
            it[password] = user.password.orEmpty()
            it[birthDay] = user.birthDay.orEmpty()
            it[phone] = user.phone.orEmpty()
            it[gender] = user.gender ?: Gender.OTHER
        }
        Unit
    }

    override fun deleteUser(id: Long) = transaction(db) {
        Users.deleteWhere { Users.id eq id }
        Unit
    }

    override fun getUserById(id: Long): User? = transaction(db) {
        Users.select { Users.id eq id }.map {
            toUser(it)
        }.singleOrNull()
    }

    override fun getAllUsers(): List<User> = transaction(db) {
        Users.selectAll().map {
            toUser(it)
        }
    }

    override fun close() {}

    override fun getUserByEmailAndPassword(email: String, password: String) = transaction(db){
        Users.select { Users.email eq email and(Users.password eq password)}.map {
            toUser(it)
        }.singleOrNull()
    }

    private fun toUser(row : ResultRow) : User =
        User(
            row[Users.firstName],
            row[Users.lastName],
            row[Users.email],
            row[Users.phone],
            row[Users.birthDay],
            row[Users.gender],
            row[Users.password],
            row[Users.id]
        )
}
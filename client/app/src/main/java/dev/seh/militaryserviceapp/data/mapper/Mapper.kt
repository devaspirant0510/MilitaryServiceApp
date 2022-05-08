package dev.seh.militaryserviceapp.data.mapper;

import com.google.firebase.database.DataSnapshot
import dev.seh.militaryserviceapp.data.dto.LoginRequestDTO
import dev.seh.militaryserviceapp.data.entitiy.UserEntity
import timber.log.Timber

/**
 * @author SeungHo Lee
 * summary :
 */
object Mapper {
    inline fun <reified T> snapShotChildrenToIterable(snapshot: DataSnapshot): List<T> {
        val list = mutableListOf<T>()
        Timber.e("엥 갑자기 왜안됨?")
        snapshot.children.forEach { value ->

        }
        snapshot.children.map {
            Timber.e("itaaa ${it.value}")
            val getItem = it.getValue(T::class.java)
            (getItem as? T)?.let{ v->
                list.add(v)
            }
        }
        return list.toList()
    }
}
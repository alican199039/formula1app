package com.aydemir.formula1app.util.helper

import com.aydemir.formula1app.model.data.Driver
import com.aydemir.formula1app.model.data.FavoriteDriver
import com.aydemir.formula1app.util.extension.genericType


class FavoriteHelper(private val sharedHelper: SharedHelper) {
    private var favoriteDrivers: ArrayList<FavoriteDriver>

    init {
        val turnsType = genericType<List<FavoriteDriver>>()
        favoriteDrivers = sharedHelper.retrieveListData("favorites", turnsType)
    }

    fun addDriverFavorite(driver: Driver): Boolean {
        val favoriteDriver = FavoriteDriver(driver.id)

        if (favoriteDrivers.isEmpty()) {
            favoriteDrivers.add(favoriteDriver)
            sharedHelper.addListData("favorites", favoriteDrivers)
            return true
        } else {
            if (favoriteDrivers.any { x -> x.id == driver.id }) {
                favoriteDrivers.remove(favoriteDriver)
                sharedHelper.addListData("favorites", favoriteDrivers)
                return false
            } else {

                favoriteDrivers.add(favoriteDriver)
                sharedHelper.addListData("favorites", favoriteDrivers)
                return true
            }
        }
    }

    fun checkDriverInFavoriteList(id: Int): Boolean {
        return favoriteDrivers.any { x -> x.id == id }
    }
}
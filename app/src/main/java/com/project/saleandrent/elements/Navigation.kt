package com.project.saleandrent.elements

enum class MainNavRoutes(val route: String, val header: String) {
    ListMenu("listMenu", "Houses"),
    HouseMenu("HouseMenu", ""),
    BuyMenu("BuyMenu", "Purchases");

    companion object {
        fun getByRoute(name: String?): MainNavRoutes? {
            return entries.find { it.route.equals(name, ignoreCase = false) }
        }
    }
}

enum class EnterNavRoutes(val route: String) {
    First("first"),
    Login("login"),
    Signup("signup");

    companion object {
        fun getByRoute(name: String?): EnterNavRoutes? {
            return entries.find { it.route.equals(name, ignoreCase = false) }
        }
    }
}
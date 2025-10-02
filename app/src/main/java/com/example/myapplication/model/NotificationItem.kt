package com.example.myapplication.model

/*Creacion de una data clas global, esta encapsula tanto
* a la notficiacion como al lugar, esto para ser utilizado
* en el tab de myNotifications, no afecta a la logica, solo
* a el tab que muestra la informacion*/

data class NotificationItem(
    val notificationItem: Notification,
    val placeItem: Place?) {
}
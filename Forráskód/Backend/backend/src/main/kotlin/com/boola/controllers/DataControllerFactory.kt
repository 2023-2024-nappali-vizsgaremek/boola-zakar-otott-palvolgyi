package com.boola.controllers

import java.util.*

class DataControllerFactory(poolSize: Int) {


    init {
        for (d in 0..poolSize){
            controllerPool.add(DataController(DbConnector("localhost").getConnection()))
        }
    }
    companion object {
        private val controllerPool: Queue<DataController> = LinkedList()
        fun getController():DataController?{
            return try {
                controllerPool.remove()
            } catch (e:Exception){
                e.message?.let { error(it) }
                null
            }
        }

    }
}
package com.boola.controllers

import java.util.*

class DataControllerFactory(poolSize: Int) {


    init {
        for (d in 0..poolSize){
            controllerPool.add(DataController(DbConnector().getConnection()))
        }
    }
    companion object {
        private val controllerPool: Queue<DataController> = LinkedList()
        fun getController():DataController?{
            return try {
                controllerPool.remove()
            } catch (e:Exception){
                error("Error while obtaining connection: " + e.message)
            }
        }

        fun returnController(controller: DataController){
            println("returned controller, we've got ${controllerPool.size} left")
            controllerPool.add(controller)
        }

    }
}
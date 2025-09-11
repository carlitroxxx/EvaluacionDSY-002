package org.example


abstract class Fruta (
    val nombre: String,
    val precioPorKilo : Int,
    val stockKilos : Int
){
    abstract fun descripcion():String

    fun frutaCara(): Boolean = precioPorKilo>5000

    fun valorTotal(): Int =precioPorKilo*stockKilos
}
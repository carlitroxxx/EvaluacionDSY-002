package org.example

class FrutaLocal(
    nombre: String,
    precioPorKilo : Int,
    stockKilos : Int
) : Fruta(nombre, precioPorKilo, stockKilos){
    override fun descripcion():String{
        return "Fruta Local: $nombre \n Precio: $$precioPorKilo \n Stock: $stockKilos kg"
    }
}
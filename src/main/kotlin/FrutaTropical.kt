package org.example

class FrutaTropical(
    nombre: String,
    precioPorKilo : Int,
    stockKilos : Int
) : Fruta(nombre,precioPorKilo,stockKilos){
    override fun descripcion():String{
        return "Fruta Tropical: $nombre \n Precio: $$precioPorKilo \n Stock: $stockKilos kg"
    }
}
package org.example
import kotlinx.coroutines.*


//Sealed
sealed class Resultado
data class Exitoso(val mensaje:String) : Resultado()
data class Error(val mensaje:String) : Resultado()

//Suspend
suspend fun controlarCalidad(fruta: Fruta): Resultado {
    println("Control de calidad para: ${fruta.nombre} iniciado...")
    delay(1500L)//1.5 seg
    return Exitoso("${fruta.nombre} paso el control de calidad!")
    //Error("${fruta.nombre} NO paso el control de calidad!")
}
//Mostrar resultado
fun mostrarResultado(resultado: Resultado){
    when(resultado){
        is Exitoso -> print("✅ ${resultado.mensaje}")
        is Error -> print("❌ ${resultado.mensaje}")
    }
}

//MAIN
fun main() = runBlocking {
    //inicializacion de frutas
    val productos = mutableListOf<Fruta>(
        FrutaTropical("Maracuya", 7500, 30),
        FrutaTropical("Piña", 5000, 50),
        FrutaTropical("Granada", 10000, 10),

        FrutaLocal("Manzana roja", 1990,25),
        FrutaLocal("Mandarina", 1290,40),
        FrutaLocal("Platano", 2500,20)
    )
    //Ingreso nombre fruta
    println("Ingrese nombre de fruta para agregar: ")
    val nombreFruta = readLine() ?: ""
    //Ingreso precio fruta
    var precioFruta = 0
    while (true){
        try {
            println("Ingrese el precio de venta para la fruta $nombreFruta")
            val entrada = readLine() ?: throw Exception("No se ingreso nada")
            precioFruta = entrada.toInt()
            if(precioFruta<=0) throw IllegalArgumentException("El precio debe mayor a 0")
            break
        }catch (ex: Exception){
            println("Error: ${ex.message}")
        }
    }
    //Ingreso stock fruta
    var stockFruta = 0
    while (true){
        try {
            println("Ingrese el stock en kilos de la fruta $nombreFruta")
            val entrada = readLine() ?: throw Exception("No se ingreso nada")
            stockFruta = entrada.toInt()
            if (stockFruta<0) throw IllegalArgumentException("El stock debe mayor o igual a 0")
            break
        }catch (ex: Exception){
            println("Error: ${ex.message}")
        }
    }

    //Tipo fruta

    var frutaNueva: Fruta
    while (true){
        try {
            println("Seleccione el tipo de fruta:\n1. Fruta Local\n2. Fruta Tropical")
            val entrada = readLine() ?: throw Exception("No se ingreso nada")
            val tipoFruta = entrada.toInt()
            frutaNueva = when (tipoFruta){
                1 -> FrutaLocal(nombreFruta,precioFruta,stockFruta)
                2 -> FrutaTropical(nombreFruta,precioFruta,stockFruta)
                else -> throw IllegalArgumentException("Opción inválida...")
            }
            break
        } catch (ex: Exception){
            println("Error: ${ex.message}")
        }
    }

    //
    productos.add(frutaNueva)
    println("Fruta agregada correctamente:")
    println(frutaNueva.descripcion())
    println("")


    //mostrar todas las frutas
    println("\n\nLista de frutas:")
    productos.forEach{println(it.descripcion())}


    //filtro frutas caras
    println("\n\nFrutas con precio mayor a $1000:")
    productos.filter { it.frutaCara() }.forEach{ println(it.descripcion()) }


    //valor total delas frutas
    val totalValor=productos.sumOf{ it.valorTotal()}
    println("\n\nValor total de todos las frutas: $$totalValor")

    //proceso calidad
    println("\n\nIniciando control de calidad de las frutas:")
    productos.forEach{ fruta ->
        val resultado = controlarCalidad(fruta)
        mostrarResultado(resultado)
    }

}

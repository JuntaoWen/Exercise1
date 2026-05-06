package com.example.mobileshop.data

object SampleCatalog {

    val shoes: List<Shoe> = listOf(
        Shoe(
            id = "Nike Air Force 1 '07",
            name = "Nike Air Force 1 '07",
            brandLine = "Nike",
            description = "Comfortable, durable and timeless—it's number one for a reason. The classic '80s construction pairs smooth leather with bold details for style that tracks whether you're on court or on the go.",
            price = 150.00,
            rating = 4.7f,
            category = "Lifestyle",
            colors = listOf(
                ShoeColor("c-white", "White", 0xFFE8E8E8L),
                ShoeColor("c-black", "Black", 0xFF222222L),
                ShoeColor("c-red", "University Red", 0xFFB71C1CL),
            ),
            sizes = listOf(40, 41, 42, 43, 44),
            imageRes = 0,
        ),
        Shoe(
            id = "Luka 5",
            name = "Luka 5",
            brandLine = "Nike",
            description = "On the court? Luka is a Bad man. And the shoes? Now those are nice. For the player who turns pressure into performance, the Luka 5 is designed for 360 degrees of court control.",
            price = 170.00,
            rating = 4.5f,
            category = "Basketball",
            colors = listOf(
                ShoeColor("c-navy", "Midnight Navy", 0xFF1A237EL),
                ShoeColor("c-gray", "Cool Grey", 0xFF9E9E9EL),
            ),
            sizes = listOf(40, 41, 42, 43),
            imageRes = 0,
        ),
        Shoe(
            id = "Pegasus 42",
            name = "Pegasus 42",
            brandLine = "Nike",
            description = "Responsive full-length cushioning sculpted to energise an icon.",
            price = 180.00,
            rating = 4.6f,
            category = "Running",
            colors = listOf(
                ShoeColor("c-green", "Cargo Khaki", 0xFF558B2FL),
                ShoeColor("c-sand", "Desert Sand", 0xFFD7CCC8L),
            ),
            sizes = listOf(41, 42, 43, 44, 45),
            imageRes = 0,
        ),
    )

    fun shoeById(id: String): Shoe? = shoes.find { it.id == id }
}

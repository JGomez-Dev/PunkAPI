package com.jgomez.punkapi.ui.navigator

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


class DestinationTest {

    @Test
    fun `homeScreen route should be homeScreen`() {
        val homeScreen = Destination.HomeScreen
        assertEquals("homeScreen", homeScreen.route)
    }

    @Test
    fun `detailScreen route should be detailScreen`() {
        val detailScreen = Destination.DetailScreen
        assertEquals("detailScreen", detailScreen.route)
    }

    @Test
    fun `verify that there are only 2 objects in Destination`() {
        val sealedClassMembers = Destination::class.sealedSubclasses

        assertEquals(2, sealedClassMembers.size)

        val expectedRoutes = setOf("homeScreen", "detailScreen")
        val actualRoutes = sealedClassMembers.map { it.objectInstance?.route }.toSet()

        assertEquals(expectedRoutes, actualRoutes)
    }

}
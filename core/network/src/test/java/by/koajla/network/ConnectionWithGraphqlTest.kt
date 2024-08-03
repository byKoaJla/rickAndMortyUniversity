package by.koajla.network

import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.Assert.*

class ConnectionWithGraphqlTest {
    private val url = "https://rickandmortyapi.com/graphql"

    @Test
    fun testConnection() = runTest {
        val rickAndMortyClient = RickAndMortyClient(url)
        rickAndMortyClient.getCharacter(361)
            .collect { result ->
                result.onSuccess { character ->
                    assertEquals("Toxic Rick", character.name)
                }
            }
    }
}
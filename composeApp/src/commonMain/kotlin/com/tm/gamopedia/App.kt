package com.tm.gamopedia

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.tm.gamopedia.navigation.FavoriteNavGraph
import com.tm.gamopedia.navigation.GameNavGraph
import com.tm.gamopedia.navigation.SearchNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {

        val navHostController = rememberNavController()

        NavHost(navHostController, startDestination = GameNavGraph.Dest.Root.route) {
            listOf(GameNavGraph, SearchNavGraph, FavoriteNavGraph)
                .forEach {
                    it.build(
                        modifier = Modifier.fillMaxSize(),
                        navHostController = navHostController,
                        navGraphBuilder = this
                    )
                }
        }
    }
}
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import kotlin.random.Random
import kotlin.time.Duration.Companion.seconds

class Greeting {
    private val platform = getPlatform()
    private val rocketComponent = RocketComponent()

    fun greet(): List<String> = buildList {
        add(if (Random.nextBoolean()) "Hi" else "hello!")
        add("Guess what this is! > ${platform.name.reversed()}")
        add(daysPhrase())
    }

    fun greetFlow(): Flow<String> = flow {
        emit(if (Random.nextBoolean()) "Hi" else "hello!")
        delay(1.seconds)
        emit("Guess what this is! > ${platform.name.reversed()}")
        delay(1.seconds)
        emit(daysPhrase())
        delay(1.seconds)
        emit(rocketComponent.launchPhrase())
    }
}
package hu.frontrider.gearcraft2

import com.openmodloader.api.event.Event
import com.openmodloader.loader.event.LoadEvent
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

class GearCraft {

    @Event.Subscribe
    fun onConstruction(event: LoadEvent.Construction) {
        GearCraft.LOGGER.info("Example Mod loading")
    }

    @Event.Subscribe
    fun onFinalization(event: LoadEvent.Finalization) {
        GearCraft.LOGGER.info("Example Mod loaded")
    }

    companion object {
        private val LOGGER = LogManager.getLogger("gearcraft")
    }
}

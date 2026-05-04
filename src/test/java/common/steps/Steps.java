package common.steps;

/**
 * Base class for all step definition classes.
 * Provides access to the shared World object via Cucumber PicoContainer constructor injection.
 */
public class Steps {

    protected World world;

    public Steps(World world) {
        this.world = world;
    }
}

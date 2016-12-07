package lv.ctco.cukesrest.internal;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import cucumber.api.guice.CucumberModules;
import cucumber.runtime.java.guice.InjectorSource;
import lv.ctco.cukescore.internal.GuiceModule;

public class GuiceInjectorSource implements InjectorSource {

    private static Injector injector;

    @Override
    public synchronized Injector getInjector() {
        if (injector == null) {
            injector = Guice.createInjector(Stage.PRODUCTION, CucumberModules.SCENARIO, new CukesRestGuiceModule());
        }
        return injector;
    }
}

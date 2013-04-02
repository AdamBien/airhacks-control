package com.airhacks.control.presentation.airhacks;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 *
 * @author adam-bien.com
 */
public class InjectorProvider {

    private static Injector injector;

    public static Injector get() {
        if (injector == null) {
            injector = Guice.createInjector(new PersistenceModule());
        }
        return injector;
    }
}

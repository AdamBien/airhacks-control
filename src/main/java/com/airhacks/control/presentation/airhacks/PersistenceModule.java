package com.airhacks.control.presentation.airhacks;

import com.airhacks.control.business.registrations.boundary.RegistrationService;
import com.google.inject.AbstractModule;

/**
 *
 * @author adam-bien.com
 */
public class PersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
        final RegistrationService registrationService = new RegistrationService();
        registrationService.init();
        bind(RegistrationService.class).toInstance(registrationService);

    }
}

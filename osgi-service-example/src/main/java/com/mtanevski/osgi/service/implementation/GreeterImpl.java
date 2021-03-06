package com.mtanevski.osgi.service.implementation;

import com.mtanevski.osgi.service.definition.Greeter;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.framework.ServiceRegistration;

import java.util.Hashtable;

public class GreeterImpl implements Greeter, BundleActivator {

    private ServiceReference<Greeter> reference;
    private ServiceRegistration<Greeter> registration;

    @Override
    public String sayHiTo(String name) {
        return "Hello " + name;
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Registering service.");
        registration = bundleContext.registerService(
                Greeter.class,
                new GreeterImpl(),
                new Hashtable<String, String>());
        reference = registration
                .getReference();
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("Unregistering service.");
        registration.unregister();
    }
}

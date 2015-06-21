package de.tum.in.eist.config;

import com.google.inject.AbstractModule;

import de.tum.in.eist.FindRoute;

/*
 * Register Jersey resource classes here by binding them.
 */
public class JerseyModule extends AbstractModule {

  @Override
  protected void configure() {
    bind(FindRoute.class);
  }

}

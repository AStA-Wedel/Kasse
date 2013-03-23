package org.fhw.asta.kasse.client.inject.module;

import org.fhw.asta.kasse.client.activity.ArticleListActivity;
import org.fhw.asta.kasse.client.activity.BackofficeActivity;
import org.fhw.asta.kasse.client.activity.LoginActivity;
import org.fhw.asta.kasse.client.activity.PrintCustomsActivity;
import org.fhw.asta.kasse.client.activity.UserListActivity;
import org.fhw.asta.kasse.client.inject.module.factory.ArticleListActivityFactory;
import org.fhw.asta.kasse.client.inject.module.factory.BackofficeActivityFactory;
import org.fhw.asta.kasse.client.inject.module.factory.LoginActivityFactory;
import org.fhw.asta.kasse.client.inject.module.factory.PrintCustomsActivityFactory;
import org.fhw.asta.kasse.client.inject.module.factory.UserListActivityFactory;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.assistedinject.GinFactoryModuleBuilder;

public class ActivityModule extends AbstractGinModule {

	@Override
	protected void configure() {
		install(new GinFactoryModuleBuilder().implement(Activity.class, LoginActivity.class).build(LoginActivityFactory.class));
		install(new GinFactoryModuleBuilder().implement(Activity.class, ArticleListActivity.class).build(ArticleListActivityFactory.class));
		install(new GinFactoryModuleBuilder().implement(Activity.class, BackofficeActivity.class).build(BackofficeActivityFactory.class));
		install(new GinFactoryModuleBuilder().implement(Activity.class, PrintCustomsActivity.class).build(PrintCustomsActivityFactory.class));
		install(new GinFactoryModuleBuilder().implement(Activity.class, UserListActivity.class).build(UserListActivityFactory.class));
	}

}

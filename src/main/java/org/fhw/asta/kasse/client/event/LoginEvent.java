package org.fhw.asta.kasse.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class LoginEvent extends GwtEvent<LoginEventHandler>{
	
	public static final Type<LoginEventHandler> TYPE = new Type<LoginEventHandler>();

	private final String email;
	
	public LoginEvent(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}
	
	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<LoginEventHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(LoginEventHandler handler) {
		handler.onLogin(this);
	}

}

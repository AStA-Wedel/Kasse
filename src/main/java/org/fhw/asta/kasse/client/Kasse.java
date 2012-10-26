package org.fhw.asta.kasse.client;

import org.fhw.asta.kasse.shared.service.UserService;
import org.fhw.asta.kasse.shared.service.UserServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

public class Kasse implements EntryPoint {

  private UserServiceAsync userService = GWT.create(UserService.class);
	 
  public void onModuleLoad() {

	  userService.login("alex", "daddeldu", new AsyncCallback<Boolean>() {
		
		@Override
		public void onSuccess(Boolean result) {
			
			if (result) {
				Window.alert("hallo alex");
			}
			
		}
		
		@Override
		public void onFailure(Throwable caught) {
			// TODO Auto-generated method stub
			
		}
		
	});
	  
  
  }
}

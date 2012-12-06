package org.fhw.asta.kasse.server.common;

import java.io.Serializable;
import java.util.List;

import org.fhw.asta.kasse.shared.authentication.AuthenticationResult.AuthenticationStatus;
import org.fhw.asta.kasse.shared.basket.BasketItem;

import com.google.common.collect.Lists;
import com.google.inject.servlet.SessionScoped;

@SessionScoped
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;

	private AuthenticationStatus authenticationStatus = AuthenticationStatus.NOT_AUTHENTICATED;

	private List<BasketItem> basket = Lists.newArrayList();
	
	private Integer basketDiscount = 0;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public Integer getBasketDiscount(){
		return basketDiscount;
	}

	public void setBasketDiscount(Integer discount){
		basketDiscount = discount;
	}
	
	public void setAuthenticationStatus(AuthenticationStatus authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
	}
	
	public AuthenticationStatus getAuthenticationStatus() {
		return authenticationStatus;
	}
	
	public List<BasketItem> getBasket() {
		return basket;
	}

	public void setBasket(List<BasketItem> basket) {
		this.basket = basket;
	}
		
}

package org.fhw.asta.kasse.server.servlet;

import org.fhw.asta.kasse.shared.service.billorder.BillOrderService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class BillOrderServiceServlet extends RemoteServiceServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	public BillOrderServiceServlet(BillOrderService billorderService) {
		super(billorderService);
	}
	
}

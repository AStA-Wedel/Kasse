package org.fhw.asta.kasse.server.service;

import org.fhw.asta.kasse.shared.model.LetterHead;
import org.fhw.asta.kasse.shared.service.letterhead.LetterHeadService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Singleton;

@Singleton
public class LetterHeadServiceEndpoint extends RemoteServiceServlet implements LetterHeadService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public LetterHead getLetterHead() {
		// TODO Auto-generated method stub
		return new LetterHead("Feldstr.", "22880", "Wedel", "666", "Astala");
	}

}

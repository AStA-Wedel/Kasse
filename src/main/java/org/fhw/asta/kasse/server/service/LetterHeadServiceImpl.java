package org.fhw.asta.kasse.server.service;

import org.fhw.asta.kasse.shared.model.LetterHead;
import org.fhw.asta.kasse.shared.service.letterhead.LetterHeadService;

import com.google.inject.Singleton;

@Singleton
public class LetterHeadServiceImpl implements LetterHeadService {

	@Override
	public LetterHead getLetterHead() {
		// TODO Auto-generated method stub
		return new LetterHead("Feldstr.", "22880", "Wedel", "666", "Astala");
	}

}

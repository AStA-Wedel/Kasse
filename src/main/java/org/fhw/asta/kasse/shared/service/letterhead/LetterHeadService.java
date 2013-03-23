package org.fhw.asta.kasse.shared.service.letterhead;

import org.fhw.asta.kasse.shared.model.LetterHead;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("letterhead")
public interface LetterHeadService extends RemoteService {
	LetterHead getLetterHead();
}

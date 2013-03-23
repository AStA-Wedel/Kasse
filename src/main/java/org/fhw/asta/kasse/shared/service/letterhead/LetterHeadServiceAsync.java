package org.fhw.asta.kasse.shared.service.letterhead;

import org.fhw.asta.kasse.shared.model.LetterHead;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface LetterHeadServiceAsync {

	void getLetterHead(AsyncCallback<LetterHead> callback);

}

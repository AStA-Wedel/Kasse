package org.fhw.asta.kasse.server.servlet;

import org.fhw.asta.kasse.shared.service.article.ArticleService;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ArticleServiceServlet extends RemoteServiceServlet {

	private static final long serialVersionUID = 1L;

	@Inject
	public ArticleServiceServlet(ArticleService articleService) {
		super(articleService);
	}
	
}

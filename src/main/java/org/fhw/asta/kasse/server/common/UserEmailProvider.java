package org.fhw.asta.kasse.server.common;

import java.util.Arrays;

import javax.annotation.Nullable;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.collect.Iterables;

public class UserEmailProvider implements Supplier<Optional<String>> {

	private static final String EMAIL_COOKIE = "email";

	private final HttpServletRequest req;

	public UserEmailProvider(HttpServletRequest req) {
		this.req = req;
	}

	@Override
	public Optional<String> get() {

		Optional<Cookie> cookie = Iterables
				.tryFind(Arrays.asList(req.getCookies()),
						new ValidUserCookiePredicate());

		if (!cookie.isPresent()) {
			return Optional.absent();
		} else {
			return Optional.of(cookie.get().getValue());
		}
	}

	private static class ValidUserCookiePredicate implements Predicate<Cookie> {

		@Override
		public boolean apply(@Nullable Cookie cookie) {
			return cookie.getName().equals(EMAIL_COOKIE)
					&& !Strings.isNullOrEmpty(cookie.getValue());
		}

	}

}

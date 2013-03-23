package org.fhw.asta.kasse.server.common;

import java.util.Arrays;

import javax.annotation.Nullable;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.base.Strings;
import com.google.common.base.Supplier;
import com.google.common.collect.Iterables;

/**
 * Returns the email of the current logged in user if present.
 * 
 * @author alexbiehl
 * 
 */
public class UserEmailProvider implements Supplier<Optional<String>> {

	private static final String EMAIL_COOKIE = "email";

	private final HttpServletRequest req;

	public UserEmailProvider(HttpServletRequest req) {
		this.req = req;
	}

	@Override
	public Optional<String> get() {
		return Iterables.tryFind(Arrays.asList(req.getCookies()),
				new ValidUserCookiePredicate()).transform(
				new CookieTransformFunction());
	}

	private static class CookieTransformFunction implements
			Function<Cookie, String> {

		@Override
		public String apply(@Nullable Cookie input) {
			return input.getValue();
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

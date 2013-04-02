package org.fhw.asta.kasse.client.common;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nullable;

import org.fhw.asta.kasse.shared.common.Tuple;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.thirdparty.guava.common.base.Strings;

public class HTMLTableBuilder {

	public static class RowBuilder {

		private final String className;

		private final List<Tuple<String, String>> columns;

		public RowBuilder() {
			this("");
		}

		public RowBuilder(String className) {
			this.className = className;
			this.columns = Lists.newArrayList();
		}

		public RowBuilder col(int cont, String className) {
			return col(Integer.toString(cont), className);
		}

		public RowBuilder col(String content, String className) {
			columns.add(Tuple.mk(content, className));
			return this;
		}

		public RowBuilder col(int cont) {
			return col(Integer.toString(cont));
		}

		public RowBuilder col(String content) {
			return col(content, "");
		}

		public RowBuilder cols(String... columns) {
			this.columns.addAll(Lists.transform(Arrays.asList(columns),
					new Tuplelize()));
			return this;
		}

		private SafeHtml toSafeHTML() {

			SafeHtmlBuilder builder = new SafeHtmlBuilder();

			builder.appendHtmlConstant("<tr "
					+ (Strings.isNullOrEmpty(className) ? "" : "class=\""
							+ className + "\"") + ">");

			List<SafeHtml> cols = Lists.transform(columns, new ColumnWrapper());

			for (SafeHtml col : cols) {
				builder.append(col);
			}

			builder.appendHtmlConstant("</tr>");

			return builder.toSafeHtml();
		}

	}

	public HTMLTableBuilder(String tableClassName) {
		this.tableClass = tableClassName;
	}

	public static HTMLTableBuilder mk() {
		return new HTMLTableBuilder("");
	}

	public static HTMLTableBuilder mk(String tableClassName) {
		return new HTMLTableBuilder(tableClassName);
	}

	private final String tableClass;

	private final List<RowBuilder> rows = Lists.newArrayList();

	public RowBuilder row() {

		RowBuilder rb = new RowBuilder();

		rows.add(rb);

		return rb;
	}

	public RowBuilder row(String className) {

		RowBuilder rb = new RowBuilder(className);

		rows.add(rb);

		return rb;
	}

	public SafeHtml toSafeHtml() {

		SafeHtmlBuilder htmlBuilder = new SafeHtmlBuilder();

		htmlBuilder.appendHtmlConstant("<table "
				+ (Strings.isNullOrEmpty(tableClass) ? "" : "class=\""
						+ tableClass + "\"") + ">");

		for (RowBuilder rb : rows) {
			htmlBuilder.append(rb.toSafeHTML());
		}

		htmlBuilder.appendHtmlConstant("</table>");

		return htmlBuilder.toSafeHtml();
	}

	private static class Tuplelize implements
			Function<String, Tuple<String, String>> {

		@Override
		@Nullable
		public Tuple<String, String> apply(@Nullable String input) {
			return Tuple.mk(input, "");
		}
	}

	private static class ColumnWrapper implements
			Function<Tuple<String, String>, SafeHtml> {

		@Override
		@Nullable
		public SafeHtml apply(@Nullable Tuple<String, String> input) {

			String content = input.fst();
			String className = input.snd();

			return SafeHtmlUtils.fromString("<td"
					+ (Strings.isNullOrEmpty(className) ? "" : "class=\""
							+ className + "\"") + ">" + content + "</td>");
		}

	}

}

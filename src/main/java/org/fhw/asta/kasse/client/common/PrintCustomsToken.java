package org.fhw.asta.kasse.client.common;

import com.google.common.base.Optional;
import com.google.common.base.Strings;

public class PrintCustomsToken {

	public static final PrintCustomsToken EMPTY_TOKEN = new PrintCustomsToken(null, 0);
	
	public enum PrintType {
		BILLORDER,
		RECEIPT,
		TAX,
		USERLIST				
	}
	

	private final PrintType printType;
	private final int id;
	
	public PrintType getPrintType() {
		return printType;
	}

	public int getId() {
		return id;
	}
	
	public PrintCustomsToken(PrintType printType, int id) {
		this.printType = printType;
		this.id = id;
	}
	
	public String toString() {
		return this != EMPTY_TOKEN ? printType.toString() + "," + Integer.toString(id) : "";
	}

	public static Optional<PrintCustomsToken> fromString(String token) {
				
		if (Strings.isNullOrEmpty(token)){
			return Optional.absent();
		}
		
		String[] tokens = token.split(",");
		if (tokens.length == 2) {
			return Optional.of(new PrintCustomsToken(PrintType.valueOf(tokens[0]), Integer.valueOf(tokens[1])));
		} else {
			return Optional.absent();
		}
	}
}

package org.thibaut.wheretoclimb.model.entity;

import java.util.Arrays;
import java.util.List;

public abstract class Verticality {
	private final static  List<String> verticalities = Arrays.asList(
	"unknown",
	"very less than vertical",
	"sligthly less than vertical",
	"vertical",
	"sligthly more than vertical",
	"much more than vertical");

	public static List< String > getVerticalities( ) {
		return verticalities;
	}
}
package eu.abra.dojo;

import java.util.List;
import java.util.function.Predicate;

public class DatabaseOperations {

	public static List<Refugee> findAll(List<Refugee> refugees, Country origin) {
		// TODO #1
		return null;
	}

	public static List<Refugee> findAll(List<Refugee> refugees, Country origin, Country destination) {
		// TODO #2
		return null;
	}
	
	public static List<Refugee> findAll(List<Refugee> refugees, Country origin, Country destination, int youngerThan) {
		// TODO #3
		return null;
	}

	public static List<Refugee> findAll(List<Refugee> refugees, Predicate<Refugee> predicate) {
		// TODO #4 + #5 + #7
		return null;
	}


	// TODO #7
	// Type that should be replaced with a real one,
	// see http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
	interface FixMe<T> {

	}

	// TODO #7
	public static FixMe<Refugee> print = /* TODO */ null;

	public static String printAll(List<Refugee> refugees, FixMe<Refugee> print) {
		// TODO #7
		return null;
	}


	// TODO #9
	// Type that should be replaced with a real one,
	// see http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
	interface FixMe2<T, U> {

	}

	// TODO #9
	public static FixMe2<Refugee, Country> isFrom = /* TODO */ null;


	// TODO #10 + #12
	public static Predicate<Refugee> isFromCountryBuilder(Country c) {
		return /* TODO */ null;
	}
	

	// TODO #11 + #13
	public static Predicate<Refugee> partial(FixMe2<Refugee, Country> predicate, Country c) {
		return /* TODO */ null;
	}

}

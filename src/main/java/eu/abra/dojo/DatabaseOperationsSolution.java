package eu.abra.dojo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

public class DatabaseOperationsSolution {

	public static List<Refugee> findAll(List<Refugee> refugees, Country origin) {
		List<Refugee> found = new ArrayList<Refugee>();
		for (Refugee r : refugees) {
			if (origin.equals(r.getOrigin())) {
				found.add(r);
			}
		}
		return found;
	}

	public static List<Refugee> findAll(List<Refugee> refugees, Country origin, Country destination) {
		List<Refugee> found = new ArrayList<Refugee>();
		for (Refugee r : refugees) {
			if (origin.equals(r.getOrigin()) && destination.equals(r.getDestination())) {
				found.add(r);
			}
		}
		return found;
	}

	public static List<Refugee> findAll(List<Refugee> refugees, Country origin, Country destination, int youngerThan) {
		List<Refugee> found = new ArrayList<Refugee>();
		for (Refugee r : refugees) {
			if (origin.equals(r.getOrigin()) && destination.equals(r.getDestination()) && r.getAge() < youngerThan) {
				found.add(r);
			}
		}
		return found;
	}

	public static List<Refugee> findAll(List<Refugee> refugees, Predicate<Refugee> predicate) {
		List<Refugee> found = new ArrayList<Refugee>();
		for (Refugee r : refugees) {
			if (predicate.test(r)) {
				found.add(r);
			}
		}
		return found;
	}

	// SOLUTION #7
	// Type that should be replaced with a real one,
	// see http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
	// interface FixMe<T> {
	//
	// }

	// SOLUTION #7
	public static Function<Refugee, String> print = (Refugee r) -> {
		return r.getName() + ", age " + r.getAge() + ".\n";
	};

	public static String printAll(List<Refugee> refugees, Function<Refugee, String> print) {
		// SOLUTION #7
		return print.apply(refugees.get(0));
	}

	// SOLUTION #9
	// Type that should be replaced with a real one,
	// see http://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html
	// interface FixMe2<T, U> {
	//
	// }

	// SOLUTION #9
	public static BiPredicate<Refugee, Country> isFrom = (Refugee r, Country c) -> {
		return c.equals(r.getOrigin());
	};

	// SOLUTION #10
	public static Predicate<Refugee> isFromCountryBuilder(Country c) {
		return (Refugee r) -> {
			return c.equals(r.getOrigin());
		};
	}

	// SOLUTION #11
	public static Predicate<Refugee> partial(BiPredicate<Refugee, Country> predicate, Country c) {
		return (Refugee r) -> {
			return predicate.test(r, c);
		};
	}

	// SOLUTION #12
	public static Function<Country, Predicate<Refugee>> isFromCountryBuilderLambda_12 = (Country c) -> {
		return (Refugee r) -> {
			return c.equals(r.getOrigin());
		};
	};

	// SOLUTION #13
	public static BiFunction<BiPredicate<Refugee, Country>, Country, Predicate<Refugee>> partial_13 = (BiPredicate<Refugee, Country> bp, Country c) -> {
		return (Refugee r) -> {
			return bp.test(r, c);
		};
	};

	// SOLUTION #14
	public static Function<Country, Predicate<Refugee>> isFromCountryBuilderLambda_14 = c -> r -> c.equals(r.getOrigin());

	public static BiFunction<BiPredicate<Refugee, Country>, Country, Predicate<Refugee>> partial_14 = (bp, c) -> r -> bp.test(r, c);

	// SOLUTION #15 (see the test as well)
	@FunctionalInterface
	interface Partial_15<T, U> {
		public Predicate<T> apply(BiPredicate<T, U> bp, U u);
	}

	public static Partial_15<Refugee, Country> partial_15 = (bp, c) -> r -> bp.test(r, c);

}

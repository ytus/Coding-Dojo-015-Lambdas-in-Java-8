package eu.abra.dojo;

import com.google.common.collect.Lists;
import eu.abra.dojo.Country;
import eu.abra.dojo.DatabaseOperationsSolution;
import eu.abra.dojo.Refugee;
import java.util.List;
import java.util.function.Predicate;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DatabaseOperationsSolutionTest {

	private List<Refugee> getAllRefugees() {
		return  Lists.newArrayList(
			new Refugee("Aqeela Asifi", 35, Country.ERITREA, Country.GERMANY),
			new Refugee("Hosein", 17, Country.ERITREA, Country.CZECH_REPUBLIC),
			new Refugee("Shokoufeh Toure", 23, Country.SERBIA, Country.GERMANY),
			new Refugee("Yacoub", 42, Country.SUDAN, Country.GERMANY),
			new Refugee("David Okafor", 22, Country.SYRIA, Country.SWEDEN)
		);
	}
	
	// #1
	@Test
	public void findAllByOrigin() {

		List<Refugee> found = DatabaseOperationsSolution.findAll(getAllRefugees(), Country.ERITREA);
		
		Assert.assertEquals(found.size(), 2);
		Assert.assertEquals(found.get(0).getOrigin(), Country.ERITREA);
		Assert.assertEquals(found.get(1).getOrigin(), Country.ERITREA);
	}

	// #2
	@Test
	public void findAllByOriginAndDestination() {

		List<Refugee> found = DatabaseOperationsSolution.findAll(getAllRefugees(), Country.ERITREA, Country.CZECH_REPUBLIC);
		
		Assert.assertEquals(found.size(), 1);
		Assert.assertEquals(found.get(0).getOrigin(), Country.ERITREA);
		Assert.assertEquals(found.get(0).getDestination(), Country.CZECH_REPUBLIC);
	}

	// #3
	@Test
	public void findAllByOriginAndDestinationAndAge() {

		List<Refugee> found = DatabaseOperationsSolution.findAll(getAllRefugees(), Country.ERITREA, Country.CZECH_REPUBLIC, 18);

		Assert.assertEquals(found.size(), 1);
		Assert.assertEquals(found.get(0).getOrigin(), Country.ERITREA);
		Assert.assertEquals(found.get(0).getDestination(), Country.CZECH_REPUBLIC);
		Assert.assertTrue(found.get(0).getAge() < 18);
	}

	// #4
	@Test
	public void findAllByPredicate_anonymousInnerClass() {
		
		Predicate<Refugee> isFromEritreaAndYoungerThan18 = new Predicate<Refugee>() {

			@Override
			public boolean test(Refugee r) {
				return Country.ERITREA.equals(r.getOrigin()) && r.getAge() < 18;
			}
		};

		List<Refugee> found = DatabaseOperationsSolution.findAll(getAllRefugees(), isFromEritreaAndYoungerThan18);

		Assert.assertEquals(found.size(), 1);
		Assert.assertEquals(found.get(0).getOrigin(), Country.ERITREA);
		Assert.assertEquals(found.get(0).getDestination(), Country.CZECH_REPUBLIC);
		Assert.assertTrue(found.get(0).getAge() < 18);
	}

	// #5

	// an example of functional interface
	@FunctionalInterface
	public interface Int2String {
		public String apply(Integer i);
	}

	// lambda expression that implements that func. iterface
	public Int2String integer2String = (Integer i) -> {
		return Integer.toString(i);
	};

	// and usage
	@Test
	public void int2String() {
		String two = integer2String.apply(2);
		Assert.assertEquals(two, "2");
	}
	
	// more general functional interface
	@FunctionalInterface
	public interface Type2Type<T, U> {
		public U apply(T t);
	}

	// lambda expression that implements that func. iterface
	public Type2Type<String, Boolean> string2Boolean = (String s) -> {
		return Boolean.valueOf(s);
	};

	// usage
	@Test
	public void type2Type() {
		Boolean bTrue = string2Boolean.apply("true");
		Assert.assertTrue(bTrue);
	}

	// #6
	@Test
	public void findAllByPredicate_lambdaExpression() {

		Predicate<Refugee> isFromEritreaAndYoungerThan18 = (Refugee r) -> {
			return Country.ERITREA.equals(r.getOrigin()) && r.getAge() < 18;
		};

		List<Refugee> found = DatabaseOperationsSolution.findAll(getAllRefugees(), isFromEritreaAndYoungerThan18);

		Assert.assertEquals(found.size(), 1);
		Assert.assertEquals(found.get(0).getOrigin(), Country.ERITREA);
		Assert.assertEquals(found.get(0).getDestination(), Country.CZECH_REPUBLIC);
		Assert.assertTrue(found.get(0).getAge() < 18);
	}

	// #7
	@Test
	public void printRefugeeDetails_lambdaExpression() {

		Refugee ageela = new Refugee("Aqeela Asifi", 35, Country.ERITREA, Country.GERMANY);

		String details = DatabaseOperationsSolution.printAll(Lists.newArrayList(ageela), DatabaseOperationsSolution.print);

		Assert.assertEquals(details, "Aqeela Asifi, age 35.\n");
	}

	// #8
	@Test
	public void findAllByPredicate_smallerLambdaExpression() {

		Predicate<Refugee> isFromEritrea = (Refugee r) -> {
			return Country.ERITREA.equals(r.getOrigin());
		};
		Predicate<Refugee> isYoungerThan18 = (Refugee r) -> {
			return r.getAge() < 18;
		};
		Predicate<Refugee> isFromEritreaAndYoungerThan18 = isFromEritrea.and(isYoungerThan18);

		List<Refugee> found = DatabaseOperationsSolution.findAll(getAllRefugees(), isFromEritreaAndYoungerThan18);

		Assert.assertEquals(found.size(), 1);
		Assert.assertEquals(found.get(0).getOrigin(), Country.ERITREA);
		Assert.assertEquals(found.get(0).getDestination(), Country.CZECH_REPUBLIC);
		Assert.assertTrue(found.get(0).getAge() < 18);
	}

	// #9
	@Test
	public void isFrom() {

		Refugee ageela = new Refugee("Aqeela Asifi", 35, Country.ERITREA, Country.GERMANY);

		Assert.assertTrue(DatabaseOperationsSolution.isFrom.test(ageela, Country.ERITREA));
		Assert.assertFalse(DatabaseOperationsSolution.isFrom.test(ageela, Country.CZECH_REPUBLIC));
	}

	// #10
	@Test
	public void isFromCountryBuilder() {

		Refugee ageela = new Refugee("Aqeela Asifi", 35, Country.ERITREA, Country.GERMANY);

		Assert.assertTrue(DatabaseOperationsSolution.isFromCountryBuilder(Country.ERITREA).test(ageela));
		Assert.assertFalse(DatabaseOperationsSolution.isFromCountryBuilder(Country.CZECH_REPUBLIC).test(ageela));
	}

	// #11
	@Test
	public void partial() {

		Refugee ageela = new Refugee("Aqeela Asifi", 35, Country.ERITREA, Country.GERMANY);

		Predicate<Refugee> isFromEritrea = DatabaseOperationsSolution.partial(DatabaseOperationsSolution.isFrom, Country.ERITREA);
		Assert.assertTrue(isFromEritrea.test(ageela));

		Predicate<Refugee> isFromCzechRepublic = DatabaseOperationsSolution.partial(DatabaseOperationsSolution.isFrom, Country.CZECH_REPUBLIC);
		Assert.assertFalse(isFromCzechRepublic.test(ageela));
	}

	// #15
	@Test
	public void partial_15() {

		Refugee ageela = new Refugee("Aqeela Asifi", 35, Country.ERITREA, Country.GERMANY);

		Predicate<Refugee> isFromEritrea = DatabaseOperationsSolution.partial_15.apply(DatabaseOperationsSolution.isFrom, Country.ERITREA);
		Assert.assertTrue(isFromEritrea.test(ageela));

		Predicate<Refugee> isFromCzechRepublic = DatabaseOperationsSolution.partial_15.apply(DatabaseOperationsSolution.isFrom, Country.CZECH_REPUBLIC);
		Assert.assertFalse(isFromCzechRepublic.test(ageela));
	}
}

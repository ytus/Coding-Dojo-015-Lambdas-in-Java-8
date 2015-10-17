package eu.abra.dojo;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.function.Predicate;
import org.testng.Assert;

public class DatabaseOperationsTest {

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
	// @Test
	public void findAllByOrigin() {

		List<Refugee> found = DatabaseOperations.findAll(getAllRefugees(), Country.ERITREA);
		
		Assert.assertEquals(found.size(), 2);
		Assert.assertEquals(found.get(0).getOrigin(), Country.ERITREA);
		Assert.assertEquals(found.get(1).getOrigin(), Country.ERITREA);
	}

	// #2
	// @Test
	public void findAllByOriginAndDestination() {

		List<Refugee> found = DatabaseOperations.findAll(getAllRefugees(), Country.ERITREA, Country.CZECH_REPUBLIC);
		
		Assert.assertEquals(found.size(), 1);
		Assert.assertEquals(found.get(0).getOrigin(), Country.ERITREA);
		Assert.assertEquals(found.get(0).getDestination(), Country.CZECH_REPUBLIC);
	}

	// #3
	// @Test
	public void findAllByOriginAndDestinationAndAge() {

		List<Refugee> found = DatabaseOperations.findAll(getAllRefugees(), Country.ERITREA, Country.CZECH_REPUBLIC, 18);

		Assert.assertEquals(found.size(), 1);
		Assert.assertEquals(found.get(0).getOrigin(), Country.ERITREA);
		Assert.assertEquals(found.get(0).getDestination(), Country.CZECH_REPUBLIC);
		Assert.assertTrue(found.get(0).getAge() < 18);
	}

	// #4
	// @Test
	public void findAllByPredicate_anonymousInnerClass() {
		
		Predicate<Refugee> isFromEritreaAndYoungerThan18 = /* TODO new ... */ null;

		List<Refugee> found = DatabaseOperations.findAll(getAllRefugees(), isFromEritreaAndYoungerThan18);

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

	// usage
	// @Test
	public void int2String() {
		String two = integer2String.apply(2);
		Assert.assertEquals(two, "2");
	}
	
	// a more general functional interface
	@FunctionalInterface
	public interface Type2Type<T, U> {
		public U apply(T t);
	}

	// lambda expression that implements that func. iterface
	public Type2Type<String, Boolean> string2Boolean = (String s) -> {
		return Boolean.valueOf(s);
	};

	// and usage
	// @Test
	public void type2Type() {
		Boolean bTrue = string2Boolean.apply("true");
		Assert.assertTrue(bTrue);
	}

	// #6
	// @Test
	public void findAllByPredicate_lambdaExpression() {

		Predicate<Refugee> isFromEritreaAndYoungerThan18 = /* TODO */ null;

		List<Refugee> found = DatabaseOperations.findAll(getAllRefugees(), isFromEritreaAndYoungerThan18);

		Assert.assertEquals(found.size(), 1);
		Assert.assertEquals(found.get(0).getOrigin(), Country.ERITREA);
		Assert.assertEquals(found.get(0).getDestination(), Country.CZECH_REPUBLIC);
		Assert.assertTrue(found.get(0).getAge() < 18);
	}

	// #7
	// @Test
	public void printRefugeeDetails_lambdaExpression() {

		Refugee ageela = new Refugee("Aqeela Asifi", 35, Country.ERITREA, Country.GERMANY);

		String details = DatabaseOperations.printAll(Lists.newArrayList(ageela), DatabaseOperations.print);

		Assert.assertEquals(details, "Aqeela Asifi, age 35.\n");
	}

	// #8
	// @Test
	public void findAllByPredicate_smallerLambdaExpression() {

		Predicate<Refugee> isFromEritrea = /* TODO */ null;
		Predicate<Refugee> isYoungerThan18 = /* TODO */ null;
		Predicate<Refugee> isFromEritreaAndYoungerThan18 = /* TODO */ null;

		List<Refugee> found = DatabaseOperations.findAll(getAllRefugees(), isFromEritreaAndYoungerThan18);

		Assert.assertEquals(found.size(), 1);
		Assert.assertEquals(found.get(0).getOrigin(), Country.ERITREA);
		Assert.assertEquals(found.get(0).getDestination(), Country.CZECH_REPUBLIC);
		Assert.assertTrue(found.get(0).getAge() < 18);
	}

	// #9
	// @Test
	public void isFrom() {

		Refugee ageela = new Refugee("Aqeela Asifi", 35, Country.ERITREA, Country.GERMANY);

		// TODO
		// Assert.assertTrue(DatabaseOperations.isFrom ??? (ageela, Country.ERITREA));
		// Assert.assertFalse(DatabaseOperations.isFrom ??? (ageela, Country.CZECH_REPUBLIC));
	}

	// #10
	// @Test
	public void isFromCountryBuilder() {

		Refugee ageela = new Refugee("Aqeela Asifi", 35, Country.ERITREA, Country.GERMANY);

		// TODO

		Predicate<Refugee> isFormEritrea = DatabaseOperations.isFromCountryBuilder(Country.ERITREA);
		Predicate<Refugee> isFormCzech = DatabaseOperations.isFromCountryBuilder(Country.CZECH_REPUBLIC);

		// Assert.assertTrue(isFromEritrea ??? (ageela));
		// Assert.assertFalse(isFromCzech ??? (ageela));
	}

	// #11
	// @Test
	public void partial() {

		Refugee ageela = new Refugee("Aqeela Asifi", 35, Country.ERITREA, Country.GERMANY);

		// TODO
		// ??? isFromEritrea = DatabaseOperations.partial(DatabaseOperations.isFrom, Country.ERITREA);
		// Assert.assertTrue(isFromEritrea ??? (ageela));

		// ??? isFromCzechRepublic = DatabaseOperations.partial(DatabaseOperations.isFrom, Country.CZECH_REPUBLIC);
		// Assert.assertFalse(isFromCzechRepublic ??? (ageela));
	}
}

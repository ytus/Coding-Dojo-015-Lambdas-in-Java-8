package eu.abra.dojo;

class Refugee {
	private String name;
	private int age;
	private Country origin;
	private Country destination;
	
	public Refugee(String name, int age, Country origin, Country destination) {
		this.name = name;
		this.age = age;
		this.origin = origin;
		this.destination = destination;
	}

	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}
	
	public Country getOrigin() {
		return origin;
	}
	
	public Country getDestination() {
		return destination;
	}
}
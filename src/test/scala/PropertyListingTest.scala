/**
  * Created by default on 16/11/16.
  */
class PropertyListingTest extends org.scalatest.FunSuite {

  test("can view a property") {
    val property = Property(1, "someName", 5, List.empty[Person], None)
    val propertyListing: PropertyListing = PropertyListing()
    propertyListing.save(property)
    assert(propertyListing.view(1) == property)
  }

  test("change maximum occupancy for property") {
    val property = Property(1, "someName", 5, List.empty[Person], None)
    assert(property.changeMaximumOccupancy(10).maximumOccupancy == 10)
  }

  test("add person to a property") {
    val property = Property(1, "someName", 5, List.empty[Person], None)
    assert(property.addPerson(person = Person("Octavian", Male)).occupants == List(Person("Octavian", Male)))
  }

  test("remove person from a property") {
    val property = Property(1, "someName", 5, List.empty[Person], None)
    val octavian = Person("Octavian", Male)
    val newProperty = property.addPerson(octavian)
    assert(newProperty.removePerson(octavian).occupants == List.empty[Person])
  }
}

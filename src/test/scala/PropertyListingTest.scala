import scala.util.{Failure, Success}
import java.time.Instant

/**
  * Created by default on 16/11/16.
  */
class PropertyListingTest extends org.scalatest.FunSuite {

  test("can view a property") {
    val property = Property(1, "someName", 5, List.empty[Person], None)
    val propertyListing: PropertyListing = PropertyListing()
    propertyListing.save(property)
    assert(propertyListing.view(1) == Success(property))
  }

  test("change maximum occupancy for property") {
    val property = Property(1, "someName", 5, List.empty[Person], None)
    assert(property.changeMaximumOccupancy(10).get.maximumOccupancy == 10)
  }

  test("add person to a property") {
    val property = Property(1, "someName", 5, List.empty[Person], None)
    assert(property.addPerson(person = Person("Octavian", Male)).get.occupants == List(Person("Octavian", Male)))
  }

  test("remove person from a property") {
    val property = Property(1, "someName", 5, List.empty[Person], None)
    val octavian = Person("Octavian", Male)
    val newProperty = property.addPerson(octavian).get
    assert(newProperty.removePerson(octavian).get.occupants == List.empty[Person])
  }

  test("can not modify after issueing a contract") {
    val property = Property(1, "someName", 5, List.empty[Person], None)
    val contract = Contract(List((Person("Mike", Male), Instant.now()), (Person("Hannah", Female), Instant.now())), Some(Instant.now()))
    val newProperty: Property = property.sign(Some(contract)).get
    assert(newProperty.changeMaximumOccupancy(10).failed.get.getMessage == "Contract already signed")
  }

  test("all persons must sign the contract before it's executed") {
    val property = Property(1, "someName", 5, List(Person("Mike", Male), Person("Hannah", Female)), None)
    val contract = Contract(List((Person("Mike", Male), Instant.now())), Some(Instant.now()))
    assert(property.sign(Some(contract)).failed.get.getMessage == "Not everyone signed the contract")
  }
}

import org.scalatest.FunSuite

/**
  * Created by default on 16/11/16.
  */
class PersonTest extends FunSuite {

  test("update name") {
    val person = Person("John", Male)
    assert(person.updateName("Chris") == Person("Chris", Male))
  }

  test("upate name and gender") {
    val person = Person("John", Male)
    assert(person.updateName("Wendy").updateGender(Female) == Person("Wendy", Female))
  }

  test("name must be unique in a property") {
    val property: Property = Property(1, "someName", 5, List(Person("Alex", Female)), None)
    val newPerson: Person = Person("Alex", Male)
    assert(property.addPerson(newPerson).failed.get.getMessage == "Name must be unique in a property")
  }
}

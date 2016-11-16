/**
  * Created by default on 16/11/16.
  */

case class Person(name: String, gender: Gender) {
  def updateName(newName: String): Person = this.copy(name=newName)

  def updateGender(newGender: Gender): Person = this.copy(gender=newGender)
}
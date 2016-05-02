package introsde.assignment.soap.utils;

import java.util.List;


//This statement means that class "Peoples.java" is the root-element of our example
//@XmlRootElement
public class Peoples {

  // XmlElement sets the name of the entities
//  @XmlElement(name = "person")
  private List<Pason> personList;

  public void setPeopleList(List<Pason> personList) {
    this.personList = personList;
  }

  public List<Pason> getPersonList() {
    return personList;
  }

} 
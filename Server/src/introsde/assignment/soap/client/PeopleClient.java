package introsde.assignment.soap.client;

import introsde.assignment.soap.dao.mtds.PersonMtd;
import introsde.assignment.soap.model.Helthprofile;
import introsde.assignment.soap.model.Person;
import introsde.assignment.soap.utils.HealthProfilez;
import introsde.assignment.soap.utils.Measure;
import introsde.assignment.soap.utils.MeasureTyp;
import introsde.assignment.soap.utils.MeasureType;
import introsde.assignment.soap.utils.Peoples;
import introsde.assignment.soap.utils.Pason;
import introsde.assignment.soap.ws.People;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.ws.Service;

public class PeopleClient {

    static int first_person_id = 0;
    static int last_person_id = 0;
    static int new_person_id = 0;
    static int mid = 0;
    static int new_mid = 0;
    static int count=0;
    static int newcount=0;

    public static void main(String[] args) throws Exception {
	try {
	    URL url = new URL("http://127.0.1.1:6902/ws/people?wsdl");
        //1st argument service URI, refer to wsdl document above
	    //2nd argument is service name, refer to wsdl document above
	    QName qname = new QName("http://ws.soap.assignment.introsde/", "PeopleService");
	    Service service = Service.create(url, qname);
	    People pService = service.getPort(People.class);

	//Read person list 
	    //Method #1: readPersonList()
	    System.out.println("Method #1: readPersonList()");
	    System.out.println("Parameters: None.");
	    System.out.println("Description: Read all the people in our database and save their number\n ");
	    List<Pason> pList = pService.getPeople();
	    //if the list of people is empty
	    if (pList!= null) {		
	    count = pList.size();
		//if the list of people has few people
		if (count > 3) {
		    System.out.println("There are "+count + " people in our database");
	    System.out.println("Save Ids of first and last person");
		    first_person_id = Integer.parseInt(pList.get(0).getPersonId());
		    last_person_id = Integer.parseInt(pList.get(pList.size()-1).getPersonId());
		    
		    Peoples people = new Peoples();
		    people.setPeopleList(pList);
		    printOutput(people, Peoples.class);

		    //Method #2: readPerson(Long id)
		    System.out.println("Method #2: readPerson(Long id)");
		    System.out.println("Parameters: id of person");
		    System.out.println("Description: Prints first person");
		    Pason p = pService.readPerson(first_person_id);
		    printOutput(p, Pason.class);
		    
		    
		    //Method #3: updatePerson(Person p)
		    System.out.println("Method #3: updatePerson(Person p)");
		    System.out.println("Parameters: Person object with parameters to update eg first or last name");
		    System.out.println("Description: Updates the first person's name to 'Pavel'");
		    Person pp = new Person();
		    pp.setFirstname("Pavel");
		    printOutput(pService.updatePerson(pp, first_person_id), Peoples.class);
		    
		    
		    //Method #4: createPerson(Person p)
		    System.out.println("Method #4: createPerson(Person p)");
		    System.out.println("Parameters: New person object with first and last name, birthdate and healthprofile");
		    System.out.println("Description: Creates a person with name 'Yoweri' 'Museveni' saves "
			    + "the id of new person and counts the people");
		    Person p4 = new Person();
		    HealthProfilez hp4 = new HealthProfilez();
		    hp4.setBloodpressure("67.9");
		    hp4.setHeight("2.6");
		    hp4.setWeight("98.5");
		    p4.setFirstname("Yoweri");
		    p4.setLastname("Museveni");
		    p4.setBirthdate("18/03/1960");
		    Helthprofile hp = new Helthprofile();
		    hp.setValue("67.9");
		    Pason psn = pService.addPerson(p4,hp);
		    new_person_id = Integer.parseInt(psn.getPersonId());
		    printOutput(psn, Pason.class);
		    
		    List<Pason>ppnn = pService.getPeople();
		    newcount = ppnn.size();		    
		    System.out.println("************* New people count is "+newcount + " after insertion *************");
		    Peoples ppn = new Peoples();
		    ppn.setPeopleList(ppnn);
		    printOutput(ppn,Peoples.class);
		    
		//Method #5: deletePerson(Long id)
		    System.out.println("Method #5: deletePerson(Long id)");
		    System.out.println("Parameters: id of person to delete");
		    System.out.println("Description: Deletes the newly created person");
		    List<Pason>ppl = pService.deletePerson(new_person_id);
		    Peoples ppd = new Peoples();
		    ppd.setPeopleList(ppl);
		    printOutput(ppd,Peoples.class);
		    
		    newcount = pService.getPeople().size();
		    System.out.println("************** New count is "+newcount + " after deleting *************\n");
		    
		    
		    //Method #6: readPersonHistory(Long id, String measureType) 
		    System.out.println("Method #6: readPersonHistory(Long id, String measureType)");
		    System.out.println("Parameters: id of person and measuretype string eg 'height';\n NB: the measuretype should be in our database");
		    System.out.println("Description: Reads height history of last person and saves id of last height measure");
		    List<Measure>ml = pService.readPersonHistory(first_person_id, "height");
		    for(Measure mm:ml){
		mid = Integer.parseInt(mm.getTypeId());
		    printOutput(mm, Measure.class);
		    }
		    
		    
		    //Method #7: readMeasureTypes()
		    System.out.println("Method #7: readMeasureTypes()");
		    System.out.println("Parameters: None");
		    System.out.println("Description: Reads the Measuretypes in our database");
		    List<MeasureTyp> mt = pService.readMeasureTypes();
		    MeasureType mtt = new MeasureType();
		    mtt.setMeasure(mt);
		    printOutput(mtt, MeasureType.class);
		    

		    //Method #8: readPersonMeasure(Long id, String measureType, Long mid) 
		    System.out.println("Method #8: readPersonMeasure(Long id, String measure, Long mid)");
		    System.out.println("Parameters: id of person, measuretype string eg 'weight' and id of measuretype in database");
		    System.out.println("Description: Prints the height of the first person with the measure id we saved in step 6 above");
		    printOutput(pService.readPersonMeasure(first_person_id, "height", mid), Measure.class);
		    

                  //Method #9: savePersonMeasure(Long id, Measure m)
		    System.out.println("Method #9: savePersonMeasure(Long id,String measuretype, Measure m)");
		    System.out.println("Parameters: id of person,measuretype string, object of measuretype with the value");
		    System.out.println("Description: Saves a new height of 20 for the first person, we keep the new measure id");
		    Measure m = new Measure();
		    m.setValue("20");
		    m.setDatechanged(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		   List<Measure> nm = pService.savePersonMeasure(first_person_id, "height", m);
		   new_mid = Integer.parseInt(nm.get(nm.size()-1).getTypeId());
		    printOutput(nm.get(nm.size()-1), Measure.class);
		    

		//Method #10: updatePersonMeasure(Long id, Measure m)
		    System.out.println("Method #10: updatePersonMeasure(Long id, Measure m, measure_object, measure_id)");
		    System.out.println("Parameters: id of person,measuretype string, object of measuretype with the value and the measure id");
		    System.out.println("Description: Updates the newly created height to 30 using its measure id saved in method 9");
		    Measure mm = new Measure();
		    mm.setValue("30");
		    Measure nmm = pService.updatePersonHP(first_person_id, "height", mm,new_mid);
		    printOutput(nmm, Measure.class);
		
		} else {
		    System.out.println("Too few values, cant proceed!!");
		}
	    } else {
		System.out.println("No values in the database!!");
	    }
	} catch (Exception ex) {
	    ex.printStackTrace();
	}
    }

    public static void printOutput(Object ob, Class c) {
	try {
	    System.out.println("\n");
	    JAXBContext jaxbContext = JAXBContext.newInstance(c);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	    jaxbMarshaller.marshal(ob, System.out);
	    System.out.println("\n================================================\n");
	} catch (Exception ex) {
	}
    }

}

package introsde.assignment.soap.ws;

import introsde.assignment.soap.dao.mtds.HealthMtd;
import introsde.assignment.soap.dao.mtds.PersonMtd;
import introsde.assignment.soap.dao.mtds.TypeMtd;
import introsde.assignment.soap.model.Helthprofile;
import introsde.assignment.soap.model.Person;
import introsde.assignment.soap.model.Type;
import introsde.assignment.soap.utils.Measure;
import introsde.assignment.soap.utils.MeasureTyp;
import introsde.assignment.soap.utils.Pason;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import javax.jws.WebService;

//Service Implementation

@WebService(endpointInterface = "introsde.assignment.soap.ws.People",
    serviceName="PeopleService")
public class PeopleImpl implements People {

    @Override
    public Pason readPerson(int id) {
        System.out.println("---> Reading Person by id = "+id);
        Pason p = PersonMtd.returnPersonById(id);
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p.getFirstname());
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
        return p;
    }

    @Override
    public List<Pason> getPeople() {
        return PersonMtd.getAll();
    }

//    @Override
//    public Pason addPerson(Pason p) {
//        Person pp = new Person();
//		try {
//		   
//pp.setFirstname(p.getFirstname());
//pp.setLastname(p.getLastname());
//pp.setBirthdate(p.getBirthdate());
//PersonMtd.savePerson(pp);
//
//Helthprofile hp = new Helthprofile();
//Date det = new Date();
//hp.setPid(pp);
//hp.setDatecreated(new SimpleDateFormat("yyyy-MM-dd").format(det));
//Type t=null;
//if(p.getHProfile().getWeight()!=null){
//    System.out.println("wei "+ p.getHProfile().getWeight());
//t = TypeMtd.getTypeByString("weight").get(0);
//hp.setTid(t);
//hp.setValue(String.valueOf(p.getHProfile().getWeight()));
//    HealthMtd.saveHealthprofile(hp);
//		}
//    if(p.getHProfile().getHeight()!=null){
//	System.out.println("hei "+ p.getHProfile().getHeight());
//t = TypeMtd.getTypeByString("height").get(0);
//hp.setTid(t);
//hp.setValue(String.valueOf(p.getHProfile().getHeight()));
//hp.setHid(null);
//		
//    HealthMtd.saveHealthprofile(hp);}
//if(p.getHProfile().getBloodpressure()!=null) {   
//    System.out.println("blo "+ p.getHProfile().getBloodpressure());
//t = TypeMtd.getTypeByString("bloodpressure").get(0);
//hp.setTid(t);
//hp.setValue(String.valueOf(p.getHProfile().getBloodpressure()));
//hp.setHid(null);
//    HealthMtd.saveHealthprofile(hp);
//}
//	    } catch (Exception ex) {
//	   ex.printStackTrace();
//	    } 
// 
//	    return PersonMtd.returnPersonById(pp.getPid());
//}
//
//
//    @Override
//    public Pason updatePerson(Pason person,int pid) {
//     List<Person> plist = PersonMtd.getPersonById(pid);
//      if(plist!=null){
//      Person p = plist.get(0);
//      p.setPid(pid);
//      if(!"".equals(person.getFirstname()))
//      p.setFirstname(person.getFirstname());
//      else p.setFirstname(p.getFirstname());
//      
//      if(!"".equals(person.getLastname()))
//      p.setLastname(person.getLastname());
//      else p.setLastname(p.getLastname());
//      
//      if(!"".equals(person.getBirthdate()))
//      p.setBirthdate(person.getBirthdate());
//      else p.setBirthdate(p.getBirthdate());
//      PersonMtd.updatePerson(p);
//      }
//
//      return PersonMtd.returnPersonById(pid);
//    }
//
    @Override
    public List<Pason> deletePerson(int id) {
		System.out.println("deleting person with id " + id );            
      List<Person> plist = PersonMtd.getPersonById(id);
      if(plist!=null){
      Person p = plist.get(0);
	PersonMtd.removePerson(p);
      }
      return PersonMtd.getAll();
    }

  
    @Override
    public List<Measure> readPersonHistory(int id, String measure) {
	return PersonMtd.getPersonMeasure(id, measure);
    }

    @Override
    public List<MeasureTyp> readMeasureTypes() {
	 System.out.println("Getting list of measuretypes...");
        return TypeMtd.getMeasuretypes();
    }

    @Override
    public Measure readPersonMeasure(int id, String measure, int mid) {
	
 return PersonMtd.getPersonMeasureId(id, measure, mid);
    }

    @Override
    public List<Measure> savePersonMeasure(int id, String measure,Measure m) {
List<Helthprofile> list = PersonMtd.getHealthprof(id);
    Helthprofile hp = new Helthprofile();
    Date det = new Date();
    if(list!=null){
	for(Helthprofile hp1:list){
	if(hp1.getTid().getType().equals(measure)){
	    hp.setValue(m.getValue());
	    hp.setPid(hp1.getPid());
	    hp.setTid(hp1.getTid());
	    hp.setDatecreated(new SimpleDateFormat("yyyy-MM-dd").format(det));
	    }
    }
	HealthMtd.saveHealthprofile(hp);
    }
 return PersonMtd.getPersonMeasure(id, measure);
    }

    @Override
    public Measure updatePersonHP(int id, String measure, Measure m, int mid) {
	if(!HealthMtd.getByPidHid(mid, id).isEmpty()){
	List<Helthprofile> hp = HealthMtd.getByPidHid(mid, id);
	Helthprofile h = hp.get(0);
	h.setDatecreated(h.getDatecreated());
	h.setHid(h.getHid());
	h.setPid(h.getPid());
	h.setTid(h.getTid());
	h.setValue(m.getValue());
	
	HealthMtd.updateHealth(h);
    }
 return PersonMtd.getPersonMeasureId(id, measure, mid);
    }

    @Override
    public Pason addPerson(Person person, Helthprofile hp) {
	PersonMtd.savePerson(person);
	hp.setPid(person);
	hp.setHid(null);
	hp.setDatecreated(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
	Type t = new Type();
	t.setTid(1);
	hp.setTid(t);
	hp.setValue("28");
	HealthMtd.saveHealthprofile(hp);
	hp.setHid(null);
	t.setTid(2);
	hp.setTid(t);
	hp.setValue("98");
	HealthMtd.saveHealthprofile(hp);
	List<Pason> ps = PersonMtd.getAll();
	return ps.get(ps.size()-1);
    }

    @Override
    public Pason updatePerson(Person person, int id) {
	System.out.println("hhh "+ person.getBirthdate() + person.getLastname()+person.getFirstname());
	List<Person> plist = PersonMtd.getPersonById(id);
      if(plist!=null){
      Person p = plist.get(0);
      p.setPid(id);
      if(!"".equals(person.getFirstname()))
      p.setFirstname(person.getFirstname());
      else p.setFirstname(p.getFirstname());
      p.setBirthdate(p.getBirthdate());
      p.setLastname(p.getLastname());
      
      if(!"".equals(person.getLastname()))
      p.setLastname(person.getLastname());
      else p.setLastname(p.getLastname());
      
      
      if(!"".equals(person.getBirthdate()))
      p.setBirthdate(person.getBirthdate());
      else p.setBirthdate(p.getBirthdate());
     // PersonMtd.updatePerson(p);
      }

      return PersonMtd.returnPersonById(id);
	}

 

    
}
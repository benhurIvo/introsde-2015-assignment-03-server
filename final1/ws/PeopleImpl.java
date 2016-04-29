package final1.ws;


import final1.domain.Goal;
import final1.domain.Healthprofile;
import final1.domain.Person;
import final1.domain.Type;
import final1.mtds.GoalMtd;
import final1.mtds.HealthMtd;
import final1.mtds.PersonMtd;
import final1.mtds.TypeMtd;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;

import javax.jws.WebService;

//Service Implementation

@WebService(endpointInterface = "final1.ws.People",
    serviceName="PeopleService")
public class PeopleImpl implements People {

    @Override
    public Person readPerson(int id) {
        System.out.println("---> Reading Person by id = "+id);
        Person p = PersonMtd.returnPersonById(id);
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p.getFirstname());
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
        return p;
    }

    @Override
    public List<Person> getPeople() {
        return PersonMtd.getAll();
    }

    @Override
    public List<Person> deletePerson(int id) {
		System.out.println("deleting person with id " + id );            
      List<Person> plist = PersonMtd.getPersonById(id);
      if(plist!=null){
      Person p = plist.get(0);
	PersonMtd.removePerson(p);
      }
      return PersonMtd.getAll();
    }

  
    @Override
    public List<Healthprofile> readPersonHistory(int id, String measure) {
	return PersonMtd.getPersonMeasure(id, measure);
    }

    @Override
    public List<Type> readMeasureTypes() {
	 System.out.println("Getting list of measuretypes...");
        return TypeMtd.getAll();
    }

    @Override
    public Healthprofile readPersonMeasure(int id, String measure, int mid) {
	
 return PersonMtd.getPersonMeasureId(id, measure, mid);
    }

    @Override
    public List<Healthprofile> savePersonMeasure(int id, String measure,String m) {
List<Healthprofile> list = PersonMtd.getHealthprof(id);
    Healthprofile hp = new Healthprofile();
    Date det = new Date();
    if(list!=null){
	for(Healthprofile hp1:list){
	if(hp1.getTid().getType().equals(measure)){
	    hp.setValue(m);
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
    public Healthprofile updatePersonHP(int id, String measure, int m, int mid) {
	if(!HealthMtd.getByPidHid(mid, id).isEmpty()){
	List<Healthprofile> hp = HealthMtd.getByPidHid(mid, id);
	Healthprofile h = hp.get(0);
	h.setDatecreated(h.getDatecreated());
	h.setHid(h.getHid());
	h.setPid(h.getPid());
	h.setTid(h.getTid());
	h.setValue(String.valueOf(m));
	
	HealthMtd.updateHealth(h);
    }
 return PersonMtd.getPersonMeasureId(id, measure, mid);
    }

    @Override
    public Person addPerson(Person person, Healthprofile hp) {
	PersonMtd.savePerson(person);	
	HealthMtd.saveHealthprofile(hp);
	List<Person> ps = PersonMtd.getAll();
	return ps.get(ps.size()-1);
    }

    @Override
    public Person updatePerson(Person person, int id) {
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
      
      if(!"".equals(person.getUsertype()))
      p.setBirthdate(person.getUsertype());
      else p.setBirthdate(p.getUsertype());
      
      if(!"".equals(person.getUname()))
      p.setBirthdate(person.getUname());
      else p.setBirthdate(p.getUname());
      
      if(!"".equals(person.getPassword()))
      p.setBirthdate(person.getPassword());
      else p.setBirthdate(p.getPassword());
      PersonMtd.updatePerson(p);
      }

      return PersonMtd.returnPersonById(id);
	}

    @Override
    public List<Goal> getGoals() {
	return GoalMtd.getAll();
    }

    @Override
    public Goal addGoal(Person person, Type t, String g) {
	Goal gg = new Goal();
	gg.setPid(person);
	gg.setTid(t);
	GoalMtd.saveGoal(gg);
	return gg;
    }

    @Override
    public Goal updateGoal(Goal g) {
	GoalMtd.updateGoal(g);
	return g;
    }

    @Override
    public List<Goal> deleteGoal(int gid) {
	GoalMtd.removeGoal(GoalMtd.getGoalById(gid));
	return GoalMtd.getAll();
    }

    @Override
    public Type addType(Type t) {
	return TypeMtd.saveType(t);
    }

    @Override
    public Type updateType(Type t) {
	return TypeMtd.updateType(t);
    }

    @Override
    public List<Type> deleteType(Type t) {
	 TypeMtd.removeType(t);
	 return TypeMtd.getAll();
    }

 

    
}
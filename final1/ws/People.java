package final1.ws;

import final1.domain.Goal;
import final1.domain.Healthprofile;
import final1.domain.Person;
import final1.domain.Type;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface People {
    @WebMethod(operationName="readPerson")
//    @WebResult(name="person") 
    public Person readPerson(@WebParam(name="personId") int id);
 
    @WebMethod(operationName="getPeopleList")
//    @WebResult(name="person") 
    public List<Person> getPeople();
 
    @WebMethod(operationName="createPerson")
//    @WebResult(name="person") 
    public Person addPerson(@WebParam(name="person") Person person,@WebParam(name="healthp") Healthprofile hp);
 
    @WebMethod(operationName="updatePerson")
//    @WebResult(name="person") 
    public Person updatePerson(@WebParam(name="person") Person person,@WebParam(name="personId") int id);
    
    @WebMethod(operationName="deletePerson")
//    @WebResult(name="person") 
    public List<Person> deletePerson(@WebParam(name="personId") int pid);
    
    @WebMethod(operationName="readPersonHistory")
//    @WebResult(name="measure") 
    public List<Healthprofile> readPersonHistory(@WebParam(name="personId") int id, @WebParam(name="measure") String measure);

    @WebMethod(operationName="readMeasureTypes")
//    @WebResult(name="measuretype") 
    public List<Type> readMeasureTypes();

    @WebMethod(operationName="readPersonMeasure")
//    @WebResult(name="measure") 
    public Healthprofile readPersonMeasure(@WebParam(name="personId") int id, @WebParam(name="measure") String measure, @WebParam(name="mid")int mid);

    @WebMethod(operationName="savePersonMeasure")
//    @WebResult(name="measure") 
    public List<Healthprofile> savePersonMeasure(@WebParam(name="personId") int id, @WebParam(name="measure") String measure,@WebParam(name="value") String m);

    @WebMethod(operationName="updatePersonHealthProfile")
//    @WebResult(name="measure") 
    public Healthprofile updatePersonHP(@WebParam(name="personId") int id, @WebParam(name="measure") String measure,@WebParam(name="MeasureObj") int m,@WebParam(name="mid")int mid);


    @WebMethod(operationName="getGoalList")
//    @WebResult(name="person") 
    public List<Goal> getGoals();
 
    @WebMethod(operationName="createGoal")
//    @WebResult(name="person") 
    public Goal addGoal(@WebParam(name="person") Person person,@WebParam(name="tid") Type t,@WebParam(name="goal") String g);
 
    @WebMethod(operationName="updateGoal")
//    @WebResult(name="person") 
    public Goal updateGoal(@WebParam(name="goal") Goal g);
    
    @WebMethod(operationName="deleteGoal")
//    @WebResult(name="person") 
    public List<Goal> deleteGoal(@WebParam(name="GoalId") int gid);
    
 @WebMethod(operationName="createType")
//    @WebResult(name="person") 
    public Type addType(@WebParam(name="Type") Type t);
 
    @WebMethod(operationName="updateType")
//    @WebResult(name="person") 
    public Type updateType(@WebParam(name="Type") Type t);
    
    @WebMethod(operationName="deleteType")
//    @WebResult(name="person") 
    public List<Type> deleteType(@WebParam(name="Type") Type t);   
}
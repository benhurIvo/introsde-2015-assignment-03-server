package introsde.assignment.soap.ws;
import introsde.assignment.soap.model.Helthprofile;
import introsde.assignment.soap.model.Person;
import introsde.assignment.soap.utils.Measure;
import introsde.assignment.soap.utils.MeasureTyp;
import introsde.assignment.soap.utils.Pason;

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
    public Pason readPerson(@WebParam(name="personId") int id);
 
    @WebMethod(operationName="getPeopleList")
//    @WebResult(name="person") 
    public List<Pason> getPeople();
 
    @WebMethod(operationName="createPerson")
//    @WebResult(name="person") 
    public Pason addPerson(@WebParam(name="person") Person person,@WebParam(name="healthp") Helthprofile hp);
 
    @WebMethod(operationName="updatePerson")
//    @WebResult(name="person") 
    public Pason updatePerson(@WebParam(name="person") Person person,@WebParam(name="personId") int id);
    
    @WebMethod(operationName="deletePerson")
//    @WebResult(name="person") 
    public List<Pason> deletePerson(@WebParam(name="personId") int pid);
    
    @WebMethod(operationName="readPersonHistory")
//    @WebResult(name="measure") 
    public List<Measure> readPersonHistory(@WebParam(name="personId") int id, @WebParam(name="measure") String measure);

    @WebMethod(operationName="readMeasureTypes")
//    @WebResult(name="measuretype") 
    public List<MeasureTyp> readMeasureTypes();

    @WebMethod(operationName="readPersonMeasure")
//    @WebResult(name="measure") 
    public Measure readPersonMeasure(@WebParam(name="personId") int id, @WebParam(name="measure") String measure, @WebParam(name="mid")int mid);

    @WebMethod(operationName="savePersonMeasure")
//    @WebResult(name="measure") 
    public List<Measure> savePersonMeasure(@WebParam(name="personId") int id, @WebParam(name="measure") String measure,@WebParam(name="MeasureObj") Measure m);

    @WebMethod(operationName="updatePersonHealthProfile")
//    @WebResult(name="measure") 
    public Measure updatePersonHP(@WebParam(name="personId") int id, @WebParam(name="measure") String measure,@WebParam(name="MeasureObj") Measure m,@WebParam(name="mid")int mid);
}
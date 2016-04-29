/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final1;

import com.sun.jndi.toolkit.url.Uri;
import final1.domain.Goal;
import final1.domain.Healthprofile;
import final1.domain.Person;
import final1.domain.Type;
import final1.mtds.GoalMtd;
import final1.mtds.HealthMtd;
import final1.mtds.PersonMtd;
import final1.mtds.TypeMtd;
import final1.ws.PeopleImpl;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author benhur
 */
public class Final1 {

    /**
     * @param args the command line arguments
     */
     private final static String CLIENT_ID = "d2e8c62e82a24439868579320bcb06a9";
    private final static String CLIENT_SECRET = "dccb69d503c44772886cabcc075e740a";
    private final static String CALLBACK_URL = "com.example.runkeeperapi://RunKeeperIsCallingBack";
    public static void main(String[] args) throws URISyntaxException {
	// TODO code application logic here
//	Person p = new Person();
//	p.setLastname("kay");
//	p.setFirstname("ivo");
//	p.setPassword("hhhhh");
//	p.setUname("root");
//	p.setBirthdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//	p.setUsertype("Admin");
//	//PersonMtd.savePerson(p);
////	
//	Type T = new Type();
//	T.setMeasure("kgs");
//	T.setType("weight");
//	TypeMtd.saveType(T);
//	
//	Healthprofile hp = new Healthprofile();
//	hp.setPid(p);
//	hp.setTid(T);
//	hp.setValue("65");
//	hp.setDatecreated(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
//	//HealthMtd.saveHealthprofile(hp);
//	PeopleImpl pl = new PeopleImpl();
//	pl.addPerson(p, hp);
//	
//	Goal g = new Goal();
//	g.setGoal("25");
//	g.setPid(p);
//	g.setTid(T);
//	GoalMtd.saveGoal(g);
	
	
	//System.out.println("here "+TypeMtd.getAll());;
//	try{
//	getTotalDistance("978c2ed2267e4313983b4950db494a0f");
//	}catch(Exception ex){
//	ex.printStackTrace();
//	}
    }

    
//    private static void getTotalDistance(String accessToken) {        
//        try {
//            HttpClient client = new DefaultHttpClient();
//            HttpGet get = new HttpGet("http://api.runkeeper.com/records");
//            
//            get.addHeader("Authorization", "Bearer " + accessToken);
//            get.addHeader("Accept", "*/*");
//            
//            HttpResponse response = client.execute(get);
//            
//            String jsonString = EntityUtils.toString(response.getEntity());
//            JSONArray jsonArray = new JSONArray(jsonString);
//	    System.out.println("data "+jsonString);
//            findTotalWalkingDistance(jsonArray);
//
//        } catch (Exception e) {
//            System.err.println("Exception occured:(");
//            e.printStackTrace();
//            //resetUi();
//        }
//    }
    
        private static void findTotalWalkingDistance(JSONArray arrayOfRecords) {
        try {
            //Each record has activity_type and array of statistics. Traverse to  activity_type = Walking
            for (int ii = 0; ii < arrayOfRecords.length(); ii++) {
                JSONObject statObject = (JSONObject) arrayOfRecords.get(ii);
                if ("Walking".equalsIgnoreCase(statObject.getString("activity_type"))) {
                    //Each activity_type has array of stats, navigate to "Overall" statistic to find the total distance walked.
                    JSONArray walkingStats = statObject.getJSONArray("stats");
                    for (int jj = 0; jj < walkingStats.length(); jj++) {
                        JSONObject iWalkingStat = (JSONObject) walkingStats.get(jj);
                        if ("Overall".equalsIgnoreCase(iWalkingStat.getString("stat_type"))) {
                            long totalWalkingDistanceMeters = iWalkingStat.getLong("value");
                            double totalWalkingDistanceMiles = totalWalkingDistanceMeters * 0.00062137;
                            //displayTotalWalkingDistance(totalWalkingDistanceMiles);
			    System.out.println("dist "+totalWalkingDistanceMeters);
                            return;
                        }
                    }
                }
            }
           // displayToast("Something went wrong!!!");
        } catch (JSONException e) {
           // displayToast("Exception occured:(");            
            e.printStackTrace();
          //  resetUi();
        }
    }
}

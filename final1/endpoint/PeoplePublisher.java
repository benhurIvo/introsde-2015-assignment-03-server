/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package final1.endpoint;

/**
 *
 * @author benhur
 */
import final1.ws.PeopleImpl;
import javax.xml.ws.Endpoint;

public class PeoplePublisher {
    public static String SERVER_URL = "http://localhost";
    public static String PORT = "6902";
    public static String BASE_URL = "/ws/people";

    public static String getEndpointURL() {
        return SERVER_URL+":"+PORT+BASE_URL;
    }

    public static void main(String[] args) {
        String endpointUrl = getEndpointURL();
        System.out.println("Starting People Service...");
        System.out.println("--> Published at = "+endpointUrl);
        Endpoint.publish(endpointUrl, new PeopleImpl());
    }
}

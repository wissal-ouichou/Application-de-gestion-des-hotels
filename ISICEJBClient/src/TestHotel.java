import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import dao.IDaoRemote;
import entities.Hotel;
import entities.Ville;
import dao.IDaoRemote;
import entities.Hotel;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class TestHotel {
    public static IDaoRemote<Hotel> lookUpHotelRemote() throws NamingException {
        final Hashtable<String, String> jndiProperties = new Hashtable<>();
        jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8084");
        final Context context = new InitialContext(jndiProperties);

        return (IDaoRemote<Hotel>) context.lookup("ejb:ISICEJBEAR/ISICEJBServer/wissal!dao.IDaoRemote");
    }

    public static void main(String[] args) {
        try {
            IDaoRemote<Hotel>  hotel = lookUpHotelRemote();
    
            Ville ville1 = new Ville("El Jadida");
            Ville ville2 = new Ville("Marrakech");
            Ville ville3 = new Ville("El Jadida");
         hotel.create(new Hotel("Ibis", "El Jadida", "05675648", ville1 ));
         hotel.create(new Hotel("Saada", "El Jadida", "05675648", ville2));
         hotel.create(new Hotel("Mamounia", "Marrakech-Gueliz", "05675648", ville3));

            for (Hotel h : hotel.findAll()) {
                System.out.println(h.getNom());
            }
        } catch (NamingException e) {
            // Handle naming exception appropriately (log, throw, etc.)
            e.printStackTrace();
        } 
    }
}


package com.zalupa.demo.Bean;
/*
import com.zalupa.demo.models.Client;
//import com.zalupa.demo.models.Track;
//import com.zalupa.demo.models.Tracklist;



import javax.persistence.*;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;


public class ClientEJB {

    public EntityManager entityManager = Persistence.createEntityManagerFactory("UNIT").createEntityManager();

   // private HttpSession session;

   /* public HttpSession getSession() {
        return session;
    }



public ClientEJB(){}

   /* public Client getUser() {
        if (session != null) {
            return (Client) session.getAttribute("user");
        } else return null;
    }

    public void setUser(Client user) {
        session.setAttribute("user", user);
    }

    public Client validateUserLogin(String login, String password) {
        TypedQuery<Client> query = entityManager.createNamedQuery("getClient", Client.class)
                .setParameter("client_login", login)
                .setParameter("client_password", password);
        Client user = new Client();
        try {
            user = query.getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
        catch (NoSuchElementException e){
            return null;
        }
       // setUser(user);
        return user;

    }
/*
    public List<Tracklist> selectLists(){
        Client user = (Client) session.getAttribute("user");
        List<Tracklist> tracklists = null;
        try {

            TypedQuery<Date> tracklistIdsQuery = entityManager.createNamedQuery("getTracklists", Date.class)
                    .setParameter("TRACKLIST_CLIENT_ID", user.getId());
            List<Date> usersTracklistIds = tracklistIdsQuery.getResultList();
            tracklists = new LinkedList<>();
            for (Date tracklistId : usersTracklistIds) {
                TypedQuery<Track> trackListQuery = entityManager.createNamedQuery("getTracks", Track.class)
                        .setParameter(1, tracklistId);
                List<Track> trackList = trackListQuery.getResultList();
                Tracklist tracklist = new Tracklist((Timestamp) tracklistId, trackList);
                tracklists.add(tracklist);
            }
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }

        return tracklists;
    }

    public void addTrack(Track track, Timestamp tracklistId) {
        entityManager.getTransaction().begin();
        try {
            entityManager.createNamedQuery("insertTrack").setParameter(1, track.getName())
                    .setParameter(2, track.getSize())
                    .setParameter(3, track.getDuration())
                    .executeUpdate();
            entityManager.createNativeQuery("INSERT INTO connector2 (CONNECTOR2_TRACKLIST_TIMEID, CONNECTOR2_TRACK_NAME) VALUES (?, ?)")
                    .setParameter(1, tracklistId)
                    .setParameter(2, track.getName())
                    .executeUpdate();
            entityManager.getTransaction().commit();
        } catch (PersistenceException e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }
    }







    public String getUserDataXMLRepresentation() {
        Client user = getUser();
        try {
            user.setTracklists(selectLists());
            StringWriter writer = new StringWriter();
            JAXBContext context = JAXBContext.newInstance(Client.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(getUser(), writer);
            return writer.toString();
        } catch (JAXBException e) {
            e.printStackTrace();
            return "";
        }

    }
}
*/
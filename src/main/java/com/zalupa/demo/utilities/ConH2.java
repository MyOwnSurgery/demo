package com.zalupa.demo.utilities;

import java.io.IOException;
import java.sql.Connection;
import java.sql.*;

public class ConH2 {
    /**
     *
     * @throws SQLException
     * @throws IOException
     */
  /*  public static void insertTracklist(Client client, Tracklist tracklist)
            throws SQLException, ClassNotFoundException {
        Connection con2 = connect();
        con2.setAutoCommit(false);
        String query = "SELECT MAX(CONID) FROM CONNECTOR"; // ����� ����� � ������� ��������� (����� ���� �� �������),
        // ����� �� ���������� ������ �������� ������ ������
        PreparedStatement pst = con2.prepareStatement(query);
        ResultSet res = pst.executeQuery();
        int i;
        if (res.next()) {
            i = res.getInt("MAX(CONID)") + 1;
        } else
            i = 0;
        Timestamp id = tracklist.getId();
        query = "INSERT INTO TRACKLIST VALUES(?);";
        pst = con2.prepareStatement(query);
        pst.setTimestamp(1, id);
        pst.executeUpdate();
        long cid = client.getID();
        query = "INSERT INTO CONNECTOR VALUES(?,?,?);";
        pst = con2.prepareStatement(query);
        pst.setInt(1, (int) i);
        pst.setInt(2, (int) cid);
        pst.setTimestamp(3, id);
        pst.executeUpdate();
        for (i = 0; i < tracklist.length(); i++) {
            insertTrack(tracklist.getTrack(i), tracklist.getId());
        }
        con2.commit();
        con2.setAutoCommit(true);
    }

    public static void insertClient(Client client, Connection con, Statement stmt) throws SQLException, ClassNotFoundException {
        int id = client.getID();
        String login = client.getLogin();
        String name = client.getName();
        String password = client.getPassword();
        String query = "INSERT INTO Client VALUES(?,?,?,?);";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setInt(1, (int) id);
        pst.setString(2, login);
        pst.setString(3, name);
        pst.setString(4, password);
        pst.executeUpdate();
        for (int i = 0; i < client.GetSize(); i++) {
            insertTracklist(client, client.GetList(i));
        }

    }

    public static void insertTrack(Track track, Timestamp tracklistId)
            throws SQLException, ClassNotFoundException {
        Connection con2 = connect();
        con2.setAutoCommit(false);

        String query = "SELECT MAX(CONID) FROM CONNECTOR2"; // ����� ����� � ������� ���������2 (����� ���� �� �������),
        // ����� �� ���������� ������ �������� ������ ������
        PreparedStatement pst = con2.prepareStatement(query);
        ResultSet res = pst.executeQuery();
        int i;
        if (res.next()) {
            i = res.getInt("MAX(CONID)") + 1;
        } else
            i = 0;
        String trName = track.getName();
        long size = track.getSize();
        long duration = track.getDuration();
        query = "INSERT INTO TRACK VALUES(?,?,?,?);";
        pst = con2.prepareStatement(query);
        pst.setInt(1, i);
        pst.setString(2, trName);
        pst.setFloat(3, size);
        pst.setFloat(4, duration);
        pst.executeUpdate();
        query = "INSERT INTO CONNECTOR2 VALUES(?,?,?);";
        pst = con2.prepareStatement(query);
        pst.setInt(1, i);
        pst.setTimestamp(2, tracklistId);
        pst.setInt(3, i);
        pst.executeUpdate();
        con2.commit();
        con2.setAutoCommit(true);

    }
*/
    public static void Create(Connection con, Statement stmt) throws SQLException, IOException, ClassNotFoundException {
        con.setAutoCommit(false);
        stmt.executeUpdate("Create table Client (\r\n" + "CLIENT_ID INT(11) NOT NULL,\r\n" + "CLIENT_NAME VARCHAR(50) NOT NULL,\r\n"
                + "CLIENT_PASSWORD VARCHAR(50) NOT NULL,\r\n" + "CLIENT_LOGIN VARCHAR(50) NOT NULL,\r\n" + "PRIMARY KEY (CLIENT_ID)\r\n"
                + ");\r\n" + "Create table Tracklist (\r\n" + "TRACKLIST_TRACKLIST_ID INT(11) NOT NULL, \r\n" +
                "TRACKLIST_CLIENT_ID INT(11),\r\n" + "PRIMARY KEY (TRACKLIST_TRACKLIST_ID),\r\n" + "FOREIGN KEY (TRACKLIST_CLIENT_ID) REFERENCES CLIENT(CLIENT_ID)"
                + ");\r\n" + "Create table Track (\r\n" + "TRACK_TRACK_ID INT(11) AUTO_INCREMENT, \r\n"+ "TRACK_TRACKLIST_ID INT(11), \r\n"+"TRACK_TRACK_NAME VARCHAR(50) NOT NULL,\r\n"
                + "TRACK_TRACK_SIZE FLOAT(6),\r\n" + "TRACK_TRACK_DURATION FLOAT(6),\r\n" + "PRIMARY KEY (TRACK_TRACK_ID),\r\n" + "FOREIGN KEY (TRACK_TRACKLIST_ID) REFERENCES TRACKLIST(TRACKLIST_TRACKLIST_ID));");

        stmt.executeUpdate("INSERT INTO CLIENT VALUES (1,'user1','1','user1');\n" + "INSERT INTO CLIENT VALUES (2,'user2','2','user2');\n " + "INSERT INTO CLIENT VALUES (3,'user3','3','user3');\n");
        stmt.executeUpdate("INSERT INTO TRACKLIST VALUES (1,1);\n" + "INSERT INTO TRACKLIST VALUES (2,1);\n " + "INSERT INTO TRACKLIST VALUES (3,2);\n"+ "INSERT INTO TRACKLIST VALUES (4,2);\n"+ "INSERT INTO TRACKLIST VALUES (5,3);\n"+ "INSERT INTO TRACKLIST VALUES (6, 3);\n");
        stmt.executeUpdate("INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (1,'track1',100,100);\n" + "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (1,'track2',100,100);\n" + "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (1,'track3',100,100);\n" + "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION)  VALUES (1,'track4',100,100);\n" + "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (1,'track5',100,100);\n" + "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (2,'track6',100,100);\n"+"INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (2,'track7',100,100);\n" + "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (2,'track8',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (2,'track9',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (2,'track10',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (3,'track11',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (3,'track12',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (3,'track13',100,100);\n"
                + "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (3,'track14',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (3,'track15',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (4,'track16',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION)  VALUES (4,'track17',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (4,'track18',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (4,'track19',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (4,'track20',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (5,'track21',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (5,'track22',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (5,'track23',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (5,'track24',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (5,'track25',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (6,'track26',100,100);\n"
                + "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (6,'track27',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (6,'track28',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (6,'track29',100,100);\n"+ "INSERT INTO TRACK (TRACK_TRACKLIST_ID, TRACK_TRACK_NAME, TRACK_TRACK_SIZE, TRACK_TRACK_DURATION) VALUES (6,'track30',100,100);\n");

/*
		List<Tracklist> list = new ArrayList<Tracklist>();
		List<Client> list2 = new ArrayList<Client>();
		int j = 1;
		for (int i = 1; i <= 10; i++, j++) {
			Tracklist tr = new Tracklist(i);
			if (i > 4) {
				j = 1;
			}
			String path = String.format("C:\\Users\\�������\\Desktop\\%d.txt", j);
			tr.ReadFile(path);
			list.add(tr);
		}


		for (int i = 0; i < 5; i++) {
			String str = String.format("%d", i);
			Client cl = new Client(i, "a" + str, "a" + str, "a" + str);
			list2.add(cl);
		}
		j = 0;
		for (int i = 0; i < 10; i++, j++) {
			if (j > 4) {
				j = 0;
			}
			list2.get(j).AddList(list.get(i));
		}
		for (int i = 0; i < 5; i++) {
			insertClient(list2.get(i), con, stmt);
		}
*/
        con.commit();
        con.setAutoCommit(true);
    }
/*
    public static List<Tracklist> selectLists(Client client) throws SQLException, ClassNotFoundException {
        Connection con2 = connect();
        con2.setAutoCommit(false);
        int id = client.getID();
        List<Integer> list = new ArrayList<Integer>();
        List<Integer> list2 = new ArrayList<Integer>();
        List<Timestamp> list3 = new ArrayList<Timestamp>();
        String query = "SELECT CTRID FROM CONNECTOR WHERE CID = ?";
        PreparedStatement pst = con2.prepareStatement(query);
        pst.setInt(1, id);
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            list.add(res.getInt("CTRID"));
        }
        int n = list.size();
        for (int i = 0; i < n; i++){
            query = "SELECT TIMEID FROM TRACKLIST WHERE TRID = ?";
            pst = con2.prepareStatement(query);
            pst.setInt(1, list.get(i));
            res = pst.executeQuery();
            while (res.next()) {
                list3.add(res.getTimestamp("TIMEID"));
            }
        }
        n = list3.size();
        for (int i = 0; i < n; i++) {
            query = "SELECT TRID FROM CONNECTOR2 WHERE TIMEID = ?";
            pst = con2.prepareStatement(query);
            pst.setTimestamp(1, list3.get(i));
            res = pst.executeQuery();
            while (res.next()) {
                list2.add(res.getInt("TRID"));
            }
        }
        for (int i = 0; i < list3.size(); i++) {
            Tracklist tracklist = new Tracklist(list3.get(i));
            tracklist = selectItems(tracklist, con2);
            client.AddList(tracklist);
        }
        con2.commit();
        con2.setAutoCommit(true);

        return client.GetLists();
    }

    public static Tracklist selectItems(Tracklist tracklist, Connection con) throws SQLException {
        con.setAutoCommit(false);
        List<Integer> list = new ArrayList<Integer>();

        String query = "SELECT TRID FROM CONNECTOR2 WHERE TIMEID = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setTimestamp(1, tracklist.getId());
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            list.add(res.getInt("TRID"));
        }

        int n = list.size();
        for (int i = 0; i < n; i++) {
            query = "SELECT NAME,SIZE,DURATION FROM TRACK WHERE ID = ?";
            pst = con.prepareStatement(query);
            pst.setInt(1, list.get(i));
            res = pst.executeQuery();

            while (res.next()) {
                long size = (long) res.getInt("SIZE");
                long dur = (long) res.getInt("DURATION");
                Track track = new Track(res.getString("NAME"), size, dur);
                tracklist.addTrack(track);
            }
        }
        con.commit();
        con.setAutoCommit(true);
        return tracklist;

    }

    public static List<Track> sameSize(Track track, Connection con, Statement stmt) throws SQLException {
        con.setAutoCommit(false);
        List<Track> list = new ArrayList<Track>();
        long size = track.getSize();
        String query = "SELECT NAME,SIZE,DURATION FROM TRACK WHERE SIZE = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setFloat(1, size);
        ResultSet res = pst.executeQuery();
        while (res.next()) {
            size = (long) res.getInt("SIZE");
            long dur = (long) res.getInt("DURATION");
            Track trackTemp = new Track(res.getString("NAME"), size, dur);
            list.add(trackTemp);
        }
        con.commit();
        con.setAutoCommit(true);
        return list;
    }

    public static void updateClient(Client client, Connection con, Statement stmt) throws SQLException {
        String login = client.getLogin();
        String query = "UPDATE CLIENT SET LOGIN  = ? WHERE ID = 3";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, login);
        pst.executeUpdate();
    }

    public static void Delete(Tracklist tracklist, Connection con, Statement stmt) throws SQLException {
        con.setAutoCommit(false);
        Timestamp id = tracklist.getId();
        String query = "DELETE FROM CONNECTOR WHERE CTRID  = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setTimestamp(1, id);
        pst.executeUpdate();

        query = "DELETE FROM CONNECTOR2 WHERE TRCID = ?";
        pst = con.prepareStatement(query);
        pst.setTimestamp(1, id);
        pst.executeUpdate();

        query = "DELETE FROM Tracklist WHERE TRID = ?";
        pst = con.prepareStatement(query);
        pst.setTimestamp(1, id);
        pst.executeUpdate();
        con.commit();
        con.setAutoCommit(true);

    }

    public static boolean Check(Client client, Connection con, Statement stmt) throws SQLException {
        con.setAutoCommit(false);
        String login = client.getLogin();
        String password = client.getPassword();
        String query = "SELECT * FROM CLIENT WHERE LOGIN = ?  AND PASSWORD = ?";
        PreparedStatement pst = con.prepareStatement(query);
        pst.setString(1, login);
        pst.setString(2, password);
        ResultSet res = pst.executeQuery();
        con.commit();
        con.setAutoCommit(true);
        return res.next();
    }
    public static boolean checkExist(Client client) throws SQLException, ClassNotFoundException {
        Connection con2 = connect();
        con2.setAutoCommit(false);
        String login = client.getLogin();
        String password = client.getPassword();
        String query = "SELECT * FROM CLIENT WHERE LOGIN = ?  AND PASSWORD = ?";
        PreparedStatement pst = con2.prepareStatement(query);
        pst.setString(1, login);
        pst.setString(2, password);
        ResultSet res = pst.executeQuery();
        con2.commit();
        con2.setAutoCommit(true);
        return res.next();
    }
    public static  Client getClient(String login, String password) throws SQLException, ClassNotFoundException{
        Connection con2 = connect();
        con2.setAutoCommit(false);
        PreparedStatement statement = con2.prepareStatement("SELECT name, id FROM Client WHERE login = ? AND password = ?");
        statement.setString(1, login);
        statement.setString(2, password);
        ResultSet rst = statement.executeQuery();
        Client user = null;
        if (rst.next()) {
            String name = rst.getString(1);
            int id = rst.getInt(2);
            user = new Client(id, login, name, password);
        }
        return user;
    }
    public static Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "shodan", "11");
        return con;
    }
*/
    public static void main(String[] args) {
        try {
            Class.forName("org.h2.Driver");
            Connection con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "shodan", "11");
            Statement stmt = con.createStatement();
            Create(con, stmt); // 1 ������, �������� ������, ������ � ���������� �� �����


            // Client client = new Client(7,"shodan","Alex","qwerty");
            // insertClient(client,con,stmt); //2 ������ ���������� �������

            // Tracklist tracklist = new Tracklist(2);
            // Delete(tracklist,con,stmt); // 3 ������ �������� ���������

            //Client client3 = new Client(2,"a2","a2","a2");
            //Client client4 = new Client(2,"aad","df","adad");
            // boolean a = Check(client3,con,stmt); // 4 ������ �������� �������������
            // ������� � �������� ������� � �������
            // System.out.println(a);

            //Client client2 = new Client(0, "a0", "a0", "a0");
            //client2 = selectLists(client2); // 5 ������ ����� ����
            // ������� ������������� �������
            //System.out.println(client2.GetList(1)); //

            // Tracklist tracklist2 = new Tracklist(3);
            //tracklist2 = selectItems(tracklist2,con,stmt); // 6 ������ ����� ����
            // ��������� ��������� ������
            // System.out.println(tracklist2);

            // Track track = new Track("AAAAAAAA",211000L,22200L);
            // List<Track> result = sameSize(track,con,stmt); // 7 ������ ������ ������ �
            // ����� �� ��������, ��� � ���, ��� ��������
            // System.out.println(result);

            // Client client = new Client(1,"shodan","Alex","qwerty");
            // updateClient(client,con,stmt); // 8 ������ ��������� ������ �� ���, ��� �
            // ����������� �������

            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

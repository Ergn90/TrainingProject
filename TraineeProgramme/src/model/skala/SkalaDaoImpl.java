package model.skala;

import model.ConnectionFactory;

import java.sql.*;
import java.util.HashSet;
import java.util.Set;

public class SkalaDaoImpl implements SkalaDao {

    @Override
    public Skala getSkala(int id) {
        Connection connection = ConnectionFactory.getConnection();

        try {

            Statement query = connection.createStatement();
            ResultSet skal = query.executeQuery("Select * from skala where skalaid=" + id);

            if (skal.next()) {
                return extractSkalaFromSet(skal);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Skala extractSkalaFromSet(ResultSet skal) throws SQLException {

        Skala skala = new Skala();
        skala.setSkalaId(skal.getInt("skalaid"));
        skala.setSkalaName(skal.getString("skalaname"));
        return skala;

    }
    @Override
    public Set<Skala> getAllSkala() {

        Connection connection = ConnectionFactory.getConnection();

        try {

            Statement query = connection.createStatement();
            ResultSet skal = query.executeQuery("select * from skala");

            Set<Skala> skala = new HashSet<Skala>();

            while (skal.next()) {

                Skala singleSkala = extractSkalaFromSet(skal);
                skala.add(singleSkala);

            }

            return skala;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean insertSkala(Skala skala) {
        Connection connection = ConnectionFactory.getConnection();

        try {

            PreparedStatement statement = connection.prepareStatement("insert into skala (skalaname) values(?)");
            statement.setString(1 ,skala.getSkalaName());

            int i = statement.executeUpdate();

            if(i == 1){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean updateSkala(Skala skala) {
        Connection connection = ConnectionFactory.getConnection();

        try {

            PreparedStatement statement = connection.prepareStatement("Update skala set skalaname = ? where skalaid = ?");
            statement.setString(1, skala.getSkalaName());
            statement.setInt(2, skala.getSkalaId());

            int i = statement.executeUpdate();

            if (i == 1){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteSkala(int id) {
        Connection connection = ConnectionFactory.getConnection();

        try {

            Statement stmt = connection.createStatement();
            int i = stmt.executeUpdate("delete from skala where skalaid ="+id);

            if(i == 1){

                return true;

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return false;
    }
}

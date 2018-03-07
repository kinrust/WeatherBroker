package ru.kinzin.train.weatherbroker.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.kinzin.train.weatherbroker.app.HibernateUtil;
import ru.kinzin.train.weatherbroker.model.Weather;
import ru.kinzin.train.weatherbroker.model.WeatherPK;

import java.util.List;

@Repository
public class WeatherDAOImpl implements WeatherDAO {

    @Override
    public void addWeather(Weather weather) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        session.saveOrUpdate(weather);  //!

        session.getTransaction().commit();
    }

    @Override
    public Weather getWeatherById(WeatherPK weatherPK) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Weather weather = (Weather) session.find(Weather.class, weatherPK);

        return weather;
    }

    @Override
    public List<Weather> getAllWeathers() {
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Weather> weatherlist = (List<Weather>) session.createQuery("from Weather").list();

        return weatherlist;
    }
}

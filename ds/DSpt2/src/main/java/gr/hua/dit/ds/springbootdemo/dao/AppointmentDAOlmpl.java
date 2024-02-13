package gr.hua.dit.ds.springbootdemo.dao;

import gr.hua.dit.ds.springbootdemo.entity.Appointment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AppointmentDAOlmpl implements AppointmentDAO{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    @Transactional
    public List<Appointment> getAppointments() {
        TypedQuery<Appointment> query = entityManager.createQuery("from Appointment", Appointment.class);
        return query.getResultList();
    }

    @Override
    public Appointment getAppointment(Integer appointment_id) {
        return entityManager.find(Appointment.class, appointment_id);
    }

    @Override
    @Transactional
    public void saveAppointment(Appointment appointment) {
        System.out.println("appointment "+ appointment.getId());
        if (appointment.getId() == null) {
            entityManager.persist(appointment);
        } else {
            entityManager.merge(appointment);
        }
    }

    @Override
    @Transactional
    public void deleteAppointment(Integer appointment_id) {
        System.out.println("Deleting appointment with id: " + appointment_id);
        entityManager.remove(entityManager.find(Appointment.class, appointment_id));
    }

    public void showMessages(){

    }
}

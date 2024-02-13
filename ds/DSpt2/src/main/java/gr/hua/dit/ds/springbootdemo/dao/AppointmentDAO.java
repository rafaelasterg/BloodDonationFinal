package gr.hua.dit.ds.springbootdemo.dao;

import gr.hua.dit.ds.springbootdemo.entity.Appointment;

import java.util.List;

public interface AppointmentDAO {

    public List<Appointment> getAppointments();
    public Appointment getAppointment(Integer appointment_id);
    public void saveAppointment(Appointment appointment);
    public void deleteAppointment(Integer appointment_id);

}

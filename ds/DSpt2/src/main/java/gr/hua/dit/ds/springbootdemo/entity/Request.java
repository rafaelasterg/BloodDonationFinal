package gr.hua.dit.ds.springbootdemo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;

    @Column
    private String appointmentDate;

    @Column
    private String living_area;

    @Column
    private String blood_type;

    @Column
    private String last_blood_test;


    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name="request_appointment",
            joinColumns = @JoinColumn(name="request_id"),
            inverseJoinColumns = @JoinColumn(name="appointment_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames={"appointment_id", "request_id"})}
    )
    private List<Appointment> appointments;

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    public void addAppointment (Appointment appointment) {
        this.appointments.add(appointment);
    }


    public Request() {
    }

    public Request(String appointmentDate, String living_area, String blood_type, String last_blood_test) {
        this.appointmentDate = appointmentDate;
        this.living_area = living_area;
        this.blood_type = blood_type;
        this.last_blood_test = last_blood_test;
    }

    public int getId() {
        return id;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public String getLiving_area() {
        return living_area;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public String getLast_blood_test() {
        return last_blood_test;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public void setLiving_area(String living_area) {
        this.living_area = living_area;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public void setLast_blood_test(String last_blood_test) {
        this.last_blood_test = last_blood_test;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", appointmentDate='" + appointmentDate + '\'' +
                ", living_area='" + living_area + '\'' +
                ", blood_type='" + blood_type + '\'' +
                ", last_blood_test='" + last_blood_test + '\'' +
                '}';
    }



}

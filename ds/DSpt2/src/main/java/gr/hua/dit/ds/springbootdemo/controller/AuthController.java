package gr.hua.dit.ds.springbootdemo.controller;

import gr.hua.dit.ds.springbootdemo.dao.AppointmentDAO;
import gr.hua.dit.ds.springbootdemo.entity.Appointment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import gr.hua.dit.ds.springbootdemo.service.RequestService;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String login() {
       return "you are logged in";}

    @Controller
    @RequestMapping("appointment")
    public static class AppointmentController {

        @Autowired
        private AppointmentDAO appointmentDAO;

        @GetMapping("")
        public String showAppointments(Model model){
            model.addAttribute("appointments", appointmentDAO.getAppointments());
            return appointmentDAO.toString();
        }

        @GetMapping("/add")
        public String addAppointment(Model model){
            Appointment appointment = new Appointment();
            model.addAttribute("appointment", appointment);
            return "added";

        }

        @GetMapping("{appointment_id}")
        public String editAppointment(@PathVariable Integer appointment_id, Model model){
            Appointment appointment = appointmentDAO.getAppointment(appointment_id);
            model.addAttribute("appointment", appointment);
            return "added ap";

        }

        @PostMapping("/new")
        public String saveAppointment(@ModelAttribute("appointment") Appointment appointment, Model model) {
            appointmentDAO.saveAppointment(appointment);
            model.addAttribute("appointments", appointmentDAO.getAppointments());
            return "appointments";
        }

        @DeleteMapping("{appointment_id}")
        public String deleteAppointment(@PathVariable Integer appointment_id){
            appointmentDAO.deleteAppointment(appointment_id);
            return "appointments";
        }



    }
}

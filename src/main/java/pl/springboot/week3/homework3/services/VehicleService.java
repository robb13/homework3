package pl.springboot.week3.homework3.services;

import org.springframework.stereotype.Service;
import pl.springboot.week3.homework3.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

@Service
public class VehicleService {

    private List<Vehicle> vehiclesList;

    public VehicleService() {
        this.vehiclesList = new ArrayList<>();
        vehiclesList.add(new Vehicle(1, "Audi", "A4", "black"));
        vehiclesList.add(new Vehicle(2, "BMW", "3", "red"));
        vehiclesList.add(new Vehicle(3, "Honda", "Accord", "red"));


    }

    public List<Vehicle> getVehiclesList() {
        return vehiclesList;
    }


    public void setVehiclesList(List<Vehicle> vehiclesList) {
        this.vehiclesList = vehiclesList;
    }
}

package pl.springboot.week3.homework3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.springboot.week3.homework3.model.Vehicle;
import pl.springboot.week3.homework3.services.VehicleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/vehicles")
public class VehicleApi {

    private VehicleService vehicleService;

    @Autowired
    public VehicleApi(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public ResponseEntity<List<Vehicle>> getVehicles() {
        return new ResponseEntity<>(vehicleService.getVehiclesList(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable long id) {
        Optional<Vehicle> first = vehicleService.getVehiclesList().stream().filter(vehicle -> vehicle.getId() == id).findFirst();
        if (first.isPresent()) {
            return new ResponseEntity<>(first.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(first.get(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/color/{color}")
    public ResponseEntity<Stream<Vehicle>> getVehicleByColor(@PathVariable String color) {
        Stream<Vehicle> vehicleColor = vehicleService.getVehiclesList().stream().filter(vehicle -> vehicle.getColor().equals(color));
        ResponseEntity<Stream<Vehicle>> streamResponseEntity = new ResponseEntity<>(vehicleColor, HttpStatus.OK);
        return streamResponseEntity;

    }

    @PostMapping
    public ResponseEntity<Vehicle> addVehicle(@RequestBody Vehicle newVehicle) {
        boolean add = vehicleService.getVehiclesList().add(newVehicle);
        if (add) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PutMapping
    public ResponseEntity<List<Vehicle>> modVehicle(@RequestBody Vehicle newVehicle) {
        Optional<Vehicle> first = vehicleService.getVehiclesList().stream().filter(vehicle -> vehicle.getId() == newVehicle.getId()).findFirst();
        if (first.isPresent()) {
            vehicleService.getVehiclesList().remove(first);
            vehicleService.getVehiclesList().add(newVehicle);

            return new ResponseEntity<>(vehicleService.getVehiclesList(), HttpStatus.OK);
        }
        return new ResponseEntity<>(vehicleService.getVehiclesList(), HttpStatus.NOT_MODIFIED);

    }

    @PatchMapping
    public ResponseEntity<List<Vehicle>> patchVehicle(@RequestBody Vehicle modVehicle) {
        Optional<Vehicle> first = vehicleService.getVehiclesList().stream().filter(vehicle -> vehicle.getId() == modVehicle.getId()).findFirst();
        if (first.isPresent()) {

            if (!(first.get().getColor().equals(modVehicle.getColor())) || (first.get().getColor().equals(modVehicle.getColor() == null))) {
                first.get().setMark(first.get().getMark());
                first.get().setModel(first.get().getModel());
                first.get().setColor(modVehicle.getColor());

            }
            return new ResponseEntity<>(vehicleService.getVehiclesList(), HttpStatus.OK);

        }
        return new ResponseEntity<>(vehicleService.getVehiclesList(),HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Vehicle>> deleteVehicle(@PathVariable long id) {
        Optional<Vehicle> first = vehicleService.getVehiclesList().stream().filter(vehicle -> vehicle.getId() == id).findFirst();
        if (first.isPresent()) {
            vehicleService.getVehiclesList().remove(first.get());
        }
        return new ResponseEntity<>(vehicleService.getVehiclesList(), HttpStatus.OK);
    }


}













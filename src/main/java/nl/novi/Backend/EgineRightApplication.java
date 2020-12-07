package nl.novi.Backend;

import nl.novi.Backend.model.Car;
import nl.novi.Backend.model.Customer;
import nl.novi.Backend.model.Inspection;
import nl.novi.Backend.repo.CarRepository;
import nl.novi.Backend.repo.CustomerRepository;
import nl.novi.Backend.repo.InspectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.text.SimpleDateFormat;
import java.util.Date;



import java.util.Date;

import static nl.novi.Backend.model.Gender.FEMALE;

@SpringBootApplication
public class EgineRightApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(EgineRightApplication.class, args);
	}

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CarRepository carRepository;
	@Autowired
	private InspectionRepository inspectionRepository;

	@Override
	public void run(String... args) throws Exception{
		Customer customer = new Customer();
		customer.setFirstName("Xiaoling");
		customer.setLastName("Qi");
		customer.setEmail("x.qi@novi-education.nl");
		customer.setGender(FEMALE);

		Car car = new Car();
		car.setNumberPlate("64-PF-PT");
		car.setMake("bmw");
		car.setModel("1serie");

		customer.setCar(car);
		car.setCustomer(customer);

		customerRepository.save(customer);
		SimpleDateFormat dt = new SimpleDateFormat("dd/mm/yyyy");
		Inspection inspection1 = new Inspection(dt.parse("03/11/2018"),
			"64-PF-PT", false, true, 45.00);
		Inspection inspection2 = new Inspection(dt.parse("01/12/2019"),
				"64-PF-PT", true, true, 45.00);
		Inspection inspection3 = new Inspection(dt.parse("30/10/2020"),
				"64-PF-PT", false, true, 45.00);

		car.getInspections().add(inspection1);
		car.getInspections().add(inspection2);
		car.getInspections().add(inspection3);

		this.carRepository.save(car);

	}
}

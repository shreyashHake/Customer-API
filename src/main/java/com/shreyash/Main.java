package com.shreyash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
    private final CustomerRepository customerRepository;

    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    /**
     * Simple API :
     *
     * @RequestMapping("/jayShreeRam") private GreetResponse greet() {
     * return new GreetResponse("Jay Shree Ram",
     * List.of("Java", "Cpp", "C", "Python"),
     * new Person("Shreyash")
     * );
     * }
     * <p>
     * record Person(String name){};
     * <p>
     * record GreetResponse(String greet,
     * List<String> favProgrammingLanguages,
     * Person person){}
     */

    @GetMapping("/get")
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/get/{customerId}")
    public Optional getCustomerById(@PathVariable("customerId") Integer id) {
        return customerRepository.findById(id);
    }

    @PostMapping("/post")
    public String addCustomer(@RequestBody NewCustomeRequest newCustomeRequest) {
        Customer customer = new Customer(
                newCustomeRequest.name,
                newCustomeRequest.email,
                newCustomeRequest.age
        );

        customerRepository.save(customer);
        return newCustomeRequest.name + "'s data added successfully!";
    }

    @DeleteMapping("/delete/{customerId}")
    public String deleteCustomer(@PathVariable("customerId") Integer id) {
        customerRepository.deleteById(id);
        return "Customer data with id " + id + " is deleted!";
    }

    @PutMapping("update/{customerId}")
    public String updateCustomer(
            @PathVariable("customerId") Integer id,
            @RequestBody NewCustomeRequest newCustomeRequest
    ) {
        Customer customer = customerRepository.getById(id);

        customer.setName(newCustomeRequest.name);
        customer.setEmail(newCustomeRequest.email);
        customer.setAge(newCustomeRequest.age);

        customerRepository.save(customer);

        return "Customer data with id " + id + " is updated!";
    }

    record NewCustomeRequest(
            String name,
            String email,
            Integer age
    ) {
    }

}


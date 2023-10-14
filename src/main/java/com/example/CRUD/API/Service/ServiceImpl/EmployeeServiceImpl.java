package com.example.CRUD.API.Service.ServiceImpl;

import com.example.CRUD.API.Repository.EmployeeRepository;
import com.example.CRUD.API.Service.EmployeeService;
import com.example.CRUD.API.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.ValidationException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    public Employee findById(Long id) {
        return repository.findById(id);
    }

    public List<Employee> findAll() {
        return repository.findAll();
    }

    public Employee save(Employee employee) {

        //Check Name is Arabic
        if (!textContainsArabic(employee.getName()))
            throw new ValidationException("Name must contain only Arabic letters");

        //Check Lenght National Id
        if (employee.getNationalId().length() != 14) throw new ValidationException("Invalid national ID");

        //Validate Age from National Id
        if (!natIdValid(employee.getNationalId(), employee.getAge())) {
            throw new ValidationException("Invalid national ID or Age");
        }


        return repository.save(employee);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Employee update(Long id, Employee employee) {
        if (!textContainsArabic(employee.getName()))
            throw new ValidationException("Name must contain only Arabic letters");

        return repository.update(id, employee);
    }

    public boolean natIdValid(String n, int i) {
        String temp = "";
        for (int j = 0; j < 7; j++) {
            if (j == 0) {
                if (n.charAt(j) == '2') {
                    temp = temp + "19";
                } else {
                    temp = temp + "20";
                }
            } else {
                temp = temp + n.charAt(j);
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        long age;
        try {
            LocalDate birthDate = LocalDate.parse(temp, formatter);
            // Get the current date
            LocalDate currentDate = LocalDate.now();

            // Calculate the age
            age = ChronoUnit.YEARS.between(birthDate, currentDate);
        } catch (Exception e) {
            throw new ValidationException("Invalid national ID");
        }

//        System.out.println("Age: " + age + " years");
        if (age == i) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean textContainsArabic(String text) {
        for (char charac : text.toCharArray()) {
            if (Character.UnicodeBlock.of(charac) == Character.UnicodeBlock.ARABIC) {
                return true;
            }
        }
        return false;
    }
}

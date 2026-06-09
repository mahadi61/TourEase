package interfaces;

import entity.*;
import java.util.ArrayList;


public interface ICustomerRepo {
    boolean addCustomer(Customer c);

    boolean updateCustomer(Customer c);

    boolean deleteCustomer(String id);

    ArrayList<Customer> searchCustomer(String keyword);
}
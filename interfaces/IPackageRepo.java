package interfaces;

import entity.*;
import java.util.ArrayList;


public interface IPackageRepo {
    boolean addPackage(TourPackage p);

    ArrayList<TourPackage> getAllPackages();
}
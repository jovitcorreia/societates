package dev.castanhocorreia.societates.database;

import dev.castanhocorreia.societates.model.Company;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class JVMMemory {
  private static List<Company> list = new ArrayList<>();

  public static void add(Company company) {
    JVMMemory.list.add(company);
  }

  public static void remove(long id) {
    Iterator<Company> iterator = JVMMemory.list.iterator();
    while (iterator.hasNext()) {
      if (iterator.next().getId() == id) {
        iterator.remove();
      }
    }
  }

  public static void updateCompanyById(long id, String name, Date foundedIn) {
    for (Company company : JVMMemory.list) {
      if (company.getId() == id) {
        company.setName(name);
        company.setFoundedIn(foundedIn);
      }
    }
  }

  public static Company getCompany(long id) {
    for (Company company : JVMMemory.list) {
      if (company.getId() == id) {
        return company;
      }
    }
    throw new NoSuchElementException("The company does not exist.");
  }

  public static List<Company> getCompanies() {
    return JVMMemory.list;
  }
}
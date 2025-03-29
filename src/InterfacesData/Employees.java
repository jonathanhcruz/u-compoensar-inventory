package InterfacesData;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

public class Employees {
  String name;
  String id;
  int age;
  String workingDay;
  Date startDate;

    public Employees(String name, String id, int age, String workingDay, Date startDate ) {
        this.name = name;
        this.id = id;
        this.age = age;
        this.workingDay = workingDay;
        this.startDate = startDate;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getWorkingDay() {
        return workingDay;
    }

    public int getTimeWorked() {
      LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
      LocalDate now = LocalDate.now();
      return (int) ChronoUnit.YEARS.between(start, now);
    }

    public Date getStartDate() {
        return startDate;
    }

    public Map<String, Double> getDiscount() {
        return Calculation.calculateDiscount(getTimeWorked());
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setWorkingDay(String workingDay) {
        this.workingDay = workingDay;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}

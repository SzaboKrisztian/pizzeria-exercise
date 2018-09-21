import java.time.LocalTime;

public class Pizzeria {
  private String name;
  private Coordinate location;
  private WorkingHours hours;

  public Pizzeria(String name, Coordinate location, WorkingHours hours) {

    this.name = name;
    this.location = location;
    this.hours = hours;
  }

  public Coordinate getLocation() {
    return this.location;
  }

  public String getName() {
    return this.name;
  }

  public WorkingHours getWorkingHours() {
    return this.hours;
  }

  public Pizzeria clone() {
    return new Pizzeria(this.name, this.location.clone(), this.hours.clone());
  }
}

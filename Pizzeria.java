import java.time.LocalTime;

public class Pizzeria {
  private String name;
  private Coordinate location;
  private LocalTime openingTime;
  private LocalTime closingTime;

  public Pizzeria(String name, Coordinate location, LocalTime openingTime, LocalTime closingTime) {

    this.name = name;
    this.location = location;
    this.openingTime = openingTime;
    this.closingTime = closingTime;
  }

  public Coordinate getLocation() {
    return this.location;
  }

  public String getName() {
    return this.name;
  }

  public LocalTime getOpeningTime() {
    return this.openingTime;
  }

  public LocalTime getClosingTime() {
    return this.closingTime;
  }

  public Pizzeria clone() {
    return new Pizzeria(this.name, this.location.clone(), this.openingTime, this.closingTime);
  }
}

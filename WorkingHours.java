import java.time.LocalTime;

public class WorkingHours {
  private LocalTime from;
  private LocalTime to;

  public WorkingHours(LocalTime from, LocalTime to) {
    this.from = from;
    this.to = to;
  }

  public LocalTime getOpeningTime() {
    return this.from;
  }

  public LocalTime getClosingTime() {
    return this.to;
  }

  public boolean isItOpen(LocalTime time) {
    return this.from.isBefore(this.to)?
    time.isAfter(this.from) && time.isBefore(this.to):
    time.isBefore(this.to) || time.isAfter(this.from);
  }

  public WorkingHours clone() {
    return new WorkingHours(this.from, this.to);
  }
}

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.InputMismatchException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Main {
  public static void main(String[] args) {
    Scanner scn = new Scanner(System.in);
    List<Pizzeria> pizzerias = new ArrayList<>();
    double x = 0, y = 0;
    boolean success;

    // The loop used for entering several pizzerias
    while (true) {

      // Read the restaurant's name:
      String name;
      System.out.print("Name: ");
      name = scn.nextLine();

      // Read the X coordinate of restaurant's location
      System.out.print("Location, X coord: ");
      success = false;
      while (!success) {
        try {
          x = scn.nextDouble();
          scn.nextLine();
          success = true;
        } catch (InputMismatchException e) {
          scn.nextLine();
          System.out.println("Invalid input. Try again: ");
        }
      }

      // Read the Y coordinate of restaurant's location
      System.out.print("Location, Y coord: ");
      success = false;
      while (!success) {
        try {
          y = scn.nextDouble();
          scn.nextLine();
          success = true;
        } catch (InputMismatchException e) {
          scn.nextLine();
          System.out.println("Invalid input. Try again: ");
        }
      }

      // Read the restaurant's opening hour
      int openingHour = 0;
      while (true) {
        System.out.print("Opening Hour (0-23): ");
        success = false;
        while (!success) {
          try {
            openingHour = scn.nextInt();
            scn.nextLine();
            success = true;
          } catch (InputMismatchException e) {
            scn.nextLine();
            System.out.println("Invalid input. Try again: ");
          }
        }
        if (openingHour < 0 || openingHour > 23) {
          System.out.println("Value out of bounds. Try again.");
        } else {
          break;
        }
      }

      // Read the restaurant's opening minute
      int openingMinute = 0;
      while (true) {
        System.out.print("Opening Minute (0-59): ");
        success = false;
        while (!success) {
          try {
            openingMinute = scn.nextInt();
            scn.nextLine();
            success = true;
          } catch (InputMismatchException e) {
            scn.nextLine();
            System.out.println("Invalid input. Try again: ");
          }
        }
        if (openingMinute < 0 || openingMinute > 59) {
          System.out.println("Value out of bounds. Try again.");
        } else {
          break;
        }
      }

      // Read the restaurant's closing hour
      int closingHour = 0;
      while (true) {
        System.out.print("Closing Hour (0-23): ");
        success = false;
        while (!success) {
          try {
            closingHour = scn.nextInt();
            scn.nextLine();
            success = true;
          } catch (InputMismatchException e) {
            scn.nextLine();
            System.out.println("Invalid input. Try again: ");
          }
        }
        if (closingHour < 0 || closingHour > 23) {
          System.out.println("Value out of bounds. Try again.");
        } else {
          break;
        }
      }

      // Read the restaurant's closing minute
      int closingMinute = 0;
      while (true) {
        System.out.print("Closing Minute (0-59): ");
        success = false;
        while (!success) {
          try {
            closingMinute = scn.nextInt();
            scn.nextLine();
            success = true;
          } catch (InputMismatchException e) {
            scn.nextLine();
            System.out.println("Invalid input. Try again: ");
          }
        }
        if (closingMinute < 0 || closingMinute > 59) {
          System.out.println("Value out of bounds. Try again.");
        } else {
          break;
        }
      }

      // Saving the data
      pizzerias.add(new Pizzeria(name, new Coordinate(x, y),
        LocalTime.of(openingHour, openingMinute),
        LocalTime.of(closingHour, closingMinute)));
      System.out.println("Would you like to add another pizzeria? [Y to confirm]");
      String prompt = scn.nextLine();
      if (prompt.equals("") || prompt.toUpperCase().charAt(0) != 'Y')
      break;
    }

    // Read the user's X location
    System.out.print("Your location, X coord: ");
    success = false;
    while (!success) {
      try {
        x = scn.nextDouble();
        scn.nextLine();
        success = true;
      } catch (InputMismatchException e) {
        scn.nextLine();
        System.out.println("Invalid input. Try again: ");
      }
    }

    // Read the user's Y location
    System.out.print("Your location, Y coord: ");
    success = false;
    while (!success) {
      try {
        y = scn.nextDouble();
        scn.nextLine();
        success = true;
      } catch (InputMismatchException e) {
        scn.nextLine();
        System.out.println("Invalid input. Try again: ");
      }
    }

    // Turn X and Y into coords, and close the scanner
    Coordinate myPlace = new Coordinate(x, y);
    scn.close();

    // Get the user's local time
    LocalTime now = LocalTime.now();
    System.out.println("Current time is: "
    + now.format(DateTimeFormatter.ofPattern("HH:mm")));

    // Figure out the closest open pizzeria
    Pizzeria closest = null;
    double distance = Double.MAX_VALUE;
    for (Pizzeria current: pizzerias) {
      // If it's not open now, skip this pizzeria
      // Note that there's two cases to handle here:
      // When openingTime < closingTime and when closingTime < openingTime
      // (i.e. the workday ends after midnight, sometime in the next day)
      if (current.getOpeningTime().isBefore(current.getClosingTime())?
      now.isAfter(current.getOpeningTime()) && now.isBefore(current.getClosingTime()):
      now.isBefore(current.getClosingTime()) || now.isAfter(current.getOpeningTime())) {
        double dist = myPlace.distanceTo(current.getLocation());
        // If it's closer then the previously found closest
        // remember this instance as being the closest, and also
        // save this newest, shorter distance.
        if (dist < distance) {
          closest = current.clone();
          distance = dist;
        }
      }
    }

    // And finally display the result.
    if (closest == null) {
      System.out.println("There are no pizzerias open right now.");
    }
    else {
      System.out.printf("The closest open pizzeria to you is %s, %.2f away from you.\n",
      closest.getName(), distance);
    }
  }


}

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;



public class TimeExplorer {
    //Constants for time formatters
    private static final DateTimeFormatter DATE_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private static final DateTimeFormatter TIME_INPUT_FORMAT =
            DateTimeFormatter.ofPattern("HH:mm");

    private static final DateTimeFormatter LONG_DATE_FORMAT =
            DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy");

    private static final DateTimeFormatter SHORT_DATE_FORMAT =
            DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private static final DateTimeFormatter FULL_DATE_TIME_FORMAT =
            DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy h:mm a");

    //Main Method
public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    boolean keepRunning = true;


    System.out.println();


    //while loop to handle user input
while (keepRunning){
    printMenu();
    System.out.print("Enter your choice: ");
    String choice = input.nextLine();

    switch (choice){
        case "1":
            showCurrentDateAndTime();
            break;
        case "2":
            formatDate();
            break;
        case "3":
            parseDate(input);
            break;
        case "4":
            calculateDateDifference(input);
            break;
        case "5":
            countdownToFutureDate(input);
            break;
            case "6":
                calculateTimeDuration(input);
                break;
        case "7":
                convertTimeZones();
            break;
        case "8":
            showInstantTimeStamp();
            break;
        case "9":
            keepRunning = false;
            System.out.println("Goodbye");
            break;
        default:
            System.out.println("Invalid menu option, please choose a number between 1 and 9");
    }
    System.out.println();
}
    input.close();
}
//Helper Method to print the menu
    public static void printMenu(){
    System.out.println("Java Time Explorer:");
    System.out.println("1. Show current date and time.");
    System.out.println("2. Format a Date.");
    System.out.println("3. Parse a Date from text.");
    System.out.println("4. Calculate age or event difference.");
    System.out.println("5. Countdown to a future date.");
    System.out.println("6. Calculate time duration.");
    System.out.println("7. Convert Time Zones.");
    System.out.println("8. Show Current Time Stamp.");
    System.out.println("9. Exit.");
    System.out.println();
    }

    //Time-handling Methods
    //Void to show current date/time
    public static void showCurrentDateAndTime(){
    System.out.println("Current Date and Time:");

    LocalDate currentDate = LocalDate.now();
    LocalTime currentTime = LocalTime.now();
    LocalDateTime currentDateTime = LocalDateTime.now();

    System.out.println("Current Date: " + currentDate);
    System.out.println("Current Time: " + currentTime.truncatedTo(ChronoUnit.SECONDS));
    System.out.println("Current Date and Time: " + currentDateTime.truncatedTo(ChronoUnit.SECONDS));



    }
    //Void to format the current date multiple ways
    public static void formatDate() {
        System.out.println("Date Formatting:");
        LocalDate today = LocalDate.now();
        System.out.println("Default Format: " + today);
        System.out.println("Short Format: " + today.format(SHORT_DATE_FORMAT));
        System.out.println("Long Format: " + today.format(LONG_DATE_FORMAT));
    }

    //Void to parse a date the user enters
    public static void parseDate(Scanner input) {
        System.out.println("Date Parsing:");
        System.out.print("Enter a date using MM/dd/yyyy format: ");
        String userDate = input.nextLine();
        try{
            LocalDate parsedDate = LocalDate.parse(userDate, DATE_INPUT_FORMAT);

            System.out.println();
            System.out.println("User entry: " + userDate);
            System.out.println("Parsed LocalDate: " + parsedDate);
            System.out.println("Formatted Date: " + parsedDate.format(LONG_DATE_FORMAT));
        }catch (DateTimeParseException e){
            System.out.println("Invalid date format.");
        }
    }

    //Void to calculate difference between two dates
    public static void calculateDateDifference(Scanner input){
    System.out.println("Age or event difference calculator.");
    System.out.print("Enter a past date using MM/dd/yyyy format: ");
    String userDate = input.nextLine();
    try{
        LocalDate pastDate = LocalDate.parse(userDate, DATE_INPUT_FORMAT);
        LocalDate today = LocalDate.now();

        //handle for future days
        if(pastDate.isAfter(today)){
            System.out.println("The date is in the future. Enter a past date.");
            return;
        }
        //Get the difference
        Period difference = Period.between(pastDate, today);

        System.out.println("Time Since: " + pastDate.format(LONG_DATE_FORMAT) + ":");
        System.out.println(difference.getYears() + " years, "
        + difference.getMonths() + " months, "
        + difference.getDays() + " days, ");

    }catch (DateTimeParseException e){
        System.out.println("Invalid date format.");
    }
    }

    //Void to countdown to a future date
    public static void countdownToFutureDate(Scanner input){
    System.out.println("Countdown to a future date:");

    System.out.print("Enter a future date using MM/dd/yyyy format: ");
    String userDate = input.nextLine();

    try{
        LocalDate futureDate = LocalDate.parse(userDate, DATE_INPUT_FORMAT);
        LocalDate today = LocalDate.now();
        if(futureDate.isBefore(today)){
            System.out.println("That date is in the past. Enter a future date.");
            return;
        }
        long daysUntil = ChronoUnit.DAYS.between(today, futureDate);
        System.out.println("Future Date: " + futureDate.format(LONG_DATE_FORMAT));
        System.out.println("Days until that date: " + daysUntil);
    }catch (DateTimeParseException e){
        System.out.println("Invalid date format.");
    }
    }

    //Void to calculate time duration
    public static void calculateTimeDuration(Scanner input){
    System.out.println("Calculate time duration:");
    //Get two times from user
        System.out.print("Enter start time using HH:mm format: ");
        String startText  = input.nextLine();
        System.out.print("Enter end time using HH:mm format: ");
        String endText  = input.nextLine();
        try{
            LocalTime startTime = LocalTime.parse(startText, TIME_INPUT_FORMAT);
            LocalTime endTime = LocalTime.parse(endText, TIME_INPUT_FORMAT);

            //Make sure start time is before end time
            if(endTime.isBefore(startTime)){
                System.out.println("The time is before the start time.");
                return;
            }
            Duration duration = Duration.between(startTime, endTime);
            long hours = duration.toHours();
            long minutes = duration.toMinutes();

            System.out.println("Raw Duration: " + duration);
            System.out.println("Hours: " + hours);
            System.out.println("Minutes: " + minutes);
            System.out.println("Duration: " + hours + " hour and " + minutes + " minutes");

        }catch (DateTimeParseException e){
            System.out.println("Invalid time format.");
        }
    }

    //Void to convert to multiple time zones
    public static void convertTimeZones(){
    System.out.println("Time Zone Converter:");
    //Define time zones
        ZoneId chicagoZone = ZoneId.of("America/Chicago");
        ZoneId newYorkZone = ZoneId.of("America/New_York");
        ZoneId losAngelesZone = ZoneId.of("America/Los_Angeles");
        ZoneId londonZone = ZoneId.of("Europe/London");
        ZoneId tokyoZone = ZoneId.of("Asia/Tokyo");

        //Hardcode the date and time
        LocalDate meetingDate = LocalDate.of(2026, 12, 26);
        LocalTime meetingTime = LocalTime.of(14, 30);

        //Convert to zone date time
        ZonedDateTime chicagoMeeting = ZonedDateTime.of(meetingDate, meetingTime, chicagoZone);

        //Convert the Chicago time meeting to other time zones
        ZonedDateTime newYorkMeeting = chicagoMeeting.withZoneSameInstant(newYorkZone);
        ZonedDateTime losAngelesMeeting = chicagoMeeting.withZoneSameInstant(losAngelesZone);
        ZonedDateTime londonMeeting = chicagoMeeting.withZoneSameInstant(londonZone);
        ZonedDateTime tokyoMeeting = chicagoMeeting.withZoneSameInstant(tokyoZone);

        //Print all meeting date/times
        System.out.println("Meeting Time: ");
        System.out.println("Chicago: " + chicagoMeeting.format(FULL_DATE_TIME_FORMAT));
        System.out.println("New York: " + newYorkMeeting.format(FULL_DATE_TIME_FORMAT));
        System.out.println("Los Angeles: " + losAngelesMeeting.format(FULL_DATE_TIME_FORMAT));
        System.out.println("London: " + londonMeeting.format(FULL_DATE_TIME_FORMAT));
        System.out.println("Tokyo: " + tokyoMeeting.format(FULL_DATE_TIME_FORMAT));
    }

    //Void to get the current instant
    public static void showInstantTimeStamp(){
    System.out.println("Current Instant:");
    Instant currentInstant =  Instant.now();
    System.out.println("This specific moment in computer time: " + currentInstant);
    }
}

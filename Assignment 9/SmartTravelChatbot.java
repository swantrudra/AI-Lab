import java.util.*;

public class SmartTravelChatbot {

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println(" Hello! I’m your Travel Assistant Chatbot.");
    System.out.println("I can help with flights, hotels, destinations, weather, visa info, and more!");
    System.out.println("Type 'bye' anytime to exit.\n");

    while (true) {
        System.out.print(" You: ");
        String query = sc.nextLine().trim().toLowerCase();

        if (query.contains("bye") || query.contains("exit") || query.contains("quit")) {
            System.out.println(" Chatbot: Safe travels! Hope to assist you again soon. 👋");
            break;
        }

        if (query.contains("flight")) {
            System.out.println(" You can find and book flights on MakeMyTrip, Yatra, or Skyscanner.");
            System.out.println("Pro tip: Booking 3–4 weeks early often gets you the best prices.");
        } 
        else if (query.contains("hotel") || query.contains("stay")) {
            System.out.println(" For stays, try Booking.com, OYO, or Agoda.");
            System.out.println("Always check location, reviews, and cancellation policy.");
        } 
        else if (query.contains("place") || query.contains("destination") || query.contains("travel")) {
            System.out.println(" Some great Indian destinations include:");
            System.out.println("- Goa: beaches and parties");
            System.out.println("- Manali: mountains and snow");
            System.out.println("- Kerala: backwaters and greenery");
            System.out.println("- Jaipur: forts and culture");
            System.out.println("- Ladakh: adventure and biking trails");
        } 
        else if (query.contains("tip") || query.contains("advice")) {
            System.out.println(" Travel Tips:");
            System.out.println("- Carry a power bank and water bottle.");
            System.out.println("- Keep soft copies of tickets and IDs.");
            System.out.println("- Inform someone about your travel plan.");
        } 
        else if (query.contains("weather") || query.contains("temperature")) {
            System.out.println(" Check live weather on AccuWeather or Weather.com.");
            System.out.println("Always pack according to the forecast!");
        } 
        else if (query.contains("visa")) {
            System.out.println(" Visa Information:");
            System.out.println("Visit embassy websites or VFS Global for the latest requirements.");
            System.out.println("Ensure your passport is valid for 6+ months from your travel date.");
        } 
        else if (query.contains("currency") || query.contains("money") || query.contains("exchange")) {
            System.out.println(" Currency Exchange:");
            System.out.println("Exchange at airports, forex centers, or use travel cards.");
            System.out.println("Keep small denominations for taxis and tips.");
        } 
        else if (query.contains("emergency") || query.contains("help") || query.contains("sos")) {
            System.out.println(" Emergency Contacts:");
            System.out.println("India: 112 | USA: 911 | UK: 999");
            System.out.println("Also note your country’s embassy contact while abroad.");
        } 
        else if (query.contains("budget") || query.contains("cheap") || query.contains("save")) {
            System.out.println(" Budget Tips:");
            System.out.println("- Travel off-season for cheaper prices.");
            System.out.println("- Use local transport and food stalls.");
            System.out.println("- Share rooms or stay in hostels.");
        } 
        else if (query.contains("transport") || query.contains("local")) {
            System.out.println(" Local Transport:");
            System.out.println("Use metros, buses, Ola, or Uber. In tourist cities, try rental bikes.");
        } 
        else if (query.contains("pack") || query.contains("luggage") || query.contains("bag")) {
            System.out.println("Packing Tips:");
            System.out.println("- Roll clothes to save space.");
            System.out.println("- Keep essentials in hand luggage.");
            System.out.println("- Carry medicines and a power adapter.");
        } 
        else {
            System.out.println(" I’m not sure about that. Try asking about flights, hotels, destinations, or travel tips!");
        }
    }

    sc.close();
}


}

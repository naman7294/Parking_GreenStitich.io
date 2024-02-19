package parking;

import java.util.List;
import java.util.Scanner;

public class Parking {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ParkingLot parkingLot = null;

		while (true) {
			String input = scanner.nextLine();
			String trimmedinput = input.trim();
			String[] tokens = trimmedinput.split(" ");
				if (tokens[0].equals("create_parking_lot")) {
					int capacity = Integer.parseInt(tokens[1]);
					if (capacity <= 0) {
						System.out.println("Capacity Should be positive");
					} else {
						parkingLot = new ParkingLot(capacity);
						System.out.println("Created a parking lot with " + capacity + " slots");
					}
				} else if (tokens[0].equals("park")) {
					parkingLot.park(tokens[1], tokens[2]);
				} else if (tokens[0].equals("leave")) {
					int slotNumber = Integer.parseInt(tokens[1]);
					parkingLot.leave(slotNumber);
				} else if (tokens[0].equals("status")) {
					parkingLot.status();
				} else if (tokens[0].equals("registration_numbers_for_cars_with_colour")) {
					List<Pair<String, Integer>> registrationNumbersWithSlot = parkingLot
							.registrationNumbersForCarsWithColor(tokens[1]);
					for (Pair<String, Integer> pair : registrationNumbersWithSlot) {
						System.out.println("Registration Number: " + pair.getFirst() + ", Slots: " + pair.getSecond());
					}
				} else if (tokens[0].equals("slot_number_for_registration_number")) {
					int slotNumber = parkingLot.slotNumberForRegistration(tokens[1]);
					if (slotNumber != -1) {
						System.out.println(slotNumber);
					} else {
						System.out.println("Not found");
					}
				} else if (tokens[0].equals("slot_numbers_for_cars_with_colour")) {
					List<Integer> slotNumbers = parkingLot.slotNumbersForCarsWithColor(tokens[1]);
					System.out.println(slotNumbers.isEmpty() ? "Not found" : slotNumbers.toString());
				} else if (tokens[0].equals("exit")) {
					System.out.println("You have now been exited to the application");
					break;
				} else {
					System.out.println("Wrong parameter has been entered");
				}
		}

		scanner.close();
	}
}

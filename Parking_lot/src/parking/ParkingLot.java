package parking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class ParkingLot{
	private int capacity;
	private Map<Integer, String> slots;
	private Map<String, Integer> registrationToSlot;
	private Map<String, List<Integer>> colorToSlots;

	public ParkingLot(int capacity) {
		this.capacity = capacity;
		this.slots = new HashMap<>();
		this.registrationToSlot = new HashMap<>();
		this.colorToSlots = new HashMap<>();
	}

	public void park(String registrationNumber, String color) {
		if (isFull()) {
			System.out.println("Sorry, parking lot is full");
			return;
		}

		int slotNumber = getNextAvailableSlot();
		slots.put(slotNumber, registrationNumber);
		registrationToSlot.put(registrationNumber, slotNumber);

		colorToSlots.putIfAbsent(color, new ArrayList<>());
		colorToSlots.get(color).add(slotNumber);

		System.out.println("Allocated slot number: " + slotNumber);
	}

	public void leave(int slotNumber) {
		if (!slots.containsKey(slotNumber)) {
			System.out.println("Slot number " + slotNumber + " is already free");
			return;
		}

		String registrationNumber = slots.get(slotNumber);
		slots.remove(slotNumber);
		registrationToSlot.remove(registrationNumber);

		for (List<Integer> slotList : colorToSlots.values()) {
			slotList.remove(Integer.valueOf(slotNumber));
		}

		System.out.println("Slot number " + slotNumber + " is free");
	}

	public void status() {
		System.out.println("Slot No. Registration No Colour");
		if(slots.entrySet().size()==0)
		{
			System.out.println("Parking lot is empty");
		}
		for (Map.Entry<Integer, String> entry : slots.entrySet()) {
			System.out
					.println(entry.getKey() + " " + entry.getValue() + " " + getColorByRegistration(entry.getValue()));
		}
	}

	public List<Pair<String, Integer>> registrationNumbersForCarsWithColor(String color) {
		List<Pair<String, Integer>> registrationNumbers = new ArrayList<>();
		if (colorToSlots.containsKey(color)) {
			List<Integer> slots = colorToSlots.get(color);
			for (int slot : slots) {
				registrationNumbers.add(new Pair<>(this.slots.get(slot), slot));
			}
		}
		return registrationNumbers;
	}

	public int slotNumberForRegistration(String registrationNumber) {
		return registrationToSlot.getOrDefault(registrationNumber, -1);
	}

	public List<Integer> slotNumbersForCarsWithColor(String color) {
		return colorToSlots.getOrDefault(color, new ArrayList<>());
	}

	private boolean isFull() {
		return slots.size() == capacity;
	}

	private int getNextAvailableSlot() {
		for (int i = 1; i <= capacity; i++) {
			if (!slots.containsKey(i)) {
				return i;
			}
		}
		return -1; // Parking lot is full
	}

	private String getColorByRegistration(String registrationNumber) {
		for (Map.Entry<String, List<Integer>> entry : colorToSlots.entrySet()) {
			if (entry.getValue().contains(registrationToSlot.get(registrationNumber))) {
				return entry.getKey();
			}
		}
		return "";
	}
}

package simple.design;
import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
  public static final String MSG_PARKING_LOT_IS_FULL = "Can not park now, ParkingLot is full!";
  static final int PARK_EMPTY = -1;
  private String lotName;
  private int capacity;
  private int currentNub;   
  private List<Integer> positions = new ArrayList<Integer>();
  
  public ParkingLot(String lotName, int capacity) {
    this.lotName = lotName;
    this.capacity = capacity;
    for(int index = 0; index < capacity; index ++) {
      positions.add(index, PARK_EMPTY);
    }
    currentNub = 0;
  }
  
  public int park() throws Exception {
    if(currentNub < getCapacity()) {
      for(int index = 0; index < positions.size(); index ++) {
        if(positions.get(index) == PARK_EMPTY) {
          positions.set(index, 0);
          currentNub ++;
          return index;
        }
      }      
    }      
    throw new Exception(MSG_PARKING_LOT_IS_FULL);
  }
  
  public boolean fetch(int index) {
    if (currentNub == 0) {
      return false;
    }
    if (positions.get(index) == PARK_EMPTY){
      return false;
    }
    positions.set(index, PARK_EMPTY);
    currentNub --;
    return true;
  }

  public int getCapacity() {
    return capacity;
  }
  
  public String getLotName() {
    return lotName;
  }
  
  public boolean isFull() {
    return currentNub >= capacity ;
  }
  
  public int getCurrentNumber() {
    return currentNub;
  }
}

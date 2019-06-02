package simple.design;
import java.util.ArrayList;
import java.util.List;

public class ParkingSystem {
  public static final String MSG_PARKING_SYSTEM_IS_FULL = "Can not park now, ParkingSystem is full!";
  private static final int DEFAULT_PARKING_CAPACITY = 5;
  private double factor = 1.0;
  private List<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
  
  public ParkingSystem(int lotNub) {
    for(int index = 1; index <= lotNub; index ++) {
      addParkingLot("lot" + String.valueOf(index), DEFAULT_PARKING_CAPACITY);
    }
  }
  
  private void addParkingLot(String name, int capacity) {
    parkingLots.add(new ParkingLot(name, capacity));
  }
  
  public ParkingInfo park() throws Exception {
    for(ParkingLot parkLot : parkingLots) {
      if(isParkingLotNotFull(parkLot)) {
        int nub = parkLot.park();
        String name = parkLot.getLotName();
        return new ParkingInfo(name, nub);
      }
    }
    throw new Exception(MSG_PARKING_SYSTEM_IS_FULL);
  }
  
  public boolean isFull() {
    for (ParkingLot lot : parkingLots) {
      if(isParkingLotNotFull(lot)) {
        return false;
      }
    }
    return true;
  }

  private boolean isParkingLotNotFull(ParkingLot lot) {
    return lot.getCurrentNumber() < lot.getCapacity() * factor;
  }
  
  public boolean fetch(ParkingInfo parkingInfo) {    
    if(parkingInfo == null) {
      return false;
    }
    for(ParkingLot parkLot : parkingLots) { 
      if(parkLot.getLotName().equals(parkingInfo.getParkingName())) {
        return parkLot.fetch(parkingInfo.getParkingNumber());
      }
    }
    return false;
  }
  
  public void setFactor(double factor) {
    this.factor = factor;
  }
}

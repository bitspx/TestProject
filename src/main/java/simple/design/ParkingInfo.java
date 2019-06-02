package simple.design;

public class ParkingInfo {
  public static final String DEFAULT_PARKING_SEPARATOR = "-";
  private String parkingName;
  private int parkingNumber;
  
  public ParkingInfo(String name, int number) {
    this.parkingName = name;
    this.parkingNumber = number;
  }

  public String getParkingName() {
    return parkingName;
  }

  public int getParkingNumber() {
    return parkingNumber;
  } 
  
  public String toString() {
    return parkingName + DEFAULT_PARKING_SEPARATOR + parkingNumber; 
  }
  
  public int hashCode() {
    return toString().hashCode();
  }
  
  public boolean equals(Object obj) {
    if(!(obj instanceof ParkingInfo)) {
      return false;
    }
    if(this == obj) {
      return true;
    }
    ParkingInfo parkObj = (ParkingInfo)obj;
    if(parkingName.equals(parkObj.getParkingName()) 
                          && parkingNumber == parkObj.getParkingNumber()) {
      return true;
    }
    return false;
  }
}

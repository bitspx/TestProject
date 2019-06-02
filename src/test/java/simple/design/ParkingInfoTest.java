package simple.design;
import junit.framework.TestCase;


public class ParkingInfoTest extends TestCase {
  
  private ParkingInfo parkingInfo;

  protected void setUp() throws Exception {
    parkingInfo = new ParkingInfo("WangJing1", 0);
  }
  
  public void testParkingName() {
    assertEquals("WangJing1", parkingInfo.getParkingName());    
  }
  
  public void testParkingNumber() {
    assertEquals(0, parkingInfo.getParkingNumber()); 
  }
}

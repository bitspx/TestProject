package simple.design;
import junit.framework.TestCase;

public class ParkingLotTest extends TestCase {
  
  public void testParkOk() throws Exception {
    ParkingLot parkSys = new ParkingLot("lot", 9);
    assertEquals(0, parkSys.park());   
  }
  
  public void testParkFull() throws Exception {
    ParkingLot parkLot = new ParkingLot("lot", 8);
    for(int n=0; n < parkLot.getCapacity(); n++) {
      assertEquals(n, parkLot.park());
    }
    try 
    {
      parkLot.park();
      fail("Failed, should throw Parking Full execption!"); //this line should not be executed, if execute report test failure
    } 
    catch (Exception e) {
      assertEquals(ParkingLot.MSG_PARKING_LOT_IS_FULL, e.getMessage());
    }
    assertEquals(true, parkLot.fetch(0));
  }
  
  //@Test(expected=IndexOutOfBoundsException.class)
  
  public void testFetch() throws Exception {
    ParkingLot parkSys = new ParkingLot("lot", 6);
    assertEquals(false, parkSys.fetch(0));
    
    parkSys.park();
    parkSys.park();
    assertEquals(true, parkSys.fetch(0));
    assertEquals(true, parkSys.fetch(1));
    assertEquals(false, parkSys.fetch(1));
    assertEquals(false, parkSys.fetch(2));    
  }

}

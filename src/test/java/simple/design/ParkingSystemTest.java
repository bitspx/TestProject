package simple.design;
import junit.framework.TestCase;


public class ParkingSystemTest extends TestCase {
  
  public void testParkOk() throws Exception {
    ParkingSystem parkSys = new ParkingSystem(2);
    parkSys.setFactor(0.8);
    assertEquals("lot1-0", parkSys.park().toString());  
    assertEquals("lot1-1", parkSys.park().toString());
    assertEquals("lot1-2", parkSys.park().toString());  
    assertEquals("lot1-3", parkSys.park().toString());
    assertEquals(false, parkSys.isFull());
    
    assertEquals("lot2-0", parkSys.park().toString());  
    assertEquals("lot2-1", parkSys.park().toString());
    assertEquals("lot2-2", parkSys.park().toString());  
    assertEquals("lot2-3", parkSys.park().toString());    
    assertEquals(true, parkSys.isFull());
  }
  
  public void testParkKo() throws Exception  {
    ParkingSystem parkSys = new ParkingSystem(1);    
    parkSys.park();
    parkSys.park();
    parkSys.park();
    assertNotNull(parkSys.park());
    assertNotNull(parkSys.park());
    try {
      assertNull(parkSys.park());
      fail("Failed, there is no exception throwed!"); //this line should not be executed, if execute report test failure
    } 
    catch (Exception e) {
      assertEquals(ParkingSystem.MSG_PARKING_SYSTEM_IS_FULL, e.getMessage());
    }    
  }
  
  public void testFetch() throws Exception {
    ParkingSystem parkSys = new ParkingSystem(9);
    ParkingInfo parkInfo =  parkSys.park();
    assertEquals("lot1-0", parkInfo.toString());  
    assertTrue(parkSys.fetch(parkInfo));
    assertFalse(parkSys.fetch(parkInfo));
    assertFalse(parkSys.fetch(null));
  }
  
  public void testParkWithFactor() throws Exception {
    ParkingSystem parkSys = new ParkingSystem(1);
    parkSys.setFactor(0.8);
    parkSys.park();
    parkSys.park();
    ParkingInfo parkInfo1 = parkSys.park();
    assertEquals("lot1-2", parkInfo1.toString()); 
    ParkingInfo parkInfo2 = parkSys.park();
    assertEquals("lot1-3", parkInfo2.toString());
    try {
      assertNull(parkSys.park());
      fail();
    } 
    catch (Exception e) { 
      assertEquals(ParkingSystem.MSG_PARKING_SYSTEM_IS_FULL, e.getMessage());
    }
  }
  
  public void testFetchKo() throws Exception  {
    ParkingSystem parkSys = new ParkingSystem(1);
    parkSys.setFactor(0.8);
    assertEquals(false, parkSys.fetch(new ParkingInfo("lot1", 1)));
    
    ParkingInfo parkInfo = parkSys.park();
    assertEquals(true, parkSys.fetch(parkInfo));
    assertEquals(false, parkSys.fetch(new ParkingInfo("lot1", 1)));
    
    parkInfo = parkSys.park();
    assertEquals("lot1-0", parkInfo.toString());
    parkInfo = parkSys.park();
    assertEquals("lot1-1", parkInfo.toString());
    assertEquals(true, parkSys.fetch(new ParkingInfo("lot1", 1)));
    
    assertEquals(false, parkSys.fetch(new ParkingInfo("lot1", 2)));
    assertEquals(false, parkSys.fetch(new ParkingInfo("lot2", 1)));
  }
}

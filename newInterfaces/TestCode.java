/*
 * @author Group09
 * @version 0.99.0
 *
 * This class is for testing code
 */

import titan.Vector3dInterface;

public class TestCode
{
  public static void main(String[] args)
  {
    System.out.println("\n--------------------\n");

    SolarSystem sol = new SolarSystem(60*100, 525600/100);
    SystemFrame frame = new SystemFrame(sol.getLocations());


    System.out.println("\n--------------------\n");

    SolarSystem sol = new SolarSystem(6000, 20000);
    SystemFrame frame = new SystemFrame(sol.getLocations());


  }
}
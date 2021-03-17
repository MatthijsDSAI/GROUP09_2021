package physics;
/*
 * @author Group09
 * @version 0.99.0
 *
 * This class is an implementation of the Vector3dInterface, used to represent vectors.
 */

import titan.FunctionInterface;
import titan.Vector3dInterface;
import titan.SolverInterface;
import java.lang.Math;

public class CelestialBody implements FunctionInterface
{
  private double m;                       // mass of the body
  //changed it to Vector3dInterface --> where's the difference between Vector3d and Vector3dInterface?
  private Vector3dInterface x;            // xyz coordinates of the body
  private Vector3dInterface v;                     // xyz velocity of the body
  private final double G = 6.674 * Math.pow(10, -11);
  private String name;
  private double h = 0.1;     //step size

  public CelestialBody(String name, double mass, Vector3d x0, Vector3d v0)
  {
    m = mass;
    x = x0;
    v = v0;
    this.name = name;
  }

  public Vector3dInterface getCoord()
  {
    return x;
  }

  public Vector3dInterface getVel()
  {
    return v;
  }

  public void setVel(Vector3dInterface v)
  {
    this.v = v;
  }

  public void setCoord(Vector3dInterface x)
  {
    this.x = x;
  }

  public double getMass()
  {
    return m;
  }

  public double getX()
  {
    return x.getX();
  }

  public double getY()
  {
    return x.getY();
  }

  public double getZ()
  {
    return x.getZ();
  }

  public double getVelX()
  {
    return v.getX();
  }

  public double getVelY()
  {
    return v.getY();
  }

  public double getVelZ()
  {
    return v.getZ();
  }

  /*
   * This is the function that represents the first derivative
   * denoted as f in your project manual. You need to implement
   * this function according to the laws of physics.
   *
   * For example, consider the state of the system at time t as
   * this linear function,
   * y(t) = a*t + b,
   * then we can choose any function f whose derivative is a.
   * The simplest such f is
   * f(t, y(t)) = a.
   *
   * @param   t   the time at which to evaluate the function
   * @param   s   the location at which to evaluate the function
   */
  public Vector3dInterface call(double t, Vector3dInterface s)
  {
    return s.add(new Vector3d(v.getX()*t, v.getY()*t, v.getZ()*t));
  }

  public String toString()
  {
    return name;
  }

  public Vector3dInterface gForce(CelestialBody u, CelestialBody v)
  {
    //Calculate distance vector 
    Vector3dInterface r_vec3d = u.x.sub(v.x);
    //Caclulate magnitude of distance vector 
    double r_mag = r_vec3d.norm();
    //Calculate unit vector of distance vector - Direction vector 
    Vector3dInterface r_hat = r_vec3d.mul(Math.pow(r_mag, -1));
    //Caclulate force magnitude
    double force_mag = G*u.m*v.m / Math.pow(r_mag,2);
    //Calculate force Vector
    Vector3dInterface force_vec = r_hat.mul(-force_mag);
    return force_vec;
  }
  public Vector3dInterface gPosition(CelestialBody x)
  {
    //Calculate forces
    Vector3dInterface this_Force = gForce(this, x);
    //Calculate momentum - momentum = mass * velocity
    Vector3dInterface this_Momentum = this.v.mul(this.m);
    //Calculate momentum
    this_Momentum = this_Momentum.add(this_Force.mul(h));
    //Update velocity 
    this.setVel(this_Momentum.mul(Math.pow(this.m, -1)));
    //Update position
    Vector3dInterface x_position = x.getCoord().add(this_Momentum.mul((Math.pow(x.m*h,-1)))); 
    return x_position;
  }
}
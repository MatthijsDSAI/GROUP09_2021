/*
 * @author Matthijs Vossen
 * @version 0.99.0
 *
 * This class is an implementation of the Vector3dInterface, used to represent vectors.
 */

import titan.Vector3dInterface;
import titan.StateInterface;
import titan.RateInterface;
import java.lang.Math;

public class Vector3d implements Vector3dInterface, StateInterface, RateInterface
{
  private double x;
  private double y;
  private double z;

  public Vector3d(double x, double y, double z)
  {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public double getX()
  {
    return x;
  }

  public void setX(double x)
  {
    this.x = x;
  }

  public double getY()
  {
    return y;
  }

  public void setY(double y)
  {
    this.y = y;
  }

  public double getZ()
  {
    return z;
  }

  public void setZ(double z)
  {
    this.z = z;
  }

  /*
   * Vector addition. !WARNING! does not actually add the vector to the vector
   * it's being called on, just returns the result of this addition.
   *
   * @return the result of the addition to this vector
   */
  public Vector3dInterface add(Vector3dInterface other)
  {
    return new Vector3d(x + other.getX(), y + other.getY(), z + other.getZ());
  }

  /*
   * Vector subtraction. !WARNING! does not actually subtract the vector from the vector
   * it's being called on, just returns the result of this subtraction.
   *
   * @return the result of the subtraction from this vector
   */
  public Vector3dInterface sub(Vector3dInterface other)
  {
    return new Vector3d(x - other.getX(), y - other.getY(), z - other.getZ());
  }

  public double dotProd(Vector3dInterface other)
  {
    return (this.getX()*other.getX() + this.getY()*other.getY() + this.getZ()*other.getZ());
  }

  /*
   * Vector multiplication by a scalar. !WARNING! does not actually multiply the vector
   * it's being called on, just returns the result of this multiplication.
   *
   * @return the result of the multiplication of this vector
   */
  public Vector3dInterface mul(double scalar)
  {
    return new Vector3d(x*scalar, y*scalar, z*scalar);
  }

  public double angle(Vector3dInterface other)
  {
    return Math.acos(this.dotProd(other)/(this.norm()*other.norm()));
  }

  /**
   * Scalar x vector multiplication, followed by an addition
   *
   * @param scalar the double used in the multiplication step
   * @param other  the vector used in the multiplication step
   * @return the result of the multiplication step added to this vector,
   */
  public Vector3dInterface addMul(double scalar, Vector3dInterface other)
  {
    return this.add(other.mul(scalar));
  }

  public double zDist(Vector3dInterface other)
  {
    return Math.abs(this.getZ() - other.getZ());
  }

  /**
   * @return the Euclidean norm of a vector
   */
  public double norm()
  {
    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
  }

  /**
   * @return the Euclidean distance between two vectors
   */
  public double dist(Vector3dInterface other)
  {
    return Math.sqrt(Math.pow(other.getX() - x, 2) + Math.pow(other.getY() - y, 2) + Math.pow(other.getZ() - z, 2));
  }

  /**
   * @return A string in this format:
   * Vector3d(-1.0, 2, -3.0) should print out (-1.0,2.0,-3.0)
   */
  public String toString()
  {
    return "(" + x + "," + y + "," + z + ")";
  }

  /**
   * Update a state to a new state computed by: this + step * rate
   *
   * @param step   The time-step of the update
   * @param rate   The average rate-of-change over the time-step. Has dimensions of [state]/[time].
   * @return The new state after the update. Required to have the same class as 'this'.
   */
  public StateInterface addMul(double step, RateInterface rate)
  {
    Vector3dInterface v = (Vector3d) rate;
    return (StateInterface) this.addMul(step, v);
  }
}
/*
 * @author Matthijs Vossen
 * @version 0.99.0
 *
 * This class is an implementation of the Vector3dInterface, used to represent vectors.
 */

import titan.Vector3dInterface;
import java.lang.Math;

public class Vector3d implements Vector3dInterface
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

  public Vector3dInterface add(Vector3dInterface other)
  {
    x += other.getX();
    y += other.getY();
    z += other.getZ();
    return this;
  }

  public Vector3dInterface sub(Vector3dInterface other)
  {
    x -= other.getX();
    y -= other.getY();
    z -= other.getZ();
    return this;
  }

  public Vector3dInterface mul(double scalar)
  {
    x *= scalar;
    y *= scalar;
    z *= scalar;
    return this;
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
    this.add(other.mul(scalar));
    return this;
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
}

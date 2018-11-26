package io.vertx.starter;

import java.util.concurrent.atomic.AtomicInteger;

public class Airplane {
  private static final AtomicInteger COUNTER = new AtomicInteger();

  private final int id;

  private String tailNumber;

  private String origin;

  public Airplane(String tailNumber, String origin) {
    this.id = COUNTER.getAndIncrement();
    this.tailNumber = tailNumber;
    this.origin = origin;
  }

  public Airplane() {
    this.id = COUNTER.getAndIncrement();
  }

  public String getTailNumber() {
    return tailNumber;
  }

  public String getOrigin() {
    return origin;
  }

  public int getId() {
    return id;
  }

  public void setTailNumber(String tailNumber) {
    this.tailNumber = tailNumber;
  }

  public void setOrigin(String origin) {
    this.origin = origin;
  }
}

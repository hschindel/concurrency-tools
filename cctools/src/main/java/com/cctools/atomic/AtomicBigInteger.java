package com.cctools.atomic;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author howard.schindel
 * 
 * This is an immutable atomic version of BigInteger. 
 *
 */
public class AtomicBigInteger extends Number {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2312525508327637250L;
	
	private AtomicReference<BigInteger> bigInt;
	
	private static final BigInteger ONE = new BigInteger("1");
	
	public AtomicBigInteger(BigInteger bigInt) {
		this.bigInt = new AtomicReference<BigInteger>(bigInt);
	}
	
	public AtomicBigInteger(byte[] val) {
		bigInt = new AtomicReference<BigInteger>(new BigInteger(val));
	}
	
	public AtomicBigInteger(int signum, byte[] magnitude) {
		bigInt = new AtomicReference<BigInteger>(new BigInteger(signum, magnitude));
	}
	
	public AtomicBigInteger(int bitLength, int certainty, Random rnd) {
		bigInt = new AtomicReference<BigInteger>(new BigInteger(bitLength, certainty, rnd));
	}
	
	public AtomicBigInteger(String val) {
		bigInt = new AtomicReference<BigInteger>(new BigInteger(val));
	}
	
	public AtomicBigInteger(String val, int radix) {
		bigInt = new AtomicReference<BigInteger>(new BigInteger(val, radix));
	}
	
	
	@Override
	public double doubleValue() {
		return bigInt.get().doubleValue();
	}

	@Override
	public float floatValue() {
		return bigInt.get().floatValue();
	}

	@Override
	public int intValue() {
		return bigInt.get().intValue();
	}

	@Override
	public long longValue() {
		return bigInt.get().longValue();
	}
	
	public BigInteger addAndGet(BigInteger delta) {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = bigInt.get().add(delta);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return nextVal;
			}
		}
	}
	
	public BigInteger getAndAdd(BigInteger delta) {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = bigInt.get().add(delta);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return currentVal;
			}
		}
	}

	public BigInteger getAndIncrement() {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = bigInt.get().add(ONE);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return currentVal;
			}
		}
	}
	
	public BigInteger incrementAndGet() {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = bigInt.get().add(ONE);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return nextVal;
			}
		}
	}

}

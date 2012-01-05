package com.cctools.atomic;

import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author howard.schindel
 * 
 * This is an mutable atomic version of BigInteger.
 *
 */
public class AtomicBigInteger extends Number {
	AtomicInteger i;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2312525508327637250L;
	
	private AtomicReference<BigInteger> bigInt;
	
	private static final BigInteger ONE = new BigInteger("1");
	private static final BigInteger NEGATIVE_ONE = new BigInteger("-1");
	
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
	
	public final void set(BigInteger newValue) {
		bigInt.set(newValue);
	}
	
	public final BigInteger addAndGet(BigInteger delta) {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = currentVal.add(delta);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return nextVal;
			}
		}
	}
	
	public final BigInteger getAndAdd(BigInteger delta) {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = currentVal.add(delta);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return currentVal;
			}
		}
	}

	public final BigInteger getAndIncrement() {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = currentVal.add(ONE);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return currentVal;
			}
		}
	}
	
	public final BigInteger incrementAndGet() {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = currentVal.add(ONE);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return nextVal;
			}
		}
	}
	
	public final BigInteger getAndDecrement() {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = currentVal.add(NEGATIVE_ONE);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return currentVal;
			}
		}
	}
	
	public final BigInteger decrementAndGet() {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = bigInt.get().add(NEGATIVE_ONE);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return nextVal;
			}
		}
	}

	public final BigInteger getAndDivide(BigInteger val) {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = currentVal.divide(val);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return currentVal;
			}
		}
	}
	
	public final BigInteger divideAndGet(BigInteger val) {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = currentVal.divide(val);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return nextVal;
			}
		}
	}
	
	public final BigInteger getAndDivideRemainder(BigInteger val) {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger[] result = currentVal.divideAndRemainder(val);
			if(bigInt.compareAndSet(currentVal, result[0])) {
				return currentVal;
			}
		}
	}
	
	public final BigInteger[] divideRemainderAndGet(BigInteger val) {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger[] result = currentVal.divideAndRemainder(val);
			if(bigInt.compareAndSet(currentVal, result[0])) {
				return result;
			}
		}
	}
	
	public final BigInteger getAndMultiply(BigInteger val) {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = currentVal.multiply(val);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return currentVal;
			}
		}
	}
	
	public final BigInteger multiplyAndGet(BigInteger val) {
		while(true) {
			BigInteger currentVal = bigInt.get();
			BigInteger nextVal = currentVal.multiply(val);
			if(bigInt.compareAndSet(currentVal, nextVal)) {
				return nextVal;
			}
		}
	}
	
}

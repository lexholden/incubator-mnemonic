package org.apache.mnemonic.collections;

import org.apache.mnemonic.*;
import org.testng.annotations.Test;
import java.util.List;

/**
 *
 *
 */

@NonVolatileEntity
public abstract class Person<E> implements Durable, Comparable<Person<E>> {
	E element;
	
	@Override
	public void initializeAfterCreate() {
		System.out.println("Initializing After Created");
	}

	@Override
	public void initializeAfterRestore() {
		System.out.println("Initializing After Restored");
	}
	
	@Override
	public void setupGenericInfo(EntityFactoryProxy[] efproxies, GenericField.GType[] gftypes) {
		
	}
	
	@Test
	public void testOutput() throws RetrieveNonVolatileEntityError {
		System.out.printf("Person %s, Age: %d ( %s ) \n", getName(), getAge(), 
				null == getMother()? "No Recorded Mother" : "Has Recorded Mother");
	}
	
	public int compareTo(Person<E> anotherPerson) {
		int ret = 0;
		if (0 == ret) ret = getAge().compareTo(anotherPerson.getAge());
		if (0 == ret) ret = getName().compareTo(anotherPerson.getName());
		return ret;
	}

	@NonVolatileGetter(Id = 1L)
	abstract public Short getAge();
	@NonVolatileSetter
	abstract public void setAge(Short age);
	
	@NonVolatileGetter(Id = 2L)
	abstract public String getName() throws RetrieveNonVolatileEntityError;
	@NonVolatileSetter
	abstract public void setName(String name, boolean destroy) throws OutOfPersistentMemory, RetrieveNonVolatileEntityError;
	
	@NonVolatileGetter(Id = 3L)
	abstract public Person<E> getMother() throws RetrieveNonVolatileEntityError;
	@NonVolatileSetter
	abstract public void setMother(Person<E> mother, boolean destroy) throws RetrieveNonVolatileEntityError;
	
	@NonVolatileGetter(Id = 4L)
	abstract public Person<E> getFather() throws RetrieveNonVolatileEntityError;
	@NonVolatileSetter
	abstract public void setFather(Person<E> mother, boolean destroy) throws RetrieveNonVolatileEntityError;
}


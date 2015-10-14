package exceptions;

import java.util.NoSuchElementException;

/**
 * @author Koloturka
 * @version 21.08.2015 20:47
 */
public class NoSuchDataCouplingException extends NoSuchElementException {

	private Class dependentClass;
	private Class mainClass;
	private int idDependentObject;
	private int idMainObject;

	public NoSuchDataCouplingException(Class dependentClass, Class mainClass, int idDependentObject, int idMainObject) {
		this.dependentClass = dependentClass;
		this.mainClass = mainClass;
		this.idDependentObject = idDependentObject;
		this.idMainObject = idMainObject;
	}

	@Override
	public String getMessage() {
		return "NoSuchDataCouplingException: There is no " +
				dependentClass.getName() +
				" with id = " + idDependentObject +
				" in " + mainClass.getName() +
				"(id = " + idMainObject + ")";
	}

}

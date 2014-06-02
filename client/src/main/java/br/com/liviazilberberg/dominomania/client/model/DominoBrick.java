package br.com.liviazilberberg.dominomania.client.model;

public class DominoBrick {
	private int leftSideNumber;
	private int rigthSideNumber;

	public DominoBrick(int leftSideNumber, int rigthSideNumber) {
		super();
		this.leftSideNumber = leftSideNumber;
		this.rigthSideNumber = rigthSideNumber;
	}

	public int getLeftSideNumber() {
		return leftSideNumber;
	}

	public void setLeftSideNumber(int leftSideNumber) {
		this.leftSideNumber = leftSideNumber;
	}

	public int getRigthSideNumber() {
		return rigthSideNumber;
	}

	public void setRigthSideNumber(int rigthSideNumber) {
		this.rigthSideNumber = rigthSideNumber;
	}

	public Boolean verifyCompatibility(DominoBrick dominoBrick, Side side){
		
		int valueToCompare = side == Side.LEFT ? this.leftSideNumber : this.rigthSideNumber;
		
		if (dominoBrick.getLeftSideNumber() == valueToCompare || 
				dominoBrick.getRigthSideNumber() == valueToCompare) {
			return true;
		}
		return false;
	}
	
	public enum Side{
		LEFT, RIGTH
	}

	@Override
	public String toString() {
		return "DominoBrick [leftSideNumber=" + leftSideNumber + ", rigthSideNumber=" + rigthSideNumber + "]";
	}
}

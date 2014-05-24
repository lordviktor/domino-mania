package br.com.liviazilberberg.dominomania.client.model.base;

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

}

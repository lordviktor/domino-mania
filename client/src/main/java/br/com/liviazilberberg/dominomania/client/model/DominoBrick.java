package br.com.liviazilberberg.dominomania.client.model;

import br.com.liviazilberberg.dominomania.client.objects.Domino;

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

	public Boolean verifyCompatibility(DominoBrick dominoBrick, Side side) {

		int valueToCompare = side == Side.LEFT ? this.leftSideNumber : this.rigthSideNumber;

		if (dominoBrick.getLeftSideNumber() == valueToCompare || dominoBrick.getRigthSideNumber() == valueToCompare) {
			return true;
		}
		return false;
	}

	public enum Side {
		LEFT, RIGTH
	}

	@Override
	public String toString() {
		return "DominoBrick [leftSideNumber=" + leftSideNumber + ", rigthSideNumber=" + rigthSideNumber + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + leftSideNumber;
		result = prime * result + rigthSideNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof DominoBrick)) {
			if (obj instanceof Domino) {
				Domino other = (Domino) obj;
				if (other.getLeftSide() == this.getLeftSideNumber() && other.getRigthSide() == this.getRigthSideNumber()) {
					return true;
				} else if (other.getLeftSide() == this.getRigthSideNumber() && other.getRigthSide() == this.getLeftSideNumber()) {
					return true;
				}
			}
			return false;
		}
		DominoBrick other = (DominoBrick) obj;
		if (leftSideNumber != other.leftSideNumber) {
			return false;
		}
		if (rigthSideNumber != other.rigthSideNumber) {
			return false;
		}
		return true;
	}
}

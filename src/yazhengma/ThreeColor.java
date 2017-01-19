package yazhengma;

public class ThreeColor {
	private int red;
	private int green;
	private int blue;
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public int getGreen() {
		return green;
	}
	public void setGreen(int green) {
		this.green = green;
	}
	public int getBlue() {
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = blue;
	}

	@Override
	public boolean equals(Object obj) {
		ThreeColor threeColor = (ThreeColor) obj;
		return ((this.blue - threeColor.blue)<10 && (this.red - threeColor.red)<10 && (this.green - threeColor.green)<10);
	}
	@Override
	public int hashCode() {
		return 0;
	}
	@Override
	public String toString() {
		return "ThreeColor [red=" + red + ", green=" + green + ", blue=" + blue
				+ "]";
	}

}

import java.math.BigDecimal;


class Test {
	public static void main(String[] args) {
		String path = System.getProperty("java.library.path");
		System.out.println(path);
	}
	public static void fun(int a){
		System.out.println(a);
	}
	public static float getArpu(Integer backPrice,Integer reqSumNum,Double spRatio){
		if(backPrice == null ){
			return 0f;
		}
		if(reqSumNum == null ){
			return 0f;
		}
		if(spRatio == null){
			spRatio = 0.5d;
		}
		float arpu =new BigDecimal(backPrice).multiply(new BigDecimal(spRatio)).divide(new BigDecimal(reqSumNum)).floatValue();
		return arpu;
	}
}
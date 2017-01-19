package druid;

public class TestPerson {
	public static void main(String[] args) throws CloneNotSupportedException {
		Person p = new  Person();
		p.setName("zhangSan");
		p.setGae(12);
		System.out.println(p);
		Person p2=(Person) p.clone();
		System.out.println(p2);
		p2.setGae(14);
		System.out.println(p);
		System.out.println(p2);
	}
}

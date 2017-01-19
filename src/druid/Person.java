package druid;

public class Person implements Cloneable{
	private String name;
	private Integer gae;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getGae() {
		return gae;
	}
	public void setGae(Integer gae) {
		this.gae = gae;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", gae=" + gae + "]";
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		Person p = null;
        try {
            p = (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
	}

}

package interfaces;


public interface IShape {
	String getType();

	IShape klone();
	int getId();

	void setId(int id);
}
